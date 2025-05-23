package kc.alabuga.space.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("IP Whitelist Admin API")
                        .description("Сервис для управления белым списком IP-адресов в Keycloak")
                        .version("1.0.0"));
    }
}

