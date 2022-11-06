package com.binar.kampusmerdeka.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Ticketing API")
                        .description("OpenAPI for Ticketing")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .name("Cahyadi Surya Nugraha")
                                        .email("cahyadisn6@gmail.com")
                                        .url("github.com/Chroax")
                        )
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(servers());
    }

    private List<Server> servers() {
        List<Server> servers = new ArrayList<>();

        Server serverDev = new Server();
        serverDev.setUrl("http://localhost:8080/");
        serverDev.setDescription("Main server for Dev");

        servers.add(serverDev);
        return servers;
    }
}