package controladores;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import entregable1.Coin;
import entregable1.TipoMoneda;
import entregable1.Usuario;
import vistas.PanelMonedasVista;
import vistas.TarjetaVista;

public class PanelMonedasControlador {
	final int _POPUP_HEIGHT = 75;
	
	JLayeredPane layeredPane;
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	PanelMonedasVista panelMonedas;
	public PanelMonedasVista getPanelMonedas() {
		return panelMonedas;
	}

	List<TarjetaVista> listaTarjetas;
	
	public List<TarjetaVista> getListaTarjetas() {
		return listaTarjetas;
	}

	public PanelMonedasControlador(List<Coin> listaMonedas, Usuario user, int width, int height) {
		// Instanciar JLayeredPane principal
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(0, 0, width, height);
		
		// Instanciar PanelMonedasVista
		panelMonedas = new PanelMonedasVista(width, height);
		panelMonedas.setBounds(0, 0, width, height);
		
		// Instanciar lista de TarjetaVista
		listaTarjetas = new LinkedList<TarjetaVista>();
		
		// Agregar monedas
		for (Coin c : listaMonedas) {
			if (c.getTipo().equals(TipoMoneda.CRIPTOMONEDA))
			{
				agregarMoneda(c);
			}
		}
		
		// Asignar comportamiento a las tarjetas
		this.getListaTarjetas().forEach(t -> {
			new TarjetaVistaControladorCompra(t, user);
			new TarjetaVistaControladorSwap(t, user);
		});
		
		//
		OpPanel opPanel = new OpPanel(height - _POPUP_HEIGHT, width, _POPUP_HEIGHT);
		layeredPane.add(opPanel, JLayeredPane.POPUP_LAYER);
		
		// Agregar PanelMonedasVista al JLayeredPane
		layeredPane.add(panelMonedas, JLayeredPane.DEFAULT_LAYER);
	}
	
	public void agregarMoneda(Coin c) {
		// Instanciar Tarjeta
		TarjetaVista tarjeta = new TarjetaVista(c);
		// Agregar Tarjeta a la lista
		listaTarjetas.add(tarjeta);
		
		// Agregar Tarjeta al panel
		panelMonedas.agregarMoneda(tarjeta);
	}
	
	public class OpPanel extends JLayeredPane {
		private JPanel background,
					content;
		
		private JComboBox comboBox;
		
		private String[] selection = {"1", "2", "3", "abc", "1", "2", "3", "abc", "1", "2", "3", "abc"};
		
		public OpPanel(int PosY, int width, int height) {
			// this: Atributos
			this.setLayout(null);
			this.setBounds(0, PosY, width, height);
			this.setBackground(null);
			
			// background: Instanciar
			background = new JPanel() {
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
					g2.setPaint(Color.RED);  
					g2.fill(new RoundRectangle2D.Double(0, 0, width-10, height, 10, 10));
					
//					// Dibujar el texto
//					Font font = this.getFont();
//					
//					// Cálculo de la posición del texto
//					FontMetrics metrics = getFontMetrics(font);
//					int dX = (this.getWidth() - metrics.stringWidth(this.getText())) / 2;
//					int dY = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
//					
//					g2.setFont(font);
//					g2.setPaint(new Color(0x3d3d3d));
//					g2.drawString(text, 10, dY);
				}
			
			};
			// background: Atributos
			background.setLayout(null);
			background.setBounds(0, 0, width, height);
			background.setBackground(null);
			
			this.add(background);
			
			// content: Instanciar
			content = new JPanel();
			// content: Atributos
			content.setLayout(null);
			content.setBounds(0, 0, width, height);
			content.setOpaque(false);
			
			this.add(content, JLayeredPane.POPUP_LAYER);
			
			// comboBox: Instanciar
			comboBox = new JComboBox(selection);
			// comboBox: Atributos
			comboBox.setBounds(0, 0, 200, 40);
			
			content.add(comboBox);
		}
	}
}
