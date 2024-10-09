package entregable1;

import java.util.Scanner;

public class Programa {
	public static void main(String[] args) {
		final int _EXIT = 9;
		
		final Scanner in = new Scanner(System.in);
		
		Sistema sistema = new Sistema();
		
		Integer opt;
		
		do {
			 opt = -1;
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
			
			System.out.printf("[%d]\n", opt);
			
			switch (opt) {
			case 1: sistema.crearMoneda();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case _EXIT:
				break;
			default:
				System.out.printf("Opción incorrecta\n");
				break;
			}
		} while (opt != _EXIT);
		
		
		in.close();
	}
}
