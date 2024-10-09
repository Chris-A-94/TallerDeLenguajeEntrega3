package entregable1;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private final int dimfCrip = 6;
	private List<Coin> monedas = new ArrayList<Coin>();
	private List<BlockChain> blockChain;
	private List<Usuario> usuarios;
	//private MonitoreoCoin APIcoins;
	
	public Sistema() {
		
		
		
	
	}
	public boolean crearMoneda() {
		
		
		Coin auxCoin = this.leerMoneda();	
		if (auxCoin != null)
			this.monedas.add(auxCoin);
		else
			return false;
		Scanner in = new Scanner(System.in);
		System.out.println("Desea almacenar la moneda en la base de datos? \n (1) SI (0) NO");
	    int i = in.nextInt();
	    
	    while ((i != 1) && (i != 0)) {
	        System.out.println("Entrada incorrecta. Ingrese (1) SI (0) NO");
	        i = in.nextInt();
	    }
	    if (i == 0)
		{
			
			return false;
		}
		Connection con = null;

		try {
		    con = DriverManager.getConnection("jdbc:sqlite:src/BASE_ENTREGABLE.db");
		    String query = "INSERT INTO COIN (SIGLA, NOMBRE, PRECIO_DOLAR, TIPO, STOCK) VALUES (?, ?, ?, ?, ?)";
		    PreparedStatement pstmt = con.prepareStatement(query);
		    
		    pstmt.setString(1,auxCoin.getSigla());
		    pstmt.setString(2,auxCoin.getNombre());
		    pstmt.setDouble(3,auxCoin.getPrecio());  
		    pstmt.setString(4,auxCoin.getTipo());
		    pstmt.setDouble(5,auxCoin.getStock());
		    
		    pstmt.executeUpdate();
		    
		    pstmt.close();
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
 
		
		
		return true;
	}
	private Coin leerMoneda()
	{
		Scanner in = new Scanner(System.in);
	    
	    System.out.println("Ingrese el tipo de moneda (1) FIAT (0) Cripto");
	    int i = in.nextInt();
	    
	    String tipo;
	    while ((i != 1) && (i != 0)) {
	        System.out.println("Entrada incorrecta. Ingrese el tipo de moneda (1) FIAT (0) Cripto");
	        i = in.nextInt();
	    }
	    
	    if (i == 1) {
	        tipo = "FIAT";
	    } else {
	        tipo = "CRIPTOMONEDA";
	    }
	    
	    System.out.println("Ingrese el nombre de la moneda");
	    String nombre = in.next();
	    
	    System.out.println("Ingrese la sigla de la moneda");
	    String sigla = in.next();
	    
	    //El Stock se genera de forma aleatoria
	    
	    System.out.println("Ingrese el precio en USD");
	    double price = in.nextDouble();
	    
	    System.out.println("Confirmar: (1) SI (0) NO");
	    i = in.nextInt();
	    
	    while ((i != 1) && (i != 0)) {
	        System.out.println("Entrada incorrecta. Ingrese (1) SI (0) NO");
	        i = in.nextInt();
	    }
	    if (i == 0)
		{
			
			return null;
		}
	    
	    // En lugar de la conexión a la base de datos, imprime los valores
	    System.out.println("Moneda creada:");
	    System.out.println("Tipo: " + tipo);
	    System.out.println("Nombre: " + nombre);
	    System.out.println("Sigla: " + sigla);
	    System.out.println("Precio: " + price);
		
	    return new Coin(nombre,sigla,tipo,price);
	}
	public boolean listarStock() {
		//Esperar base de data...
		return false;
	}
}
