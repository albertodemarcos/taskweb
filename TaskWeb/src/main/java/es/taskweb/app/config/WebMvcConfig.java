package es.taskweb.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		//registry.addViewController("/login").setViewName("login");
		//registry.addViewController("/index").setViewName("index");
		
		//registry.addViewController("/home").setViewName("home");
		//registry.addViewController("/").setViewName("home");
		//registry.addViewController("/hello").setViewName("hello");
		//registry.addViewController("/login").setViewName("login");
	}
	
	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver(){
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(webApplicationContext);
	    templateResolver.setOrder(9);
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix("");
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine springTemplateEngine= new SpringTemplateEngine();
	    springTemplateEngine.setTemplateResolver(thymeleafTemplateResolver());
	    springTemplateEngine.setEnableSpringELCompiler(true);
	    return springTemplateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(){
	    final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setViewNames(new String[] {"*.html"});
	    viewResolver.setExcludedViewNames(new String[] {"*.jsp"});
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("UTF-8");
	    return viewResolver;
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver(){
	    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setOrder(10);
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix("");
	    viewResolver.setViewNames("*.jsp");
	    return viewResolver;
	}
	
	
}
