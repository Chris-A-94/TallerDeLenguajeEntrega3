package entregable1;

import java.util.Scanner;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import daos.CoinDAO;


public class Programa {
	
	private static void optGenerarActivos(Usuario user,Sistema sistema) {
			Scanner in = new Scanner(System.in);
			System.out.printf("[Generar Activos]\n"
					+ "Introduzca las siglas de la moneda (* para seleccionar todas): ");
			String sigla = in.next();
			System.out.printf("Introduzca la cantidad de monedas: ");
			Double cantidad = in.nextDouble();
			boolean encontro = false;
			// Actualizar el saldo en todas las monedas
			if (sigla.equals("*")) {
				for (Coin m : sistema.getMonedas()) {
					encontro = false;
					for (Saldo s: user.getBilletera().getArregloSaldo())
						if (s.getSigla().equals(m.getSigla())) {
							encontro = true;
						}
					if (encontro ==  false)
						user.getBilletera().agregarMoneda(m, cantidad);
				}
			// Actualizar unicamente la moneda indicada
			} else {
				// Busca si la moneda existe en la base de datos
				
				if (!sistema.existeMoneda(sigla)) {
					System.out.printf("ERROR::DB::LA_MONEDA_NO_EXISTE\n");
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
				// Nota: Es medio redundante hacer esto pero existe la posibilidad de que la moneda exista en la base de datos
				// y que no se encuentre instanciada un 'saldo' dentro de la billetera del usuario, para quitarle responsabilidad 
				// a '.optCrearMoneda()', se agrega un paso extra con el fin de evitar un posible error en el futuro.
				user.getBilletera().getArregloSaldo().add(new Saldo(sigla, cantidad));
			}
	}
	private static void optListarActivos(Usuario user) {
		// Comparators
		// Nota: Se declara y define los 'Comparator's según por criterio unicamente dentro del
		// Scope de este método.
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
			list.sort(porCantidad);
		}
		
		// Listar los Activos
		System.out.printf("[Listar Activos]\n"
				+ "%s\n", list.toString());
	}
	private static void optCrearMoneda(Usuario user, Sistema sistema) {
		Coin auxCoin = sistema.crearMoneda();
		//user.getBilletera().agregarMoneda(auxCoin);
	}
	private static void optSimularCompra(Usuario temp,Sistema sistema)
	{
		if (temp.getBilletera().getArregloSaldo().isEmpty()) {
			System.out.println("¡No tenés saldos cargados!\nIngresá a la opción 5 para generarlos.");
			return;
		}
		System.out.println("Ingrese sigla de crypto a comprar (BTC/ETH/USDT): ");
		Scanner in = new Scanner(System.in);
		String moneda = in.next();
		boolean existe = sistema.existeMoneda(moneda);
		if(!existe)
		{
			System.out.println("La moneda actual no existe, se procede a generarla: ");
			optCrearMoneda(temp,sistema);
		}
		System.out.println("Ingrese sigla de Fiat a usar (USD/ARS/EUR):");
		String fiat = in.next();
		existe = sistema.existeMoneda(fiat);
		
		if(!existe)
		{
			System.out.println("La divisa Fiat no esta cargada, se procede a generarla: ");
			optCrearMoneda(temp,sistema);
		}
		
		//esto es para chequear el tipo, hasta que se me ocurra algo mas conveniente.
		CoinDAO monedasDB = new CoinDAO();
		LinkedList<Coin> monedasMem = new LinkedList<Coin>();
		monedasMem.addAll(monedasDB.devolverTabla());
		Coin auxFiat = null;
		Coin auxMoneda = null;
		for(Coin monedaAux: monedasMem)
		{
			if(fiat.equals(monedaAux.getSigla()))
				auxFiat = monedaAux;
			if(moneda.equals(monedaAux.getSigla()))
				auxMoneda = monedaAux;
		}
		
		if(auxFiat.getTipo().equals("CRIPTOMONEDA") || auxMoneda.getTipo().equals("FIAT"))
		{
			System.out.println("Error, necesita una moneda de tipo fiat para comprar una de tipo cripto.");
			return;
		}
		
		temp.getBilletera().comprar(moneda,fiat);
		
	}
	
	public static Usuario leerUsuario() {
		return null;
	}
	
	public static void main(String[] args) {
		final int _EXIT = 9;


		final Scanner in = new Scanner(System.in);

		
		//Esto es un login para un usuario administrador.
		String dniTemp;
        Sistema sistema = new Sistema();
        System.out.println("[LISTA USUARIOS]");
        sistema.listarUsuarios();
        System.out.print("Ingrese el DNI del usuario que va a usar: ");
        dniTemp = in.next();
        Usuario temp = sistema.getUsuario(dniTemp);
        while (temp == null)
        {
        	System.out.print("El DNI no existe, ingrese otro: ");
            dniTemp = in.next();
            temp = sistema.getUsuario(dniTemp);
            
        }
        sistema.devolverActivosUsuario(temp);    
        System.out.println(temp.getBilletera().getArregloSaldo().toString());
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
                    + "9. Cerrar\n"
                    + "opt: ");
            
            opt = in.nextInt();
            System.out.printf("[%d]\n\n", opt);

			switch (opt) {
			case 1:
			    /*
			     * Acá modificaríamos la lista de saldos de todos los usuarios almacenados en sistema,
			     * para este entregable solo actualizaremos el usuario 'temp'
			     */

			    optCrearMoneda(temp, sistema);

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
			    break;
			case 6:
				optListarActivos(temp);
			    break;
			case 7:
				optSimularCompra(temp,sistema);
				break;
			case 8:
			    break;
			case _EXIT:
			    break;
			default:
			    System.out.printf("Opción incorrecta\n");
			    break;
			}
			if (opt != 9) {
				System.out.println("Presione [ENTER] para continuar...");
				try{System.in.read();}
				catch(Exception e){}
			
			}
		
        } while (opt != _EXIT);
        
        //GUARDAR LOS CAMBIOS EN TEMP Y EN SISTEMA
        sistema.actualizarCoinDB();
        System.out.println(temp.getBilletera().getArregloSaldo().toString());
        sistema.actualizarActivosDB(temp);
        
        in.close();//El scanner solo se cierra acá, para evitar problemas en lectura.
    }
}
