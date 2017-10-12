/*
 * 文件名：${PersonService}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.20}
 * 修改：
 * 描述：人员     Service接口层
 *
 *
 * 版权：亚略特
 */
package com.services;

import com.model.Person;
import com.vo.PageVO;

import java.util.List;

public interface PersonService {
	/**
	 * 分页获取日志对象列表
	 * @param page（分页对象）
	 * @param queryType (查询类型 1.全字段模糊查询  2. 精确查询)
	 * @param values（查询条件）
	 * @return 日志对象list
	 */
	List<Person> getObjListPage(PageVO page, int queryType, Object... values);

	/**
	 * 获取对象列表
	 * @param values（查询条件）
	 * @return 对象list
	 */
	List<Person> getObjList(Object... values);
}
