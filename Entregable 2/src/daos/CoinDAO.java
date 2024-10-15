package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import entregable1.Coin;

public class CoinDAO {
	private Connection con = null;
	public CoinDAO() {
		con = MyConnection.getCon();
		this.crearTabla();
	}
	public void crearTabla() {

		try {
			
			String query = "CREATE TABLE IF NOT EXISTS COIN (" +
                    "SIGLA TEXT PRIMARY KEY," +
                    "NOMBRE TEXT NOT NULL," +
                    "PRECIO_DOLAR REAL NOT NULL," +
                    "TIPO TEXT NOT NULL," +
                    "STOCK INTEGER NOT NULL" +
                    ");";
			Statement pstmt = con.createStatement();
			pstmt.execute(query);
			pstmt.close();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return;

	}
	public void remover(String sigla) {

		try {
			
			String query = ("DELETE FROM COIN WHERE SIGLA = '"+sigla+"';");
			Statement pstmt = con.createStatement();
			pstmt.execute(query);
			pstmt.close();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return;

	}
	public void modificar(String sigla) {
		try {
			
			String query = ("DELETE FROM COIN WHERE SIGLA = '"+sigla+"';");
			Statement pstmt = con.createStatement();
			pstmt.execute(query);
			pstmt.close();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return;

	}

	public boolean guardar(Coin auxCoin)
	{
		try {
		    String query = "INSERT INTO COIN (SIGLA, NOMBRE, PRECIO_DOLAR, TIPO, STOCK) VALUES (?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = con.prepareStatement(query);
		    
		    pstmt.setString(1,auxCoin.getSigla());
		    pstmt.setString(2,auxCoin.getNombre());
		    pstmt.setDouble(3,auxCoin.getPrecio());  
		    pstmt.setString(4,auxCoin.getTipo());
		    pstmt.setDouble(5,auxCoin.getStock());
		    
		    pstmt.executeUpdate();
		    pstmt.close();
		} catch (SQLException e) {
			switch (e.getErrorCode()) {
			case 19:
			    	System.out.println("La sigla de criptomoneda ya existe (debe ser única)");
			    	break;
			default:
			    System.out.println(e.getMessage());
			    break;
			}
			return false;
		}		
		System.out.println("¡Se agregó con éxito la criptomoneda a la base de datos!");
		return true;
	
	}
	public List<Coin> devolverTabla() {
		
		Coin auxCoin;
		List<Coin> monedas = new LinkedList<Coin>();
		try {
			Statement sent = con.createStatement();	
			ResultSet resul = sent.executeQuery("SELECT * FROM COIN");
			 
			// Si entra al while obtuvo al menos una fila
			while (resul.next()){
			auxCoin = new Coin(resul.getString("NOMBRE"),resul.getString("SIGLA"), resul.getString("TIPO"),resul.getDouble("PRECIO_DOLAR"),resul.getDouble("STOCK"));
			if (auxCoin != null)
				monedas.add(auxCoin);
		}
			sent.close();
			return monedas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return monedas;
		}
		
		
	}

	
}
