package com.bigbata.craftsman.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestUtils {
	/**
	 * 验证请求是否ajax请求，不一定通用
	 * @param request
	 * @return
	 */
	public static boolean isAajaxRequest(HttpServletRequest request) {
		return !StringUtils.isEmpty(request.getHeader("x-request-with"))
				&& request.getHeader("x-request-with").equals("xmlhttprequest");
	}

	/**
	 * 获取request中的请求参数，将其封装在map中，如果是多个参数，在value中使用逗号分隔
	 * @param request
	 * @return
	 */
	public static Map getParamsMap(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			map.put(entry.getKey(), ArrayUtil.join(entry.getValue()));
		}
		return map;
	}
}
