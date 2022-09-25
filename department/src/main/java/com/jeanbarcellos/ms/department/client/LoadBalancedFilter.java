package com.jeanbarcellos.ms.department.client;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;

public class LoadBalancedFilter implements ClientRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadBalancedFilter.class);

    private Consul consulClient;

    private AtomicInteger counter = new AtomicInteger();

    public LoadBalancedFilter(Consul consulClient) {
        this.consulClient = consulClient;
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        URI uri = requestContext.getUri();

        LOGGER.info("Get instances of '{}'.", uri.getHost());

        HealthClient healthClient = consulClient.healthClient();

        List<ServiceHealth> instances = healthClient.getHealthyServiceInstances(uri.getHost()).getResponse();

        this.logInstances(instances);

        if (instances.isEmpty()) {
            LOGGER.error("The '{}' instance not found.", uri.getHost());
        }

        ServiceHealth instance = getInstance(instances);

        URI newUri = createUri(uri, instance);

        requestContext.setUri(newUri);
    }

    private void logInstances(List<ServiceHealth> instances) {
        LOGGER.info("Instances:");

        var index = 0;
        for (ServiceHealth instance : instances) {
            LOGGER.info("  index={} uri={}:{}",
                    index,
                    instance.getService().getAddress(),
                    instance.getService().getPort());
            index++;
        }
    }

    private ServiceHealth getInstance(List<ServiceHealth> instances) {
        Integer random = selectInstance(instances.size());

        var instance = instances.get(random);

        LOGGER.info("Selected:");
        LOGGER.info("  index={} uri={}:{}",
                random, instance.getService().getAddress(), instance.getService().getPort());

        return instance;
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
