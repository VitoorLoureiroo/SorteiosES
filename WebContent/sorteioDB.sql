CREATE DATABASE apostaDB;
USE apostaDB;
CREATE TABLE adm (
	id int PRIMARY KEY AUTO_INCREMENT, 
	nome char(10), 
	senha char(30)
);
CREATE TABLE apostas (
	id int PRIMARY KEY AUTO_INCREMENT, 
	numeros char(30), 
	valor char(30),
	data date,
	cpf_apostador char(30),
	id_sorteio char(30)	
);
CREATE TABLE apostador (
	id int PRIMARY KEY AUTO_INCREMENT, 
	nome char(30), 
	cpf char(30),
	saldo char(30),
	email char(30),
	senha char(30)	
);
CREATE TABLE bolao (
	id int PRIMARY KEY AUTO_INCREMENT, 
	apostadores char(30), 
	id_sorteio char(30),
	data date,
	valor char(30)	
);
CREATE TABLE sorteio (
	id int PRIMARY KEY AUTO_INCREMENT, 
	id_sorteio char(30), 
	data_abertura date,
	data_encerramento date,
	range_numeros char(30),
	valor_aposta char(30),
	numeros_por_aposta char(30)
);