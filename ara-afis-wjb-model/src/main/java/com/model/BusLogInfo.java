/*
 * 文件名：${BusLogInfo}
 * 作者：${Tree}
 * 版本：
 * 时间：${2014.4.15}
 * 修改：
 * 描述：业务日志对象  PO类
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
@Table(name = "TAS_LOG_BUSINESS")
public class BusLogInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;            	//标示ID
	private String actionType;		//业务类型
	private String resultFlag;		//业务结果
	private String resultCode;		//业务返回码
	private String resultMSG;		//业务返回信息
	private String content;			//业务内容
	private String resultId;		//回执号   任务ID，关联任务队列表
	private String clientIp;		//客户端IP
	private String createOn;   		//日志记录时间
    private String actionTypeDes;	//业务说明

	public static BusLogInfo convert(BusLogInfo busLogInfo) throws CommonUtilException {
		CommonStringUtil.nullConvertNullString(busLogInfo);
		parseObjectForWeb(busLogInfo);
		return busLogInfo;
	}

	@Id
	@Column(name = "LOG_BUS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="trustafis_seq")
	@SequenceGenerator(name="trustafis_seq", sequenceName="SEQ_TAS_BUSINESS_LOG",allocationSize = 1)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ACTION_TYPE")
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Column(name = "RESULT_FLAG")
	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	@Column(name = "RESULT_CODE")
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Column(name = "RESULT_MSG")
	public String getResultMSG() {
		return resultMSG;
	}

	public void setResultMSG(String resultMSG) {
		this.resultMSG = resultMSG;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "RESULT_ID")
	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	@Column(name = "CLIENT_IP")
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name = "CREATE_DATE")
	public String getCreateOn() {
		return createOn;
	}

	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

    @Transient
    public String getActionTypeDes() {
        return actionTypeDes;
    }

    public void setActionTypeDes(String actionTypeDes) {
        this.actionTypeDes = actionTypeDes;
    }

    public static void parseObjectForWeb(BusLogInfo busLogInfo){
		switch (busLogInfo.actionType){
			case "1":
				busLogInfo.setActionTypeDes("Fingerprint registration");
				break;
			case "2":
				busLogInfo.setActionTypeDes("Fingerprint Matching");
				break;
			case "3":
				busLogInfo.setActionTypeDes("Fingerprint check");
				break;
			case "4":
				busLogInfo.setActionTypeDes("Fingerprint service login");
				break;
			case "5":
				busLogInfo.setActionTypeDes("Fingerprint Export");
				break;
			case "6":
				busLogInfo.setActionTypeDes("Fingerprint import");
				break;
			case "7":
				busLogInfo.setActionTypeDes("Get the fingerprint service results");
				break;
            case "8":
                busLogInfo.setActionTypeDes("Fingerprint number log off");
                break;
            case "9":
                busLogInfo.setActionTypeDes("Visa staff data cancellation");
                break;
            case "10":
                busLogInfo.setActionTypeDes("Match according to the fingerprint collection number");
                break;
            case "11":
                busLogInfo.setActionTypeDes("Visa personnel information change");
                break;
			default:
				busLogInfo.setActionTypeDes("Unknown type");
				break;
		}
		switch (busLogInfo.resultFlag){
			case "Y":
				busLogInfo.setResultFlag("Success");
				break;
			default:
				busLogInfo.setResultFlag("Failed");
				break;
		}
	}

}
