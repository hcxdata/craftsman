/**
 *
 */
package com.bigbata.craftsman.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 类说明:<br>
 * 创建时间: 2014年12月11日 上午11:30:05<br>
 *
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
@Configuration
@ComponentScan(basePackages = {"com.bigbata.craftsman"},
        excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@EnableAspectJAutoProxy
public class RootConfig {

}
