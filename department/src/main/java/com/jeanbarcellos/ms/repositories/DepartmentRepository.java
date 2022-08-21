package com.jeanbarcellos.ms.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.ms.entities.Department;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepositoryBase<Department, Long> {

    public List<Department> findByOrganization(Long organizationId) {
        return find("organizationId", organizationId).list();
    }

}
