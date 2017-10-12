/*
 * 文件名：${WarnInfoDaoImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.26}
 * 修改：
 * 描述：监控报警信息  Dao实现层
 *
 *
 * 版权：亚略特
 */
package com.dao.impl;

import com.dao.WarnInfoDao;
import com.model.Engine;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("warnInfoDao")
public class WarnInfoDaoImpl extends BaseDaoImpl<Engine> implements WarnInfoDao {

	@Override
	public List<Engine> getAvailableWarnData() {
		//获取engine状态为预警和工作状态为故障和网络状态不通
		String hql="FROM Engine where warnField is not null or workStationStatus != :workStationStatusNormal or networkStatus != :networkStatusNormal";
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("workStationStatusNormal", "0");
        paramMap.put("networkStatusNormal", "0");

		return find(hql, paramMap);
	}

}
