/*
 * 文件名：${MonitorInfo}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.25}
 * 修改：
 * 描述：比对引擎监控信息  PO类
 *
 *
 * 版权：亚略特
 */
package com.model;

import com.util.CommonStringUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TAS_MON_SERVICE_STATISTIC")
public class MonitorInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;					//标示ID
	private String clusterCode;			//引擎集群编码
	private String engineCode;			//引擎节点编码
	private String mainKey; 			//业务名称
	private String totalValue; 			//业务统计值
	private String createOn;			//记录生成时间



	public static MonitorInfo convert(MonitorInfo engine) throws Exception {
		CommonStringUtil.nullConvertNullString(engine);
		return engine;
	}


	@Id
	@Column(name = "MON_SERVICE_ID")
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


	@Column(name = "CREATE_DATE")
	public String getCreateOn() {
		return createOn;
	}


	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

	@Column(name = "SERVICE_KEY")
	public String getMainKey() {
		return mainKey;
	}


	public void setMainKey(String mainKey) {
		this.mainKey = mainKey;
	}

	@Column(name = "TOTAL_VALUE")
	public String getTotalValue() {
		return totalValue;
	}


	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

}
