package com.jeanbarcellos.ms.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.jeanbarcellos.ms.entities.Department;
import com.jeanbarcellos.ms.repositories.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class DepartmentService {

    @Inject
    DepartmentRepository repository;

    public List<Department> getAll() {
        log.info("Department find");

        return repository.findAll().list();
    }

    public Department getById(Long id) {
        log.info("Department find: id={}", id);

        return repository.findById(id);
    }

    @Transactional
    public Department insert(@Valid Department department) {
        log.info("Department created: {}", department);

        repository.persist(department);

        return department;
    }

}
