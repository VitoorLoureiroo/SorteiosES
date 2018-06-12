package model;

import java.util.Date;

public class ViewModelApostas extends Bolao{

	int numPorAposta;
	
	public ViewModelApostas(String [] apostadores, int[] numeros, long idSorteio, Date data, double valor, int numPorAposta) {
		super(apostadores,numeros,idSorteio,data,valor);
		this.numPorAposta = numPorAposta;
	}

	public int getNumPorAposta() {
		return numPorAposta;
	}

	public void setNumPorAposta(int numPorAposta) {
		this.numPorAposta = numPorAposta;
	}
	
}
