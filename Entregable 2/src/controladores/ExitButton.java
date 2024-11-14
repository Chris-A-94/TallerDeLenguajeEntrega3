package controladores;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import vistas.*;

public class ExitButton {
	private Vista vista;
	private JButton exitbut;
	public ExitButton(Vista vista) {
		this.vista = vista;
		exitbut = this.vista.getExit();
		exitbut.addMouseListener(new MouseControlExit());
	}
	
	class MouseControlExit implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (exitbut != null)
				vista.close();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (exitbut != null)
				vista.close();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			exitbut.setBackground(new Color(0x94847b));
			exitbut.setForeground(new Color(0xf5ded0));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			exitbut.setBackground(new Color(0xE4E0E1));
			exitbut.setForeground(new Color(0xAB886D));
		}

	}

}
