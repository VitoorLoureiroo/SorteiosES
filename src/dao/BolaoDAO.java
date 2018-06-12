package dao;

import java.util.List;

import model.Bolao;

public interface BolaoDAO {
	void adicionarBolao(Bolao bolao);
	List<Bolao> pesquisarBolaoGanhador(int[] numeros);
	List<Bolao> pesquisarBolaoPorSorteio(long idSorteio);
}
