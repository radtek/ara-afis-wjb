/*
 * 文件名：${FpTemplateDaoImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.23}
 * 修改：
 * 描述：FpTemplate  dao实现层
 *
 *
 * 版权：亚略特
 */
package com.dao.impl;


import com.dao.FpTemplateDao;
import com.model.FpTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("fpTemplateDao")
public class FpTemplateDaoImpl extends BaseDaoImpl<FpTemplate> implements FpTemplateDao {

	@Override
	public List<FpTemplate> getFpTemplatesByCodeAndIndex(int flag, String code, int[] values) {
        //若为条件查询，构造查询数据
        Map<String, Object> paramMap = new HashMap<>();
        String hql = "from FpTemplate where collectStatus = 0 ";
        if (0 == flag) {
            hql += " and fpId = :code ";
        } else {
            hql += " and personId = :code ";
        }
        paramMap.put("clusterCode", code);
        hql += " and fpIndexCode in ( ";
        for (int value : values) {
            hql += value + ",";
        }
        hql = hql.substring(0, hql.length() - 1) + " ) ";
        return find(hql, paramMap);
    }
}
