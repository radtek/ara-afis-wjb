/*
 * 文件名：${FpTemplateServiceImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.23}
 * 修改：
 * 描述：指纹图像  Service实现层
 *
 *
 * 版权：亚略特
 */
package com.services.impl;

import com.dao.FpTemplateDao;
import com.model.FpTemplate;
import com.services.FpTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fpTemplateService")
public class FpTemplateServiceImpl implements FpTemplateService {

	@Autowired
	private FpTemplateDao fpTemplateDao;

	@Override
	public List<FpTemplate> getFpTemplatesByCodeAndIndex(int flag, String code, int[] values) {
		return fpTemplateDao.getFpTemplatesByCodeAndIndex(flag,code,values);
	}
}
