package entregable1;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

import daos.CoinDAO;

public class Programa {
	private static boolean existeMoneda(String sigla) {
		boolean existe = false;
		
		CoinDAO myCoin = new CoinDAO();
		List<Coin> monedas = new LinkedList<Coin>();
		monedas.addAll(myCoin.devolverTabla());
		
		for (Coin coin : monedas) {
			if (coin.getSigla().equals(sigla)) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	private static void optGenerarActivos(Usuario user) {
			Scanner in = new Scanner(System.in);
			
			System.out.printf("[Generar Activos]\n"
					+ "Introduzca las siglas de la moneda (* para seleccionar todas): ");
			String sigla = in.next();
			System.out.printf("Introduzca la cantidad de monedas: ");
			Double cantidad = in.nextDouble();
			
			// Actualizar el saldo en todas las monedas
			if (sigla.equals("*")) {
				for (Saldo saldo : user.getBilletera().getArregloSaldo()) {
					saldo.setCantMonedas(cantidad);
				}
			// Actualizar unicamente la moneda indicada
			} else {
				// Busca si la moneda existe en la base de datos
				
				if (!existeMoneda(sigla)) {
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
		LinkedList<Saldo> list = new LinkedList<Saldo>();
		for (Saldo saldo : user.getBilletera().getArregloSaldo()) {
			list.add(saldo);
		}
		list.sort(null);
		System.out.printf("[Listar Activos]\n"
				+ "%s\n", list.toString());
	}
	private static void optCrearMoneda(Usuario user, Sistema sistema) {
		Coin auxCoin = sistema.crearMoneda();
		user.getBilletera().agregarMoneda(auxCoin);
	}
	
	
	public static Usuario leerUsuario() {
		return null;
	}
	
	public static void main(String[] args) {
		final int _EXIT = 9;

        //Agregar Login y Register...


		final Scanner in = new Scanner(System.in);
		
        Sistema sistema = new Sistema();
        Usuario temp = new Usuario(new String("admin"),
        						new String("0123"),
        						new String("Argentina"));
        
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
                    + "10. remove\n"
                    + "opt: ");
            
            opt = in.nextInt();
            System.out.printf("[%d]\n\n", opt);

			switch (opt) {
			case 1:
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
				optGenerarActivos(temp);
			    break;
			case 6:
				optListarActivos(temp);
			    break;
			case 7:
			    break;
			case 8:
			    break;
			case _EXIT:
			    break;
			case 10: sistema.removerMoneda();
				break;
			default:
			    System.out.printf("Opción incorrecta\n");
			    break;
			}
			System.out.println("Presione [ENTER] para continuar...");
			try{System.in.read();}
			catch(Exception e){}
		
        } while (opt != _EXIT);

        in.close();
    }
}
