package com.mindbook

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.kotlin.core.deployVerticleAwait
import io.vertx.kotlin.core.undeployAwait
import io.vertx.spi.cluster.hazelcast.ConfigUtil
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

object VertxApplication {

    private var log: Logger = LoggerFactory.getLogger(VertxApplication::class.java)

    private var APPLICATION_VERTICLES = mutableMapOf<KClass<*>, List<String>>()

    private var VERTX: Vertx

    init {
        val clusterManager = HazelcastClusterManager(ConfigUtil.loadConfig())
        VERTX = Vertx.vertx(VertxOptions().setClusterManager(clusterManager))
    }

    fun boot(applicationClass: KClass<*>, args: Array<String>) {
        val verticles = applicationClass.findAnnotation<Verticles>()
        if (verticles == null) {
            log.warn("{} not vertx application", applicationClass)
            return
        }

        if (verticles.classes.isEmpty()) {
            log.info("{} not verticle classes", applicationClass)
            return
        }

        runBlocking {
            val deploymentIds = mutableListOf<String>()
            APPLICATION_VERTICLES[applicationClass] = deploymentIds
            launch {
                val deploymentOptions = loadDeploymentOptions()
                for (verticle in verticles.classes) {
                    val deploymentId = VERTX.deployVerticleAwait(verticle.qualifiedName!!, deploymentOptions)
                    deploymentIds.add(deploymentId)
                    log.info("deploy verticle {}, id: {}", verticle.qualifiedName, deploymentId)
                }
            }.join()
        }
    }

    fun stop(applicationClass: KClass<*>) {
        val deploymentIds = APPLICATION_VERTICLES[applicationClass]
        runBlocking {
            launch {
                if (deploymentIds != null) {
                    for (deploymentId in deploymentIds) {
                        VERTX.undeployAwait(deploymentId)
                    }
                }
            }
        }
    }

    private suspend fun loadDeploymentOptions(): DeploymentOptions {
        return DeploymentOptions()
    }

}
