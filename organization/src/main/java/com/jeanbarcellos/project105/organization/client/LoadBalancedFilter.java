package com.jeanbarcellos.project105.organization.client;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;

public class LoadBalancedFilter implements ClientRequestFilter {

    private Consul consulClient;

    private AtomicInteger counter = new AtomicInteger();

    public LoadBalancedFilter(Consul consulClient) {
        this.consulClient = consulClient;
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        URI uri = requestContext.getUri();

        HealthClient healthClient = consulClient.healthClient();

        List<ServiceHealth> instances = healthClient.getHealthyServiceInstances(uri.getHost()).getResponse();

        ServiceHealth instance = getInstance(instances);

        URI newUri = createUri(uri, instance);

        requestContext.setUri(newUri);
    }

    private ServiceHealth getInstance(List<ServiceHealth> instances) {
        Integer random = selectInstance(instances.size());
        return instances.get(random);
    }

    private Integer selectInstance(Integer qtd) {
        return counter.getAndAccumulate(qtd, (cur, n) -> cur >= n - 1 ? 0 : cur + 1);
    }

    private URI createUri(URI uri, ServiceHealth instance) {
        return UriBuilder.fromUri(uri)
                .host(instance.getService().getAddress())
                .port(instance.getService().getPort())
                .build();
    }

}
