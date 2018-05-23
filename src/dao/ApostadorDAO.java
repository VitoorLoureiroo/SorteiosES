package dao;

import java.util.List;

import model.Apostador;

public interface ApostadorDAO {
	void adicionarApostador(Apostador apostador);
	List<Apostador> pesquisarApostador(String cpf);
}
