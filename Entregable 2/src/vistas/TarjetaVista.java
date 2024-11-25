package vistas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import entregable1.Coin;

@SuppressWarnings("serial")
public class TarjetaVista extends JPanel {
	
	private JLabel title;
	private JButton comprar;
	private JTextArea textContent;
	
	public TarjetaVista(Coin moneda) {
		if (moneda == null) {
			
			return;
		}
		//colores
		Color fondoContenido = new Color(0xD6C0B3);
		Color colorTitulo 	 = new Color(0x493628);
		Color textColor 		= new Color(0xD6C0B3);
		//Config JPanel
		this.setLayout(new GridLayout(3,1));
		this.setBorder(BorderFactory.createLineBorder(Color.gray)); 
		
		// Título
		title = new JLabel();
		title.setText(" "+moneda.getNombre()+" ");
		title.setOpaque(true);
		title.setBackground(colorTitulo);
		title.setForeground(textColor);
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setHorizontalAlignment(title.CENTER);
		this.add(title);
		//Precio
		textContent = new JTextArea();
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		textContent.setText("Precio: " + numberFormat.format(moneda.getPrecio()).toString() + "\nStock: " + numberFormat.format(moneda.getStock()).toString());
		textContent.setBackground(null);
		textContent.setFont(new Font("Arial", Font.PLAIN, 20));
		textContent.setBackground(fondoContenido);
		this.add(textContent);
		//Comprar
		comprar = new JButton("COMPRAR");
		comprar.setBackground(new Color(0xAB886D));
		comprar.setFont(new Font("Arial", Font.PLAIN,20));
		comprar.setFocusPainted(false);
		
		this.add(comprar);
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
	
	public JButton getBotonComprar()
	{
		return this.comprar;
	}
}
