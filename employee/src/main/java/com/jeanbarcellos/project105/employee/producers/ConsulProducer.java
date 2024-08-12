package com.jeanbarcellos.project105.employee.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;

/**
 * Service Provider/Producer para
 *
 * Registrar o Client Consult
 *
 * @author Jean Silva de Barcellos
 */
@ApplicationScoped
public class ConsulProducer {

    @ConfigProperty(name = "consul.host")
    protected String host;

    @ConfigProperty(name = "consul.port")
    protected int port;

    @Produces
    Consul consulClient() {
        return Consul.builder()
                .withHostAndPort(HostAndPort.fromParts(host, port))
                .build();

    }

}
