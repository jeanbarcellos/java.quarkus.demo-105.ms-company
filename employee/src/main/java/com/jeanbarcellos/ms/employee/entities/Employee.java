package com.jeanbarcellos.ms.employee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "\"employee\"")
public class Employee {

    @Id
    @GeneratedValue(generator = "employee_id_seq_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "employee_id_seq_generator", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, columnDefinition = "BIGSERIAL")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @NotNull
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Min(1)
    @Max(100)
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotBlank
    @Column(name = "position", nullable = false)
    private String position;
}