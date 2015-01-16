package com.bigbata.craftsman.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * 系统初始化启动程序
 * 
 * @author hui_ease@163.com
 *
 */
public class AppInitializer implements WebApplicationInitializer {

	private static final String CONFIG_LOCATION = "com.bigbata.craftsman.config";
	private static final String WEB_MAPPING_URL = "/web/*";
	private static final String API_MAPPING_URL = "/api/*";

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		WebApplicationContext context = getContext();

		// 监听地址
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(WEB_MAPPING_URL);
		dispatcher.addMapping(API_MAPPING_URL);

		// security 过滤配置
		FilterRegistration.Dynamic filter = servletContext.addFilter(
				"springSecurityFilterChain", new DelegatingFilterProxy(
						"springSecurityFilterChain"));
		filter.addMappingForUrlPatterns(null, false, WEB_MAPPING_URL);
		filter.addMappingForUrlPatterns(null, false, API_MAPPING_URL);

	}

	/**
	 * 加载注解程序路径
	 * 
	 * @return
	 */
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION);
		return context;
	}

}
