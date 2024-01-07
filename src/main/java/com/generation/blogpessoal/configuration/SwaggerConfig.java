package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;


@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal")
						.description("Projeto Blog Pessoal - Natan Oliveira Santos")
						.version("v0.0.1")
						.license(new License()
								.name("Generation Brasil")
								.url("https://brazil.generation.org"))
						.contact(new Contact()
								.name("Natan Oliveira Santos")
								.url("https://github.com/NatanOSantos")
								.email("natanoliveirasantosmed@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("GitHub")
						.url("https://github.com/NatanOSantos"));
	}

	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
					.forEach(operation -> {
						
						ApiResponses apiResponse = operation.getResponses();
						
						apiResponse.addApiResponse("200", createApiResponse("Sucesso"));
						apiResponse.addApiResponse("201", createApiResponse("Objeto persistido"));
						apiResponse.addApiResponse("204", createApiResponse("Objeto excluído"));
						apiResponse.addApiResponse("400", createApiResponse("Erro na requisição"));
						apiResponse.addApiResponse("401", createApiResponse("Acesso não autorizado"));
						apiResponse.addApiResponse("403", createApiResponse("Acesso Proibido"));
						apiResponse.addApiResponse("404", createApiResponse("Não Encontrado"));
						apiResponse.addApiResponse("500", createApiResponse("Erro na aplicação"));
						
					}));
			};
	}

	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
	}
	
}
