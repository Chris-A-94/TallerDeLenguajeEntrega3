package main;

import entregable1.*;
import modelos.MonitoreoCoin;
import vistas.logInPage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import controladores.logInController;
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
//		PrototipoControlador prototipo = new PrototipoControlador(sistema, sistema.getUsuario("Chris@gmail.com"));

		
	}	
}
