package com.iiifi.framework.server.common.common.result;

import java.io.Serializable;

public class CommonParameters implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4129507529491098275L;
	
	private String requestId; //全链路id
	private String token;//请求token
	private String userId;//用户id
	private String deviceType;//设备类型0:android 1:ios
	private String deviceInfo;//设备型号
	private String deviceNum;//设备号
	private String platform;//请求来源  0.app1.微信公众号2.网站端
	private String sysVersion;//当前系统版本号
	private String pageName;//发起该次请求页面名称
	
	public static final String REQUEST_ID="requestId";
	public static final String TOKEN="token";
	public static final String USER_ID="userId";
	public static final String DEVICE_TYPE="deviceType";
	public static final String DEVICE_INFO="deviceInfo";
	public static final String DEVICE_NUM="deviceNum";
	public static final String PLATFORM="platform";
	public static final String SYS_VERSION="sysVersion";
	public static final String PAGE_NAME="pageName";
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getSysVersion() {
		return sysVersion;
	}
	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
}
