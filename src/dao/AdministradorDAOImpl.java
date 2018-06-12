package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Administrador;

public class AdministradorDAOImpl implements AdministradorDAO{
	
	private Connection getConnection() {
		try {
			// Obtain our environment naming context
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/sorteioDB");
			return ds.getConnection();		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void adicionarAdm(Administrador adm) {
		Connection con = getConnection();
		String sql = "INSERT INTO adm "
				+ "(nome, senha) "
				+ "VALUES (?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,  adm.getNome());
		
			stmt.setString(2,  adm.getSenha());
			
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Administrador> pesquisarAdm(String nome) {
		Connection con = getConnection();
		List<Administrador> lista = new ArrayList<>();
		String sql = "SELECT * FROM adm WHERE nome like ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Administrador adm = new Administrador(rs.getString("nome"),
								rs.getString("senha"));
				
			lista.add( adm );
			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
