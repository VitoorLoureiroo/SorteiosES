package model;

import java.util.Date;

public class ViewModelApostas {

	String organizador;
	int numPart;
	Date dataAbertura;
	Date dataEnc;
	String numApostados;
	int numPorAposta;
	
	public ViewModelApostas(String organizador, int numPart, Date dataAbertura, Date dataEnc, String numApostados) {
		this.organizador = organizador;
		this.numPart = numPart;
		this.dataAbertura = dataAbertura;
		this.dataEnc = dataEnc;
		this.numApostados = numApostados;
	}

	public String getOrganizador() {
		return organizador;
	}

	public int getNumPart() {
		return numPart;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public Date getDataEnc() {
		return dataEnc;
	}

	public String getNumApostados() {
		return numApostados;
	}

	public int getNumPorAposta() {
		return numPorAposta;
	}

	public void setNumPorAposta(int numPorAposta) {
		this.numPorAposta = numPorAposta;
	}
	
	
	
	
}
