/*
 * 文件名：${FpImageDao}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.22}
 * 修改：
 * 描述：FpImage  dao层
 *
 *
 * 版权：亚略特
 */
package com.dao;


import com.model.FpImage;

import java.util.List;


public interface FpImageDao extends BaseDao<FpImage> {

	/**
	 * 获取所有此种类型的字典对象
	 * @param flag（编码类型）
	 * @param code (编码)
	 * @param values (指位数组)
	 * @return  字典列表
	 */
	List<FpImage> getFpImagesByCodeAndIndex(int flag, String code, int[] values);
}
