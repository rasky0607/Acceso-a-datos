create database if not exists jerarquia;
	use jerarquia;
	drop table if exists empleado;

create table empleado(
   dni char(9),
   nom_emp varchar(60) not null,
   num_emp integer unique,
   tipo varchar(10),
   primary key(dni),
   constraint check((tipo='plantilla' and num_emp!=null) or (tipo='no' and num_emp=null)) 
);