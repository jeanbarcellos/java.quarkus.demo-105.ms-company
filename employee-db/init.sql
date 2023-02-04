-- create sequence employee_id_seq start 1 increment 1;
create table "employee" (id BIGSERIAL not null, age int4 not null, department_id int8 not null, name varchar(255) not null, organization_id int8 not null, position varchar(255) not null, primary key (id));

insert into "employee" (id, organization_id, department_id, name, age, position) values (1, 1, 1, 'Mateus', 30, 'Nivel 1');
insert into "employee" (id, organization_id, department_id, name, age, position) values (2, 1, 1, 'Marcos', 31, 'Nivel 2');
insert into "employee" (id, organization_id, department_id, name, age, position) values (3, 1, 2, 'Lucas', 32, 'Nivel 1');
insert into "employee" (id, organization_id, department_id, name, age, position) values (4, 1, 2, 'João', 33, 'Nivel 2');
insert into "employee" (id, organization_id, department_id, name, age, position) values (5, 2, 3, 'Pedro', 34, 'Nivel 1');
insert into "employee" (id, organization_id, department_id, name, age, position) values (6, 2, 3, 'Paulo', 35, 'Nivel 2');
insert into "employee" (id, organization_id, department_id, name, age, position) values (7, 2, 4, 'Maria', 34, 'Nivel 1');
insert into "employee" (id, organization_id, department_id, name, age, position) values (8, 2, 4, 'Cláudia', 35, 'Nivel 2');
