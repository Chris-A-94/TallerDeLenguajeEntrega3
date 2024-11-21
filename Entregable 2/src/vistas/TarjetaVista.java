package vistas;

import java.awt.Color;
import java.awt.GridLayout;
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
	
	private JLabel title;
	private JLabel precio;
	
	private JTextField textContent;
	
	public TarjetaVista(Coin moneda) {
		if (moneda == null) {
			
			return;
		}
		
		//Config JPanel
		this.setLayout(new GridLayout(5,1));
		this.setBackground(null);
		// Título
		title = new JLabel();
		title.setText(moneda.getNombre());
		title.setBackground(null);
		this.add(title);
		//Precio
		precio = new JLabel();
		precio.setText(moneda.getPrecio().toString());
		precio.setBackground(Color.RED);
		this.add(precio);
		
		// Cargar imagen de una URL
		
		URL url;
		try {
			if (moneda.getUrl_small()== null)
				return;
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
