package dao;

import java.util.List;

import model.Administrador;

public interface AdministradorDAO {
	void adicionarAdm(Administrador adm);
	List<Administrador> pesquisarAdm(String nome);
}
