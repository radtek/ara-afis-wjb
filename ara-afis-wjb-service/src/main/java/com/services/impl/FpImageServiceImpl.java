/*
 * 文件名：${FpImageServiceImpl}
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

import com.dao.FpImageDao;
import com.model.FpImage;
import com.services.FpImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fpImageService")
public class FpImageServiceImpl implements FpImageService {

	@Autowired
	private FpImageDao fpImageDao;

	@Override
	public List<FpImage> getFpImagesByCodeAndIndex(int flag, String code, int[] values) {
		return fpImageDao.getFpImagesByCodeAndIndex(flag,code,values);
	}
}
