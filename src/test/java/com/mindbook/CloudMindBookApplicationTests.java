package com.mindbook;

import com.mindbook.word.domain.Word;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudMindBookApplicationTests {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Test
    public void contextLoads() {
        DatabaseClient client = DatabaseClient.create(connectionFactory);

        Mono<Word> execResult = client.execute("select * from mindbook_word where id = $1")
                .as(Word.class)
                .bind(0, 1)
                .fetch()
                .one();

        StepVerifier.create(execResult).expectComplete().verify(Duration.ofSeconds(10));
    }

}
