/*
 * 文件名：${ParameterDaoImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.23}
 * 修改：
 * 描述：Parameter  dao实现层
 *
 *
 * 版权：亚略特
 */
package com.dao.impl;


import com.dao.FpImageDao;
import com.model.FpImage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("fpImageDao")
public class FpImageDaoImpl extends BaseDaoImpl<FpImage> implements FpImageDao {

	@Override
	public List<FpImage> getFpImagesByCodeAndIndex(int flag, String code, int[] values) {
		//若为条件查询，构造查询数据
        Map<String, Object> paramMap = new HashMap<>();
		String hql = "from FpImage where collectStatus = 0 ";

		if(0 == flag){
			hql += " and person.fileId = :code " ;
		}else{
			hql +=  " and person.id = :code ";
		}

        paramMap.put("clusterCode", code);

		hql +=  " and fpIndex in ( ";
		for (int value : values) {
			hql += value + ",";
		}
		hql = hql.substring(0,hql.length()-1) + " ) ";

		return find(hql,paramMap);
	}
}
