package decoradores;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import vistas.Vista;

public class ExitButton {
	private Vista vista;
	private JButton salida;
	
	private Color defaultColorBG;
	private Color defaultColorFG;
	
	public ExitButton(Vista vista) {
		this.vista = vista;
		salida = this.vista.getExit();
		salida.addMouseListener(new MouseControlExit());
		
		defaultColorBG = salida.getBackground();
		defaultColorFG = salida.getForeground();
	}
	class MouseControlExit implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			vista.callExit();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			vista.getExit().setBackground(defaultColorBG);
			vista.getExit().setForeground(defaultColorFG);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			vista.getExit().setBackground(defaultColorBG );
			vista.getExit().setForeground(defaultColorFG);
		}

	
	}
}
