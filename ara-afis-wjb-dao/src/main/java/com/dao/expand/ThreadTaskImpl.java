/**
 * @author: tree
 * @version: 1.0
 * date: 2017/9/5 17:48
 * @description: 定时统计首页数据
 * own: Aratek
 */
package com.dao.expand;

import com.dao.impl.BaseDaoImpl;
import com.param.ConfigParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public class ThreadTaskImpl extends BaseDaoImpl<ThreadTaskImpl> {

	private static Logger logger = LoggerFactory.getLogger(ThreadTaskImpl.class);

	public ThreadTaskImpl() {
        logger.info("[SYS_LOG][Start thread to collect data]");
	}

	/**
	 * 线程执行方法
	 */
	public void queryHomeData() {
        logger.info("[SYS_LOG][Start collect data]");
        long start = Instant.now().toEpochMilli();
        List<?> list;
        //获取在库的指纹数
        String sql1 = "SELECT COUNT(*) NUM FROM TAS_FP_TEMPLATE WHERE COL_STATU_CODE = ?0 AND ACTIVE_STATU = ?1";
        list = findListBySql(sql1, "0", ConfigParam.ACTIVE_STATU.AVAILABLE.getElementName());
        logger.debug("[SYS_LOG][Avaliable FP NUM : {}]",list.get(0));
        ConfigParam.ANALYSE_DATA.put("fpNum", ((BigDecimal) list.get(0)).longValue());

        //获取在库的在库的人员数
        String sql2 = "SELECT COUNT(*) NUM FROM TAS_PERSON WHERE ACTIVE_STATU = ?0";
        list = findListBySql(sql2, ConfigParam.ACTIVE_STATU.AVAILABLE.getElementName());
        logger.debug("[SYS_LOG][Avaliable person NUM : {}]",list.get(0));
        ConfigParam.ANALYSE_DATA.put("userNum", ((BigDecimal) list.get(0)).longValue());

        //获取在线的引擎数
        String sql3 = "SELECT COUNT(*) NUM FROM TAS_MON_ENV_STATISTIC WHERE ENGINE_WORKSTATION_STATUS = ?0 AND ENGINE_NETWORK_STATUS != ?1";
        list = findListBySql(sql3, "0","1");
        logger.debug("[SYS_LOG][Online engine NUM : {}]",list.get(0));
        ConfigParam.ANALYSE_DATA.put("serverNum", ((BigDecimal) list.get(0)).longValue());

        //获取今天的业务数
        String sql4 = "SELECT COUNT(*) NUM FROM TAS_LOG_BUSINESS WHERE ACTION_TYPE NOT IN(4,7) AND CREATE_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD')";
        list = findListBySql(sql4);
        logger.debug("[SYS_LOG][Today business NUM : {}]",list.get(0));
        ConfigParam.ANALYSE_DATA.put("busNum", ((BigDecimal) list.get(0)).longValue());

        //获取今天失败的业务数
        String sql5 = "SELECT COUNT(*) NUM FROM TAS_LOG_BUSINESS WHERE RESULT_CODE = ?0 AND CREATE_DATE >= TO_CHAR(SYSDATE,'YYYY-MM-DD')";
        list = findListBySql(sql5, "19999");
        logger.debug("[SYS_LOG][Today fail business NUM : {}]",list.get(0));
        ConfigParam.ANALYSE_DATA.put("busFailNum", ((BigDecimal) list.get(0)).longValue());

        //获取当月每天增长的指纹数量
        String sql6 = "SELECT TO_CHAR(TO_DATE(CREATE_DATE, 'YYYY-MM-DD HH24:MI:SS'), 'DD')  KEYTIME, COUNT(*) NUM FROM TAS_FP_TEMPLATE  WHERE  COL_STATU_CODE = ?0 AND ACTIVE_STATU = ?1 AND CREATE_DATE >= TO_CHAR(SYSDATE,'YYYY-MM') GROUP BY TO_CHAR(TO_DATE(CREATE_DATE, 'YYYY-MM-DD HH24:MI:SS'), 'DD') ORDER BY KEYTIME";
        list = findListBySql(sql6, "0", ConfigParam.ACTIVE_STATU.AVAILABLE.getElementName());
        ConfigParam.FP_ADD_DATA.clear();
        for (Object object : list) {
            logger.debug("[SYS_LOG][Every day add FP NUM][{}][{}]",((Object[]) object)[0],((BigDecimal) ((Object[]) object)[1]).longValue());
            ConfigParam.FP_ADD_DATA.put(Integer.valueOf((String) (((Object[]) object)[0])), ((BigDecimal) ((Object[]) object)[1]).longValue());
        }

        //获取当月业务类型分布趋势
        String sql7 = "SELECT ACTION_TYPE, COUNT(*) NUM FROM TAS_LOG_BUSINESS  WHERE ACTION_TYPE NOT IN(4,7) AND  CREATE_DATE >= TO_CHAR(SYSDATE,'YYYY-MM') GROUP BY ACTION_TYPE";
        list = findListBySql(sql7);
        ConfigParam.BUS_TYPE_DATA.clear();
        for (Object object : list) {
            logger.debug("[SYS_LOG][Every month add business NUM][{}][{}]",((Object[]) object)[0],((BigDecimal) ((Object[]) object)[1]).longValue());
            ConfigParam.BUS_TYPE_DATA.put(Integer.valueOf((String) (((Object[]) object)[0])), ((BigDecimal) ((Object[]) object)[1]).longValue());
        }

        //获取当前引擎状态数据（正常）
//        String sql8 = "SELECT ENGINE_WORKSTATION_STATUS, COUNT(*) FROM (SELECT * FROM TAS_MON_ENV_STATISTIC WHERE ENGINE_WARN_FIELD IS NULL OR (ENGINE_WARN_FIELD IS NOT NULL  AND ENGINE_WORKSTATION_STATUS = ?0))  GROUP BY ENGINE_WORKSTATION_STATUS ORDER BY ENGINE_WORKSTATION_STATUS";
        String sql8 = "SELECT COUNT(*) FROM  TAS_MON_ENV_STATISTIC WHERE  ENGINE_WORKSTATION_STATUS = ?0 AND ENGINE_WARN_FIELD IS NULL AND ENGINE_NETWORK_STATUS != ?1";
        list = findListBySql(sql8, 0,1);
        ConfigParam.SERVER_STATU_DATA.clear();
        for (Object object : list) {
            logger.debug("---------引擎正常数目: " + ((BigDecimal) object).longValue());
            logger.debug("[SYS_LOG][Current engine N statu][{}][{}]",((BigDecimal) object).longValue());
            ConfigParam.SERVER_STATU_DATA.put(0, ((BigDecimal) object).longValue());
        }

        //获取当前引擎状态数据（告警）
        String sql9 = "SELECT COUNT(*) FROM TAS_MON_ENV_STATISTIC WHERE ENGINE_WARN_FIELD IS NOT NULL AND ENGINE_WORKSTATION_STATUS != ?0";
        list = findListBySql(sql9, ConfigParam.ENGINE_FAULT_CODE);
        for (Object object : list) {
            logger.debug("---------引擎告警数目: " + ((BigDecimal) object).longValue());
            logger.debug("[SYS_LOG][Current engine W statu][{}][{}]",((BigDecimal) object).longValue());
            ConfigParam.SERVER_STATU_DATA.put(ConfigParam.ENGINE_WARN_CODE, ((BigDecimal) object).longValue());
        }

        //获取当前引擎状态数据（故障）
        String sql11 = "SELECT COUNT(*) FROM TAS_MON_ENV_STATISTIC WHERE ENGINE_WORKSTATION_STATUS = ?0 OR ENGINE_NETWORK_STATUS = ?1";
        list = findListBySql(sql11, ConfigParam.ENGINE_FAULT_CODE,1);
        for (Object object : list) {
            logger.debug("---------引擎故障数目: " + ((BigDecimal) object).longValue());
            logger.debug("[SYS_LOG][Current engine F statu][{}][{}]",((BigDecimal) object).longValue());
            ConfigParam.SERVER_STATU_DATA.put(ConfigParam.ENGINE_FAULT_CODE, ((BigDecimal) object).longValue());
        }

        //获取任务状态数据
        String sql10 = "SELECT TASK_STATE, COUNT(*) FROM TAS_TASK  WHERE  CREATE_DATE >= TO_CHAR(SYSDATE,'YYYY-MM') GROUP BY TASK_STATE ORDER BY TASK_STATE";
        list = findListBySql(sql10);
        ConfigParam.TASK_STATU_DATA.clear();
        for (Object object : list) {
            logger.debug("[SYS_LOG][Current task statu][{}][{}]",((Object[]) object)[0],((BigDecimal) ((Object[]) object)[1]).longValue());
            ConfigParam.TASK_STATU_DATA.put(Integer.valueOf((String) (((Object[]) object)[0])), ((BigDecimal) ((Object[]) object)[1]).longValue());
        }
        long end = Instant.now().toEpochMilli();
        logger.info("[SYS_LOG][Finish collect data][Use time : {}]",end - start);
    }
}
