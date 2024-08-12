package com.jeanbarcellos.project105.department.lifecycle;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegistration;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class ConsulLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsulLifecycle.class);

    @Inject
    protected Consul consulClient;

    @ConfigProperty(name = "quarkus.application.name")
    protected String appName;

    @ConfigProperty(name = "quarkus.application.version")
    protected String appVersion;

    @ConfigProperty(name = "app.address")
    protected String appAddress;

    private int appPort;

    private String instanceId;

    void onStart(@Observes StartupEvent ev) {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(() -> {
            try {
                this.instanceId = this.appName + "-" + UUID.randomUUID();
                this.appPort = Integer.parseInt(System.getProperty("quarkus.http.port"));

                ImmutableRegistration registration = ImmutableRegistration.builder()
                        .id(this.instanceId)
                        .name(this.appName)
                        .address(this.appAddress)
                        .port(this.appPort)
                        .putMeta("version", this.appVersion)
                        .build();

                consulClient.agentClient().register(registration);

                LOGGER.info("Intance registered: id={}, name={}, address={}, port {}",
                        this.instanceId, this.appName, this.appAddress, this.appPort);
            } catch (Exception exception) {
                LOGGER.error("Caught exception in ScheduledExecutorService. StackTrace", exception);
            }

        }, 5, TimeUnit.SECONDS);
    }

    void onStop(@Observes ShutdownEvent ev) {
        consulClient.agentClient().deregister(this.instanceId);

        LOGGER.info("Instance de-registered: id={}", this.instanceId);
    }

}
