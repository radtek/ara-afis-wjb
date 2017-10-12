/*
 * 文件名：${FpImageDetail}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.22}
 * 修改：
 * 描述：指纹图像详细数据model
 *
 *
 * 版权：亚略特
 */
package com.model;

import com.util.CommonStringUtil;

import javax.persistence.*;

@Entity
@Table(name = "TAS_FPIMAGE_DETAIL")
public class FpImageDetail {

	private Integer id;				  	//字典标示
	private String fpId;				//指纹编号
	private Integer imageWidth;			//指纹图像宽度
	private Integer imageHeight;		//指纹图像高度
	private String deviceCode;			//指纹采集设备型号
	private String deviceSN;			//设备生产序列号
	private Integer technicalFlag;		//设备技术标示
	private String devSubFlag;			//设备供方标示
	private String devTypeFlag;			//设备类型标示
	private String deviceVersion;		//设备版本
	private Integer acqtLevel;			//获取级别
	private Integer scaleuUnits;		//度量单位
	private Integer horizscanRL;		//水平扫描分辨率
	private Integer vertscanRL;			//垂直扫描分辨率
	private Integer maxImageWidth;		//最大图像宽度
	private Integer maxImageHeight;		//最大图像高度
	private Integer cerMark;			//认证标示
	private Integer cerCount;			//认证块数量
	private String cerOrg;				//认证机构标示
	private Integer horizimageRL;		//水平图像分辨率
	private Integer vertimageRL;		//垂直图像分辨率
	private Integer imageRL;			//图像分辨率
	private Integer pixelDepth;			//像素深度
	private Integer imprintType;		//印记类型
	private Integer comAlgorithm;		//压缩算法标示
	private String comSupFlag;			//压缩算法供方标示
	private Integer comAlgVersion;		//压缩算法版本
	private String quaAlgorithm;		//质量算法标示
	private Integer quaSubFlag;			//质量算法供方标示
	private Integer quaAlgVersion;		//质量算法版本L
	private String collectPlace;		//信息采集单位
	private String collectPerson;   	//信息采集人
	private String collectTime;			//采集时间
	private String createOn;        	//添加日期

	public static FpImageDetail convert(FpImageDetail imageDetail) throws Exception {
		CommonStringUtil.nullConvertNullString(imageDetail);
		return imageDetail;
	}

