/**
 * @author: tree
 * @version: 1.0
 * date: 2017/9/5 18:02
 * @description: 公共操作类
* own: Aratek
 */
package com.controller;

import com.model.*;
import com.vo.OperateVO;
import com.vo.PageVO;
import com.param.ConfigParam;
import com.services.BusLogService;
import com.services.ManagerService;
import com.services.PageService;
import com.services.SysLogService;
import com.time.TimeUtil;
import com.util.CommonStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Scope("prototype")
public class CommonController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ManagerService managerService;
    @Autowired
    private PageService pageService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private BusLogService busLogService;

    /**
     *  判断Cookie中是否留存有系统的登录数据，有则进行登录，否则跳转到登录界面
     * @param request 请求对象
     * @param session session对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/")
    public String loginCookie(HttpServletRequest request, HttpSession session) {
        logger.debug("[SYS_LOG][loginCookie]");
        initController();
        logInfo.setLogFlag(false);
        //打包系统日志
        packageLogInfo("LOGIN", OperateVO.LOGIN_OPERATE);
        //获取Cookie对象
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if (ConfigParam.MANAGER_COOKIE.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    if (StringUtils.isNotBlank(value)) {
                        String[] split = value.split("#");
                        String account = split[0];
                        String password = split[1];
                        if (managerService.checkLogin(account, password)) {
                            session.setAttribute("managerName", account);
                            Manager managerTemp = managerService.getObjByObj("account", account);
                            //异常判定
                            if (null != managerTemp) {
                                //获取有效页面
                                List<Page> pagelistTemp = pageService.getAllEnablePages();
                                //根据有效页面，对权限数据进行再次筛选
                                String purview = filterString(pagelistTemp, managerTemp.getRole().getPurview());
                                session.setAttribute("purview", purview);

                                //更改用户最近登录时间
                                managerTemp.setLastLogin(TimeUtil.getFormatDate());
                                try {
                                    managerService.updateObj(managerTemp, (String) session.getAttribute("managerName"), managerTemp.getId());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            logInfo.setCreateBy(account);
                            logInfo.setLogFlag(true);
                            logInfo.setContent(getMessage("manager") + " " + managerTemp.getAccount() + " " + getMessage("login_success"));
                            return "main";
                        }
                    }
                }
            }
        }
        return "common/login";
    }

    /**
     *  处理用户登入请求
     * @param manager 用户对象
     * @param remember 是否记住密码
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/login")
    public String login(Model model, Manager manager, String remember, HttpSession session, HttpServletResponse response){
        logger.debug("[SYS_LOG][login][{}][{}]",manager.toString(),remember);
        initController();
        //打包系统日志
        packageLogInfo("LOGIN", OperateVO.LOGIN_OPERATE);
        try{
            if(Optional.of(manager).isPresent()){
                if(managerService.checkLogin(manager.getAccount(), manager.getPassword())){
                    Manager managerTemp = managerService.getObjByObj("account", manager.getAccount());
                    if("E".equals(managerTemp.getStatu())){
                        //用户选择了记住我
                        if("true".equals(remember)){
                            Cookie cookie = new Cookie(ConfigParam.MANAGER_COOKIE,manager.getAccount()+"#"+manager.getPassword());
                            //设置cookie有效期为2周
                            cookie.setMaxAge(60 * 60 * 24 * 14);
                            response.addCookie(cookie);
                        }
                        session.setAttribute("managerName", manager.getAccount());
                        //异常判定
                        if(null != managerTemp){
                            //获取有效页面
                            List<Page> pagelistTemp = pageService.getAllEnablePages();
                            //根据有效页面，对权限数据进行再次筛选
                            String purview = filterString(pagelistTemp,managerTemp.getRole().getPurview());
                            session.setAttribute("purview", purview);

                            //更改用户最近登录时间
                            managerTemp.setLastLogin(TimeUtil.getFormatDate());
                            managerService.updateObj(managerTemp,getSessionManager(session),managerTemp.getId());
                        }
                        logInfo.setContent(getMessage("manager")+" "+manager.getAccount()+" "+getMessage("login_success"));
                        return "/main";
                    }else{
                        msg = getMessage("java_commonController_loginAcountIsDisable");
                        logInfo.setContent(getMessage("manager")+" "+manager.getAccount()+getMessage("java_commonController_loginFailedReason") + ":"+msg);
                    }
                }else{
                    msg = getMessage("id_pw_error");
                    logInfo.setCreateBy(manager.getAccount());
                    logInfo.setContent(getMessage("manager")+" "+manager.getAccount()+" "+getMessage("login_fail"));
                }
            }else{
                logInfo.setLogFlag(false);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logInfo.setContent(getMessage("login_fail")+":"+e.getMessage());
        }
        model.addAttribute("msg", msg);
        return "common/login";
    }

    /**
     *  封装首页展示数据内容
     * @param model 请求模型对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/common/loadContent")
    public String loadContent(Model model){
        ReportHome homeData = new ReportHome();
        List<SysLogInfo> sysLogInfolist;
        List<BusLogInfo> busLogInfolist;
        try{
            //从内存中获取在库的有效指纹数
            if(null != ConfigParam.ANALYSE_DATA.get("fpNum")){
                homeData.setFpNum(ConfigParam.ANALYSE_DATA.get("fpNum"));
            }
            //从内存中获取在库的有效用户数
            if(null != ConfigParam.ANALYSE_DATA.get("userNum")){
                homeData.setUserNum(ConfigParam.ANALYSE_DATA.get("userNum"));
            }
            //从内存中获取比对引擎服务器数目
            if(null != ConfigParam.ANALYSE_DATA.get("serverNum")){
                homeData.setServerNum(ConfigParam.ANALYSE_DATA.get("serverNum"));
            }
            //从内存中获取今天的业务数目
            if(null != ConfigParam.ANALYSE_DATA.get("busNum")){
                homeData.setBusNum(ConfigParam.ANALYSE_DATA.get("busNum"));
            }
            //从内存中获取今天失败的业务数目
            if(homeData.getBusNum() > 0){
//                DecimalFormat df = new DecimalFormat("#.00");//格式化小数
//                homeData.setBusFailPercent((int)(Double.valueOf(df.format((double) ConfigParam.ANALYSE_DATA.get("busFailNum")/homeData.getBusNum()))*100));
                homeData.setBusFailPercent((int)(Double.valueOf((double) ConfigParam.ANALYSE_DATA.get("busFailNum")/homeData.getBusNum())*100));
            }else{
                homeData.setBusFailPercent(0);
            }

            //从内存中获取本月的指纹增长数据，按天计算
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            String addFpData = CommonStringUtil.mapConvertString(ConfigParam.FP_ADD_DATA, 1, calendar.get(Calendar.DAY_OF_MONTH));
            homeData.setFpAddData(addFpData);

            //从内存中获取本月的业务类型分布数据
            if(ConfigParam.BUS_TYPE_DATA.size() > 0){
                String busTypeData = changeBusTypeData(ConfigParam.BUS_TYPE_DATA);
                homeData.setBusTypeData(busTypeData);
            }

            //从内存中获取在库的服务器运行状态数据
            String serverStatuData = "[" + CommonStringUtil.mapConvertString(ConfigParam.SERVER_STATU_DATA, 0, 2)+ "]";
            homeData.setServerStatuData(serverStatuData);

            //从内存中获取当前在库的任务执行状态数据
            String taskStatuData = "[" + CommonStringUtil.mapConvertString(ConfigParam.TASK_STATU_DATA, 0, 2)+ "]";
            homeData.setTaskStatuData(taskStatuData);
            //获取最近5条系统日志
            sysLogInfolist = sysLogService.getObjListPage(new PageVO(5), ConfigParam.QUERY_TYPE_SOME);
            //获取最近5条业务日志
            busLogInfolist = busLogService.getObjListPage(new PageVO(5), ConfigParam.QUERY_TYPE_SOME);
            model.addAttribute("homeData", homeData);
            model.addAttribute("sysLogInfolist", sysLogInfolist);
            model.addAttribute("busLogInfolist", busLogInfolist);
            //跳转前台页面
            return "common/content";
        }catch (Exception e) {
            e.printStackTrace();
            //跳转前台页面
            return "common/login";
        }
    }

    /**
     *  处理用户退出请求
     * @param session session对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        logInfo.setCreateBy(getSessionManager(session));
        //打包系统日志
        packageLogInfo("LOGIN", OperateVO.LOGIN_OPERATE);
        logInfo.setContent(getMessage("manager")+" "+session.getAttribute("managerName")+" "+getMessage("login_out"));
        session.removeAttribute("managerName");
        session.removeAttribute("purview");
        return "common/login";
    }

    /**
     *  处理用户超时退出
     * @param account 登出账户
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/logTimeout")
    public String logTimeout(Model model, String account){
        model.addAttribute("account", account);
        //构造日志内容
        logInfo.setCreateBy(account);
        //打包系统日志
        packageLogInfo("LOGIN", OperateVO.LOGIN_OPERATE);
        logInfo.setContent(getMessage("java_commonController_pageTimeOutLockPage") + ".");
        //设置不记日志
        logInfo.setLogFlag(false);
        return "common/lockScreen";
    }

    /**
     *  处理用户超时操作
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/checkTimeOut")
    @ResponseBody
    public Map<String,Object> checkTimeOut(HttpSession session){
        initController();
        if(getSessionManager(session) == null){
            msg = "true";
        }else{
            msg = "false";
        }
        responseMap.put("msg", msg);
        return responseMap;
    }

    /**
     *  处理用户越权操作
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/logNopower")
    public String logNopower(){
        //构造日志内容
        logInfo.setOperate(OperateVO.OVERPOWER_OPERATE);
        logInfo.setContent(getMessage("java_commonController_refuseVisit"));
        return "common/login";
    }

    /**
     *  更改系统语言
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/common/changeLu")
    @ResponseBody
    public Model changeLu(Model model,HttpSession session) {
        //设置不记日志
        logInfo.setLogFlag(false);
        // TODO: 2017/5/11 此处存在BUG 暂时不解决
        //判定当前的语言
        Locale l = (Locale)session.getAttribute("WW_TRANS_I18N_LOCALE");
        //切换语言
        if(l.getLanguage().equals("zh")){
            l = new Locale("en", "US");
        }else{
            l = new Locale("zh","CN");
        }
        //设置语言
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, l);
        //将语言保存到回话中
        session.setAttribute("WW_TRANS_I18N_LOCALE", l);
        return model;
    }

    /**
     *  根据有效页面，对权限数据进行再次筛选
     * @param pageList 页面列表
     * @param purview 权限列表
     * @return String 权限列表
     */
    public static String filterString(List<Page> pageList, String purview){
        String result = "";
        String[] arrayTemp = purview.split(",");

        for(Page pageTemp : pageList){
            for(String str : arrayTemp){
                if(str.equals(pageTemp.getCode())){
                    result += "," + str;
                    break;
                }
            }
        }
        if(result.length()>0){
            result = result.substring(1, result.length());
        }
        return result;
    }

    /**
     *  构造业务类型分布趋势图数据
     *  1 ： 指纹注册 2 ： 指纹比对 3 ： 指纹查重 5 ： 指纹导出 6 ： 指纹导入
     * 			 数据格式类似：{value:335, name:'指纹注册'},{value:310, name:'指纹查重'},{value:234, name:'指纹比对'},{value:135, name:'指纹导出'},{value:1548, name:'指纹导入'}
     * @param map 业务数据
     * @return String 转换后的权限业务数据
     */
    public String changeBusTypeData(Map<Integer,Long> map){
        String result = "";
        //合并比对
        if(map.containsKey(10)){
            if(map.containsKey(2)){
                map.put(2, map.get(2) + map.get(10));
            }else{
                map.put(2, map.get(10));
            }
            map.remove(10);
        }
        //合并注销
        if(map.containsKey(9)){
            if(map.containsKey(8)){
                map.put(8, map.get(8) + map.get(9));
            }else{
                map.put(8, map.get(9));
            }
            map.remove(9);
        }
        int[] arrayTemp = {1,2,5,6,8};
        for(int type : arrayTemp){
            long value = 0;
            String typeName;
            switch(type){
                case 1:
                    typeName = getMessage("java_commonController_fingerprintRegister");
                    break;
                case 2:
                    typeName = getMessage("java_commonController_fingerprintCompare");
                    break;
//                case 3:
//                    typeName = getMessage("java_commonController_fingerprintSelect");
//                    break;
                case 5:
                    typeName = getMessage("java_commonController_fingerprintExport");
                    break;
                case 6:
                    typeName = getMessage("java_commonController_fingerprintImport");
                    break;
                case 8:
                    typeName = getMessage("java_commonController_fingerprintLogout");
                    break;
                case 9:
                    typeName = getMessage("java_commonController_fingerprintLogout");
                    break;
                case 10:
                    typeName = getMessage("java_commonController_fingerprintCompare");
                    break;
                default:
                    typeName = getMessage("java_commonController_unknownType");
                    break;
            }
            if(map.containsKey(type)){
                value = map.get(type);
            }
            result += ",{value:"+value+",name:\'"+typeName+"\'}";
        }

        if(result.length()>0){
            result = result.substring(1, result.length());
        }
        return result;
    }
}
