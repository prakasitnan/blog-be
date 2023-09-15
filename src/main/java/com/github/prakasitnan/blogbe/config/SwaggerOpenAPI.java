package com.github.prakasitnan.blogbe.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "Bearer Authentication",type=SecuritySchemeType.HTTP, bearerFormat="JWT", scheme="bearer")
public class SwaggerOpenAPI {
    @Bean
    public OpenAPI springOpenAPi() {
        return new OpenAPI().info(new Info().title("Blog API")
                .description("Spring blog be-api sample application")
                .version("v0.1.0")
        );


    }

}
