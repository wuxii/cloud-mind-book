package com.mindbook.config

import io.vertx.mysqlclient.MySQLConnectOptions
import io.vertx.mysqlclient.MySQLPool
import io.vertx.sqlclient.Pool
import io.vertx.sqlclient.PoolOptions
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SqlClientConfig {

    @Bean
    fun sqlPool(): Pool {
        val connectOptions = MySQLConnectOptions()
                .setUser("root")
                .setPassword(null)
                .setHost("localhost")
                .setPort(3306)
                .setDatabase("mindbook")
        val poolOptions = PoolOptions()
                .setMaxSize(100)
                .setMaxWaitQueueSize(100)
        return MySQLPool.pool(connectOptions, poolOptions)
    }

}