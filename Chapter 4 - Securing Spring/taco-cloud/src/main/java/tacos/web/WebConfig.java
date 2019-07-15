package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//indicates that the class has @Bean definition methods. So spring container can process 
// the class and generate spring beans to be used in application
@Configuration

//WebMvcConfigurer has several methods for configuring spring mvc
public class WebConfig implements WebMvcConfigurer {
	
	//webMvcConfigurer provides default implementation for addViewControllers() so we are overriding the method
	@Override
	//ViewControllerRegistry can be used to register one or more view controllers.
	//In this case we use registry to specify home as a view that a request of "/" should be forwarded to 
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
	}
}
