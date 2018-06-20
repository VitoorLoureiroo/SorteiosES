package dao;

import java.util.List;

import model.Bolao;

public interface BolaoDAO {
	void adicionarBolao(Bolao bolao);
	void updateBolao(Bolao bolao);
	void adicionarAposta(String organizador);
	List<Bolao> pesquisarBolaoGanhador(int[] numeros);
	List<Bolao> pesquisarBolaoPorSorteio(long idSorteio);
}
