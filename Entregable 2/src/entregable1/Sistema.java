package entregable1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private final int dimfCrip = 6;
	private List<Coin> monedas;
	private List<BlockChain> blockChain;
	private List<Usuario> usuarios;
	//private MonitoreoCoin APIcoins;
	
	public Sistema() {
		
		
		
	
	}
	public boolean crearMoneda() {
		
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		System.out.println("Ingrese el tipo de moneda (1) FIAT (2) Cripto");
		String tipo;
		while ((i != 1)&&(i != 0))
		{
			if (i == 1)
				tipo = "FIAT";
			else
				tipo = "Cripto";
		}
		System.out.println("Ingrese el tipo de moneda el nombre de la moneda");
		String nombre = in.next();
		System.out.println("Ingrese el precio en USD");
		Double price = in.nextDouble();
		System.out.println("Confirmar: (1) SI (2) NO");
		i = in.nextInt();
		while ((i != 1)&&(i != 0))
		{
			if (i == 1)
				return true;
			else
				return false;
		}
		return false;
	}
	public boolean listarStock() {
		//Esperar base de datita...
		return false;
	}
}
