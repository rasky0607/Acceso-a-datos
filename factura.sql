create database if not exists facturacion character set='utf8';
	use facturacion;

	drop table if exists linea_factura;
	drop table if exists factura;
	drop table if exists producto;
	drop table if exists cliente;

	create table cliente(  
	id_cliente integer not null auto_increment,
	dni char(8) not null,
	nom_cliente varchar(50) not null,
	primary key(id_cliente)
	);

	create unique index i_u_cliente_dni on cliente(dni);
	create table producto(  
	  id_producto integer not null auto_increment,
	  ean char(13) not null,
	  nom_producto varchar(64) not null,
	  primary key(id_producto)
	  );

	create unique index i_u_producto_ean on producto(ean);

	create table factura(
		num_factura integer not null auto_increment,
		id_cliente integer not null,
		fecha_factura date not null,
		precio_unitario decimal(8,2) not null,
		primary key(num_factura),
		foreign key f_factura_cliente(id_cliente) references cliente(id_cliente)
	);

	create table linea_factura(
		num_factura integer not null,
		num_linea_factura smallint not null,
		id_producto integer not null,
		cantidad integer not null,
		primary key(num_factura, num_linea_factura),
		foreign key f_linfact_factura(num_factura) references factura(num_factura),
		foreign key f_linfact_producto(id_producto) references producto(id_producto)
	);

	create user if not exists 'facturacion'@'localhost' identified with mysql_native_password by'fact_pwd';
	grant all privileges on facturacion.* to 'facturacion'@'localhost';


