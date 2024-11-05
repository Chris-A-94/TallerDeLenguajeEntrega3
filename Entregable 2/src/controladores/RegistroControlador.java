package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vistas.RegistroVista;

public class RegistroControlador {
	p RegistroVista vista;
	public RegistroControlador(RegistroVista vista) {
		this.vista = vista;
		this.vista.addMouseListener(new eraseLabel());
	}
	class eraseLabel implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
