/**
 * 
 */
package com.bigbata.craftsman.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

/** 
 * 类说明:<br> 
 * 创建时间: 2014年12月11日 下午8:49:50<br> 
 * @author 刘岩松<br> 
 * @email yansong.lau@gmail.com<br>  
 */
public class HomeControllerTest {
	
	@Test
	public void testHomePage() throws Exception {
		
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/")).andExpect(view().name("index"));
	}

}
