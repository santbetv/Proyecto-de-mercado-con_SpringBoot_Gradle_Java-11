package com.santbetv.developermercado.web.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.santbetv.developermercado.web.controller"))
                .build()
                .apiInfo(apiEndPointinfo());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

	
	private ApiInfo apiEndPointinfo() {
		return new ApiInfoBuilder().title("Api de productos")
				.description("Servicios para la consulta de productos de un supermercado")
				.license("MIT")
				.version("1.0.0")
				.licenseUrl("https://opensource.org/faq#osd")
				.build();
	}
}
