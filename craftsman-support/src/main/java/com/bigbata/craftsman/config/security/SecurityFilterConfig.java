package com.bigbata.craftsman.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建自定义的权限验证过滤器，用于从数据库中获取验证信息对用户的访问权限进行控制
 * Created by lixianghui on 15/9/9.
 */
@Configuration
public class SecurityFilterConfig {

    @Bean
    public AccessDecisionManager affirmativeBased() {
        List<AccessDecisionVoter> voters = new ArrayList<AccessDecisionVoter>();
        voters.add(new RoleVoter());
        voters.add(new AuthenticatedVoter());
        AccessDecisionManager result = new AffirmativeBased(voters);

        return result;
    }

    @Bean
    @Autowired
    public FilterSecurityInterceptor filterSecurityInterceptor(SecurityConfig securityAdapterConfig,
                                                               AccessDecisionManager affirmativeBased, DataBaseSecurityMetadataSource dataBaseSecurityMetadataSource) throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(securityAdapterConfig.authenticationManagerBean());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased);
        filterSecurityInterceptor.setSecurityMetadataSource(dataBaseSecurityMetadataSource);
        filterSecurityInterceptor.setValidateConfigAttributes(true);
        return filterSecurityInterceptor;
    }

}
