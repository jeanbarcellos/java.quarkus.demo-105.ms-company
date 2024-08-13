package com.jeanbarcellos.ms.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.ms.entities.Organization;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepositoryBase<Organization, Long> {

}
