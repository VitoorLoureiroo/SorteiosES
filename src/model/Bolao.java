package model;

import java.util.Date;

public class Bolao {

		//private 
	private String[] apostadores;
	private int numeros[];
	private long idSorteio;
	private Date data;
	private double valor;
	
	public Bolao(String [] apostadores, int[] numeros, long idSorteio, Date data, double valor) {
		
		this.apostadores = apostadores;
		this.numeros = numeros;
		this.idSorteio = idSorteio;
		this.data = data;
		this.valor = valor;
	}

	public String[] getApostadores() {
		return apostadores;
	}

	public int[] getNumeros() {
		return numeros;
	}

	public long getIdSorteio() {
		return idSorteio;
	}

	public Date getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}
	
	
	
	
	

}
