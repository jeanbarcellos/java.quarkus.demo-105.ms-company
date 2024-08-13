package com.jeanbarcellos.project105.organization.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.jeanbarcellos.project105.organization.entities.Organization;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepositoryBase<Organization, Long> {

}
