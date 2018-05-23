package model;

import java.util.Date;

public class Aposta {

	private int numeros[];
	private double valor;
	private Date data;
	private String cpfApostador;
	private long idSorteio;
	
	//teste
	//alt + shift + s, depois 'O'
	public Aposta(int[] numeros, double valor, Date data, String cpfApostador, long idSorteio) {
		
		this.numeros = numeros;
		this.valor = valor;
		this.data = data;
		this.cpfApostador = cpfApostador;
		this.idSorteio = idSorteio;
	}

	public int[] getNumeros() {
		return numeros;
	}

	public double getValor() {
		return valor;
	}

	public Date getData() {
		return data;
	}

	public String getCpfApostador() {
		return cpfApostador;
	}

	public long getIdSorteio() {
		return idSorteio;
	}
	
	
	
}
