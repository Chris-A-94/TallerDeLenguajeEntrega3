package entregable1;
import daos.*;
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
	private CoinDAO cDao;
	public Sistema() {
		
		this.monedas = new ArrayList<Coin>();
		cDao = new CoinDAO(); //se crea la tabla
		if (monedas.isEmpty())
		monedas.addAll(cDao.devolverTabla()); // traer todos los datos a una linked list
		
		this.blockChain = new ArrayList<BlockChain>();
		this.usuarios = new LinkedList<Usuario>();
	}
		
	public boolean crearMoneda() {
		Coin auxCoin = this.leerMoneda(); //Leo la moneda desde teclado y lo guardo en una variable coin.
		
		if (auxCoin == null)
			return false;
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
		cDao.guardar(auxCoin); //se agrega moneda
			
			
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
	}
	
	public void listarStock() {
		if (monedas.isEmpty()){
			System.out.println("No hay monedas dentro de la base de datos");
			return;
		}
		monedas.sort(new doubleCompartor());
		for (Coin c : monedas)
		{
			System.out.println(c.getNombre()+": "+c.getStock());
		}
	}
	
	public void generarStock() {
		for(Coin c:monedas)
		{
			c.generarStock();
		}
	}
}
