
package com.infosys.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class FreemarkerConfig {
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/views");
		
		Properties settings = new Properties();
		settings.setProperty("default_encoding", "utf-8");
		settings.setProperty("number_format", "0.##");
		configurer.setFreemarkerSettings(settings);
		
		return configurer;
	}
	
	
	/**

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer(ShiroTag shiroTag) {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/views");
		Map<String, Object> variables = new HashMap<String, Object>(1);
		variables.put("shiro", shiroTag);
		configurer.setFreemarkerVariables(variables);

		Properties settings = new Properties();
		settings.setProperty("default_encoding", "utf-8");
		settings.setProperty("number_format", "0.##");
		configurer.setFreemarkerSettings(settings);
		return configurer;
	}
	**/

}
