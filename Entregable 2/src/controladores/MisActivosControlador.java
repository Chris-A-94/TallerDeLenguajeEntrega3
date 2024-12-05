package controladores;

import entregable1.Sistema;
import entregable1.Usuario;
import vistas.MisActivosVista;

public class MisActivosControlador {
	MisActivosVista misActivosVista;
	
	public MisActivosVista getMisActivosVista() {
		return misActivosVista;
	}

	public MisActivosControlador(int width, Sistema sistema, Usuario usuario) {
		if (sistema == null || usuario == null) {
			System.out.println("MisActivosControlador::Sistema o Usuario es igual a null");
			return;
		}
		
		misActivosVista = new MisActivosVista(width, sistema, usuario);
	}
}
