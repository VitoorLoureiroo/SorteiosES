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

import model.Bolao;

public class BolaoDAOImpl implements BolaoDAO {
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
	public void adicionarBolao(Bolao bolao) {
		Connection con = getConnection();
		String sql = "INSERT INTO bolao "
				+ "(apostadores, numeros, id_sorteio, data, valor) "
				+ "VALUES (?, ?, ? , ? , ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Arrays.toString(bolao.getApostadores()));
			stmt.setString(2,  Arrays.toString(bolao.getNumeros()));
			stmt.setLong(3,  bolao.getIdSorteio());
			
			long mili = bolao.getData().getTime();
			Date d = new java.sql.Date(mili);
			stmt.setDate(4,  d);
			stmt.setDouble(5,  bolao.getValor());
			
			stmt.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Bolao> pesquisarBolaoGanhador(int[] numeros) {
		Connection con = getConnection();
		List<Bolao> lista = new ArrayList<>();
		String sql = "SELECT * FROM bolao WHERE numeros like ?";
		
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
			     
			     String apostadores = rs.getString("apostadores");
					
			     String[] stringArrayApostadores = apostadores.split(",");
				
				Bolao bolao = new Bolao(stringArrayApostadores,
								intArray,
								rs.getLong("id_sorteio"),
								rs.getDate("data"),
								rs.getDouble("valor")
								);
				
			lista.add( bolao );
			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	

}
