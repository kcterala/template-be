package dev.kcterala.template.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI openAPI() {
        final Info info = new Info()
                .title("Template API")
                .version("1.0")
                .description("Template API Documentation")
                .contact(new Contact()
                        .name("kcterala")
                        .url("https://kcterala.dev")
                        .email("kcterala@gmail.com"));

        final Components components = new Components()
                .addResponses("400", new ApiResponse().description("Bad Request"))
                .addResponses("401", new ApiResponse().description("Unauthorized"))
                .addResponses("403", new ApiResponse().description("Forbidden"))
                .addResponses("404", new ApiResponse().description("Not Found"))
                .addResponses("500", new ApiResponse().description("Internal Server Error"));

        return new OpenAPI()
                .components(components)
                .info(info);
    }
}
