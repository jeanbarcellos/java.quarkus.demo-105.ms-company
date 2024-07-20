
package com.jeanbarcellos.project105.employee.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import com.jeanbarcellos.project105.employee.entities.Employee;
import com.jeanbarcellos.project105.employee.repositories.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    public List<Employee> getAll() {
        log.info("Employee find all");

        return this.repository.findAll().list();
    }

    public Employee getById(Long id) {
        log.info("Employee find: id={}", id);

        return this.repository.findById(id);
    }

    public List<Employee> getByDepartment(Long departmentId) {
        log.info("Employee find: departmentId={}", departmentId);

        return this.repository.findByDepartment(departmentId);
    }

    public List<Employee> getByOrganization(Long organizationId) {
        log.info("Employee find: organizationId={}", organizationId);

        return this.repository.findByOrganization(organizationId);
    }

    @Transactional
    public Employee insert(@Valid Employee employee) {
        log.info("Employee insert: {}", employee);

        this.repository.persist(employee);

        return employee;
    }

    @Transactional
    public Employee update(@Valid Employee employee) {
        log.info("Employee update: {}", employee);

        var entity = this.repository.findByIdOptional(employee.getId())
                .orElseThrow(NotFoundException::new);

        entity.setName(employee.getName())
                .setOrganizationId(employee.getOrganizationId())
                .setDepartmentId(employee.getDepartmentId())
                .setAge(employee.getAge())
                .setPosition(employee.getPosition());

        this.repository.persist(entity);

        return employee;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Employee delete: {}", id);

        var entity = this.repository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        this.repository.delete(entity);
    }

}
