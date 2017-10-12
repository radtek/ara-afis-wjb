/*
 * 文件名：${FpImage}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.6.22}
 * 修改：
 * 描述：指纹图像表 model
 *
 *
 * 版权：亚略特
 */
package com.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "TAS_FPIMAGE")
public class FpImage {

	private int id;				  	//指纹图像标示
	private String fileName;	  	//指纹图像文件名
	private String sourceCode;    	//指纹数据来源库编码
	private Person person;		//访客基本数据
	private String fpIndex;       	//指位
	private FpImageDetail fpImgDetail;    //指纹图像明细数据
	private int qualityLevel;     	//指纹图像质量等级
	private int qualityScore;		//指纹图像质量分数
	private String imageType;       //指纹图像格式
	private byte[] imageData;		//原始图像数据（密文）
	private int collectStatus;		//指纹图像采集状态（编码）
	private String deviceCode;		//设备型号
	private String deviceSN;		//设备生产序列号
	private String collectPlace;	//信息采集单位
	private String collectPerson;   //信息采集人
	private String collectTime;		//采集时间
	private String createOn;        //添加日期

	@Id
	@Column(name = "FP_IMAGE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="trustafis_seq")
	@SequenceGenerator(name="trustafis_seq", sequenceName="SEQ_TAS_FPIMAGE",allocationSize = 1)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FP_IMAGE_FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FP_DATASOURCE_CODE")
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	@NotFound(action= NotFoundAction.IGNORE)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "FP_INDEX_CODE")
	public String getFpIndex() {
		return fpIndex;
	}

	public void setFpIndex(String fpIndex) {
		this.fpIndex = fpIndex;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FP_IMG_DETAIL_ID")
	@NotFound(action= NotFoundAction.IGNORE)
	public FpImageDetail getFpImgDetail() {
		return fpImgDetail;
	}

	public void setFpImgDetail(FpImageDetail fpImgDetail) {
		this.fpImgDetail = fpImgDetail;
	}

	@Column(name = "quality_level")
	public int getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(int qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

	@Column(name = "quality_score")
	public int getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(int qualityScore) {
		this.qualityScore = qualityScore;
	}

	@Column(name = "image_format_code")
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Column(name = "image_data")
	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Column(name = "col_statu_code")
	public int getCollectStatus() {
		return collectStatus;
	}

	public void setCollectStatus(int collectStatus) {
		this.collectStatus = collectStatus;
	}

	@Column(name = "fp_device_code")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Column(name = "fp_device_sn")
	public String getDeviceSN() {
		return deviceSN;
	}

	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
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
