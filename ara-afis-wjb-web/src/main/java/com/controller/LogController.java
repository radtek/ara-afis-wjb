/**
 * @author: tree
 * @version: 1.0
 * date: 2017/9/5 18:21
 * @description: 日志 相关业务逻辑类
 * own: Aratek
 *
 * @modify: gaocs
 * @note: 公共代码提取到queryLog中
 * @date: 2017/09/21 09:58
 */
package com.controller;

import com.param.ConfigParam;
import com.services.BusLogService;
import com.services.SysLogService;
import com.util.CommonStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value = "/logInfo")
public class LogController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    //注入服务层
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private BusLogService busLogService;

    /**
     * 公共查询模块的对应方法
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toQuerySysLog")
    public String toQuerySysLog(){
        //跳转前台
        return "/log/querySysLog";
    }

    /**
     * 公共查询模块的对应方法，获取所有系统日志
     * @param aoData 页面打包数据
     * @param filterItem 筛选值
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/querySysLog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> querySysLog(String aoData, String filterItem) {
        return queryLog(aoData,filterItem,"querySysLog");
//        initController();
//        Map<String,Object> paramMap = resolveControllerParam(aoData,page);
//        if(StringUtils.isNotBlank((String)paramMap.get("searchValue"))){
//            responseMap.put("data", sysLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_ALL, CommonStringUtil.parseShowString(paramMap.get("searchValue")),filterItem));
//        }else{
//            responseMap.put("data", sysLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_SOME));
//        }
//        responseMap.put("anyStatus", 1);
//        responseMap.put("recordsFiltered", page.getTotalResult());
//        responseMap.put("recordsTotal", page.getTotalResult());
//        responseMap.put("draw", (int)(paramMap.get("draw")));
//        return responseMap;
    }

    /**
     * 公共查询模块的对应方法
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toQueryBusLog")
    public String toQueryBusLog(){
        logger.debug(getMessage("java_logController_queryBusLogTable"));
        //跳转前台
        return "/log/queryBusLog";
    }

    /**
     * 公共查询模块的对应方法，获取所有业务日志
     * @param aoData 页面打包数据
     * @param filterItem 筛选值
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/queryBusLog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryBusLog(String aoData, String filterItem) {
        return queryLog(aoData,filterItem,"queryBusLog");
//        initController();
//        Map<String,Object> paramMap = resolveControllerParam(aoData,page);
//        if(StringUtils.isNotBlank((String)paramMap.get("searchValue"))){
//            responseMap.put("data", busLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_ALL, CommonStringUtil.parseShowString(paramMap.get("searchValue")),filterItem));
//        }else{
//            responseMap.put("data", busLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_SOME));
//        }
//        responseMap.put("anyStatus", 1);
//        responseMap.put("recordsFiltered", page.getTotalResult());
//        responseMap.put("recordsTotal", page.getTotalResult());
//        responseMap.put("draw", (int)(paramMap.get("draw")));
//        return responseMap;
    }

    /**
     * querySysLog和queryBusLog公共代码提取
     * @param aoData 页面打包数据
     * @param filterItem 筛选值
     * @param queryType 请求类型:"queryBusLog","querySysLog"两种
     * @return Map<String,Object> 返回响应数据
     */
    public Map<String,Object> queryLog(String aoData,String filterItem,String queryType){
        initController();
        Map<String,Object> paramMap = resolveControllerParam(aoData,page);
        if(StringUtils.isNotBlank((String)paramMap.get("searchValue"))){
            if("queryBusLog".equals(queryType)){
                responseMap.put("data", busLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_ALL, CommonStringUtil.parseShowString(paramMap.get("searchValue")),filterItem));
            }else {
                responseMap.put("data", sysLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_ALL, CommonStringUtil.parseShowString(paramMap.get("searchValue")),filterItem));
            }
        }else{
            if("queryBusLog".equals(queryType)) {
                responseMap.put("data", busLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_SOME));
            }else{
                responseMap.put("data", sysLogService.getObjListPage(page, ConfigParam.QUERY_TYPE_SOME));
            }
        }
        responseMap.put("anyStatus", 1);
        responseMap.put("recordsFiltered", page.getTotalResult());
        responseMap.put("recordsTotal", page.getTotalResult());
        responseMap.put("draw", (int)(paramMap.get("draw")));
        return responseMap;
    }

}
