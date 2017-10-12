/*
 * 文件名：${Person}
 * 作者：${Tree}
 * 版本：
 * 时间：${2016.5.20}
 * 修改：
 * 描述：签证人员  PO类
 *
 *
 * 版权：亚略特
 */
package com.model;

import com.util.CommonStringUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TAS_PERSON")
public class Person_cn implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;				//人员编号
	private String fileId;			//交互文件ID，人员相关的生物特征编号
	private String eid;				//人员护照号
	private String familyName;		//护照上的姓
	private String middleName;		//中间名
	private String firstName;		//护照上的名
	private String nationName;		//本国语言姓名
	private String cnName;			//中文名
	private String sex;				//性别
	private String birthday;		//出生日期
	private String nationCode;		//国籍编码
	private String nationIdNum;		//国籍国身份证件号码
	private String dataSourceFlag;	//人员来源标志
	private String collectPlace;	//人员信息采集地点
	private String collectPerson;	//人员信息采集人
	private String activeStatus;	//人员可用状态 E/D
	private String updateStatus;	//人员更新状态 N/U
	private String createOn;		//创建时间
	private String modifiedOn;		//修改时间


	public static Person_cn convert(Person_cn person) throws Exception {
		CommonStringUtil.nullConvertNullString(person);
		parseObjectForWeb(person);
		return person;
	}


	@Id
	@Column(name = "PERSON_ID")
	@GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FP_EXCHAGE_FILE_ID")
	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Column(name = "PERSON_EID")
	public String getEid() {
		return eid;
	}


	public void setEid(String eid) {
		this.eid = eid;
	}

	@Column(name = "FAMILY_NAME")
	public String getFamilyName() {
		return familyName;
	}


	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "NATIONAL_NAME")
	public String getNationName() {
		return nationName;
	}


	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	@Column(name = "CN_NAME")
	public String getCnName() {
		return cnName;
	}


	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "BIRTH_DATE")
	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "NATION_CODE")
	public String getNationCode() {
		return nationCode;
	}


	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	@Column(name = "NATION_ID_NUM")
	public String getNationIdNum() {
		return nationIdNum;
	}


	public void setNationIdNum(String nationIdNum) {
		this.nationIdNum = nationIdNum;
	}

	@Column(name = "FP_DATASOURCE_CODE")
	public String getDataSourceFlag() {
		return dataSourceFlag;
	}


	public void setDataSourceFlag(String dataSourceFlag) {
		this.dataSourceFlag = dataSourceFlag;
	}

	@Column(name = "COLLECT_PLACE")
	public String getCollectPlace() {
		return collectPlace;
	}


	public void setCollectPlace(String collectPlace) {
		this.collectPlace = collectPlace;
	}

	@Column(name = "COLLECT_PERSON")
	public String getCollectPerson() {
		return collectPerson;
	}


	public void setCollectPerson(String collectPerson) {
		this.collectPerson = collectPerson;
	}

	@Column(name = "ACTIVE_STATU")
	public String getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Column(name = "UPDATE_STATU")
	public String getUpdateStatus() {
		return updateStatus;
	}


	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
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

	public static void parseObjectForWeb(Person_cn person){
		switch (person.sex){
			case "1":
				person.setSex("男");
				break;
			case "2":
				person.setSex("女");
				break;
			default:
				person.setSex("未知");
				break;
		}
		switch (person.activeStatus){
			case "E":
				person.setActiveStatus("可用");
				break;
			default:
				person.setActiveStatus("停用");
				break;
		}
	}

}
