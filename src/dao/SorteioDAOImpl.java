package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import model.Sorteio;

public class SorteioDAOImpl implements SorteioDAO{
	
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
	public void adicionarSorteio(Sorteio sorteio) {
		Connection con = getConnection();
		String sql = "INSERT INTO sorteio "
				+ "(id_sorteio, data_abertura, data_encerramento, range_numeros, valor_aposta, numeros_por_aposta) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,  sorteio.getId());
			
			long mili = sorteio.getDataAbertura().getTime();
			Date dAbertura = new java.sql.Date(mili);
			stmt.setDate(2,  dAbertura);
			
			long mili2 = sorteio.getDataEncerramento().getTime();
			Date dEnc= new java.sql.Date(mili2);
			stmt.setDate(3,  dEnc);
			stmt.setInt(4,  sorteio.getRangeNumeros());
			stmt.setDouble(5,  sorteio.getValorAposta());
			stmt.setInt(6,sorteio.getNumerosPorAposta());
		
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluirSorteio(long id) {
		Connection con = getConnection();
		String sql = "DELETE * FROM sorteio WHERE id=?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,  id);
		
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Sorteio> pesquisarSorteios() {
		Connection con = getConnection();
		List<Sorteio> lista = new ArrayList<>();
		String sql = "SELECT * FROM sorteio";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Sorteio sorteio = 
						new Sorteio(rs.getLong("id"),
								rs.getDate("data_abertura"),
								rs.getDate("data_encerramento"),
								rs.getInt("range_numeros"),
								rs.getInt("valor_aposta"),
								rs.getInt("numeros_por_aposta"));
				if (sorteio.getDataEncerramento().after(new java.util.Date())) {
					lista.add( sorteio );
				}
			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Sorteio> pesquisarSorteiosPorID(long id) {
		Connection con = getConnection();
		List<Sorteio> lista = new ArrayList<>();
		String sql = "SELECT * FROM sorteio "
					+"WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,  id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Sorteio sorteio = 
						new Sorteio(rs.getLong("id"),
								rs.getDate("data_abertura"),
								rs.getDate("data_encerramento"),
								rs.getInt("range_numeros"),
								rs.getInt("valor_aposta"),
								rs.getInt("numeros_por_aposta"));
				if (sorteio.getDataEncerramento().after(new java.util.Date())) {
					lista.add( sorteio );
				}
			
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
