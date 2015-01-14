package com.bigbata.craftsman.securityHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bigbata.craftsman.util.RequestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * security 权限验证成功处理程序，添加对ajax访问的异常处理
 * 
 * @author hui_ease@163.com
 *
 */
@Component
public class AuthSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	public ObjectMapper authSuccessHandler() {
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		// Add supported media type returned by BI API.
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(new MediaType("text", "plain"));
		supportedMediaTypes.add(new MediaType("application", "json"));
		jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonMessageConverter.getObjectMapper();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		if (RequestUtils.isAajaxRequest(request)) {
			PrintWriter writer = response.getWriter();
			authSuccessHandler().writeValue(writer, userDetails.getUsername());
			writer.flush();
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
