/**
 *
 */
package com.iiifi.framework.server.common.common.config;

import java.util.Map;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import com.iiifi.framework.server.common.common.utils.PropertiesLoader;
import com.iiifi.framework.server.common.common.utils.StringUtils;

/**
 * 全局配置类
 *
 * @author vk
 * @version 2016-01-15
 */
public class IiifiConstant {

	/**
	 * 当前对象实例
	 */
	private static IiifiConstant iiifiConstant = new IiifiConstant();

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("classpath:application.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";

	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";

	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";

	/**
	 * 获取当前对象实例
	 */
	public static IiifiConstant getInstance() {
		return iiifiConstant;
	}

	/**
	 * 获取配置
	 *
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * 页面获取常量
	 *
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return IiifiConstant.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 *
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)) {
			try {
				dir = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if (!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}

}
