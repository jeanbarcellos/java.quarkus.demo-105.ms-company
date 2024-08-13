package com.jeanbarcellos.ms.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.jeanbarcellos.ms.entities.Employee;
import com.jeanbarcellos.ms.services.EmployeeService;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService service;

    @GET
    public Response getAll() {
        return Response.ok(this.service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(this.service.getById(id)).build();
    }

    @Path("/department/{departmentId}")
    @GET
    public Response getByDepartment(@PathParam("departmentId") Long departmentId) {
        return Response.ok(this.service.getByDepartment(departmentId)).build();
    }

    @Path("/organization/{organizationId}")
    @GET
    public Response getByOrganization(@PathParam("organizationId") Long organizationId) {
        return Response.ok(this.service.getByOrganization(organizationId)).build();
    }

    @POST
    @Path("/")
    @Transactional
    public Response insert(@RequestBody Employee employee) {
        return Response.ok(this.service.insert(employee)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody Employee employee) {
        return Response.ok(this.service.update(employee.setId(id))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        this.service.delete(id);
        return Response.noContent().build();
    }

}
