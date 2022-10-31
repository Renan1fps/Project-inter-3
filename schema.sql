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