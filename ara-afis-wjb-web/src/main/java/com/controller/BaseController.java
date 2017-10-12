/**
 * @author: tree
 * @version: 1.0
 * date: 2017/9/5 17:52
 * @description:
 * own: Aratek
 */
package com.controller;

import com.aspect.DirectLog;
import com.exception.InternalSystemException;
import com.exception.ServiceException;
import com.model.*;
import com.vo.PageVO;
import com.time.TimeUtil;
import com.util.CommonObjectUtil;
import com.util.CommonStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class BaseController implements DirectLog {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public Map<String,Object> responseMap;
    public String msg;
    public int anyStatus;
    public String tr;
    public String anyData;
    public PageVO page;

    @Resource
    private MessageSource messageSource;

    //系统日志对象
    public SysLogInfo logInfo = new SysLogInfo();

    /**
     * 构造返异步后台数据添加，修改
     * @param obj 新添加的对象
     * @return String
     */
    public String drawTable(Object obj){
        StringBuffer sbTable = new StringBuffer();
        if(obj != null){
            //构造表格数据
            sbTable.append("<tr>");
            if(obj instanceof Manager){
                Manager man = (Manager)obj;
                String roleName = "";
                if(null != man.getRole() && !"-1".equals(man.getRole().getId())){
                    roleName = man.getRole().getName();
                }
                String statuMean = "";
                if("E".equals(man.getStatu())){
                    statuMean = getMessage("system_code_enable");
                }else{
                    statuMean = getMessage("system_code_disable");
                }
                sbTable.append("<td>"+man.getAccount()+"</td><td>"+roleName+"</td><td>"+statuMean+"&nbsp</td><td>"+man.getLastLogin()+"</td><td>"+man.getCreateOn()+"</td>");
            }else if(obj instanceof Code){
                Code code = (Code)obj;
                String statuMean = "";
                if("E".equals(code.getStatu())){
                    statuMean = getMessage("system_code_enable");
                }else{
                    statuMean = getMessage("system_code_disable");
                }
                sbTable.append("<td>"+code.getCodeMean()+"</td><td>"+code.getCode()+"</td><td>"+code.getTypeMean()+"</td><td>"+statuMean+"</td><td >"+code.getDescribe()+"</td><td>"+code.getCreateOn()+"</td>");
            }else if(obj instanceof Person){
                Person person = (Person)obj;
                sbTable.append("<td>"+person.getFileId()+"</td><td>"+person.getEid()+"</td><td>"+person.getFamilyName()+"</td><td>"+person.getFirstName()+"</td><td >"+person.getBirthday()+"</td><td >"+person.getNationCode()+"</td><td>"+person.getCreateOn()+"</td>");
            }else if(obj instanceof EngineNode){
                EngineNode engineNode = (EngineNode)obj;
                String engineType ,engineModel;
                if("FP".equals(engineNode.getEngineModel())){
                    engineModel = getMessage("engine_finger");
                }else if("FA".equals(engineNode.getEngineModel())){
                    engineModel = getMessage("engine_face");
                }else if("FI".equals(engineNode.getEngineModel())){
                    engineModel = getMessage("engine_iris");
                }else{
                    engineModel = getMessage("system_code_disable");
                }
                if("0".equals(engineNode.getEngineType())){
                    engineType = getMessage("engine_masterNode");
                    sbTable.append("<td>"+engineNode.getMasterId()+"</td><td></td><td>"+engineType+"</td><td>"+engineModel+"</td><td></td><td></td><td ></td><td ></td>");
                }else{
                    engineType = getMessage("engine_engineNode");
                    sbTable.append("<td>"+engineNode.getMasterId()+"</td><td>"+engineNode.getNodeId()+"</td><td>"+engineType+"</td><td>"+engineModel+"</td><td>"+engineNode.getEnrollThread()+"</td><td>"+engineNode.getVerifyThread()+"</td><td >"+engineNode.getIdentifyThread()+"</td><td >"+engineNode.getDataZone()+"</td>");
                }
            }
            sbTable.append("</tr>");
        }
        return sbTable.toString();
    }

    /**
     * 基于@ExceptionHandler异常处理总机制
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handleAndReturnData(Exception ex) {
        initController();
        boolean showStackFlag = true;
        responseMap.put("anyStatus", anyStatus);
        if (ex instanceof InternalSystemException || ex instanceof ServiceException) {
            logger.error("[SYS_LOG][System error MSG : {}]",ex.getMessage());
            responseMap.put("msg", getMessage("java_baseController_systemWarn")+": " + ex.getMessage());
            showStackFlag = false;
        } else if (ex instanceof NullPointerException) {
            responseMap.put("msg", getMessage("java_baseController_nullException"));
        } else {
            responseMap.put("msg", ex.getMessage());
        }
        if (showStackFlag) {
            ex.printStackTrace();
        }
        responseMap.put("data", null);
        return responseMap;
    }

    /**
     * 初始化Controller
     */
    public void initController(){
        responseMap = new HashMap<>();
        page = new PageVO();
        msg = null;
        anyStatus = 0;
        tr = null;
        anyData = null;
    }

    /**
     * 获取当前session中用户名
     */
    public String getSessionManager(HttpSession session){
        return (String)session.getAttribute("managerName");
    }

    public String getMessage(String key){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * 功能说明：打包日志信息
     * @param type 日志类型
     * @param operate 操作
     */
    public void packageLogInfo(String type, String operate){
        logInfo.setCreateOn(TimeUtil.getFormatDate());
        logInfo.setType(type);
        logInfo.setOperate(operate);
        if(StringUtils.isEmpty(logInfo.getCreateBy())){
            logInfo.setCreateBy("  ");
        }
    }

    public Map<String,Object> resolveControllerParam(String data, PageVO page){
        Map<String,Object> tableMap = CommonStringUtil.covertJsonStringToHashMap(data);
        page.setPageSize((int)(tableMap.get("length")));
        page.setStart((int)(tableMap.get("start")));
        Map<String, Object> searchMap = CommonObjectUtil.cast(tableMap.get("search"));
        tableMap.put("searchValue", searchMap.get("value"));
        return tableMap;
    }

    @Override
    public SysLogInfo toGetLogInfo() {
        return logInfo;
    }
}
