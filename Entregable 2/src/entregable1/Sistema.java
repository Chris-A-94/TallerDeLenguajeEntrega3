package entregable1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private List<Coin> monedas;
	private List<BlockChain> blockChain;
	private List<Usuario> usuarios;
	//private MonitoreoCoin APIcoins;
	
	public Sistema() {
<<<<<<< HEAD
		
=======
		this.monedas = new ArrayList<Coin>();
		if (monedas.isEmpty())
			this.cargarMonedasDB();
		this.blockChain = new ArrayList<BlockChain>();
		this.usuarios = new LinkedList<Usuario>();
>>>>>>> main
	}
	
	public boolean crearMoneda() {
<<<<<<< HEAD
		Coin auxCoin = this.leerMoneda();	
=======
		Coin auxCoin = this.leerMoneda(); //Leo la moneda desde teclado y lo guardo en una variable coin.
>>>>>>> main
		if (auxCoin != null)
			this.monedas.add(auxCoin);
		Scanner in = new Scanner(System.in);
		System.out.println("¿Desea almacenar la moneda en la base de datos? \n (1) SI (0) NO");
	    int i = in.nextInt(); //Variable para leer opciones...
	    
	    while ((i != 1) && (i != 0)) {  
	        System.out.println("Entrada incorrecta. Ingrese (1) SI (0) NO");
	        i = in.nextInt();
	    }
	    if (i == 0)
	    	return false;
		else
		{
			this.agregarAbaseDeDatos(auxCoin);
			System.out.println("Presione [ENTER] para continuar...");
			try{System.in.read();}
			catch(Exception e){}
			
			return true;
		}
	}
	
	
	
	//Método privado que devuelve una instancia de cripto inicializada con valores ingresados en teclado...
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
	    
	    return new Coin(nombre,sigla,tipo,price);
	}
	//Agrega una instancia de criptomoneda a la base de datos...
	private boolean agregarAbaseDeDatos(Coin auxCoin)
	{
		
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
		return false;
	}
	public void listarMonedas() {
		if (monedas.isEmpty()){
			System.out.println("No hay monedas dentro de la base de datos");
			return;
		}

		Collections.sort(monedas);
		for (Coin c: monedas)
		{
			System.out.println(c.toString());	
			System.out.println("\n-----\n");

		}
		System.out.println("\u001B[31m" +"Cantidad de monedas: "+ monedas.size() + "\u001B[0m");
		System.out.println("Presione [ENTER] para continuar...");
		try{System.in.read();}
		catch(Exception e){}
	}
	
	private boolean cargarMonedasDB() {
		Coin auxCoin;
		Connection con=null;
		try {
			 con=DriverManager.getConnection("jdbc:sqlite:src/BASE_ENTREGABLE.db");
		Statement sent = con.createStatement();	
		ResultSet resul = sent.executeQuery("SELECT * FROM COIN");
		 
		// Si entra al while obtuvo al menos una fila
		while (resul.next()){
			auxCoin = new Coin(resul.getString("NOMBRE"),resul.getString("SIGLA"), resul.getString("TIPO"),resul.getDouble("PRECIO_DOLAR"),resul.getDouble("STOCK"));
			if (auxCoin != null)
				this.monedas.add(auxCoin);
		}
		sent.close();
		con.close();
		return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return false;
	}
}
