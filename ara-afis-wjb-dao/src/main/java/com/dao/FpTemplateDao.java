/*
 * 文件名：${FpTemplateDao}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.23}
 * 修改：
 * 描述：FpTemplateDao  dao层
 *
 *
 * 版权：亚略特
 */
package com.dao;


import com.model.FpTemplate;

import java.util.List;


public interface FpTemplateDao extends BaseDao<FpTemplate> {

	/**
	 * 获取所有此种类型的字典对象
	 * @param flag（编码类型）
	 * @param code (编码)
	 * @param values (指位数组)
	 * @return  字典列表
	 */
	List<FpTemplate> getFpTemplatesByCodeAndIndex(int flag, String code, int[] values);
}
