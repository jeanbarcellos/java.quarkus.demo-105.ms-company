package com.jeanbarcellos.project105.department.client;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jeanbarcellos.project105.department.entities.Employee;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface EmployeeClient {

    @GET
    @Path("/department/{departmentId}")
    public List<Employee> getByDepartment(@PathParam("departmentId") Long departmentId);

}
