package vistas;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entregable1.Coin;

@SuppressWarnings("serial")
public class TarjetaVista extends JPanel {
	Coin moneda;
	
	JLabel title;
	JTextField textContent;
	
	public TarjetaVista(Coin moneda) {
		if (moneda == null) {
			
			return;
		}
		
		// Título
		title = new JLabel();
		
		title.setText(moneda.getNombre());
		// Cargar imagen de una URL
		URL url;
		try {
			url = new URL(moneda.getUrl_small());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		Image image;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		ImageIcon imageIcon = new ImageIcon(image);
		
		title.setIcon(imageIcon);		
	}
}
