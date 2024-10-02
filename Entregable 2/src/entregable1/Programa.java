package entregable1;

import java.util.Scanner;

public class Programa {
	public static void main(String[] args) {
		final int _EXIT = 9;
		
		final Scanner in = new Scanner(System.in);
		
		//System sistema = new System();
		
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
			
			System.out.printf("[%d]\n", opt);
		} while (opt != _EXIT);
		
		
		
	}
}
