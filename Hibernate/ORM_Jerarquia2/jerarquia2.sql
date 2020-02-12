create database if not exists jerarquia2;
	use jerarquia2;

drop table if exists plantilla;
drop table if exists empleado;

create table empleado(
	dni varchar(9),
	nomEmp varchar(250),
	primary key(dni)
);

create table pantilla(
	numEmpleado integer,
	dniEmpleado varchar(9),
	primary key(numEmpleado),
	foreign key (dniEmpleado) references empleado(dni) on update cascade on delete restrict
);