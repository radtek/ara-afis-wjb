/*
 * 文件名：${FpTemplate}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.23}
 * 修改：
 * 描述：指纹特征表 model
 *
 *
 * 版权：亚略特
 */
package com.model;


import com.vo.TemplateMapKey;

import javax.persistence.*;

@Entity
@Table(name = "TAS_FP_TEMPLATE")
@IdClass(TemplateMapKey.class)
public class FpTemplate {

	@Id
	@Column(name="person_id")
	private String personId;		//指纹图像标示
	@Id
	@Column(name="fp_index_code")
	private String fpIndexCode;	  	//指纹图像文件名
	@Id
	@Column(name="fp_template_no")
	private String fpTemplateNo;    //指纹数据来源库编码
	@Column(name = "fp_datasource_code")
	private String datasourceCode;	//访客基本数据
	@Column(name = "fp_exchange_file_id")
	private String fpId;       	//指位
	@Column(name = "template")
	private String template;    //指纹图像明细数据
	@Column(name = "template_b")
	private byte[] templateB;     	//指纹图像质量等级
	@Column(name = "ext_info")
	private String extInfo;		//指纹图像质量分数
	@Column(name = "fp_categoty")
	private String fpCategory;       //指纹图像格式
	@Column(name = "singular_point_num")
	private Integer sinPointNum;		//原始图像数据（密文）
	@Column(name = "template_point_num")
	private Integer temPointNum;		//指纹图像采集状态（编码）
	@Column(name = "quality_score")
	private Float qualityScore;		//设备型号
	@Column(name = "alg_code")
	private String algCode;		//设备生产序列号
	@Column(name = "alg_version")
	private String algVersion;	//信息采集单位
	@Column(name = "fp_block_index")
	private Integer fpBlockIndex;   //信息采集人
	@Column(name = "col_statu_code")
	private String collectStatus;		//采集时间
	@Column(name = "fp_device_code")
	private String fpDeviceCode;		//采集时间
	@Column(name = "fp_device_sn")
	private String fpDeviceSN;		//采集时间
	@Column(name = "active_statu")
	private String activeStatus;		//采集时间
	@Column(name = "update_statu")
	private String updateStatus;		//采集时间
	@Column(name = "create_date")
	private String createOn;			//添加日期
	@Column(name = "modify_date")
	private String modifyOn;		//采集时间

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getFpIndexCode() {
		return fpIndexCode;
	}

	public void setFpIndexCode(String fpIndexCode) {
		this.fpIndexCode = fpIndexCode;
	}

	public String getFpTemplateNo() {
		return fpTemplateNo;
	}

	public void setFpTemplateNo(String fpTemplateNo) {
		this.fpTemplateNo = fpTemplateNo;
	}

	public String getDatasourceCode() {
		return datasourceCode;
	}

	public void setDatasourceCode(String datasourceCode) {
		this.datasourceCode = datasourceCode;
	}

	public String getFpId() {
		return fpId;
	}

	public void setFpId(String fpId) {
		this.fpId = fpId;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public byte[] getTemplateB() {
		return templateB;
	}

	public void setTemplateB(byte[] templateB) {
		this.templateB = templateB;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public String getFpCategory() {
		return fpCategory;
	}

	public void setFpCategory(String fpCategory) {
		this.fpCategory = fpCategory;
	}

	public Integer getSinPointNum() {
		return sinPointNum;
	}

	public void setSinPointNum(Integer sinPointNum) {
		this.sinPointNum = sinPointNum;
	}

	public Integer getTemPointNum() {
		return temPointNum;
	}

	public void setTemPointNum(Integer temPointNum) {
		this.temPointNum = temPointNum;
	}

	public Float getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(Float qualityScore) {
		this.qualityScore = qualityScore;
	}

	public String getAlgCode() {
		return algCode;
	}

	public void setAlgCode(String algCode) {
		this.algCode = algCode;
	}

	public String getAlgVersion() {
		return algVersion;
	}

	public void setAlgVersion(String algVersion) {
		this.algVersion = algVersion;
	}

	public Integer getFpBlockIndex() {
		return fpBlockIndex;
	}

	public void setFpBlockIndex(Integer fpBlockIndex) {
		this.fpBlockIndex = fpBlockIndex;
	}

	public String getCollectStatus() {
		return collectStatus;
	}

	public void setCollectStatus(String collectStatus) {
		this.collectStatus = collectStatus;
	}

	public String getFpDeviceCode() {
		return fpDeviceCode;
	}

	public void setFpDeviceCode(String fpDeviceCode) {
		this.fpDeviceCode = fpDeviceCode;
	}

	public String getFpDeviceSN() {
		return fpDeviceSN;
	}

	public void setFpDeviceSN(String fpDeviceSN) {
		this.fpDeviceSN = fpDeviceSN;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getCreateOn() {
		return createOn;
	}

	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
}
