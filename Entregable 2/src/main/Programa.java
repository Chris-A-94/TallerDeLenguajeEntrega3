package main;

import java.util.Scanner;

import entregable1.*;
import modelos.MonitoreoCoin;
import controladores.PrototipoControlador;
import controladores.logInController;
import daos.CoinDAO;
import vistas.logInPage;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
public class Programa {
	private static Coin crearCripto(String sigla, Sistema sistema) {
		Coin return_coin = null;
		
		// Se asume que la sigla 
		
		Scanner in = new Scanner(System.in);
		TipoMoneda tipoMoneda = TipoMoneda.CRIPTOMONEDA;
		
		String nombre;
		
		System.out.printf("Ingrese el nombre de la moneda\n: ");
		nombre = in.next();
		if (sistema.existeMoneda(nombre)) {
			System.out.printf("Esta moneda ya existe en la base de datos.\n");
			return null;
		}
		
		sigla = sigla.toUpperCase();
		if (sistema.existeMoneda(sigla)) {
			System.out.printf("Esta moneda ya existe en la base de datos.\n");
			return null;
		}
		
		// Generar stock
		System.out.printf("Ingrese el precio en USD\n: ");
		double precio = in.nextDouble();
		while (precio <= 0.0) {
			System.out.printf("El precio no es un valor válido (Debe ser mayor que 0), intente de nuevo\n: ");
			precio = in.nextDouble();
		}
		
		int confirmar;
		System.out.printf("Confirmar: (1) SI (0) NO\n: ");
		confirmar = in.nextInt();
		while (confirmar < 0 || confirmar > 1) {
			System.out.printf("Opción incorrecta, intente de nuevo\n: ");
			confirmar = in.nextInt();
		}
		
		if (confirmar == 1) {
			System.out.printf("\u001B[46mSe registró la moneda %s en el Sistema\u001B[0m\n", sigla);
			return_coin = new Coin(nombre, sigla, tipoMoneda, precio);
			
			System.out.printf("¿Desea almacenar la moneda en la base de datos?: (1) SI (0) NO\n: ");
			confirmar = in.nextInt();
			while (confirmar < 0 || confirmar > 1) {
				System.out.printf("Opción incorrecta, intente de nuevo\n: ");
				confirmar = in.nextInt();
			}
			if (confirmar == 1) {
				sistema.guardarMonedaDB(return_coin);
				System.out.printf("\u001B[42mSe registró la moneda %s en la Base de Datos.\u001B[0m\n", sigla);
			} else {
				System.out.printf("\u001B[41mNo se registró la moneda %s en la Base de Datos.\u001B[0m\n", sigla);
			}
			
		} else {
			System.out.printf("\u001B[41mNo se registró la moneda %s en el Sistema\u001B[0m\n", sigla);
		}
		
		if (return_coin != null) {
			sistema.getMonedas().add(return_coin);
		}
		System.out.printf("----------------------------\n");
		
		return return_coin;
	}
	
