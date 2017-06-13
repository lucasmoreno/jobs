package br.com.paggi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan( basePackages = { "br.com.paggi.domain" } )
@EnableSwagger2
public class InitAPI extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {
		return application.sources( InitAPI.class );
	}

	public static void main(String[] args) {
		SpringApplication.run(InitAPI.class, args);
	}
	
	/*
	 *	Swagger: localhost:8080/swagger-ui.html
	 *
	 * ## SPRING SWAGGER CONFIGURATION ##
	 */
	@Bean
	public Docket swaggerSettings() {
		return new Docket( DocumentationType.SWAGGER_2 )
				.select().apis( RequestHandlerSelectors.any() )
				.paths( PathSelectors.regex( "/.*" ) )
				.build()
				.pathMapping( "/" )
				.apiInfo( new ApiInfoBuilder()
							.title( "Paggi Api docs" )
							.description( "Rest API documentation" )
							.license( "Test" )
							.version( "1.0" )
							.build() );
	}
}
