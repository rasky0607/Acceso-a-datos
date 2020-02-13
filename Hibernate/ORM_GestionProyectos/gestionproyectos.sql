create database if not exists gestionproyectos;

use gestionproyectos;

drop table if exists proyecto_sede;
drop table if exists jefe_proyecto;
drop table if exists proyecto;
drop table if exists empleado;
drop table if exists departamento;
drop table if exists sede;

create table sede(
  id_sede integer auto_increment not null,
  nom_sede char(20) not null,
  primary key(id_sede)
);

create table departamento(
  id_depto integer auto_increment not null,
  nom_depto char(32) not null,
  id_sede integer not null,
  primary key(id_depto),
  foreign key fk_depto_sede(id_sede) references sede(id_sede)
);

create table empleado(
  dni char(9) not null,
  nom_emp char(40) not null,
  id_depto integer not null,
  primary key(dni),
  foreign key fk_empleado_depto(id_depto) references departamento(id_depto)
);

create table proyecto (
  id_proy integer auto_increment not null,
  f_inicio date not null,
  f_fin date,
  nom_proy char(20) not null,
  primary key(id_proy)
);

create table jefe_proyecto(
  dni char(9) not null,
  id_proy integer,
  f_inicio date not null,
  f_fin date,
  primary key(dni,id_proy),
  foreign key fk_empleadojefe_proyecto_empl(dni) references empleado(dni),
  foreign key fk_idproyecto_proyecto(id_proy) references proyecto(id_proy)
);


create table proyecto_sede (
  id_proy integer not null,
  id_sede integer not null,
  f_inicio date not null,
  f_fin date,
  primary key(id_proy, id_sede),
  foreign key fk_proysede_proy (id_proy) references proyecto(id_proy),
  foreign key fk_proysede_sede (id_sede) references sede(id_sede)
);

/**
//Creacion de usuario que  manerjara la BD
CREATE USER 'usuario'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON * . * TO 'usuario'@'localhost';
*/