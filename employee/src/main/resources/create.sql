-- create sequence employee_id_seq start 1 increment 1;
create table "employee" (id BIGSERIAL not null, age int4 not null, department_id int8 not null, name varchar(255) not null, organization_id int8 not null, position varchar(255) not null, primary key (id));
