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
		for (JTextComponent tf : vista.devolverCampos()) {
			//tf.addMouseListener(new MouseControlTexts(tf));
			tf.addFocusListener(new FocusControlTexts(tf));
		}
		this.vista.getBotonSalir().addMouseListener(new MouseControlExit());
		this.vista.getBotonAceptar().addMouseListener(new MouseControlAceptar());
		
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
	class FocusControlTexts implements FocusListener{
		private JTextComponent tf;
		private String firstValue;
		public FocusControlTexts(JTextComponent tf) {
			this.tf = tf;
			firstValue = tf.getText();
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			Color color = tf.getForeground();
			tf.setBackground(new Color(0xf5ded0));
			if (color.equals(new Color(0xAB886D))) {	
				this.tf.setText(null);
				this.tf.setForeground(new Color(0x291e17));
			}
			
			return;
		}

		@Override
		public void focusLost(FocusEvent e) {
			tf.setBackground(new Color(0xD6C0B3));
			if (tf.getText().equals("")) {
				tf.setText(firstValue);
				tf.setForeground(new Color(0xAB886D));
			if ((firstValue.equals("buenas@gmail.com"))) {
				System.out.println("HOLA");
				JButton warning = new JButton();
				ImageIcon icon = new ImageIcon(getClass().getResource("/Imagenes/warning.png"));
				Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				warning.setIcon(new ImageIcon(img));
				warning.setFont(new Font("Arial", Font.BOLD,20));
				warning.setBorderPainted(false);
				warning.setContentAreaFilled(false);

				warning.setIcon(icon);
				vista.add(warning);
			}	
			}
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
			vista.getBotonSalir().setForeground(new Color(0xf5ded0));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			vista.getBotonSalir().setBackground(new Color(0xE4E0E1));
			vista.getBotonSalir().setForeground(new Color(0xAB886D));
		}

	}

	
}
