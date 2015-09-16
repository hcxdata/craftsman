package com.bigbata.craftsman.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.bigbata.craftsman.securityHandler.AuthFailureHandler;
import com.bigbata.craftsman.securityHandler.AuthSuccessHandler;
import com.bigbata.craftsman.securityHandler.HttpAuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 类说明:系统权限的总体配置类<br>
 * 创建时间: 2014年12月12日 下午3:44:11<br>
 *
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    SecurityUserService securityService;
    @Autowired
    FilterSecurityInterceptor filterSecurityInterceptor;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(securityService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(
                        authenticationEntryPoint
                                .registerLoginUrl("/web/login/form"))
                .and()
                .authorizeRequests()
                        //放开的权限
                .antMatchers("/web/login", "/web/login/form", "/web/logout",
                        "/web/signup", "/web/about").permitAll()
                //登录页面
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/web/login/form")
                        //登录地址
                .loginProcessingUrl("/web/login")
                        //验证成功的处理
                .successHandler(authSuccessHandler)
                        //验证失败的处理
                .failureHandler(authFailureHandler)
                        //退出地址
                .and().logout()
                .logoutUrl("/web/logout").permitAll().and()
                //通过数据库确定的权限访问控制信息
                .addFilter(filterSecurityInterceptor)
        ;
    }
}
