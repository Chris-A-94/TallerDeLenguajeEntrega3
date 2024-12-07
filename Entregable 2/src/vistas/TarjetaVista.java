package vistas;

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
import javax.swing.SwingConstants;

import entregable1.Coin;

@SuppressWarnings("serial")
public class TarjetaVista extends JPanel {
	
	private JLabel title;
	private JButton botonCompra;
	private JButton botonSwap;
	private JTextArea textContent;
	private Coin moneda;
	public TarjetaVista(Coin c) {
		if (c == null) {
			return;
		}
		this.moneda = c;
		
		// Colores
		Color fondoContenido = new Color(0xD6C0B3);
		Color colorTitulo 	 = new Color(0x493628);
		Color textColor 		= new Color(0xD6C0B3);
		// LayoutManager	
		this.setLayout(new GridLayout(4,1));
		this.setBorder(BorderFactory.createLineBorder(Color.gray)); 
		
		
		// Título
		title = new JLabel();
		title.setText(" "+moneda.getNombre()+" ");
		title.setOpaque(true);
		title.setBackground(colorTitulo);
		title.setForeground(textColor);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title);
		// Contenido
		textContent = new JTextArea();
		DecimalFormat numberFormat = new DecimalFormat("#.000000");
		textContent.setText(" Precio: " + numberFormat.format(moneda.getPrecio()).toString() + "\n Stock: " + numberFormat.format(moneda.getStock()).toString());
		textContent.setBackground(null);
		textContent.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		textContent.setBackground(fondoContenido);
		textContent.setEditable(false);
		textContent.setHighlighter(null);
		this.add(textContent);
		
		// Botones
		// Comprar
		botonCompra = new JButton("COMPRAR");
		botonCompra.setBackground(new Color(0xAB886D));
		botonCompra.setFont(new Font("Arial", Font.PLAIN,17));
		botonCompra.setFocusPainted(false);
		// Swap	
		botonSwap = new JButton("SWAP");
		botonSwap.setBackground(new Color(0xAB886D));
		botonSwap.setFont(new Font("Arial", Font.PLAIN,17));
		botonSwap.setFocusPainted(false);
		this.add(botonCompra);
		this.add(botonSwap);
		
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
		return this.botonCompra;
	}
	
	public JButton getBotonSwap()
	{
		return this.botonSwap;
	}
	
	public JTextArea getTextContent()
	{
		return this.textContent;
	}
	
	public Coin getMoneda()
	{
		return this.moneda;
	}
}
