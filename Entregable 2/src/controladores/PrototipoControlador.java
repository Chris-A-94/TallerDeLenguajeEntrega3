package controladores;


import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entregable1.Coin;
import vistas.MenuVista;

/*
 * Posible nombre: GestionMenuControlador
 */
public class PrototipoControlador {
	private MenuVista myMenu;
	
	public PrototipoControlador(List<Coin> monedas) {
		myMenu = new MenuVista();
		
		/*
		 *  ¡¡¡TEMPORAL!!!
		 */
		JPanel newPanel = new JPanel(null);
		JLabel label = new JLabel("Demo");
		
		try {
			Coin moneda = monedas.get(0);
			
			URL url = new URL(moneda.getUrl_large());
			
			Image imageURL = ImageIO.read(url);
			ImageIcon imageIcon = new ImageIcon(imageURL);
			
			label.setIcon(imageIcon);
			label.setText(String.format("<html>NOMBRE: %s<br/>SIGLA: %s<br/>PRECIO: %f</html>", moneda.getNombre(), moneda.getSigla(), moneda.getPrecio()));
			label.setBounds(0, 0, imageIcon.getIconWidth()+1000, imageIcon.getIconHeight());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newPanel.add(label);
		
		myMenu.agregarPanel("Bitcoin", newPanel);
	}
}
