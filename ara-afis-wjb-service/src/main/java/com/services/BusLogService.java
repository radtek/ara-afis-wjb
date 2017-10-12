/*
 * 文件名：${LogService}
 * 作者：${Tree}
 * 版本：
 * 时间：${2014.4.25}
 * 修改：
 * 描述：日志     Service接口层
 *
 *
 * 版权：亚略特
 */
package com.services;

import com.model.BusLogInfo;
import com.vo.PageVO;

import java.util.List;

public interface BusLogService {
	/**
	 * 保存日志
	 * @param log（日志对象）
	 * @return
	 */
	void saveLog(BusLogInfo log);

	/**
	 * 分页获取日志对象列表
	 * @param page（分页对象）
	 * @param queryType (查询类型 1.全字段模糊查询  2. 精确查询)
	 * @param values（查询条件）
	 * @return 日志对象list
	 */
	List<BusLogInfo> getObjListPage(PageVO page, int queryType, Object... values);

	/**
	 * 获取对象列表
	 * @param values（查询条件）
	 * @return 对象list
	 */
	List<BusLogInfo> getObjList(Object... values);

	/**
	 * 清空表
	 * @param tableName（表名）
	 * @return
	 */
	void truncateTable(String tableName);

	/**
	 * 获取日志表中的时间区段
	 * @return 日志时间区段
	 */
	String getTimeArea();

	/**
	 * 获取日志表中的大小
	 * @return 日志表大小(单位M)
	 */
	String getTableSize(String tablename);
}
