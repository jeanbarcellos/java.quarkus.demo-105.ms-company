
package com.jeanbarcellos.ms.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import com.jeanbarcellos.ms.entities.Employee;
import com.jeanbarcellos.ms.repositories.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    public List<Employee> getAll() {
        log.info("Employee find all");

        return repository.findAll().list();
    }

    public Employee getById(Long id) {
        log.info("Employee find: id={}", id);

        return repository.findById(id);
    }

    @Transactional
    public Employee insert(@Valid Employee employee) {
        log.info("Employee insert: {}", employee);

        repository.persist(employee);

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

        repository.persist(entity);

        return employee;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Employee delete: {}", id);

        var entity = this.repository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        repository.delete(entity);
    }

}
