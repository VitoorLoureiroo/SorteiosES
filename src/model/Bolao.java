package model;

import java.util.ArrayList;
import java.util.Date;

public class Bolao {

		//private 
	private ArrayList<Apostador> apostadores = new ArrayList<>();
	private int numeros[];
	private int idSorteio;
	private Date data;
	private double valor;
	
	public Bolao(ArrayList<Apostador> apostadores, int[] numeros, int idSorteio, Date data, double valor) {
		
		this.apostadores = apostadores;
		this.numeros = numeros;
		this.idSorteio = idSorteio;
		this.data = data;
		this.valor = valor;
	}
	

}
