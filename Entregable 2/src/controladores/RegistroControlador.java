package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import vistas.RegistroVista;

public class RegistroControlador {
	private RegistroVista vista;
	public RegistroControlador(RegistroVista vista) {
		this.vista = vista;
		for (JTextField tf : vista.devolverCampos()) {
			tf.addMouseListener(new controlMouse(tf));
		}
	}
	class controlMouse implements MouseListener{
		private JTextField tf;
		public controlMouse(JTextField tf) {
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			tf.setSelectedTextColor(Color.BLUE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		
		}
		
	}
	
}
