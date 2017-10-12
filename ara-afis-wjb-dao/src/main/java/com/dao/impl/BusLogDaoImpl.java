/*
 * 文件名：${BusLogDaoImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：${2014.4.27}
 * 修改：
 * 描述：日志  Dao实现层
 *
 *
 * 版权：亚略特
 */
package com.dao.impl;

import com.dao.BusLogDao;
import com.exception.CommonUtilException;
import com.model.BusLogInfo;
import com.vo.PageVO;
import com.param.ConfigParam;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

@Repository("busLogDao")
public class BusLogDaoImpl extends BaseDaoImpl<BusLogInfo> implements BusLogDao {
    @Resource
    private MessageSource messageSource;

    public String getMessage(String key){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }


    @Override
    public List<BusLogInfo> getLogPage(PageVO page, int queryType, Object... values) {
        StringBuffer hql = new StringBuffer("from BusLogInfo ");
        List<BusLogInfo> loglist = new ArrayList<BusLogInfo>();

        //若为条件查询，构造查询数据
        Map<String, Object> paramMap = new HashMap<>();
        if (values != null && values.length > 0) {
            hql.append(" where  ");
            switch (queryType) {
                //全字段模糊查询
                case ConfigParam.QUERY_TYPE_ALL:
                    hql.append(" 1 = 2  ");
                    String itemName = (String)values[1];
                    //判断是否筛选策略是全局还是指定
                    if(StringUtils.isNotBlank(itemName)){hql.append(" or " + itemName + " like :" + itemName + " ");
                        if(StringUtils.isNotBlank((String)values[0])){
                            paramMap.put(itemName, "%"+values[0]+"%");
                        }else {
                            paramMap.put(itemName, values[0]);
                        }
                    }else{
                        Field[] fields = BusLogInfo.class.getDeclaredFields();
                        //利用java的反射机制，轮询元素
                        //需要模糊查询的属性
                        List<String> likeStrings = Arrays.asList("actionType","resultFlag","resultCode","createOn");
                        Arrays.asList(fields).stream().filter(filed -> likeStrings.contains(filed.getName())).forEach(name -> {
                            hql.append(" or " + name.getName() + " like :" + name.getName() + " ");
                            if(StringUtils.isNotBlank((String)values[0])){
                                paramMap.put(name.getName(), "%"+values[0]+"%");
                            }else {
                                paramMap.put(itemName, values[0]);
                            }
                        });
                    }
                    break;
                //分条件精确查询
                case ConfigParam.QUERY_TYPE_SOME:
                    // TODO: 2017/5/24 后续增加
                    hql.append(" 1 = 1  ");
                    break;
            }
        }

        //设置排序规则   降序
        hql.append(" order by createOn desc");

        //模型参数构造
        List<BusLogInfo> logListTemp = null;
        if(page != null){
            logListTemp = findPage(hql.toString(),page,paramMap);
        }else{
            logListTemp = find(hql.toString(),paramMap);
        }
        if(null != logListTemp && logListTemp.size() > 0){
            for(BusLogInfo logTemp : logListTemp){
                try {
                    loglist.add(BusLogInfo.convert(logTemp));
                } catch (CommonUtilException e) {
                    e.printStackTrace();
                }
            }
        }

        return loglist;
    }

    @Override
    public void truncateTable(String tableName) {
        String sql = "delete from "+tableName;
        SQLQuery d = getCurrentSession().createSQLQuery(sql);
        d.executeUpdate();
    }

    @Override
    public String getTimeArea() {
        String hql="SELECT MIN(createOn),MAX(createOn) FROM LogInfo";
        List<?> list=find(hql,null);
        if(null != list && list.size() == 1){
            Object[] log=(Object[]) list.get(0);
            if(null != log[0]){
                return log[0].toString().substring(0, 10) + "~" + log[1].toString().substring(0, 10);
            }else{
                return getMessage("java_busLogDao_nullData");
            }
        }else{
            return getMessage("java_busLogDao_nullData");
        }
    }

    @Override
    public String getTableSize(String tablename) {
        DecimalFormat df = new DecimalFormat("0.00");
        String sql="SELECT (data_length+INDEX_LENGTH) FROM information_schema.TABLES WHERE TABLE_NAME ='"+tablename+"'";
        SQLQuery d = getCurrentSession().createSQLQuery(sql);
        List<?> list = d.list();
        if(list!=null&&list.size()>0){
            return df.format(Double.parseDouble(list.get(0).toString())/1024/1024) + "MB";
        }else{
            return "0MB";
        }
    }
}
