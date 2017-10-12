/*
 * 文件名：${FpImageService}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.21}
 * 修改：
 * 描述：指纹图像  Service接口层
 *
 *
 * 版权：亚略特
 */
package com.services;

import com.model.FpImage;

import java.util.List;


public interface FpImageService {
	/**
	 * 获取所有此种类型的字典对象
	 * @param flag（编码类型）  0 为指纹编码   1 为人员编号
	 * @param code (编码)
	 * @param values (指位数组)
	 * @return  字典列表
	 */
	List<FpImage> getFpImagesByCodeAndIndex(int flag, String code, int[] values);
}
