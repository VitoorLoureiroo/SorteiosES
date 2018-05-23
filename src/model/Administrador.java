package model;

import java.util.Date;

import dao.SorteioDAO;
import dao.SorteioDAOImpl;

public class Administrador {

		private String nome;
		private String senha;
		
		
		
		public Administrador(String nome, String senha) {
			this.nome = nome;
			this.senha = senha;
		}

		public Sorteio criarSorteio(int id, Date dataAbertura, Date dataEnc, int range, double valor, int nAposta){
			
			return new Sorteio(id, dataAbertura, dataEnc, range, valor, nAposta);
			
		};
		
		public void excluirSorteio(int id){
			
			SorteioDAO sorteioDAO = new SorteioDAOImpl();
			
			sorteioDAO.excluirSorteio(id);
		}

		public String getNome() {
			return nome;
		}

		public String getSenha() {
			return senha;
		};
		
		
		
		
}
