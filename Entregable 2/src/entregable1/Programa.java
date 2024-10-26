package entregable1;

import java.util.Scanner;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Programa {
	private static boolean existeMoneda(String sigla, List<Coin> monedas) {
		boolean existe = false;
		
		for (Coin coin : monedas) {
			if (coin.getSigla().equals(sigla)) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
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
				if (!existeMoneda(sigla, sistema.getMonedas())) {
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
				user.getBilletera().getArregloSaldo().add(new Saldo(sigla, cantidad));
				// Nota: Es medio redundante hacer esto pero existe la posibilidad de que la moneda exista en el Sistema y
				// que no se encuentre instanciada un 'Saldo' dentro de la billetera del usuario se agrega un paso extra con
				// el fin de evitar un error a futuro.
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
				+ "SIGLA (1), CANTIDAD (2)\n: ");
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
		System.out.printf("[Listar Activos]\n"
				+ "%s\n", list.toString());
	}
	private static void optCrearMoneda(Sistema sistema) {
		Coin auxCoin = sistema.crearMoneda();
	}
	private static void optSimularCompra(Usuario temp, Sistema sistema)
	{
		if (temp.getBilletera().getArregloSaldo().isEmpty()) {
			System.out.printf("No tiene saldos cargados para este usuario.\n");
			return;
		}
		
		// Monedas
		LinkedList<Coin> monedasCripto = new LinkedList<Coin>();
		LinkedList<Coin> monedasFiat = new LinkedList<Coin>();
		
		sistema.getMonedas().forEach((coin) -> {
			if (coin.getTipo().equals("Criptomoneda")) {
				monedasCripto.add(coin);
			} else if (coin.getTipo().equals("Fiat")) {
				monedasFiat.add(coin);
			}
		});
		
		// Scanner
		Scanner in = new Scanner(System.in);
		
		// Se listan todas las criptomonedas
		System.out.println("Ingrese sigla de crypto a comprar");
		System.out.printf("( ");
		for (Coin coin : monedasCripto) {
			System.out.printf("%s ", coin.getSigla());
		}
		System.out.printf(")\n: ");
		// Lectura de la Criptomoneda
		String siglaMoneda = in.next();
		
		if(!existeMoneda(siglaMoneda,monedasCripto))
		{
			System.out.println("La moneda actual no existe, se procede a generarla.");
			optCrearMoneda(sistema);
		}
		
		// Se listan todas las monedas fiat
		System.out.println("Ingrese sigla de Fiat a usar");
		System.out.printf("( ");
		for (Coin coin : monedasFiat) {
			System.out.printf("%s ", coin.getSigla());
		}
		System.out.printf(")\n: ");
		
		// Lectura de la Moneda Fiat
		String siglaFiat = in.next();
		
		while (!existeMoneda(siglaFiat,monedasFiat)) {
			System.out.printf("La sigla no existe\n: ");
			siglaFiat = in.next();
		}
		
//		Nota: Para las consultas de las monedas presentes en el Sistema, se debe intentar reducir los accesos 
//		a la Base de Datos y en su lugar, usar el arreglo 'arregloSaldo[]' de 'Sistema'.
//		Se presenta otra solución al problema, la cual es crear un arreglo por cada tipo de moneda (Cripto o Fiat)
//		y consultar la presencia de la moneda en estos arreglos.
		
		temp.getBilletera().comprar(siglaMoneda,siglaFiat);	
	}
	
	public static Usuario leerUsuario() {
		return null;
	}
	
	public static void main(String[] args) {
		final int _EXIT = 9;


		final Scanner in = new Scanner(System.in);

		TransaccionCompra transac = new TransaccionCompra(1000.0, 1, 1, 1);
		System.out.printf("%s\n", transac.toString());
		
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
			    optCrearMoneda(sistema);
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
			System.out.println("Presione [ENTER] para continuar...");
			try{System.in.read();}
			catch(Exception e){}
		
        } while (opt != _EXIT);
        
        //GUARDAR LOS CAMBIOS EN TEMP Y EN SISTEMA
        sistema.actualizarCoinDB();
        
        in.close();//El scanner solo se cierra acá, para evitar problemas en lectura.
    }
}
