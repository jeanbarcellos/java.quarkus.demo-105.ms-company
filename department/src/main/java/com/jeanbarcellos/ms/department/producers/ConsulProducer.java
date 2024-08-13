package com.jeanbarcellos.ms.department.producers;

import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import com.jeanbarcellos.ms.department.client.EmployeeClient;
import com.jeanbarcellos.ms.department.client.LoadBalancedFilter;
import com.orbitz.consul.Consul;

@ApplicationScoped
public class ConsulProducer {

    @Produces
    Consul consulClient = Consul.builder().build();

    @ConfigProperty(name = "client.employee.uri")
    String employeeUri;

    @Produces
    LoadBalancedFilter filter = new LoadBalancedFilter(consulClient);

    @Produces
    EmployeeClient employeeClient() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(employeeUri);

        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(filter)
                .build(EmployeeClient.class);
    }

}
