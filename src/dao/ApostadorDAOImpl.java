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

import model.Apostador;

public class ApostadorDAOImpl implements ApostadorDAO{
	
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
	public void adicionarApostador(Apostador apostador) {
		Connection con = getConnection();
		String sql = "INSERT INTO apostador "
				+ "(nome, cpf, saldo, email, senha) "
				+ "VALUES (?, ?, ? , ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,  apostador.getNome());
			stmt.setString(2,  apostador.getCpf());
			stmt.setDouble(3,  apostador.getSaldo());
			stmt.setString(4,  apostador.getEmail());
			stmt.setString(5,  apostador.getSenha());
			
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Apostador> pesquisarApostador(String cpf) {
		Connection con = getConnection();
		List<Apostador> lista = new ArrayList<>();
		String sql = "SELECT * FROM apostador WHERE cpf like ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + cpf + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Apostador apostador = new Apostador(rs.getString("nome"),
								rs.getString("cpf"),
								rs.getDouble("saldo"),
								rs.getString("email"),
								rs.getString("senha"));
				
			lista.add( apostador );
			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		
	}
}
