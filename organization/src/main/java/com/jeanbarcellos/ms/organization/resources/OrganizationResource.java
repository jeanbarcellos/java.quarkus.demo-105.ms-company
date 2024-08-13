package com.jeanbarcellos.ms.organization.resources;

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

import com.jeanbarcellos.ms.organization.entities.Organization;
import com.jeanbarcellos.ms.organization.services.OrganizationService;

@Path("/organizations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationResource {

    @Inject
    OrganizationService service;

    @GET
    public Response getAll() {
        return Response.ok(this.service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(this.service.getById(id)).build();
    }

    @GET
    @Path("/{id}/with-departments")
    public Response getByIdWithDepartments(@PathParam("id") Long id) {
        return Response.ok(this.service.getByIdWithDepartments(id)).build();
    }

    @GET
    @Path("/{id}/with-departments-and-employees")
    public Response getByIdWithDepartmentsAndEmployees(@PathParam("id") Long id) {
        return Response.ok(this.service.getByIdWithDepartmentsAndEmployees(id)).build();
    }

    @GET
    @Path("/{id}/with-employees")
    public Response getByIdWithEmployees(@PathParam("id") Long id) {
        return Response.ok(this.service.getByIdWithEmployees(id)).build();
    }

    @POST
    @Path("/")
    @Transactional
    public Response insert(@RequestBody Organization organization) {
        return Response.ok(this.service.insert(organization)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody Organization organization) {
        return Response.ok(this.service.update(organization.setId(id))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        this.service.delete(id);
        return Response.noContent().build();
    }

}
