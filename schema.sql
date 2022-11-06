use projeto;

create table if not exists tb_user(
  id_user INT AUTO_INCREMENT,
  name VARCHAR(100),
  cpf VARCHAR(11),
  age INT,
  createdAt DATETIME,
  password VARCHAR(255),
  PRIMARY KEY (id_user)
);

create table if not exists tb_unit(
  id_unit INT AUTO_INCREMENT,
  name VARCHAR(100),
  PRIMARY KEY (id_unit)
);


create table if not exists tb_car(
	id_unit INT AUTO_INCREMENT,
	id_unidade INT,
  	marca VARCHAR(100),
  	modelo VARCHAR(100),
  	tipo VARCHAR(50),
  	ano INT,
  	cor VARCHAR(50),
  	valor FLOAT,
  	vidro_eletrico BOOLEAN,
  	cambio_automatico BOOLEAN,
  	ar_condicionado BOOLEAN,
  	freio_abs BOOLEAN,
  	quatro_portas BOOLEAN,
  	direcao_hidraulica BOOLEAN,
  	porta_mala_grande BOOLEAN,
  	premium BOOLEAN,
  	PRIMARY KEY (id_unit),
  	FOREIGN KEY (id_unidade) REFERENCES tb_unit(id_unit)
);