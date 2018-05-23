package model;

import java.util.Date;

public class Aposta {

	private int numeros[];
	private double valor;
	private Date data;
	private String cpfApostador;
	private int idSorteio;
	
	//teste
	//alt + shift + s, depois 'O'
	public Aposta(int[] numeros, double valor, Date data, String cpfApostador, int idSorteio) {
		
		this.numeros = numeros;
		this.valor = valor;
		this.data = data;
		this.cpfApostador = cpfApostador;
		this.idSorteio = idSorteio;
	}
	
}