	private static boolean existeMoneda(String sigla, List<Coin> monedas) {
		boolean existe = false;
		
		for (Coin coin : monedas) {
			if (coin.getSigla().equalsIgnoreCase(sigla)) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	private static void optGenerarActivos(Usuario user,Sistema sistema) {
			Scanner in = new Scanner(System.in);
			System.out.printf("[Generar Activos]\n"
					+ "Introduzca las siglas de la moneda (* para seleccionar todas)\n"
					+ "( ");
			for (Coin c : sistema.getMonedas()) {
				System.out.printf("%s ", c.getSigla());
			}
			System.out.printf(")\n:> ");
			
			String sigla = in.next();
			sigla = sigla.toUpperCase();
			while (!sistema.existeMoneda(sigla) && !sigla.equals("*")) {
				System.out.printf("La moneda %s no existe, intente de nuevo\n:> ", sigla);
				sigla = in.next();
				sigla = sigla.toUpperCase();
			}
			
			System.out.printf("Introduzca la cantidad de monedas a generar\n:> ");
			Double cantidad = in.nextDouble();
			while (cantidad < 0.0) {
				System.out.printf("La cantidad no es un valor válido, intente de nuevo\n:> ");
				cantidad = in.nextDouble();
			}
			
			boolean encontro = false;
			// Actualizar el saldo en todas las monedas
			if (sigla.equals("*")) {
				for (Coin m : sistema.getMonedas()) {
					encontro = false;
					for (Saldo s: user.getBilletera().getArregloSaldo())
						if (s.getSigla().equals(m.getSigla())) {
							// No toca activos ya generados.
							encontro = true;
						}
					if (encontro ==  false) {
						user.getBilletera().agregarSaldo(new Saldo(user.getDNI(), m.getTipo(), m.getSigla(), cantidad));
					}
						
				}
			// Actualizar unicamente la moneda indicada
			} else {
				// Busca si la moneda existe en el sistema
				if (!existeMoneda(sigla, sistema.getMonedas())) {
					System.out.printf("ERROR::LA_MONEDA_NO_EXISTE\n");
					return;
				}
				// Se busca la moneda en arregloSaldo[]
				for (Saldo saldo : user.getBilletera().getArregloSaldo()) {
					if (saldo.getSigla().equals(sigla)) {
						saldo.setCantMonedas(cantidad);
						return;
					}
				}
				// Si existe en la base de datos y no fue encontrada dentro de arregloSaldo[]...
				// Agrega la moneda al arregloSaldo[]
				Coin moneda = sistema.buscarMoneda(sigla);
				user.getBilletera().getArregloSaldo().add(new Saldo(user.getDNI(),moneda.getTipo(), moneda.getSigla(), cantidad));
				// Nota: Es medio redundante hacer esto pero existe la posibilidad de que la moneda exista en el Sistema y
				// que no se encuentre instanciada un 'Saldo' dentro de la billetera del usuario se agrega un paso extra con
				// el fin de evitar un error a futuro.
			}
			
			System.out.printf("La operación se realizó con exito\n");
	}
	private static void optListarActivos(Usuario user) {
		// Comparators
		// Nota: Se declara y define los 'Comparator's según por criterio unicamente dentro del
		// Scope de este método.
		if (user.getBilletera().getArregloSaldo().isEmpty()) {
			System.out.printf("No tenés activos en la cuenta!...\n");
			return;
		}
		if (user.getBilletera().getArregloSaldo().size() == 1) {
			Saldo s = user.getBilletera().getArregloSaldo().get(0);
			System.out.printf("\033[1;37m%s \033[0;33m%f\033[0m\n", s.getSigla(), s.getCantMonedas());
			return;
		}
		
		Comparator<Saldo> porSigla = new Comparator<Saldo>() {
			public int compare(Saldo s1, Saldo s2) {
				return s1.getSigla().compareTo(s2.getSigla());
			}
		};
		Comparator<Saldo> porCantidad = new Comparator<Saldo>() {
			public int compare(Saldo s1, Saldo s2) {
				return s1.getCantMonedas().compareTo(s2.getCantMonedas()) * (-1);
			}
		};
		
		// Scanner
		Scanner in = new Scanner(System.in);
		
		// Obtener lista de Activos (Saldos)
		LinkedList<Saldo> list = new LinkedList<Saldo>();
		for (Saldo saldo : user.getBilletera().getArregloSaldo()) {
			list.add(saldo);
		}
		
		// Pregunta
		System.out.printf("Ordenar por\n"
				+ "SIGLA (1), CANTIDAD (2)\n:> ");
		Integer lectura = in.nextInt();
		
		while (lectura < 1 || lectura > 2) {
			System.out.printf("Valor Incorrecto\n: ");
			lectura = in.nextInt();
		}
		
		// Ordenar
		if (lectura.equals(1)) {
			list.sort(porSigla);
		} else if (lectura.equals(2)) {
			list.sort(porCantidad);
		}
		
		// Listar los Activos
		System.out.printf("[Listar Activos]\n");
		for (Saldo s : list) {
			System.out.printf("\033[1;37m%s \033[0;33m%f\033[0m\n", s.getSigla(), s.getCantMonedas());
		}
	}
	private static void optCrearMoneda(Sistema sistema,Usuario temp) {
		Coin auxCoin = sistema.crearMoneda();
		}
	private static void optSimularCompra(Usuario temp, Sistema sistema)
	{
		
		Scanner in = new Scanner(System.in);
		
		// Monedas
		LinkedList<Coin> monedasCripto = new LinkedList<Coin>();
		LinkedList<Coin> monedasFiat = new LinkedList<Coin>();
		
		for (Coin c : sistema.getMonedas()) {
			if (c.getTipo().equals("Criptomoneda")) 
				monedasCripto.add(c);
		}
		for (Saldo s : temp.getBilletera().getArregloSaldo()) {
			if (s.getTipo().equals("Fiat"))
				monedasFiat.add(sistema.buscarMoneda(s.getSigla()));
		}
		if (monedasFiat.isEmpty()) {
			System.out.println("¡No tenés saldos cargados!\nIngresá a la opción 5 para generarlos.");
			return;
		}
		// Se listan todas las criptomonedas
		System.out.println("Ingrese sigla de crypto a comprar");
		System.out.printf("( ");
		for (Coin coin : monedasCripto) {
			System.out.printf("%s ", coin.getSigla());
		}
		System.out.printf(")\n: ");
		// Lectura de la Criptomoneda
		String siglaMoneda = in.next();
		siglaMoneda = siglaMoneda.toUpperCase();
		while(!existeMoneda(siglaMoneda,monedasCripto))
		{
			System.out.printf("La criptomoneda %s no existe, se procede a generarla.\n", siglaMoneda);
			if (crearCripto(siglaMoneda, sistema) == null) {
				System.out.printf("¿Desea cancelar la operación? (1) SI (0) NO\n: ");
				int opt = in.nextInt();
				while (opt < 0 || opt > 1) {
					in.nextInt();
				}
				if (opt == 0) {
					continue;
				}
				else {
					return;
				}
					
			}
			
			monedasCripto.clear();
			for (Coin c : sistema.getMonedas()) {
				if (c.getTipo().equals("Criptomoneda")) 
					monedasCripto.add(c);
			}
			if (existeMoneda(siglaMoneda, monedasCripto)) {
				break;
			}
		}
		
		// Se listan todas las monedas fiat
		System.out.println("Ingrese sigla de Fiat a usar");
		System.out.printf("( ");
		for (Coin c : monedasFiat) {
			System.out.printf("%s ", c.getSigla());
		}
		System.out.printf(")\n: ");
		
		// Lectura de la Moneda Fiat
		String siglaFiat = in.next();
		
		while(!existeMoneda(siglaFiat,monedasFiat))
		{
			System.out.printf("La moneda fiat %s no existe, intente de nuevo\n: ", siglaFiat);
			siglaFiat = in.next();
		}
		
		temp.getBilletera().comprar(sistema.buscarMoneda(siglaMoneda),siglaFiat,sistema.getMonedas());
	}

	public static Usuario leerUsuario() {
		return null;
	}
	
	public static void main(String[] args) {
		// Nota: En pantallas con alto DPI la UI se rompe.
		// Esto se soluciona forzando la escala en 1
		System.setProperty("sun.java2d.uiScale", "1");
		
		Sistema sistema = new Sistema();
		List<String> listaMonedas = new LinkedList<String>();
		listaMonedas.add("ethereum");
		listaMonedas.add("bitcoin");
		listaMonedas.add("dogecoin");
		listaMonedas.add("apu-s-club");
		//estas de abajo no andan, find out why
		/*listaMonedas.add("usdc");
		listaMonedas.add("usdt");*/

		MonitoreoCoin.setListaIDMonedas(listaMonedas);
		try {
			MonitoreoCoin.updateMonedas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Coin> list = MonitoreoCoin.getListaMonedas();
		list.addAll(sistema.getMonedas());		
		sistema.setMonedas(list);
		
		logInPage log = new logInPage();
		logInController logs = new logInController(log,sistema);
		
//		// Menu
//		PrototipoControlador prototipo = new PrototipoControlador(MonitoreoCoin.getListaMonedas());
//		PrototipoControlador prototipo = new PrototipoControlador(sistema, sistema.getUsuario("1@gmail.com"));

		/*
		final int _EXIT = 9;


		final Scanner in = new Scanner(System.in);
		
		//Esto es un login para un usuario administrador.
		String dniTemp;
        
        System.out.println("[LISTA USUARIOS]");
        sistema.listarUsuarios();
        System.out.print("----------------------------\n"
        		+ "Ingrese el DNI del usuario que va a usar\n:> ");
        dniTemp = in.next();
        while (temp == null)
        {
        	System.out.print("El DNI no existe, ingrese otro\n:> ");
            dniTemp = in.next();
            temp = sistema.getUsuario(dniTemp);
        }

        sistema.devolverActivosUsuario(temp);    
        //BUEN LUGAR PARA DEBUGGEAR BASE DE DATOS
        Integer opt = -1;
        
        do {
            System.out.printf("Seleccionar opción: \n"
            		+ "1. Crear moneda \n"
                    + "2. Listar monedas \n"
                    + "3. Generar Stock \n"
                    + "4. Listar Stock \n"
                    + "5. Generar Activos \n"
                    + "6. Listar Mis Activos \n"
                    + "7. Simular una compra \n"
                    + "8. Simular Swap \n"
                    + "%d. \033[1;37mCerrar\033[0m\n"
                    + "opt: ", _EXIT);
            
            opt = in.nextInt();
            System.out.printf("----------------------------\n");
            if (!opt.equals(_EXIT))
            	System.out.printf("[%d]\n", opt);

			switch (opt) {
			case 1:
			    optCrearMoneda(sistema,temp);
			    break;
			case 2:
				sistema.listarMonedas();
			    break;
			case 3:
				sistema.generarStock();
			    break;
			case 4: 
				sistema.listarStock();
			    break;
			case 5:
				optGenerarActivos(temp,sistema);
				// Actualización de DB
				sistema.actualizarActivosDB(temp);
			    break;
			case 6:
				optListarActivos(temp);
			    break;
			case 7:
				optSimularCompra(temp,sistema);
				// Actualización de DB
				sistema.actualizarCoinDB();
		        sistema.actualizarActivosDB(temp);
				break;
			case 8:
				temp.getBilletera().swap(sistema.getMonedas());
				// Actualización de DB
				sistema.actualizarActivosDB(temp);
			    break;
			case _EXIT:
			    break;
			default:
			    System.out.printf("Opción incorrecta, intente de nuevo\n");
			    break;
			}
			if (opt != _EXIT) {
				System.out.println("----------------------------\n"
						+ "Presione [ENTER] para continuar...");
				try{System.in.read();}
				catch(Exception e){}
			}
			
        } while (opt != _EXIT);
        
        in.close();//El scanner solo se cierra acá, para evitar problemas en lectura.
        
        System.out.printf("Fin.\n");
    
    */
	}	
}
