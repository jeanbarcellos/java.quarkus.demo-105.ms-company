package com.jeanbarcellos.ms.resources;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jeanbarcellos.ms.entities.Department;
import com.jeanbarcellos.ms.services.DepartmentService;

@Path("/departments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    @Inject
    DepartmentService service;

    @GET
    public List<Department> findAll() {
        return service.getAll();
    }

    @Path("/{id}")
    @GET
    public Department findById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @Path("/")
    @POST
    @Transactional
    public Department insert(@Valid Department department) {
        return this.service.insert(department);
    }

}