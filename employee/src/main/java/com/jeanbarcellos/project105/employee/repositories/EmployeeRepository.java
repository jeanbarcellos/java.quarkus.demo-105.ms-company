package com.jeanbarcellos.project105.employee.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.project105.employee.entities.Employee;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepositoryBase<Employee, Long> {

    public List<Employee> findByDepartment(Long departmentId) {
        return find("departmentId", departmentId).list();
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return find("organizationId", organizationId).list();
    }

}
