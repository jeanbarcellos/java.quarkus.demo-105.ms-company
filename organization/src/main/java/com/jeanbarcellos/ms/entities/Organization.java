package com.jeanbarcellos.ms.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "\"organization\"")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @Transient
    private List<Department> departments = new ArrayList<>();

    @Transient
    private List<Employee> employees = new ArrayList<>();

}
