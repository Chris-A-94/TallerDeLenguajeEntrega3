package entregable1;

import java.util.LinkedList;
import java.util.List;

public class Servidor {

	public static void main(String[] args) {
		List<Usuario> listaUsuarios = new LinkedList<Usuario>();
		Usuario us = new Usuario();
		InterfazInicial inter = new InterfazInicial();
		inter.registroIngreso();
	}

}
