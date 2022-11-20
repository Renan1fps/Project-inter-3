drop database  projeto;

create database if not exists projeto;
use projeto;

drop table tb_user;

create table if not exists tb_user(
  id_user INT AUTO_INCREMENT,
  phone varchar(15) not null,
  name VARCHAR(100) not null,
  email VARCHAR(100) not null,
  nacionalidade varchar(100) not null,
  cpf VARCHAR(11) not null,
  password VARCHAR(255) not null,
  is_admin boolean default 0,
  PRIMARY KEY (id_user)
);


create table if not exists tb_unit(
  id_unit INT AUTO_INCREMENT,
  name VARCHAR(100) not null,
  phoneUni varchar(15) not null,
  adress varchar(200) not null,
  city varchar(150) not null,
  number int not null,
  state varchar (100) not null, 
  PRIMARY KEY (id_unit)
);


create table if not exists tb_car(
	id_car INT AUTO_INCREMENT,
	id_unit INT,
  	modelo VARCHAR(100),
  	ano INT,
  	cor VARCHAR(50),
  	placa varchar (10),
  	valor FLOAT,
  	vidro_eletrico BOOLEAN,
  	cambio_automatico BOOLEAN,
  	ar_condicionado BOOLEAN,
  	freio_abs BOOLEAN,
  	quatro_portas BOOLEAN,
  	direcao_hidraulica BOOLEAN,
  	porta_mala_grande BOOLEAN,
  	premium BOOLEAN,
  	PRIMARY KEY (id_car),
  	FOREIGN KEY (id_unit) REFERENCES tb_unit(id_unit)
);

ALTER TABLE tb_car 
ADD disponivel boolean default 1; 