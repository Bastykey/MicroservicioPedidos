package cl.duoc.MicroservicioPedidos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Pedidos - DUOC")
                .version("1.0")
                .description("Documentación del Microservicio de Pedidos con Swagger - Springdoc"));
    }
}
//Con esta url se conecta a swagger y se puede ver la documentación de la API
//http://localhost:8089/swagger-ui/index.html