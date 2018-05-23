package model;

import java.util.Date;

public class Administrador {

		private String nome;
		private String senha;
		
		public Sorteio criarSorteio(int id, Date dataAbertura, Date dataEnc, int range, double valor, int nAposta){
			
			return new Sorteio(id, dataAbertura, dataEnc, range, valor, nAposta);
			
		};
		
		public void excluirSorteio(int id){
			
			//chama DAO e exclui o sorteio
		};
		
		
}
