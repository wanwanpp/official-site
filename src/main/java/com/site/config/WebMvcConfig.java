package com.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/share").setViewName("share");
		registry.addViewController("/403").setViewName("403");
		registry.addViewController("/teamNotice").setViewName("teamNotice");
		registry.addViewController("/Web开发.html").setViewName("Web开发");
		registry.addViewController("/Web开发成员.html").setViewName("Web开发成员");
		registry.addViewController("/Web前端.html").setViewName("Web前端");
		registry.addViewController("/Web前端成员.html").setViewName("Web前端成员");
		registry.addViewController("/嵌入式底层开发.html").setViewName("嵌入式底层开发");
		registry.addViewController("/嵌入式开发成员.html").setViewName("嵌入式开发成员");
		registry.addViewController("/Android开发.html").setViewName("Android开发");
		registry.addViewController("/Android开发成员.html").setViewName("Android开发成员");
		registry.addViewController("/毕业生.html").setViewName("毕业生");
		registry.addViewController("/首页.html").setViewName("首页");
		registry.addViewController("/awards.html").setViewName("awards");
		registry.addViewController("/projects.html").setViewName("projects");
	}


}
