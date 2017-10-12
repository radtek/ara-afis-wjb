package com.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("config")
public class SystemConfig {

	@Value("${WS_IP}")
	private String webServiceIp;

	@Value("${WS_PORT}")
	private String webServicePort;

	@Value("${WS_PID}")
	private String webServicePid;

	@Value("${WS_NAMESPACE}")
	private String webServiceNameSpace;

	@Value("${tac.exportflag}")
	private String exportFlag;

	public String getWebServiceIp() {
		return webServiceIp;
	}

	public String getWebServicePort() {
		return webServicePort;
	}

	public String getWebServicePid() {
		return webServicePid;
	}

	public String getWebServiceNameSpace() {
		return webServiceNameSpace;
	}

	public String getExportFlag() {
		return exportFlag;
	}

}
