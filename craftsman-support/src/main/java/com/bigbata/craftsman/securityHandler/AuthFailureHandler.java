package com.bigbata.craftsman.securityHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.bigbata.craftsman.util.RequestUtils;

/**
 * security 权限验证失败处理程序，添加对ajax访问的异常处理
 * 
 * @author hui_ease@163.com
 */
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		if (RequestUtils.isAajaxRequest(request)) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			PrintWriter writer = response.getWriter();
			writer.write(exception.getMessage());
			writer.flush();

		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
