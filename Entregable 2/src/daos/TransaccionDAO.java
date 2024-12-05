package daos;

import java.util.List;
import java.util.LinkedList;

import java.sql.*;

import entregable1.Transaccion;
import entregable1.TransaccionCompra;
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
                    "FECHA TEXT NOT NULL,"+
                    "TIPO TEXT NOT NULL,"+
                    "USER_ID TEXT NOT NULL," +
                    "DESCRIPCION TEXT NOT NULL,"+
                    "FOREIGN KEY ('USER_ID') REFERENCES 'USUARIOS' ('EMAIL')" +
                    ");";
			
			//String dia, String mes, String year, String user_id
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
			    String query = "INSERT INTO TRANSACCION (FECHA, TIPO, USER_ID,DESCRIPCION) VALUES (?, ?, ?,?)";
			    PreparedStatement pstmt = con.prepareStatement(query);			    
			    pstmt.setString(1, t.getFecha());
			    pstmt.setString(2, t.getTipo().toString());
			    pstmt.setString(3, t.getUserID());
			    pstmt.setString(4, t.toString());
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
		try {
			Statement sent = con.createStatement();	
			ResultSet resul = sent.executeQuery("SELECT * FROM TRANSACCION");
			
			while (resul.next() == true) {
				String[] fecha = resul.getString("FECHA").split("/");
				String[] rawData = resul.getString("DESCRIPCION").split("-");
				if (resul.getString("TIPO").equals("COMPRA"))
				{
					
					list.add(new TransaccionCompra(fecha[0],fecha[1],fecha[2],resul.getString("USER_ID"), rawData[1], rawData[3], Double.valueOf(rawData[2]),Double.valueOf(rawData[0])));
				}
			}
			
			resul.close();
			sent.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

// 	Dado que en la base de datos solo se almacena una descripción limitada, 
//	no es posible obtener los datos suficientes para instanciar una Transacción.
		

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
