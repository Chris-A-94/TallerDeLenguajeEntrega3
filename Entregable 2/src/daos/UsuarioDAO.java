package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import entregable1.Usuario;

public class UsuarioDAO implements DaoInterface<Usuario>{

	private Connection con = null;
	public UsuarioDAO() {
		con = MyConnection.getCon();
		this.crearTabla();
		this.guardar(new Usuario("0","admin","admin", "Argentina", "indefinido","admin")); //se agrega por defecto un usuario administrador
		this.guardar(new Usuario("1","chimichurri","admin", "Argentina/Buenos_Aires/Agrogué", "admin@mail.com","admin")); //se agrega por defecto un usuario administrador
	}

	@Override
	public void crearTabla() {
		try {
			//Se crea una tabla si no existe ya en el archivo...
			String query = "CREATE TABLE IF NOT EXISTS USUARIOS (" +
                    "DNI TEXT PRIMARY KEY," +
                    "NOMBRE TEXT NOT NULL," +
                    "APELLIDO TEXT NOT NULL," +
                    "PAIS TEXT NOT NULL," +
                    "HABILITADO BOOLEAN NOT NULL," +
                    "EMAIL TEXT NOT NULL,"+
                    "CONTRASEÑA TEXT NOT NULL"+
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
	public boolean modificar(Usuario user) {
		try {
			String query = ("UPDATE USUARIOS SET NOMBRE = ?, APELLIDO = ?, PAIS = ?, HABILITADO = ?, EMAIL = ?, CONTRASEÑA = ? WHERE DNI = '"+user.getDNI()+"';");
			PreparedStatement pstmt = con.prepareStatement(query);
		    
		  pstmt.setString(2,user.getNombre());
		  pstmt.setString(3,user.getApellido());  
		  pstmt.setString(4,user.getPais());
		  pstmt.setBoolean(5,user.isHabilitado());
		  pstmt.setString(6,user.getEmail());
		  pstmt.setString(7,user.getContraseña());
		  pstmt.executeUpdate();
		  pstmt.close();
		  return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return false;
	}
	

	@Override
	public boolean guardar(Usuario user) {
		try {
		    String query = "INSERT OR REPLACE INTO USUARIOS (DNI, NOMBRE, APELLIDO, PAIS, HABILITADO, EMAIL, CONTRASEÑA) VALUES (?, ?, ?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = con.prepareStatement(query);
		    
		    pstmt.setString(1,user.getDNI());
		    pstmt.setString(2,user.getNombre());
		    pstmt.setString(3,user.getApellido());  
		    pstmt.setString(4,user.getPais());
		    pstmt.setBoolean(5,user.isHabilitado());
		    pstmt.setString(6,user.getEmail());
		    pstmt.setString(7,user.getContraseña());
		    pstmt.executeUpdate();
		    pstmt.close();
		    return true;
		} catch (SQLException e) {
			switch (e.getErrorCode()) {
			case 19:
			    	System.out.println("ERROR: el DNI ya se encuentra en la base de datos");
			    	break;
			default:
			    System.out.println(e.getMessage());
			    break;
			}
			return false;
		}		

	}

	@Override
	public List<Usuario> devolverTabla() {
		
		Usuario auxUser=null;
		List<Usuario> usuarios = new LinkedList<Usuario>();
		try {
			Statement sent = con.createStatement();	
			ResultSet resul = sent.executeQuery("SELECT * FROM USUARIOS");
			 
			// Si entra al while obtuvo al menos una fila
			while (resul.next()){
			//Suponemos a todos los usuarios habilitados...
			auxUser = new Usuario(resul.getString("DNI"),resul.getString("NOMBRE"),resul.getString("APELLIDO"),resul.getString("PAIS"),resul.getString("EMAIL"),resul.getString("CONTRASEÑA"));
			if (auxUser != null)
				usuarios.add(auxUser);
		}
			sent.close();
			return usuarios;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return usuarios;
		}

	}

	@Override
	public void remover(String dni) {

		try {
			
			String query = ("DELETE FROM USUARIOS WHERE DNI = '"+dni+"';");
			Statement pstmt = con.createStatement();
			pstmt.execute(query);
			pstmt.close();
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return;


	}

}
