package com.bigbata.craftsman.securityHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.bigbata.craftsman.util.RequestUtils;

/**
 * 请求验证异常处理方式，两种： 普通的http 请求 - 返回302错误，然后页面跳转到登录页面 ajax 请求 - 返回401 错误
 * 
 * @author hui_ease@163.com
 *
 */
@Component
public class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint;

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		if (RequestUtils.isAajaxRequest(request)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					authException.getMessage());
		} else {
			loginUrlAuthenticationEntryPoint.commence(request, response,
					authException);
		}
	}

	public HttpAuthenticationEntryPoint registerLoginUrl(String loginUrl) {
		this.loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint(
				loginUrl);
		return this;
	}
}
