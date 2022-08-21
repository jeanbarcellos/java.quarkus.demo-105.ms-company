package com.jeanbarcellos.ms.organization.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.ms.organization.entities.Organization;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepositoryBase<Organization, Long> {

}
