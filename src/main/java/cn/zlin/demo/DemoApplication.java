package cn.zlin.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@MapperScan("cn.zlin.demo.dao")
@SpringBootApplication(scanBasePackages = "cn.zlin.demo")
@ServletComponentScan
@Configuration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//  单个数据大小
		factory.setMaxFileSize("1024000KB"); // KB,MB
		/// 总上传数据大小
		factory.setMaxRequestSize("1024000KB");
		return factory.createMultipartConfig();
	}


}
