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
	private List<Saldo> saldosUsuarios;
	//private MonitoreoCoin APIcoins;
	private CoinDAO cDao;
	private UsuarioDAO uDao;
	private ActivosDAO aDao;
	private TransaccionDAO tDao;
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
	/*
	 * Constructor de sistema, inicializa un arreglo de monedas, de usuarios y de activos.
	 */
	public Sistema() {
		
		//INICIALIZAR ARREGLO DE MONEDAS CON LA BASE
		this.monedas = new ArrayList<Coin>();
		cDao = new CoinDAO(); //se crea la tabla monedas con algunas monedas predefinidas.
		
		if (monedas.isEmpty()) {
			monedas.addAll(cDao.devolverTabla());
			// traer todos los datos a una linked list
		}
		
		
		//INICIALIZAR ARREGLO DE BLOCKCHAINS
		//this.blockChain = new ArrayList<BlockChain>();
		
		//INICIALIZAR ARREGLO DE USUARIOS
		uDao = new UsuarioDAO(); //se crea la tabla de usuarios.
		this.usuarios = new LinkedList<Usuario>();
		this.usuarios.addAll(uDao.devolverTabla());
		//INICIALIZAR ARREGLO DE ACTIVOS (O SALDOS)
		aDao = new ActivosDAO();
		this.saldosUsuarios = new LinkedList<Saldo>();
		this.saldosUsuarios.addAll(aDao.devolverTabla());
		
		//INICIALIZAR tDAO
		tDao = new TransaccionDAO();
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
	    if(i == 1)
	    	cDao.guardar(auxCoin); // se agrega moneda
	    return auxCoin;			
			
		}
	    
	
	
	
	/*
	 * Método privado que devuelve una instancia de cripto inicializada con valores ingresados en teclado...
	 */
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
	    
	    nombre = nombre.toUpperCase();
	    if (this.existeMoneda(nombre)) {
	    	System.out.println("Esta moneda ya existe en la base de datos.");
	    	return null;
	    }
	    	
	    System.out.println("Ingrese la sigla de la moneda");
	    String sigla = in.next();
	    
	    sigla = sigla.toUpperCase();
	    if (this.existeMoneda(sigla)) {
	    	System.out.println("Esta moneda ya existe en la base de datos.");
	    	return null;
	    }
	    	
	    
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
	    
	    return new Coin(nombre,sigla,TipoMoneda.fromString(tipo),price);
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
				+ "SIGLA (1), VALOR (2)\n: ");
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
		
		// Imprimir
		for (Coin c: list) {
			System.out.println(c.toString());
			System.out.println("------");
		}
		System.out.println("\033[4;33m" +"Cantidad de monedas: "+ monedas.size() + "\u001B[0m");
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
				+ "SIGLA (1), STOCK (2)\n:> ");
		Integer lectura = in.nextInt();
		while (lectura < 1 || lectura > 2) {
			System.out.printf("Valor Incorrecto:\n:> ");
			lectura = in.nextInt();
		}
		// Ordenar
		if (lectura.equals(1)) {
			list.sort(porSigla);
		} else if (lectura.equals(2)) {
			list.sort(porStock);
		}
		
		System.out.printf("SIGLA\t STOCK\n");
		for (Coin c : list) {
			System.out.printf("\033[1;37m%s\033[0m: \t \033[0;33m%f\033[0m\n", c.getSigla(),c.getStock());
		}
		
	}
	/*
	 * Actualiza la base de datos con la lista modificada durante la ejecución del programa
	 */
	public void actualizarUserDB() { 
		for (Usuario u:usuarios) {
			uDao.modificar(u);
		}
	}
	/*
	 * Actualiza la base de datos de monedas
	 */
	public void actualizarCoinDB() {
		for (Coin c:monedas) {
			cDao.modificar(c);
			cDao.guardar(c);
			
		}
	}
	public void actualizarActivosDB(Usuario user) {
		for (Saldo s: user.getBilletera().getArregloSaldo()) {
			s.setUser_id(user.getDNI());
			aDao.modificar(s);
			
			if (s.getID() == 0) {
				aDao.guardar(s);
			}
			
		}
	}
	public void listarUsuarios() {
		Collections.sort(usuarios);

		int i=1;
		for (Usuario u: usuarios) {
			System.out.print(i+".\n");
			System.out.println(u.toString()+"\n...");
			i++;
		}
	}
	public Usuario getUsuario(String DNI) { //Busca un usuario en la base mediante su DNI.
		for (Usuario u : usuarios) {
			if (u.getDNI().equals(DNI)) {
				for (Saldo s: this.saldosUsuarios) {
					if (s.getUser_id() == u.getDNI())
						u.getBilletera().agregarSaldo(s);
				}
				return u;
			}	
		}
		return null;
	}
	public void generarStock() {
		boolean aux=false;
		for(Coin c:monedas)
		{
			if (c.generarStock()) //modifica los valores en la lista.
				aux = true;
		}
		if (aux == false)
			System.out.println("¿Has entrado a esta opción para no cambiar nada?");
	}
	public List<Coin> getMonedas(){
		return monedas;
	}
	public Coin buscarMoneda(String siglaMoneda) {
		for (Coin c : this.getMonedas()) {
			if (c.getSigla().equals(siglaMoneda))
				return c;
		}
		
		return null;
	}
	public boolean existeMoneda(String moneda) {
		boolean existe = false;
		
		for (Coin coin : monedas) { 
			if (coin.getSigla().equals(moneda) || coin.getNombre().equals(moneda)) { //pregunta si el nombre o la sigla existe.
				existe = true;
				break;
			}
		}
		return existe;
	}
	public void devolverActivosUsuario(Usuario user) {
		
			for (Saldo s:this.saldosUsuarios) {
				if (s.getUser_id().equals(user.getDNI())) {
					user.getBilletera().agregarSaldo(s);
				}
			}
	}
	
}