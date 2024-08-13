
package com.jeanbarcellos.ms.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import com.jeanbarcellos.ms.entities.Organization;
import com.jeanbarcellos.ms.repositories.OrganizationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class OrganizationService {

    @Inject
    OrganizationRepository repository;

    public List<Organization> getAll() {
        log.info("Organization find all");

        return repository.findAll().list();
    }

    public Organization getById(Long id) {
        log.info("Organization find: id={}", id);

        return repository.findById(id);
    }

    @Transactional
    public Organization insert(@Valid Organization organization) {
        log.info("Organization insert: {}", organization);

        repository.persist(organization);

        return organization;
    }

    @Transactional
    public Organization update(@Valid Organization organization) {
        log.info("Organization update: {}", organization);

        var entity = this.repository.findByIdOptional(organization.getId())
                .orElseThrow(NotFoundException::new);

        entity.setName(organization.getName())
                .setAddress(organization.getAddress());

        repository.persist(entity);

        return organization;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Organization delete: {}", id);

        var entity = this.repository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        repository.delete(entity);
    }

}
