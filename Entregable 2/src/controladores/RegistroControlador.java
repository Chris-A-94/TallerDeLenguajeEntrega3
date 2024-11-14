package controladores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;

import entregable1.Usuario;
import vistas.RegistroVista;

public class RegistroControlador {
	private RegistroVista vista;
	
	public RegistroControlador(RegistroVista vista) {
		this.vista = vista;
	}
	class MouseControlAceptar implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			List<JTextComponent> listaTexto = vista.devolverCampos();	
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
