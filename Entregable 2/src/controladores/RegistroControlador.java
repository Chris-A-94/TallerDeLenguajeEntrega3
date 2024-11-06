package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		}
		this.vista.getBotonSalir().addMouseListener(new MouseControlExit());
		
	}
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
			try {

				this.tf.setText(null);
			}catch (NumberFormatException ex) {
				
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			try {
				this.tf.setText(null);
			}catch (NumberFormatException ex) {
				
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			tf.setBackground(new Color(0x94847b));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			tf.setBackground(new Color(0xD6C0B3));
		}
		
	}
	
}
