package br.com.maurigvs.surveyapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SurveyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyApiApplication.class, args);
	}

	@Bean
	public OpenAPI openApiInfo() {
		return new OpenAPI()
				.info(new Info()
						.title("Survey API")
						.description("A simple Survey application")
						.version("v2.0")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
						.description("More projects from the Developer")
						.url("https://github.com/maurigvs"));
	}
}