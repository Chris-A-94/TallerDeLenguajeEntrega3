package entregable1;
import daos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private List<Coin> monedas;
	private List<BlockChain> blockChain;
	private List<Usuario> usuarios;
	//private MonitoreoCoin APIcoins;
	private CoinDAO cDao;
	
	// Comparators
	Comparator<Coin> porSigla = new Comparator<Coin>() {
		public int compare(Coin c1, Coin c2) {
			return c1.getSigla().compareTo(c2.getSigla());
		}
	};
	Comparator<Coin> porStock = new Comparator<Coin>() {
		public int compare(Coin c1, Coin c2) {
			return c1.getStock().compareTo(c2.getStock()) * (-1);
		}
	};
	Comparator<Coin> porValor = new Comparator<Coin>() {
		public int compare(Coin c1, Coin c2) {
			return c1.getPrecio().compareTo(c2.getPrecio()) * (-1);
		}
	};
	
	public Sistema() {
		
		this.monedas = new ArrayList<Coin>();
		cDao = new CoinDAO(); //se crea la tabla
		if (monedas.isEmpty())
		monedas.addAll(cDao.devolverTabla()); // traer todos los datos a una linked list
		this.blockChain = new ArrayList<BlockChain>();
		this.usuarios = new LinkedList<Usuario>();
	}
		
	public Coin crearMoneda() {
		Coin auxCoin = this.leerMoneda(); //Leo la moneda desde teclado y lo guardo en una variable coin.
		
		if (auxCoin == null)
			return null;
		this.monedas.add(auxCoin);
		Scanner in = new Scanner(System.in);
		System.out.println("¿Desea almacenar la moneda en la base de datos? \n (1) SI (0) NO");
	    int i = in.nextInt(); //Variable para leer opciones...
	    
	    while ((i != 1) && (i != 0)) {  
	        System.out.println("Entrada incorrecta. Ingrese (1) SI (0) NO");
	        i = in.nextInt();
	    }
	    if (i == 0)
	    	return null;
		else
		{
		cDao.guardar(auxCoin); //se agrega moneda
			
			
			return auxCoin;
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
			System.out.println("No hay monedas dentro de la base de datos\n");
			return;
		}
		
		// Scanner
		Scanner in = new Scanner(System.in);
		// Obtener lista de Activos (Saldos)
		LinkedList<Coin> list = new LinkedList<Coin>();
		list.addAll(monedas);
		// Pregunta
		System.out.printf("Ordenar por\n"
				+ "SIGLA (1), VALOR (2)\n:");
		Integer lectura = in.nextInt();
		while (lectura < 1 || lectura > 2) {
			System.out.printf("Valor Incorrecto: \n");
			lectura = in.nextInt();
		}
		
		// Ordenar
		if (lectura.equals(1)) {
			list.sort(porSigla);
		} else if (lectura.equals(2)) {
			list.sort(porValor);
		}
		
		System.out.printf("%s\n", list.toString());

		System.out.println("\u001B[33m" +"Cantidad de monedas: "+ monedas.size() + "\u001B[0m");
	}
	

	public void listarStock() {
		if (monedas.isEmpty()){
			System.out.println("No hay monedas dentro de la base de datos\n");
			return;
		}
		// Scanner
		Scanner in = new Scanner(System.in);
		// Obtener lista de Activos (Saldos)
		LinkedList<Coin> list = new LinkedList<Coin>();
		list.addAll(monedas);
		// Pregunta
		System.out.printf("Ordenar por\n"
				+ "SIGLA (1), CANTIDAD (2)\n:");
		Integer lectura = in.nextInt();
		while (lectura < 1 || lectura > 2) {
			System.out.printf("Valor Incorrecto: \n");
			lectura = in.nextInt();
		}
		
		// Ordenar
		if (lectura.equals(1)) {
			list.sort(porSigla);
		} else if (lectura.equals(2)) {
			list.sort(porStock);
		}
		
		System.out.printf("%s\n", list.toString());
	}
	public void removerMoneda() { //remueve moneda de la lista y la base de datos...
		String sigla;
		Scanner n = new Scanner(System.in);
		System.out.println("INGRESE LA SIGLA DE LA CRIPTOMONEDA: ");
		sigla = n.next();
		//Elimina de la lista.
		for (Coin c : monedas)
		{
			if (c.getSigla().equals(sigla))
			{
				monedas.remove(c);
				System.out.println("¡Se borró la moneda de la lista!");
			}
				
		}
		//elimina de la db.
		cDao.remover(sigla);
		System.out.println("¡Se borró la moneda de la DB!");
	}
	public void generarStock() {
		for(Coin c:monedas)
		{
			c.generarStock(); //modifica los valores en la lista.
			 cDao.modificar(c); //modifica los valores en la base datos.
		}
	}
}
