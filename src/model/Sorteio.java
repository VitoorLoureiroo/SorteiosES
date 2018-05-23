package model;

import java.util.Date;

public class Sorteio {

		private long id;
		private Date dataAbertura;
		private Date dataEncerramento;
		private int rangeNumeros;
		private double valorAposta;
		private int numerosPorAposta;
		
		public double calcularPote(){
			
			return 0.0;
		};
		
		public int[] sortear(){
			
			return new int[0];
			
		}

		public Sorteio(long id, Date dataAbertura, Date dataEncerramento, int rangeNumeros, double valorAposta,
				int numerosPorAposta) {
			
			this.id = id;
			this.dataAbertura = dataAbertura;
			this.dataEncerramento = dataEncerramento;
			this.rangeNumeros = rangeNumeros;
			this.valorAposta = valorAposta;
			this.numerosPorAposta = numerosPorAposta;
		}

		public long getId() {
			return id;
		}

		public Date getDataAbertura() {
			return dataAbertura;
		}

		public Date getDataEncerramento() {
			return dataEncerramento;
		}

		public int getRangeNumeros() {
			return rangeNumeros;
		}

		public double getValorAposta() {
			return valorAposta;
		}

		public int getNumerosPorAposta() {
			return numerosPorAposta;
		};
		
}
