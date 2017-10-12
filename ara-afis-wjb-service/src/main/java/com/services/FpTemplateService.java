/*
 * 文件名：${FpTemplateService}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.23}
 * 修改：
 * 描述：指纹特征数据  Service接口层
 *
 *
 * 版权：亚略特
 */
package com.services;

import com.model.FpTemplate;

import java.util.List;


public interface FpTemplateService {
	/**
	 * 获取所有此种类型的字典对象
	 * @param flag（编码类型）  0 为指纹编码   1 为人员编号
	 * @param code (编码)
	 * @param values (指位数组)
	 * @return  字典列表
	 */
	List<FpTemplate> getFpTemplatesByCodeAndIndex(int flag, String code, int[] values);
}
