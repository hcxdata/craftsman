package com.bigbata.craftsman.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

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
}
