package com.mindbook;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxin
 */
public class DatabaseClientTest {

    private static DatabaseClient client;

    @BeforeClass
    public static void setup() {
        PostgresqlConnectionConfiguration pgConnectionConfiguration = PostgresqlConnectionConfiguration
                .builder()
                .applicationName("mindbook")
                .database("mindbook")
                .host("localhost")
                .username("postgres")
                .password("postgres")
                .build();
        PostgresqlConnectionFactory pgConnectionFactory = new PostgresqlConnectionFactory(pgConnectionConfiguration);

//        H2ConnectionConfiguration h2ConnectionConfiguration = H2ConnectionConfiguration
//                .builder()
//                .file("~/.h2/mindbook")
//                .username("sa")
//                .password("")
//                .build();
//        H2ConnectionFactory h2ConnectionFactory = new H2ConnectionFactory(h2ConnectionConfiguration);

        client = DatabaseClient.create(pgConnectionFactory);

        client.execute("drop table if exists book")
                .fetch()
                .rowsUpdated()
                .then(client.execute("create table book(id int primary key)")
                        .fetch()
                        .rowsUpdated()
                        .then(client
                                .execute("insert into book(id) values(1)")
                                .fetch()
                                .rowsUpdated())
                ).block();
    }

    @Test
    public void testDatabaseClientSelect() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", 1);
        client.select()
                .from("book")
                .project("id")
                .fetch()
                .all()
                .as(StepVerifier::create)
                .expectNext(resultMap)
                .verifyComplete();
    }

}
