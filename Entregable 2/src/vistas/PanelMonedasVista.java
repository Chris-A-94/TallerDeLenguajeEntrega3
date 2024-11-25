package vistas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelMonedasVista extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public PanelMonedasVista() {
		this.setPreferredSize(new Dimension(1000,400));
        this.setLayout(new FlowLayout(3,40,40));
        this.setBackground(null);
	}
	
	
	public void agregarMoneda(JPanel aux) {
		this.add(aux);
	}
	
	
}
