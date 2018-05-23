package dao;

import java.util.List;

import model.Aposta;

public interface ApostaDAO {
	void adicionarAposta(Aposta aposta);
	List<Aposta> pesquisarApostasGanhadoras(int[] numeros);
}
