package controladores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.text.JTextComponent;
import vistas.Vista;

public class ControladorTextField{
	private Vista vista;
	private List<JTextComponent> campos; 
	public ControladorTextField(Vista vista) {
		this.vista = vista;
		for (JTextComponent tc : vista.devolverCamposTexto()) {
			tc.addFocusListener(new FocusControlTexts(tc));
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
				//vista.add(warning);
			}	
			}
		}
		
	}

	
}
