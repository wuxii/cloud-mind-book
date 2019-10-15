package com.mindbook;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudMindBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMindBookApplication.class, args);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration connectionConfiguration = PostgresqlConnectionConfiguration
                .builder()
                .applicationName("mindbook")
                .database("mindbook")
                .host("localhost")
                .username("postgres")
                .password("postgres")
                .build();
        return new PostgresqlConnectionFactory(connectionConfiguration);
    }

}
