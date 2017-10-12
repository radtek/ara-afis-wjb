/*
 * 文件名：${PersonServiceImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.20}
 * 修改：
 * 描述：人员  Service实现层
 *
 *
 * 版权：亚略特
 */
package com.services.impl;

import com.dao.PersonDao;
import com.model.Person;
import com.param.ConfigParam;
import com.services.PersonService;
import com.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImpl  implements PersonService {

	@Autowired
	private PersonDao personDao;


	@Override
	public List<Person> getObjListPage(PageVO page, int queryType, Object... values) {
		return personDao.getPersonPage(page,queryType, values);
	}

	@Override
	public List<Person> getObjList(Object... values) {
		return personDao.getPersonPage(null, ConfigParam.QUERY_TYPE_SOME, values);
	}
}
