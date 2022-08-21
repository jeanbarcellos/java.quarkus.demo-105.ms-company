package com.jeanbarcellos.ms.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import com.jeanbarcellos.ms.entities.Department;
import com.jeanbarcellos.ms.repositories.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class DepartmentService {

    @Inject
    DepartmentRepository repository;

    public List<Department> getAll() {
        log.info("Department find all");

        return repository.findAll().list();
    }

    public Department getById(Long id) {
        log.info("Department find: id={}", id);

        return repository.findById(id);
    }

    @Transactional
    public Department insert(@Valid Department department) {
        log.info("Department insert: {}", department);

        repository.persist(department);

        return department;
    }

    @Transactional
    public Department update(@Valid Department department) {
        log.info("Department update: {}", department);

        var entity = this.repository.findByIdOptional(department.getId())
                .orElseThrow(NotFoundException::new);

        entity.setName(department.getName())
                .setOrganizationId(department.getOrganizationId())
                .setEmployees(department.getEmployees());

        repository.persist(entity);

        return department;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Department delete: {}", id);

        var entity = this.repository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        repository.delete(entity);
    }

}
