create database if not exists provincias character set = 'utf8';

use provincias;

drop table if exists localidad;
drop table if exists provincia;
drop table if exists comunidad;

create table comunidad(

	id_com integer primary key,
	nom_com varchar(254) not null
);

create table provincia(

	id_prov integer primary key,
	nom_prov varchar(254) not null,
	id_com integer not null,

	foreign key (id_com) references comunidad(id_com) on update cascade on delete restrict
);

create table localidad(

	id_loc integer primary key,
	nom_loc varchar(254) not null,
	id_prov integer not null,

	foreign key (id_prov) references provincia(id_prov) on update cascade on delete restrict
);