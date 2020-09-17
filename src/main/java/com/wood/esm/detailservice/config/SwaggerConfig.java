package com.wood.esm.detailservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * @author chinn
 *
 */
public class SwaggerConfig implements WebMvcConfigurer
{
	
	@Autowired
	private Environment environment;
	
	@Bean
	public UiConfigurationBuilder uiConfig()
	{
		return UiConfigurationBuilder.builder();
	}
	
	@Bean
	public Docket apiProfile()
	{
		return new Docket( DocumentationType.SWAGGER_2 ).groupName( "profile")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.wood.esm.detailservice.profile.web.controller") )
			    .build()
			    .apiInfo( getApiInfo("Profile API Endpoints",
			    		            "Profile exposed REST endpoints",
			    		            getApiVersion( "1.0" )));
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers( ViewControllerRegistry registry )
	{
		registry.addRedirectViewController("/", "/swagger-ui.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	/**
	 * @param title
	 * @param description
	 * @param version
	 * @return
	 */
	private ApiInfo getApiInfo( String title, String description, String version )
	{
		return new ApiInfoBuilder().title(title).description(description).version(version).build();
	}
	
	private String getApiVersion( String version )
	{
		return environment.getProperty("service.api.version", version);
	}

}
