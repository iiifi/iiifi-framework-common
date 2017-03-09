package com.iiifi.framework.server.common.common.result;

import java.util.Map;
import org.apache.log4j.Logger;
import com.google.common.collect.Maps;
import com.iiifi.framework.server.common.common.mapper.JsonMapper;

/**返回结果的封装
 * @author xiaoyu
 *2016年5月12日
 */
public class ResponseMapper {

	protected static Logger logger = Logger.getLogger("ioLog");
	
	private String code = ResultConstant.APP_RETURN_SUCESS;
	private String message = ResultConstant.APP_RETURN_SUCESS_MESSAGE;
	private Long count;//分页查询的总条数
	private Object datas;//返回请求结果
	private String errMessage;//封装错误信息
	private Object commonResult;//封装公共返回值
	
	public String getResultJson()  {
		Map<String, Object> map = Maps.newHashMap();
		map.put("code", code);
		map.put("message", message);
		if(count != null) {
			map.put("count", count);			
		}
		if (datas != null) {
			map.put("datas", datas);
		}
		if (errMessage != null) {
			map.put("errMessage", errMessage);
		}
		if (commonResult != null) {
			map.put("commonResult", commonResult);
		}
		String res=JsonMapper.toJsonString(map);
		logger.info(res);
		return res;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}
	
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public Object getCommonResult() {
		return commonResult;
	}

	public void setCommonResult(Object commonResult) {
		this.commonResult = commonResult;
	}
}
