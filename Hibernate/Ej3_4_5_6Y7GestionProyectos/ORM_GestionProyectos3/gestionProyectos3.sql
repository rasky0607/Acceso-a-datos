create database if not exists gestionproyectos3;
use gestionproyectos3;

drop table if exists asig_proy;
drop table if exists proyecto;
drop table if exists empleado;

create table empleado(
  dni char(9) not null,
  nom_emp varchar(32) not null,
  primary key(dni)
);

create table proyecto(
  id_proy integer auto_increment not null,
  nom_proy varchar(32) not null,
  f_inicio date not null,
  f_fin date,
  primary key(id_proy)
);

create table asig_proy(
  dni_emp char(9),
  id_proy integer,
  f_inicio date not null,
  f_fin date,
  primary key(dni_emp,id_proy,f_inicio),
  foreign key fk_dni_emp(dni_emp) references empleado(dni),
  foreign key fk_id_proy(id_proy) references proyecto(id_proy)
);