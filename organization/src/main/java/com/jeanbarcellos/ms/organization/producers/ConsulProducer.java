package com.jeanbarcellos.ms.organization.producers;

import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import com.jeanbarcellos.ms.organization.client.DepartmentClient;
import com.jeanbarcellos.ms.organization.client.EmployeeClient;
import com.jeanbarcellos.ms.organization.client.LoadBalancedFilter;
import com.orbitz.consul.Consul;

@ApplicationScoped
public class ConsulProducer {

    @Produces
    Consul consulClient = Consul.builder().build();

    @ConfigProperty(name = "client.employee-api.uri")
    String employeeUri;

    @ConfigProperty(name = "client.department-api.uri")
    String departmentUri;

    @Produces
    LoadBalancedFilter employeeFilter = new LoadBalancedFilter(consulClient);

    @Produces
    LoadBalancedFilter departmentFilter = new LoadBalancedFilter(consulClient);

    @Produces
    EmployeeClient employeeClient() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(employeeUri);

        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(employeeFilter)
                .build(EmployeeClient.class);
    }

    @Produces
    DepartmentClient departmentClient() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(departmentUri);

        return RestClientBuilder.newBuilder()
                .baseUri(builder.build())
                .register(departmentFilter)
                .build(DepartmentClient.class);
    }

}
