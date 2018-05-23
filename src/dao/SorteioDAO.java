package dao;

import java.util.List;

import model.Sorteio;

public interface SorteioDAO {
	void adicionarSorteio(Sorteio sorteio);
	List<Sorteio> pesquisarSorteios();
	void excluirSorteio(long id);
}
