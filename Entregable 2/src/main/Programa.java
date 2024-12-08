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
		listaMonedas.add("usd-coin");
		listaMonedas.add("tether");

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
		
//Menu
//		PrototipoControlador prototipo = new PrototipoControlador(MonitoreoCoin.getListaMonedas());
//		PrototipoControlador prototipo = new PrototipoControlador(sistema, sistema.getUsuario("3@gmail.com"));

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
