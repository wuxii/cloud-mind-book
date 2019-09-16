package com.mindbook.config

import io.vertx.pgclient.PgConnectOptions
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.Pool
import io.vertx.sqlclient.PoolOptions
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SqlClientConfig {

    @Bean
    fun sqlPool(): Pool {
        val connectOptions = PgConnectOptions()
                .setUser("postgres")
                .setPassword("postgres")
                .setHost("localhost")
                .setPort(5432)
                .setDatabase("mindbook")
        val poolOptions = PoolOptions()
                .setMaxSize(100)
                .setMaxWaitQueueSize(100)
        return PgPool.pool(connectOptions, poolOptions)
    }

}