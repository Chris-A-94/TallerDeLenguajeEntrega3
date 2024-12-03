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
import entregable1.TipoMoneda;
import entregable1.Usuario;

public class CoinDAO implements DaoInterface<Coin>{
	private Connection con = null;
	public CoinDAO() {
		con = MyConnection.getCon();
		this.crearTabla();
		this.guardar(new Coin("Pesos", "ars", TipoMoneda.FIAT,0.0010));
		this.guardar(new Coin("Dolar", "usd", TipoMoneda.FIAT,1.0));
		
	}
	public void crearTabla() { //crea la base de datos si no existe ya.
		try {
			
			String query = "CREATE TABLE IF NOT EXISTS COIN (" +
                    "SIGLA TEXT PRIMARY KEY," +
                    "NOMBRE TEXT NOT NULL," +
                    "PRECIO_DOLAR REAL NOT NULL," +
                    "TIPO TEXT NOT NULL," +
                    "STOCK DOUBLE NOT NULL" +
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
	public void remover(String sigla) { //Remueve una moneda buscandola por sigla
		try {
			//BUSCA POR SIGLA PORQUE ES "PRIMARY ID"
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
	/*
	 * El método 'modificar' recibe una moneda para no tener que hacer un modificar con cada columna de la tabla
	 * esto con el fin de poder hacer una interface para todos los daos.
	 */
	public boolean modificar(Coin c) { //Recibe una moneda y la pisa en la base de datos.
		try {
				String query = ("UPDATE COIN SET SIGLA = ?, NOMBRE = ?, PRECIO_DOLAR = ?, TIPO = ?, STOCK = ? WHERE SIGLA = '"+c.getSigla()+"';");
				PreparedStatement pstmt = con.prepareStatement(query);
			    
			    pstmt.setString(1,c.getSigla());
			    pstmt.setString(2,c.getNombre());
			    pstmt.setDouble(3,c.getPrecio());  
			    pstmt.setString(4,c.getTipo().toString());
			    pstmt.setDouble(5,c.getStock());
			    pstmt.executeUpdate();
			    pstmt.close();
			    return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
			return false;
		}

	public boolean guardar(Coin auxCoin) //guarda una moneda en la base de datos.
	{
		try {
		    String query = "INSERT OR IGNORE INTO COIN (SIGLA, NOMBRE, PRECIO_DOLAR, TIPO, STOCK) VALUES (?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = con.prepareStatement(query);
		    
		    pstmt.setString(1,auxCoin.getSigla());
		    pstmt.setString(2,auxCoin.getNombre());
		    pstmt.setDouble(3,auxCoin.getPrecio());  
		    pstmt.setString(4,auxCoin.getTipo().toString());
		    pstmt.setDouble(5,auxCoin.getStock());
		    
		    pstmt.executeUpdate();
		    pstmt.close();
		  return true;
		} catch (SQLException e) {
			switch (e.getErrorCode()) {
			case 19:
			    	System.out.println("La sigla de criptomoneda ya existe (debe ser única)");
			    	break;
			default:
			    System.out.println(e.getMessage());
			    break;
			}
		}		
		//System.out.println("¡Se agregó con éxito la criptomoneda a la base de datos!");
		return false;
	
	}
	public List<Coin> devolverTabla() { //devuelve una lista con todas las monedas guardadas en la base de datos.
		
		Coin auxCoin;
		List<Coin> monedas = new LinkedList<Coin>();
		try {
			Statement sent = con.createStatement();	
			ResultSet resul = sent.executeQuery("SELECT * FROM COIN");
			 
			// Si entra al while obtuvo al menos una fila
			while (resul.next()){
			auxCoin = new Coin(resul.getString("NOMBRE"),resul.getString("SIGLA"), TipoMoneda.fromString(resul.getString("TIPO")),resul.getDouble("PRECIO_DOLAR"),resul.getDouble("STOCK"));
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
