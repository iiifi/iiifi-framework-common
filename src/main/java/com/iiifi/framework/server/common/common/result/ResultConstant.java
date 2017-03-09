package com.iiifi.framework.server.common.common.result;

/**
 * api返回常用状态
 *
 */
public class ResultConstant {

	/**
	 * 成功返回信息码
	 */
	public final static String APP_RETURN_SUCESS = "0";
	/**
	 * 成功返回信息
	 */
	public final static String APP_RETURN_SUCESS_MESSAGE = "success";
	/**
	 * 参数错误返回码
	 */
	public final static String APP_RETURN_ARGS_ERROR_CODE = "10000001";
	/**
	 * 参数错误返回信息
	 */
	public final static String APP_RETURN_ARGS_ERROR_MESSAGE = "参数错误";
	/**
	 * 未查询到数据返回码
	 */
	public final static String APP_RETURN_NOT_DATA_CODE = "10000002";
	
	/**
	 * 未查询到数据返回信息
	 */
	public final static String APP_RETURN_NOT_DATA_MESSAGE = "未查询到数据";
	
	/**
	 * 异常信息返回信息状态码
	 */
	public final static String APP_RETURN_EXCEPTION = "10000003";
	/**
	 * 异常信息返回信息
	 */
	public final static String APP_RETURN_EXCEPTION_MESSAGE = "网络错误";
	/**
	 * 数据已经存在返回码
	 */
	public final static String APP_RETURN_EXISTS_CODE = "10000004";
	
	/**
	 * 数据已经存在信息
	 */
	public final static String APP_RETURN_EXISTS_CODE_MESSAGE = "数据已经存在";
	
}
