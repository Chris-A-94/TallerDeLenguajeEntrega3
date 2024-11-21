package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import entregable1.Coin;

@SuppressWarnings("serial")
public class TarjetaVista extends JPanel {
	Coin moneda;
	
	private JLabel title;
	private JLabel precio;
	
	private JTextArea textContent;
	
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
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setHorizontalAlignment(title.CENTER);
		this.add(title);
		//Precio
		textContent = new JTextArea();
		textContent.setText("despresio: " + moneda.getPrecio().toString() + " nose: 123");
		textContent.setBackground(null);
		textContent.setFont(new Font("Arial", Font.PLAIN, 20));
		textContent.setBackground(Color.PINK);
		this.add(textContent);
		
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
