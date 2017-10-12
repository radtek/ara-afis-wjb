/*
 * 文件名：${MonitorInfoService}
 * 作者：${Tree}
 * 版本：
 * 时间：2016.5.26
 * 修改：
 * 描述：监控信息 service层
 *
 *
 * 版权：亚略特
 */
package com.services;

import com.model.MonitorInfo;

import java.util.List;

public interface MonitorInfoService {

	List<MonitorInfo> getMonitorData(Object... values);

	String getMonitorFingerData(Object... values);
}
