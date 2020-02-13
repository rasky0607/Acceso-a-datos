create database if not exists gestionproyectos2;
use gestionproyectos2;

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
  dni_jefe_proy char(9) not null,
  primary key(id_proy),
  foreign key fk_proy_jefe(dni_jefe_proy) references empleado(dni)
);