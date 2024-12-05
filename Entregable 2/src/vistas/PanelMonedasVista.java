package vistas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelMonedasVista extends JPanel{
	private static final long serialVersionUID = 1L;

	public PanelMonedasVista(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(3,20,20));
        this.setBackground(null);
	}
	
	public void agregarMoneda(JPanel aux) {
		this.add(aux);
	}
	
	
}