	@Id
	@Column(name = "FP_IMG_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="trustafis_seq")
	@SequenceGenerator(name="trustafis_seq", sequenceName="SEQ_TAS_FPIMAGE_DETAIL",allocationSize = 1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FP_EXCHAGE_FILE_ID")
	public String getFpId() {
		return fpId;
	}

	public void setFpId(String fpId) {
		this.fpId = fpId;
	}

	@Column(name = "image_width")
	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	@Column(name = "image_height")
	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	@Column(name = "fp_device_code")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Column(name = "device_sn")
	public String getDeviceSN() {
		return deviceSN;
	}

	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}

	@Column(name = "technical_flag")
	public Integer getTechnicalFlag() {
		return technicalFlag;
	}

	public void setTechnicalFlag(Integer technicalFlag) {
		this.technicalFlag = technicalFlag;
	}

	@Column(name = "dev_sup_flag")
	public String getDevSubFlag() {
		return devSubFlag;
	}

	public void setDevSubFlag(String devSubFlag) {
		this.devSubFlag = devSubFlag;
	}

	@Column(name = "dev_type_flag")
	public String getDevTypeFlag() {
		return devTypeFlag;
	}

	public void setDevTypeFlag(String devTypeFlag) {
		this.devTypeFlag = devTypeFlag;
	}

	@Column(name = "device_version")
	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	@Column(name = "acqt_level" )
	public Integer getAcqtLevel() {
		return acqtLevel;
	}

	public void setAcqtLevel(Integer acqtLevel) {
		this.acqtLevel = acqtLevel;
	}

	@Column(name = "scale_units")
	public Integer getScaleuUnits() {
		return scaleuUnits;
	}

	public void setScaleuUnits(Integer scaleuUnits) {
		this.scaleuUnits = scaleuUnits;
	}

	@Column(name = "horizscan_rl")
	public Integer getHorizscanRL() {
		return horizscanRL;
	}

	public void setHorizscanRL(Integer horizscanRL) {
		this.horizscanRL = horizscanRL;
	}

	@Column(name = "vertscan_rl")
	public Integer getVertscanRL() {
		return vertscanRL;
	}

	public void setVertscanRL(Integer vertscanRL) {
		this.vertscanRL = vertscanRL;
	}

	@Column(name = "max_image_width")
	public Integer getMaxImageWidth() {
		return maxImageWidth;
	}

	public void setMaxImageWidth(Integer maxImageWidth) {
		this.maxImageWidth = maxImageWidth;
	}

	@Column(name = "max_image_height")
	public Integer getMaxImageHeight() {
		return maxImageHeight;
	}

	public void setMaxImageHeight(Integer maxImageHeight) {
		this.maxImageHeight = maxImageHeight;
	}

	@Column(name = "cert_mark")
	public Integer getCerMark() {
		return cerMark;
	}

	public void setCerMark(Integer cerMark) {
		this.cerMark = cerMark;
	}

	@Column(name = "cer_count")
	public Integer getCerCount() {
		return cerCount;
	}

	public void setCerCount(Integer cerCount) {
		this.cerCount = cerCount;
	}

	@Column(name = "cer_org")
	public String getCerOrg() {
		return cerOrg;
	}

	public void setCerOrg(String cerOrg) {
		this.cerOrg = cerOrg;
	}

	@Column(name = "horizimage_rl")
	public Integer getHorizimageRL() {
		return horizimageRL;
	}

	public void setHorizimageRL(Integer horizimageRL) {
		this.horizimageRL = horizimageRL;
	}

	@Column(name = "vertimage_rl")
	public Integer getVertimageRL() {
		return vertimageRL;
	}

	public void setVertimageRL(Integer vertimageRL) {
		this.vertimageRL = vertimageRL;
	}

	@Column(name = "image_rl")
	public Integer getImageRL() {
		return imageRL;
	}

	public void setImageRL(Integer imageRL) {
		this.imageRL = imageRL;
	}

	@Column(name = "pixel_depth")
	public Integer getPixelDepth() {
		return pixelDepth;
	}

	public void setPixelDepth(Integer pixelDepth) {
		this.pixelDepth = pixelDepth;
	}

	@Column(name = "imprint_type")
	public Integer getImprintType() {
		return imprintType;
	}

	public void setImprintType(Integer imprintType) {
		this.imprintType = imprintType;
	}

	@Column(name = "com_algorithm")
	public Integer getComAlgorithm() {
		return comAlgorithm;
	}

	public void setComAlgorithm(Integer comAlgorithm) {
		this.comAlgorithm = comAlgorithm;
	}

	@Column(name = "com_sup_flag")
	public String getComSupFlag() {
		return comSupFlag;
	}

	public void setComSupFlag(String comSupFlag) {
		this.comSupFlag = comSupFlag;
	}

	@Column(name = "com_alg_version")
	public Integer getComAlgVersion() {
		return comAlgVersion;
	}

	public void setComAlgVersion(Integer comAlgVersion) {
		this.comAlgVersion = comAlgVersion;
	}

	@Column(name = "qua_algorithm")
	public String getQuaAlgorithm() {
		return quaAlgorithm;
	}

	public void setQuaAlgorithm(String quaAlgorithm) {
		this.quaAlgorithm = quaAlgorithm;
	}

	@Column(name = "qua_sup_flag")
	public Integer getQuaSubFlag() {
		return quaSubFlag;
	}

	public void setQuaSubFlag(Integer quaSubFlag) {
		this.quaSubFlag = quaSubFlag;
	}

	@Column(name = "qua_alg_version")
	public Integer getQuaAlgVersion() {
		return quaAlgVersion;
	}

	public void setQuaAlgVersion(Integer quaAlgVersion) {
		this.quaAlgVersion = quaAlgVersion;
	}

	@Column(name = "collect_place")
	public String getCollectPlace() {
		return collectPlace;
	}

	public void setCollectPlace(String collectPlace) {
		this.collectPlace = collectPlace;
	}

	@Column(name = "collect_person")
	public String getCollectPerson() {
		return collectPerson;
	}

	public void setCollectPerson(String collectPerson) {
		this.collectPerson = collectPerson;
	}

	@Column(name = "collect_time")
	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	@Column(name = "create_date")
	public String getCreateOn() {
		return createOn;
	}

	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}
}
