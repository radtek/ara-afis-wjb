/*
 * 文件名：${WarnInfoServiceImpl}
 * 作者：${Tree}
 * 版本：
 * 时间：2016.5.26
 * 修改：
 * 描述：监控报警信息  Service实现层
 *
 *
 * 版权：亚略特
 */
package com.services.impl;

import com.dao.WarnInfoDao;
import com.exception.ServiceException;
import com.model.Engine;
import com.model.WarnInfo;
import com.services.WarnInfoService;
import com.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("warnInfoService")
public class WarnInfoServiceImpl implements WarnInfoService {

	@Autowired
	private WarnInfoDao warnInfoDao;

    @Override
    public List<Engine> getAvailableWarnData() {
        return warnInfoDao.getAvailableWarnData();
    }


    @Override
    public void saveObj(Object o, String s, Object... objects) throws ServiceException {

    }

    @Override
    public void updateObj(Object o, String s, int i, Object... objects) throws ServiceException {

    }

    @Override
    public void delObj(int id, Object... values) throws ServiceException {

    }

    @Override
    public List getObjListPage(PageVO pageVO, Object... objects) {
        return null;
    }

    @Override
    public List getObjList(Object... objects) {
        return null;
    }

    @Override
    public Object getObj(int i) {
        return null;
    }

    @Override
    public Object getObjByObj(String s, Object o) {
        return null;
    }


}
