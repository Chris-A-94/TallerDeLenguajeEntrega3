package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entregable1.Coin;
import entregable1.Saldo;
import entregable1.Sistema;
import entregable1.Usuario;

@SuppressWarnings("serial")
public class MisActivosVista extends JPanel {
	private List<Activo> listaActivos;
	
	public MisActivosVista(int width, Sistema sistema, Usuario user) {
		super();
		
		// LayoutManager
		this.setLayout(null);
		
		// Atributos
		this.setBounds(0, 0, width, 1200);
		
		this.setBackground(new Color(0xE4E0E1));
		
		// Instanciar Lista de 'Activo'
		listaActivos = new LinkedList<Activo>();
		// Agregar Saldos
		for (Saldo s : user.getBilletera().getArregloSaldo()) {
			this.agregarActivo(sistema.buscarMoneda(s.getSigla()), s);
		}
	}
	
	private void agregarActivo(Coin c, Saldo s) {
		Activo activo = new Activo(c, s);
		
		listaActivos.add(activo);
		this.add(activo);
	}
	
	private class Activo extends JButton {
		final Color defaultColor = new Color(0xD6C0B3);
		final Color lightColor	 = new Color(0xe3d4c4);
		final Color shadowColor	 = new Color(0xCAB0A3);
		Color boxColor;
		
		private String text;
		
		private JLabel label;
		
		// 'Saldo' que le corresponde a la clase
		private Coin moneda;
		private Saldo saldo;
		
		public Activo(Coin c, Saldo s) {
			super();
			
			this.saldo = s;
			this.setBounds(0, 42*MisActivosVista.this.listaActivos.size() + 15, MisActivosVista.this.getWidth() / 2, 40);
			
			this.moneda = c;
			
			text = String.format("        %s %s : %f US$", c.getNombre(), c.getSigla(), s.getCantMonedas() * c.getPrecio());
			
			this.setFont(new Font("Nimbus Roman", Font.PLAIN, 20));
			this.setText(text);
			
			this.boxColor = this.defaultColor;
			
			this.setFocusable(false);
			this.setBorder(null);
			this.setContentAreaFilled(false);
			
			URL url;
			try {
				if (c.getUrl_small()== null)
					return;
				url = new URL(moneda.getUrl_thumb());
				
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
			
			label = new JLabel();
			label.setIcon(imageIcon);
	
			label.setText(" " + String.valueOf(MisActivosVista.this.listaActivos.size() + 1) + ".");
			label.setHorizontalTextPosition(JLabel.LEFT);
			
			this.add(label);
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent (g);
			Graphics2D g2 = (Graphics2D)g;
			
			// Render Hints
			{
				g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			}
            
			// Dibujar la caja de fondo
			g2.setPaint(lightColor);  
			g2.fill(new RoundRectangle2D.Double(2, 0, this.getWidth() - 4, this.getHeight(), 10, 10));
			
			// Dibujar el texto
			Font font = this.getFont();
			
			// Cálculo de la posición del texto
			FontMetrics metrics = getFontMetrics(font);
			int dX = (this.getWidth() - metrics.stringWidth(this.getText())) / 2;
			int dY = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
			
			g2.setFont(font);
			g2.setPaint(new Color(0x3d3d3d));
			g2.drawString(this.getText(), 10, dY);
		}
	}
}