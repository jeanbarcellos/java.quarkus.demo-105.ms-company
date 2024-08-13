package com.jeanbarcellos.ms.organization.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.orbitz.consul.Consul;

@ApplicationScoped
public class ConsulProducer {

    @Produces
    Consul consulClient = Consul.builder().build();

}
