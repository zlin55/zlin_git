package cn.zlin.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  // 这个方法是用来配置静态资源的，比如html，js，css，等等
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  //registry.addResourceHandler("/files/**").addResourceLocations("file:C:/Users/zlin/Tools/tempFile/");
	  registry.addResourceHandler("/files/**").addResourceLocations("file:/home/demoFile/");
  }
 

  
}
