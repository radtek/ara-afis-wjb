/**
 * @author: tree
 * @version: 1.0
 * date: 2017/9/5 18:29
 * @description: 处理访客相关业务逻辑类
 * own: Aratek
 */
package com.controller;

import com.param.ConfigParam;
import com.services.PersonService;
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
@RequestMapping(value = "/person")
public class PersonController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    //注入服务
    @Autowired
    private PersonService personService;

    /**
     * 查询模块的对应方法
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toQueryPerson")
    public String toQueryPerson(){
        return "/person/queryPerson";
    }

    /**
     * 公共查询模块的对应方法，获取所有用户列表
     * @param aoData 页面打包数据
     * @param filterItem 筛选值
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/queryPerson", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryPerson(String aoData, String filterItem) {
        initController();
        Map<String,Object> paramMap = resolveControllerParam(aoData,page);
        if(StringUtils.isNotBlank((String)paramMap.get("searchValue"))){
            responseMap.put("data", personService.getObjListPage(page, ConfigParam.QUERY_TYPE_ALL, CommonStringUtil.parseShowString(paramMap.get("searchValue")),filterItem));
        }else{
            responseMap.put("data", personService.getObjListPage(page, ConfigParam.QUERY_TYPE_SOME));
        }
        responseMap.put("anyStatus", 1);
        responseMap.put("recordsFiltered", page.getTotalResult());
        responseMap.put("recordsTotal", page.getTotalResult());
        responseMap.put("draw", (int)(paramMap.get("draw")));
        return responseMap;
    }
}
