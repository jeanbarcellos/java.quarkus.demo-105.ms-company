package com.jeanbarcellos.project105.department.producers;

import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import com.google.common.net.HostAndPort;
import com.jeanbarcellos.project105.department.client.EmployeeClient;
import com.jeanbarcellos.project105.department.client.LoadBalancedFilter;
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

    @ConfigProperty(name = "client.employee-api.uri")
    protected String employeeUri;

    @Produces
    Consul consulClient() {
        return Consul.builder()
                .withHostAndPort(HostAndPort.fromParts(host, port))
                .build();
    }

    @Produces
    LoadBalancedFilter loadBalancedFilter(Consul consulClient) {
        return new LoadBalancedFilter(consulClient);
    }

    @Produces
    EmployeeClient employeeClient(LoadBalancedFilter loadBalancedFilter) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(employeeUri);

        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(loadBalancedFilter)
                .build(EmployeeClient.class);
    }

}
