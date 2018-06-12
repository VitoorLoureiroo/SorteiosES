package dao;

import java.util.List;

import model.Sorteio;

public interface SorteioDAO {
	void adicionarSorteio(Sorteio sorteio);
	List<Sorteio> pesquisarSorteios();
	List<Sorteio> pesquisarSorteiosPorID(long id);
	void excluirSorteio(long id);
}
