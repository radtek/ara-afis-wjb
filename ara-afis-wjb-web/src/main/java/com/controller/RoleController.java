package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.exception.ServiceException;
import com.model.Page;
import com.model.Role;
import com.vo.OperateVO;
import com.services.PageService;
import com.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 文件名：RoleController
 * 作者：tree
 * 时间：2016/4/21
 * 描述：系统角色 相关业务逻辑类
 * 版权：亚略特
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private PageService pageService;

    /**
     * 公共查询模块的对应方法
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toGetList")
    public String toGetList(Model model){
        logger.debug(getMessage("java_roleController_querySystemRoleList"));
        //查询所有基本数据字典类型
        model.addAttribute("roleListJson", JSON.toJSONString(roleService.getObjList(), SerializerFeature.WriteNullStringAsEmpty));
        //跳转前台
        return "/system/role/queryRole";
    }

    /**
     * 初始化添加或修改页面的对应方法
     * @param model 请求模型对象
     * @param anyId 标识
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toAddOption")
    public String toAddOption(Model model, String anyOption, int anyId){
        //判别要执行的是添加还是修改操作
        if(anyOption.contains("add")){
            //获取所有有效页面模块
            msg = JSONArray.toJSONString(pageService.getAllEnablePages(), SerializerFeature.WriteNullStringAsEmpty);
        }else{
            //判定是否指定了角色
            if(0 != anyId){
                //根据role标示ID获取role信息
                Role role = roleService.getObj(anyId);
                //校验页面权限，对页面进行筛选
                List<Page> pagelistTemp = pageService.getAllEnablePages();
                pagelistTemp.forEach(pageTemp -> {
                    if(role.getPurview().contains(pageTemp.getCode())){
                        pageTemp.setChecked(true);
                    }
                });
                msg = JSONArray.toJSONString(pagelistTemp, SerializerFeature.WriteNullStringAsEmpty);
                //如果角色对象存在
                if(null != role){
                    model.addAttribute("role", role);
                }
            }
        }
        model.addAttribute("msg", msg);
        //跳转到添加系统角色界面
        return "/system/role/addRole";
    }

    /**
     * 公共添加/修改模块的对应方法
     * @param role 角色对象
     * @param anyOption 操作类型
     * @param anyId 标识
     * @param session Session对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOption(Role role, String anyOption, int anyId, HttpSession session) throws ServiceException {
        initController();
        //打包系统日志
        packageLogInfo("ROLE", OperateVO.ADD_OPERATE);
        //初始化参数
        String roleName = "",resultMsg = "";
        //判定数据完整性
        if (null != role) {
            roleName = role.getName();
            //判别要执行的是添加还是修改操作
            if (anyOption.contains("add")) {
                logInfo.setOperate(OperateVO.ADD_OPERATE);
                //保存角色
                roleService.saveObj(role, getSessionManager(session));
                resultMsg = getMessage("java_codeController_addSuccess");
            } else {
                logInfo.setOperate(OperateVO.MODEIFY_OPERATE);
                //修改角色
                roleService.updateObj(role, getSessionManager(session), anyId);
                resultMsg = getMessage("java_codeController_updateSuccess");
            }
            //构造前台数据模型
            anyStatus = 1;
        }
        msg = getMessage("role")+" : "+roleName + " " + resultMsg ;
        logInfo.setContent(msg);
        logInfo.setCreateBy(getSessionManager(session));
        responseMap.put("anyStatus", anyStatus);
        responseMap.put("msg", msg);
        return responseMap;
    }

    /**
     * 公共删除模块的对应方法
     * @param anyId 字典标识
     * @param session Session对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/delOption", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delOption(int anyId, HttpSession session) throws ServiceException {
        initController();
        //打包系统日志
        packageLogInfo("ROLE", OperateVO.DELETE_OPERATE);
        //初始化参数
        String roleName = "",resultMsg = "";
        //判定是否指定了引擎
        if (0 != anyId) {
            //通过角色主键获取角色对象
            Role role = roleService.getObj(anyId);
            if(null != role){
                roleName = role.getName();
                roleService.delObj(anyId);
                anyStatus = 1;
                resultMsg = getMessage("java_codeController_deleteSuccess");
            }
        }
        msg = getMessage("role")+" : "+roleName + " " + resultMsg ;
        logInfo.setContent(msg);
        logInfo.setCreateBy(getSessionManager(session));
        responseMap.put("anyStatus", anyStatus);
        responseMap.put("msg", msg);
        return responseMap;
    }
}
