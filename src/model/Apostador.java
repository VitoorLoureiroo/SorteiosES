package model;

import java.util.Date;

public class Apostador {

		private String nome;
		private String cpf;
		private double saldo;
		private String email;
		private String senha;
		
		
		
		public Apostador(String nome, String cpf, double saldo, String email, String senha) {
			super();
			this.nome = nome;
			this.cpf = cpf;
			this.saldo = saldo;
			this.email = email;
			this.senha = senha;
		}


		public Aposta criarAposta(int idSorteio, double valor, int[] numeros){
			
			this.saldo = this.saldo - valor;
			return new Aposta(numeros, valor, new Date(), this.cpf, idSorteio);
			
		};
		
		
		public void creditarSaldo(double valorCredito){
			
			this.saldo = this.saldo + valorCredito;
			
		};
		
		
		public void editarAcesso(String email, String novaSenha){
			
			this.email = email;
			this.senha = novaSenha;
			
		}


		public String getNome() {
			return nome;
		}


		public String getCpf() {
			return cpf;
		}


		public double getSaldo() {
			return saldo;
		}


		public String getEmail() {
			return email;
		}


		public String getSenha() {
			return senha;
		};
		
		
		
		
}
