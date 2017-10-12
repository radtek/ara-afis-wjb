/**
 * @author: tree
 * @version: 1.0
 * date: 2017/9/5 18:18
 * @description: 比对引擎 相关业务逻辑类
 * own: Aratek
 */
package com.controller;

import com.exception.CommonUtilException;
import com.exception.ServiceException;
import com.model.Engine;
import com.model.EngineNode;
import com.model.MonitorInfo;
import com.services.EngineNodeService;
import com.services.EngineService;
import com.services.MonitorInfoService;
import com.vo.OperateVO;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Optional;

@Controller
@Scope("prototype")
@RequestMapping(value = "/engine")
public class EngineController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(EngineController.class);

    //注入服务
    @Autowired
    private EngineService engineService;
    @Autowired
    private MonitorInfoService monitorInfoService;
    @Autowired
    private EngineNodeService engineNodeService;

    /**
     * 公共查询模块的对应方法
     * @param model 请求模型对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toGetList")
    public String toGetList(Model model, int anyId){
        //获取所有引擎信息
        List<Engine> engineList = engineService.getAllEngine();   //引擎列表
        Engine engine = null;
        if (null != engineList && engineList.size() > 0) {

            //当网络状态为1时，视为引擎故障.遍历改变状态
            for(Engine e : engineList){
                if(e.getNetworkStatus().equals("1")){
                    e.setWorkStationStatus("2");
                }else if(!e.getWorkStationStatus().equals("2") && StringUtils.isNotBlank(e.getWarnField())){
                    e.setWorkStationStatus("1");
                }
                //判断为指定的引擎
                if(e.getId() == anyId) {
                    engine = e;
                }
            }

            //如果没有指定引擎（遍历不存在）
            if(!(Optional.ofNullable(engine).isPresent())){
                engine = engineList.get(0);
                anyId = engine.getId();
            }


            //获取引擎节点信息
            EngineNode engineNode = engineNodeService.getEngineNodeByMasterAndNode(engine.getClusterCode(), engine.getEngineCode());
            //对应1：1 比对的数据队列
            String verifyNumArray = monitorInfoService.getMonitorFingerData(engine.getClusterCode(), engine.getEngineCode(), "1");
            //对应1：N 比对的数据队列
            String identifyNumArray = monitorInfoService.getMonitorFingerData(engine.getClusterCode(), engine.getEngineCode(), "2");
            model.addAttribute("engineList", engineList);
            model.addAttribute("anyId", anyId);
            model.addAttribute("engine", engine);
            model.addAttribute("engineNode", engineNode);
            model.addAttribute("verifyNumArray", verifyNumArray);
            model.addAttribute("identifyNumArray", identifyNumArray);
        }
        //跳转前台
        return "/engine/queryEngine";
    }

    /**
     * 查询图表数据
     * @param model 请求模型对象
     * @param anyId 字典标识
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/queryChartData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryChartData(Model model, int anyId){
        logger.debug("[SYS_LOG][queryChartData][{}]",anyId);
        initController();
        //判定是否指定了引擎
        if (0 != anyId) {
            //通过引擎对象主键获取引擎对象
            Engine engine = engineService.getObj(anyId);
            //拿cpu使用率数据  key值为4
            List<MonitorInfo> cpuList = monitorInfoService.getMonitorData(engine.getClusterCode(), engine.getEngineCode(), "4");
            //拿内存使用率数据  key值为5
            List<MonitorInfo> memoryList = monitorInfoService.getMonitorData(engine.getClusterCode(), engine.getEngineCode(), "5");
            //拿硬盘使用率数据  key值为6
            List<MonitorInfo> diskList = monitorInfoService.getMonitorData(engine.getClusterCode(), engine.getEngineCode(), "6");
            model.addAttribute("cpuList", cpuList);
            model.addAttribute("memoryList", memoryList);
            model.addAttribute("diskList", diskList);
            responseMap.put("anyStatus", 1);
        }
        return responseMap;
    }

    /**
     * 初始化添加或修改页面的对应方法
     * @param model 请求模型对象
     * @param anyId 字典标识
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/toAddOption")
    public String toAddOption(Model model, String anyOption, int anyId){
        EngineNode engineNode = null;
        //判别要执行的是添加还是修改操作
        if (anyOption.contains("add")) {
            //初始化引擎节点对象，主要是为了获取几个线程的初始值
            engineNode = new EngineNode();
        } else {
            //判定是否指定了引擎
            //通过引擎对象主键获取引擎对象
            Engine engine = engineService.getObj(anyId);
            //如果引擎对象存在
            if (null != engine) {
                //通过引擎节点对象主键获取引擎几点对象
                engineNode = engineNodeService.getEngineNodeByMasterAndNode(engine.getClusterCode(), engine.getEngineCode());
                //如果是主控节点，则初始化节点对象
                if (null == engineNode) {
                    engineNode = new EngineNode();
                }
            }
            model.addAttribute("engine", engine);
        }
        model.addAttribute("engineNode", engineNode);
        return "/engine/addEngine";
    }

    /**
     * 公共添加/修改模块的对应方法
     * @param engine 引擎对象
     * @param anyOption 操作类型
     * @param engineNode 引擎节点标识
     * @param session Session对象
     * @return String 返回到前台页面
     */
    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOption(Engine engine, EngineNode engineNode, String anyOption, HttpSession session) throws ServiceException {
        initController();
        //打包系统日志
        packageLogInfo("ENGINE", OperateVO.ADD_OPERATE);
        //初始化参数
        String masterId = "",nodeId = "",resultMsg= "";
        try{
            //判定数据完整性
            if(null != engineNode && null != engine){
                masterId = engineNode.getMasterId();
                nodeId = engineNode.getNodeId();
                //判别要执行的是添加还是修改操作
                if(anyOption.contains("add")){
                    nodeId = engineNode.getNodeId();
                    //保存比对引擎对象
                    engineNodeService.saveObj(engineNode, getSessionManager(session),engine);
                    resultMsg = getMessage("java_codeController_addSuccess");
                }else{
                    logInfo.setOperate(OperateVO.MODEIFY_OPERATE);
                    //修改字典
                    engineNodeService.updateObj(engineNode, getSessionManager(session),0,engine);
                    resultMsg = getMessage("java_codeController_updateSuccess");
                }
                //设置数据
                engineNode.setEngineType(engine.getEngineType());
                engineNode.setEngineModel(engine.getBiometricsModel());
                //构造前台数据模型
                anyStatus = 1;
                tr = drawTable(EngineNode.convert(engineNode));
            }
        } catch (CommonUtilException e) {
            resultMsg = getMessage("java_codeController_addFailedReason")+": " + e.getMessage();
        }
        if(StringUtils.isNotEmpty(nodeId) && !masterId.equals(nodeId)){
            msg = getMessage("java_engineController_underMasterNodeEngine1")+" "+masterId+" " + getMessage("java_engineController_underMasterNodeEngine2") + ": "+nodeId + " " + resultMsg ;
        }else{
            msg = getMessage("engine_masterNode") +masterId+ " " + resultMsg ;
        }
        logInfo.setContent(msg);
        logInfo.setCreateBy(getSessionManager(session));
        responseMap.put("anyStatus", anyStatus);
        responseMap.put("msg", msg);
        responseMap.put("tr", tr);
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
        packageLogInfo("ENGINE", OperateVO.DELETE_OPERATE);
        //初始化参数
        String masterId = "",nodeId = "",resultMsg = "";
        //判定是否指定了引擎
        if (0 != anyId) {
            //通过引擎对象主键获取引擎对象
            Engine engine = engineService.getObj(anyId);
            if (null != engine) {
                masterId = engine.getClusterCode();
                nodeId = engine.getEngineCode();
                //删除指定的引擎对象，0为定值，无意义
                engineNodeService.delObj(0, masterId, nodeId);
                anyStatus = 1;
                resultMsg = getMessage("java_codeController_deleteSuccess");
            }
        }
        if(StringUtils.isNotEmpty(nodeId) && !masterId.equals(nodeId)){
//            msg = getMessage("engine_masterNode")+" "+masterId+" 下的比对引擎: "+nodeId + " " + resultMsg ;
            msg = getMessage("java_engineController_underMasterNodeEngine1")+" "+masterId+" " + getMessage("java_engineController_underMasterNodeEngine2") + ": "+nodeId + " " + resultMsg ;
        }else{
            msg = getMessage("engine_masterNode")+" "+masterId+ " " + resultMsg ;
        }
        logInfo.setContent(msg);
        logInfo.setCreateBy(getSessionManager(session));
        responseMap.put("anyStatus", anyStatus);
        responseMap.put("msg", msg);
        return responseMap;
    }
}
