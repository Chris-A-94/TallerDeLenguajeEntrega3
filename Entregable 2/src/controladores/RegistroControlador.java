package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import vistas.RegistroVista;

public class RegistroControlador {
	private RegistroVista vista;
	public RegistroControlador(RegistroVista vista) {
		this.vista = vista;
		for (JTextComponent tf : vista.devolverCampos()) {
			tf.addMouseListener(new MouseControlTexts(tf));
			tf.addFocusListener(new FocusControlTexts(tf));
		}
		this.vista.getBotonSalir().addMouseListener(new MouseControlExit());
		this.vista.getBotonAceptar().addMouseListener(new MouseControlExit());
		
	}
	class FocusControlTexts implements FocusListener{
		private JTextComponent tf;
		public FocusControlTexts(JTextComponent tf) {
			this.tf = tf;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			Color color = tf.getForeground();
			
			if (color.equals(new Color(0xAB886D))) {	
				this.tf.setText(null);
				this.tf.setForeground(new Color(0x291e17));
			
			}
			return;
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/*
	 * Controlador del botón de salida.
	 */
	class MouseControlExit implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			vista.dispose();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			vista.dispose();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			vista.getBotonSalir().setBackground(new Color(0x94847b));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			vista.getBotonSalir().setBackground(new Color(0xE4E0E1));
		}

	}
	class MouseControlTexts implements MouseListener{
		private JTextComponent tf;
		public MouseControlTexts(JTextComponent tf) {
			this.tf = tf;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			//nada
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//nada
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			tf.setBackground(new Color(0xf5ded0));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			tf.setBackground(new Color(0xD6C0B3));
		}
		
	}
	
}
