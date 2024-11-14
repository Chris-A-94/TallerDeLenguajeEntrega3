package decoradores;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import vistas.Vista;

public class exitButton {
	private Vista vista;
	private JButton salida;
	public exitButton(Vista vista) {
		this.vista = vista;
		salida = this.vista.getExit();
		salida.addMouseListener(new MouseControlExit());
	}
	class MouseControlExit implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			vista.callExit();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			vista.callExit();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			vista.getExit().setBackground(new Color(0x94847b));
			vista.getExit().setForeground(new Color(0xf5ded0));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			vista.getExit().setBackground(new Color(0xE4E0E1));
			vista.getExit().setForeground(new Color(0xAB886D));
		}

	
	}
}
