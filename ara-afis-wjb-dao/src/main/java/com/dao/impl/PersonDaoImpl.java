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

import com.dao.PersonDao;
import com.model.Person;
import com.vo.PageVO;
import com.param.ConfigParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;

@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {

	@Override
	public List<Person> getPersonPage(PageVO page, int queryType, Object... values) {
		StringBuffer hql = new StringBuffer("FROM Person WHERE activeStatus = :activeStatus ");
		List<Person> personlist = new ArrayList<Person>();

        //若为条件查询，构造查询数据
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("activeStatus", ConfigParam.ACTIVE_STATU.AVAILABLE.getElementName());
		if (values != null && values.length > 0) {
			switch (queryType) {
				//全字段模糊查询
				case ConfigParam.QUERY_TYPE_ALL:
					hql.append(" AND 1 = 2  ");
                    String itemName = (String)values[1];
                    //判断是否筛选策略是全局还是指定
                    if(StringUtils.isNotBlank(itemName)){
                        hql.append(" OR " + itemName + " LIKE :" + itemName + " ");
                        if(StringUtils.isNotBlank((String)values[0])){
                            paramMap.put(itemName, "%"+values[0]+"%");
                        }else {
                            paramMap.put(itemName, values[0]);
                        }
                    }else{
                        Field[] fields = Person.class.getDeclaredFields();
                        //利用java的反射机制，轮询元素
                        //需要模糊查询的属性
                        List<String> likeStrings = Arrays.asList("eid","fileId","birthday","nationCode","createOn");
                        Arrays.asList(fields).stream().filter(filed -> likeStrings.contains(filed.getName())).forEach(name -> {
                            hql.append(" OR " + name.getName() + " LIKE :" + name.getName() + " ");
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
					hql.append("  AND 1 = 1  ");
					break;
			}
		}

		//设置排序规则   降序
        hql.append(" order by createOn desc");

		//模型参数构造
		List<Person> personListTemp = null;
		if(page != null){
			switch (queryType) {
				//全字段模糊查询
				case ConfigParam.QUERY_TYPE_ALL:
					personListTemp = findPage(hql.toString(),page,paramMap);
					break;
				//分条件精确查询
				case ConfigParam.QUERY_TYPE_SOME:
					personListTemp = findPage(hql.toString(),page,paramMap);
					break;
			}
		}else{
			personListTemp = find(hql.toString(),paramMap);
		}
		if(null != personListTemp && personListTemp.size() > 0){
			for(Person personTemp : personListTemp){
				try {
					personlist.add(Person.convert(personTemp));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return personlist;
	}

	@Override
	public boolean checkExist(String obj, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExist(int id, Object... values) {
		// TODO Auto-generated method stub
		return false;
	}
}
