package com.iiifi.framework.server.common.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.iiifi.framework.server.common.common.mapper.JsonMapper;


public class ExceptionUtils {
	/**
	 * 将异常信息转换成json返回
	 * @param functionName
	 * @param e
	 * @return
	 */
	public static String errorMessageToJson(String functionName,Exception e){
		Map<String, String> map=new HashMap<String, String>();
		map.put("functionName", functionName);
		map.put("errorMessage", getErrorMessage(e));
		return JsonMapper.toJsonString(map);
	}
	/**
	 * 将异常信息堆栈转换成字符串返回
	 * @param e
	 * @return
	 */
	public static String getErrorMessage(Exception e){
		StringBuffer res=new StringBuffer();
		if(e!=null){
			StackTraceElement[] el=e.getStackTrace();
			for(StackTraceElement item:el){
				res.append(item.toString());
			}	
		}
		return res.toString();
	}
}
