package daos;

import java.util.List;
import java.util.LinkedList;

import java.sql.*;

import entregable1.Transaccion;
import entregable1.TransaccionSwap;

public class TransaccionDAO implements DaoInterface<Transaccion> {
	private Connection con = null;
	
	public TransaccionDAO() {
		con = MyConnection.getCon();
		this.crearTabla();
	}
	
	public void crearTabla() {
		try {
			String query = "CREATE TABLE IF NOT EXISTS TRANSACCION (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TEXTO TEXT NOT NULL," +
                    "USER_ID TEXT NOT NULL," +
                    "FOREIGN KEY ('USER_ID') REFERENCES 'USUARIOS' ('EMAIL	')" +
                    ");";
			Statement pstmt = con.createStatement();
			pstmt.execute(query);
			pstmt.close();
		} catch (SQLException e) {
			System.out.printf("%s\n", e.toString());
		}
	}
	@Override
	public boolean modificar(Transaccion t) {
		try {
			String query = ("UPDATE TRANSACCION SET TEXTO = ?;");
			PreparedStatement pstmt = con.prepareStatement(query);
		    
		  pstmt.setString(1,t.toString());
		  pstmt.executeUpdate();
		  pstmt.close();
		  return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	@Override
	public boolean guardar(Transaccion t) {
			try {
			    String query = "INSERT INTO TRANSACCION (TEXTO, USER_ID) VALUES (?, ?)";
			    PreparedStatement pstmt = con.prepareStatement(query);
			    
			    pstmt.setString(1, t.toString());
			    pstmt.setString(2, t.getUserID());
			    
			    pstmt.executeUpdate();
			    pstmt.close();
			  return true;
			} catch (SQLException e) {
				    System.out.println(e.getMessage());
			}
			return false;
}
	@Override
	public List<Transaccion> devolverTabla() {
		List<Transaccion> list = new LinkedList<Transaccion>();
	/*	
		try {
			Statement sent = con.createStatement();	
			ResultSet resul = sent.executeQuery("SELECT * FROM TRANSACCION");
			
			while (resul.next() == true) {
				if (resul.getString("TEXTO").equals("COMPRA"))
					//list.add(new TransaccionSwap());
			}
			
			resul.close();
			sent.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

// 	Dado que en la base de datos solo se almacena una descripción limitada, 
//	no es posible obtener los datos suficientes para instanciar una Transacción.
		

*/
		return list;
	}
	
	
	@Override
	public void remover(String s) {
		// No fue implementado.
	}
	
	public List<String> devolverTablaString(){
		List<String> list = new LinkedList<String>();
		
			try {
				Statement sent = con.createStatement();	
				ResultSet resul = sent.executeQuery("SELECT * FROM TRANSACCION");
				
				while (resul.next() == true) {
					list.add(resul.getString("TEXTO"));
				}
				
				resul.close();
				sent.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		return list;
	}
}
