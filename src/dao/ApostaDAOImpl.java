package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import model.Aposta;


public class ApostaDAOImpl implements ApostaDAO{

	private Connection getConnection() {
		try {
			// Obtain our environment naming context
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/SorteioDB");
			return ds.getConnection();		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void adicionarAposta(Aposta aposta) {
		Connection con = getConnection();
		String sql = "INSERT INTO apostas "
				+ "(numeros, valor, data, cpf_apostador, id_sorteio) "
				+ "VALUES (?, ?, ? , ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,  Arrays.toString(aposta.getNumeros()));
		
			stmt.setDouble(2,  aposta.getValor());
			
			long mili = aposta.getData().getTime();
			Date d = new java.sql.Date(mili);
			stmt.setDate(3,  d);
			stmt.setString(4,  aposta.getCpfApostador());
			stmt.setLong(5,  aposta.getIdSorteio());
			
			stmt.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Aposta> pesquisarApostasGanhadoras(int[] numeros) {
		Connection con = getConnection();
		List<Aposta> lista = new ArrayList<>();
		String sql = "SELECT * FROM apostas WHERE numeros like ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + Arrays.toString(numeros) + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				
				String num = rs.getString("numeros");
				
				String[] stringArray = num.split(",");
				
				int[] intArray = new int[stringArray.length];
			     for (int i = 0; i < stringArray.length; i++) {
			         String numberAsString = stringArray[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			      }
				
				Aposta aposta = new Aposta(intArray,
								rs.getDouble("valor"),
								rs.getDate("data"),
								rs.getString("cpf_apostador"),
								rs.getLong("id_sorteio"));
				
			lista.add( aposta );
			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	
	

}
