/*
 * 文件名：${Engine}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.25}
 * 修改：
 * 描述：比对引擎  PO类
 *
 *
 * 版权：亚略特
 */
package com.model;

import com.exception.CommonUtilException;
import com.util.CommonStringUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TAS_MON_ENV_STATISTIC")
public class Engine implements Serializable {
	private int id;						//标示ID
	private String clusterCode;			//引擎集群编码
	private String engineCode;			//引擎节点编码
	private String biometricsModel; 	//引擎生物识别模态
	private String engineType; 			//引擎类别  0 ： 主控服务器 1 ： 比对引擎
	private String ip; 					//引擎IP地址
	private String mac; 				//引擎MAC地址
	private String cpuNum; 				//引擎CPU主频核数
	private String enginePort; 			//引擎端口
	private String workStationStatus; 	//引擎运行状态
    private String warnField;           //引擎报警信息
	private String networkStatus; 		//引擎网络状态
	private int runningTime;			//引擎运行时长
	private long loadFingerNum;         //引擎加载指纹数量
	private String createOn;			//引擎创建时间
	private String modifiedOn;			//引擎修改时间

	public static Engine convert(Engine engine) throws CommonUtilException {
		CommonStringUtil.nullConvertNullString(engine);
		return engine;
	}

	public static Engine init(Engine engine) throws CommonUtilException {
		CommonStringUtil.nullConvertInitString(engine);
		return engine;
	}

	@Id
	@Column(name = "MON_ENV_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="trustafis_seq")
	@SequenceGenerator(name="trustafis_seq", sequenceName="SEQ_TAS_MON_ENV_STATISTIC",allocationSize = 1)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ENGINE_CLUSTER_CODE")
	public String getClusterCode() {
		return clusterCode;
	}


	public void setClusterCode(String clusterCode) {
		this.clusterCode = clusterCode;
	}

	@Column(name = "ENGINE_SERVER_CODE")
	public String getEngineCode() {
		return engineCode;
	}


	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	@Column(name = "ENGINE_SERVER_BIOMETRICS_MODEL")
	public String getBiometricsModel() {
		return biometricsModel;
	}


	public void setBiometricsModel(String biometricsModel) {
		this.biometricsModel = biometricsModel;
	}

	@Column(name = "ENGINE_SERVER_TYPE")
	public String getEngineType() {
		return engineType;
	}


	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	@Column(name = "ENGINE_SERVER_IP")
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "ENGINE_SERVER_MAC")
	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}

	@Column(name = "ENGINE_SERVER_CPU")
	public String getCpuNum() {
		return cpuNum;
	}


	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}

	@Column(name = "ENGINE_SERVER_PORT")
	public String getEnginePort() {
		return enginePort;
	}


	public void setEnginePort(String enginePort) {
		this.enginePort = enginePort;
	}

	@Column(name = "ENGINE_WORKSTATION_STATUS")
	public String getWorkStationStatus() {
		return workStationStatus;
	}


	public void setWorkStationStatus(String workStationStatus) {
		this.workStationStatus = workStationStatus;
	}

	@Column(name = "ENGINE_NETWORK_STATUS")
	public String getNetworkStatus() {
		return networkStatus;
	}


	public void setNetworkStatus(String networkStatus) {
		this.networkStatus = networkStatus;
	}

	@Column(name = "ENGINE_RUNNING_TIME")
	public int getRunningTime() {
		return runningTime;
	}


	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	@Column(name = "ENGINE_LODA_FINGER_NUMBER")
	public long getLoadFingerNum() {
		return loadFingerNum;
	}


	public void setLoadFingerNum(long loadFingerNum) {
		this.loadFingerNum = loadFingerNum;
	}

	@Column(name = "CREATE_DATE")
	public String getCreateOn() {
		return createOn;
	}


	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

	@Column(name = "MODIFY_DATE")
	public String getModifiedOn() {
		return modifiedOn;
	}


	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

    @Column(name = "ENGINE_WARN_FIELD")
    public String getWarnField() {
        return warnField;
    }

    public void setWarnField(String warnField) {
        this.warnField = warnField;
    }
}
