package vistas;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entregable1.Transaccion;

public class TransaccionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransaccionPanel() {
		//this.setBounds(100, 100, 100, 100);	 
		this.setLayout(new GridLayout(3,3));
		JLabel aux = new JLabel("AAAAAAA");
		this.add(aux);
	}
	
	public void agregarTransaccion(String t) {
		JLabel aux = new JLabel(t);
		aux.setBounds(100, 100, 100, 100);
		this.add(aux);
	}
}


