package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Bolao;
import model.Sorteio;

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
	public void updateBolao(Bolao bolao) {
		Connection con = getConnection();
		String sql = "UPDATE  bolao "
				+ "SET numeros = ? "
				+ "WHERE apostadores like ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,  Arrays.toString(bolao.getNumeros()));
			stmt.setString(2, Arrays.toString(bolao.getApostadores()));
			
		
			
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
				String[] stringArray = num.replace("[","").replace("]","").split(",");
				
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

	@Override
	public List<Bolao> pesquisarBolaoPorSorteio(long idSorteio) {
		Connection con = getConnection();
		List<Bolao> lista = new ArrayList<>();
		String sql = "SELECT * FROM bolao WHERE id_sorteio = ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, idSorteio);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				
				String num = rs.getString("numeros");
				
				String[] stringArray = num.replace("[","").replace("]","").split(", ");
				
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

	@Override
	public void adicionarAposta(String organizador) {
		Connection con = getConnection();
		List<Bolao> lista = new ArrayList<>();
		String sql = "SELECT * FROM bolao WHERE apostadores like ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + organizador + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				
				String num = rs.getString("numeros");
				String[] stringArray = num.replace("[","").replace("]","").split(", ");
				
				int[] intArray = new int[stringArray.length];
			     for (int i = 0; i < stringArray.length; i++) {
			         String numberAsString = stringArray[i];
			         intArray[i] = Integer.parseInt(numberAsString);
			      }
			     
			     String apostadores = rs.getString("apostadores");
					
			     String[] stringArrayApostadores = apostadores.replace("[","").replace("]","").split(", ");
				
				Bolao bolao = new Bolao(stringArrayApostadores,
								intArray,
								rs.getLong("id_sorteio"),
								rs.getDate("data"),
								rs.getDouble("valor")
								);
				
			lista.add( bolao );
			
			}
			
			if(lista.size()>0) {
				int [] numeros = new int [lista.get(0).getNumeros().length + 1];
				
				
						
				for(int i=0; i<=lista.get(0).getNumeros().length-1; i++) {
					numeros[i] = lista.get(0).getNumeros()[i];
				};
				
				SorteioDAO sorteioDao = new SorteioDAOImpl();
				List<Sorteio> sorteio = sorteioDao.pesquisarSorteiosPorID(lista.get(0).getIdSorteio());
				
				Random r = new Random();
				int Low = 1;
				int High = sorteio.get(0).getRangeNumeros();
				int Result = r.nextInt(High-Low) + Low;
				numeros[numeros.length-1] = Result;
				
				lista.get(0).setNumeros(numeros);
				
				updateBolao(lista.get(0));
				
				
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
