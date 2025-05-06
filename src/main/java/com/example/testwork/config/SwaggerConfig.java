package com.example.testwork.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nikolay Kirilyuk",
                        email = "kirabb123@gmail.com"
                ), description = "Open Api documentation",
                title = "Open Api specification - User Management",
                version = "1.0",
                license = @License(
                        name = "Backend-developer",
                        url = "https://www.linkedin.com/in/nikolay-k-a91635232/"
                )
        ),
        servers = {
                @Server(
                        description = "DEV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "LOCAL",
                        url = "http://localhost:8181"
                )
        }
)
public class SwaggerConfig {
}
