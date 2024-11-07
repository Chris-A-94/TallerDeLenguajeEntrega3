package controladores;

import java.util.List;

import javax.swing.text.JTextComponent;
import vistas.vista;

public class ControladorTextField{
	private vista vista;
	private List<JTextComponent> campos; 
	public ControladorTextField(vista vista) {
		this.vista = vista;
		campos.addAll(this.vista.devolverCamposTexto());
	
	}
	
	
}
