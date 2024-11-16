package controladores;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vistas.MenuVista;

/*
 * Posible nombre: GestionMenuControlador
 */
public class PrototipoControlador {
	private MenuVista myMenu;
	
	public PrototipoControlador() {
		myMenu = new MenuVista();
		
		/*
		 *  ¡¡¡TEMPORAL!!!
		 */
		JPanel newPanel = new JPanel(null);
		JLabel label = new JLabel("Demo");
		ImageIcon image = new ImageIcon("1.gif");
		
		label.setIcon(image);
		label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		
		newPanel.add(label);
		
		myMenu.agregarPanel("Activos", newPanel);
	}
}
