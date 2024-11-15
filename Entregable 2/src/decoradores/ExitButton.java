package decoradores;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import vistas.Vista;

public class ExitButton {
	public static void asignarComportamiento(Vista vista) {
		asignarComportamiento(vista, vista.getExit().getBackground());
	}
	public static void asignarComportamiento(Vista vista, Color bgColor) {
		Color defaultColorBG;
		Color defaultColorFG;
		
		JButton botonSalida = vista.getExit();
		
		// Si no existe el botón, cancelar la ejecución
		if (botonSalida == null)
			return;
		
		defaultColorBG = botonSalida.getBackground();
		defaultColorFG = botonSalida.getForeground();
		
		class MouseControlExit extends MouseAdapter{
			@Override
			public void mouseClicked(MouseEvent e) {
				vista.callExit();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				vista.getExit().setBackground(bgColor);
				vista.getExit().setForeground(defaultColorFG);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				vista.getExit().setBackground(defaultColorBG);
				vista.getExit().setForeground(defaultColorFG);
			}
		}
		
		botonSalida.addMouseListener(new MouseControlExit());
	}

}
