package vistas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class MenuVista extends JFrame implements Vista {
	
	private int _WIDTH 	= 1270,
				_HEIGHT = 720;
	
	private LateralPanel lateralPanel;
	private ContentPanel contentPanel;
	private WindowBarPanel windowBar;
	
	private JLayeredPane mainPane;
	
	public MenuVista() {
		// Atributos
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(_WIDTH, _HEIGHT);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(0xE4E0E1));
		
		// Configuración 'Look and Feel'
		UIManager.put("Button.disabledText", new ColorUIResource(new Color(0x5e8da8)));
		
		// LayoutManager
		this.setLayout(null);
		this.setShape(new RoundRectangle2D.Double(0, 0, _WIDTH, _HEIGHT, 25, 25));
		
		// Instanciación de los Componentes
		mainPane = new JLayeredPane();
		mainPane.setBounds(0, 0, _WIDTH, _HEIGHT);
		mainPane.setBackground(new Color(0xE4E0E1));
		mainPane.setOpaque(false);
		
		this.lateralPanel = new LateralPanel();
		ContentPanel contentPanel1 = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), null);
		ContentPanel contentPanel2 = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		ContentPanel contentPanel3 = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		ContentPanel contentPanel4 = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		ContentPanel contentPanel5 = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());

		lateralPanel.newButton("Mis Activos", contentPanel1);
		lateralPanel.newButton("Visualizar Cryptos", contentPanel2);
		lateralPanel.newButton("Comprar Crypto", contentPanel3);
		lateralPanel.newButton("Swap Crypto", contentPanel4);
		lateralPanel.newButton("Mis transacciones", contentPanel5);
		
		this.windowBar = new WindowBarPanel(this, new Color(0x493628), _WIDTH, 40,true);
		
		// Setup layers
		this.mainPane.add(lateralPanel, JLayeredPane.POPUP_LAYER);
		this.mainPane.add(windowBar, JLayeredPane.DRAG_LAYER);
		this.mainPane.add(contentPanel1, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(contentPanel2, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(contentPanel3, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(contentPanel4, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(contentPanel5, JLayeredPane.DEFAULT_LAYER);
		
		// Add MainPane
		this.add(mainPane);
		
		// Set Default Panel
		if (lateralPanel.getButtons().size() > 0) {
			contentPanel = lateralPanel.getButtons().get(0).getPanel();
			lateralPanel.getButtons().get(0).selectButton();
			contentPanel.enablePanel();
		}
		
		this.agregarPanel("Tutucas.", new JPanel(null));
		
		this.setVisible(true);
	}
	
	public void agregarPanel(String title, JPanel panel) {
		ContentPanel newPanel = new ContentPanel(this, new Color(0xFF0000), new Color(0xE4E0E1), null);
		newPanel.setTitle(title);
		this.lateralPanel.newButton(title, newPanel);
		this.mainPane.add(newPanel);
	}
	
	private class LateralPanel extends JPanel {
		private static final long serialVersionUID = 332812813329689766L;
		private LinkedList<RedButton> buttons = new LinkedList<RedButton>();
		
		public LinkedList<RedButton> getButtons() {
			return buttons;
		}

		public LateralPanel() {
			this.setBackground(new Color(0xD6C0B3));
			this.setBounds(0, 40, 180, _HEIGHT - 40);
			this.setLayout(null);
			
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x493628)));
		};
		
		public void newButton(String msg, ContentPanel panel) {
			RedButton button = new RedButton(panel);
			// Asigno el mensaje al Botón.
			button.setText(msg);
			// Asigno el título
			panel.setTitle(msg);
			
			// Agregar al Panel
			this.add(button);
			buttons.add(button);
		}
		public void resetButtons() {
			for (RedButton button : buttons) {
				button.resetButton();
			}
		}
	
		private class RedButton extends JButton {
			private static final long serialVersionUID = 228929574725506575L;
			ContentPanel panelAsignado;
			
			boolean selected 	= false;
			
			final Color defaultColor = new Color(0xD6C0B3);
			final Color lightColor	 = new Color(0xe3d4c4);
			final Color shadowColor	 = new Color(0xCAB0A3);
			Color boxColor;
			
			@Override
			public boolean isSelected() {
				return selected;
			}
			public void setSelected(boolean selected) {
				this.selected = selected;
			}

			public ContentPanel getPanel() {
				return this.panelAsignado;
			}
					
			RedButton(ContentPanel panel) {
				this.panelAsignado = panel;
				
				/*
				 * Atributos
				 */
				
				// Posicionamiento
				this.setBounds(0, 80 + 40*buttons.size(), 180, 40);
				
				this.setFocusable(false);
				this.setBorder(null);
				this.setContentAreaFilled(false);
				
				// Estética
				this.boxColor = this.defaultColor;
				this.setFont(new Font("Nimbus Roman", Font.PLAIN, 20));
				
				// ActionListener
				this.addActionListener(e -> {
					resetButtons();
					this.selectButton();
					
					
					
				});
				// MouseListener
				this.addMouseListener(new MouseListener() {
					@Override
					public void mouseEntered(MouseEvent e) {
						if (!RedButton.this.isSelected()) {
							RedButton.this.boxColor = RedButton.this.lightColor;
						}
					}
					@Override
					public void mouseExited(MouseEvent e) {
						if (!RedButton.this.isSelected()) {
							RedButton.this.boxColor = RedButton.this.defaultColor;
						}
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						RedButton.this.boxColor = RedButton.this.shadowColor;
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub	
					}
				});
			}
			
			public void selectButton() {
				// Button
				this.setEnabled(false);
				this.setSelected(true);
				this.boxColor = this.shadowColor;
				
				// Panel
				contentPanel = this.getPanel();
				contentPanel.setVisible(true);				
			}			
			public void resetButton() {
				// Button
				this.setEnabled(true);
				this.setSelected(false);
				this.boxColor = this.defaultColor;
				
				// Panel
				this.getPanel().setEnabled(false);
				this.getPanel().setVisible(false);
							
			}
			
			@Override
			public void repaint() {
				super.repaint();
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
				g2.setPaint(boxColor);  
				g2.fill(new RoundRectangle2D.Double(4, 0, 170, 36, 10, 10));
				
				// Dibujar el texto
				Font font = this.getFont();
				
				// Cálculo de la posición del texto
				FontMetrics metrics = getFontMetrics(font);
				int dX = (this.getWidth() - metrics.stringWidth(this.getText())) / 2;
				int dY = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
				
				g2.setFont(font);
				g2.setPaint(new Color(0x3d3d3d));
				g2.drawString(this.getText(), dX, dY);
			}
		}
	}
	
	
	@Override
	public List<JLabel> devolverEtiquetas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JTextComponent> devolverCamposTexto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JButton> devolverBotones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void callExit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	

	@Override
	public JButton getExit() {
		this.windowBar.getExitButton();
		return null;
	}
}