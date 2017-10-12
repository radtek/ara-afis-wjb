/*
 * 文件名：${ManagerDao}
 * 作者：${Tree}
 * 版本：
 * 时间：${2014.4.21}
 * 修改：
 * 描述：管理员 dao层
 *
 *
 * 版权：亚略特
 */
package com.dao;

import com.model.Person;
import com.vo.PageVO;

import java.util.List;


public interface PersonDao extends BaseDao<Person>,CommonDao<Person> {
	/**
	 * 分页获取用户
	 * @param page（分页对象）
	 * @param queryType (查询类型 1.全字段模糊查询  2. 精确查询)
	 * @param values（查询条件）
	 * @return 日志list
	 */
	List<Person> getPersonPage(PageVO page, int queryType, Object... values);
}
