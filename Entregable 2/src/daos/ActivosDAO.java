package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import entregable1.Saldo;
import entregable1.Usuario;

public class ActivosDAO implements DaoInterface<Saldo>{
	private Connection con = null;
	public ActivosDAO() {
		con = MyConnection.getCon();
		this.crearTabla();
	}
	@Override
	public void crearTabla() {
		try {
			//Se crea una tabla si no existe ya en el archivo...
			String query = "CREATE TABLE IF NOT EXISTS ACTIVOS (" +
					"ID int PRIMARY KEY AUTO_INCREMENT" +
                    "SIGLA TEXT NOT NULL," +
					"NOMBRE TEXT NOT NULL,"+
                    "CANTIDAD DOUBLE,"+
                    "TIPO TEXT NOT NULL,"+
                    "USER_ID int NOT NULL,"+
                    "FOREING KEY ('USER_ID') REFERENCES 'USUARIOS' ('ID')"+
                    ");";
			/*
			 * Después agregaremos los demás parámetros a la base de datos,
			 * Por ahora nos limitamos a esto para su funcionamiento.
			 * Se omitieron todos los datos relacionados con seguridad.
			 */
			Statement pstmt = con.createStatement();
			pstmt.execute(query);
			pstmt.close();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return;


	}
	@Override
	public void modificar(Saldo saldo) { //SOLO MODIFICA LA CANTIDAD DE MONEDAS
		try {
			String query = ("UPDATE SALDOS SET CANTIDAD = ? WHERE ID = '"+saldo.getID()+"';");
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setDouble(1,saldo.getCantMonedas());  
			pstmt.executeUpdate();
			pstmt.close();
		  
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return;

	}

	@Override
	public void guardar(Saldo saldo) {
		try {
		    String query = "INSERT INTO saldos (SIGLA, NOMBRE, CANTIDAD, TIPO) VALUES (?, ?, ?)";
		    PreparedStatement pstmt = con.prepareStatement(query);
		    pstmt.setString(1,saldo.getSigla());
		    pstmt.setString(2,saldo.getNombre());
		    pstmt.setDouble(3,saldo.getCantMonedas());
		    pstmt.setString(4,saldo.getTipo());  
		    pstmt.executeUpdate();
		    pstmt.close();
		  
		} catch (SQLException e) {
			switch (e.getErrorCode()) {
			case 19:
			    	System.out.println("ERROR: el DNI ya se encuentra en la base de datos");
			    	break;
			default:
			    System.out.println(e.getMessage());
			    break;
			}
			return;
		}		
		System.out.println("¡Se agregó con éxito la criptomoneda a la base de datos!");
		return;


	}

	@Override
	public List<Saldo> devolverTabla() {

		Usuario auxSaldo=null;
		List<Saldo> saldos = new LinkedList<Saldo>();
		try {
			Statement sent = con.createStatement();	
			ResultSet resul = sent.executeQuery("SELECT * FROM saldos");
			 
			// Si entra al while obtuvo al menos una fila
			while (resul.next()){
			//Suponemos a todos los saldos habilitados...
			//auxSaldo = new Usuario(resul.getString("SIGLA"),resul.getString("NOMBRE"),resul.getString("APELLIDO"),resul.getString("PAIS"),resul.getString("EMAIL"));
			if (auxSaldo != null)
			{
				
			}
		}
			sent.close();
			return saldos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return saldos;
		}
	}

	@Override
	public void remover(String s) {
		
	}
	
}
