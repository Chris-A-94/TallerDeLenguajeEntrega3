package vistas;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelMonedasVista extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelMonedasVista() {
		this.setLayout(new FlowLayout());
		this.setBackground(null);
	}
	
	
	public void agregarMoneda(JPanel aux) {
		this.add(aux);
	}
	
	
}
