package vistas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.JTextComponent;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.Animator.Direction;
import org.jdesktop.animation.timing.Animator.RepeatBehavior;
import org.jdesktop.animation.timing.TimingTarget;

import decoradores.ExitButton;

@SuppressWarnings("serial")
public class MenuVista extends JFrame implements Vista {
	
	private int _WIDTH 	= 1366,
				_HEIGHT = 768;
	
	private int _BARPANEL_HEIGHT = 25;
	
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
		this.windowBar = new WindowBarPanel(this, new Color(0x493628), _WIDTH, _BARPANEL_HEIGHT, true);
		ExitButton.asignarComportamiento(this);
		
		// Setup layers
		this.mainPane.add(lateralPanel, JLayeredPane.POPUP_LAYER);
		this.mainPane.add(windowBar, JLayeredPane.DRAG_LAYER);
		
		// Add MainPane
		this.add(mainPane);
		
		// Set Default Panel
		this.agregarPanel("Inicio", new JPanel(null));
		
		if (lateralPanel.getButtons().size() > 0) {
			contentPanel = lateralPanel.getButtons().get(0).getPanel();
			lateralPanel.getButtons().get(0).selectButton();
			contentPanel.enablePanel();
		}
		
		this.setVisible(true);
		
		/*
		 * Nuevo:
		 */
		TransitionPanel transitionPanel = new TransitionPanel();
		transitionPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				transitionPanel.show();
				lateralPanel.show();
			}
		});
		
		this.mainPane.add(transitionPanel, JLayeredPane.POPUP_LAYER);
		
		// Agregar comportamiento del botón
		lateralPanel.getInteractButton().addActionListener(e -> {
			transitionPanel.show();
			lateralPanel.show();
		});
		
		// Inicializar estado
		transitionPanel.show();
		lateralPanel.show();
	}
	
	public void agregarPanel(String title, JPanel componente) {
		ContentPanel newPanel = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), componente.getLayout());
		
		newPanel.setTitle(title);
		newPanel.add(componente);
		
		this.lateralPanel.newButton(title, newPanel);
		this.mainPane.add(newPanel);
	}
	
	public void agregarPanel(String title, JComponent componente) {
		ContentPanel newPanel = new ContentPanel(this, new Color(0xE4E0E1), new Color(0xE4E0E1), null);
		
		componente.setBounds(10, 10, componente.getWidth(), componente.getHeight());
		
		newPanel.setTitle(title);
		newPanel.add(componente);
		
		this.lateralPanel.newButton(title, newPanel);
		this.mainPane.add(newPanel);
	}
	
	public int getPreferredContentWidth() {
		return this.contentPanel.getContentWidth();
	}
	public int getPreferredContentHeight() {
		return this.contentPanel.getContentHeight();
	}
	
	public JButton getGenerador()
	{
		return this.lateralPanel.generador;
	}
	
	private class TransitionPanel extends JPanel implements TimingTarget {
		int maxAlphaValue = 40;
		int alpha = maxAlphaValue;
		Animator animator;
		int animationDuration = 150;
		
		boolean status = true;
		
		MouseListener ma;
		
		public TransitionPanel() {
			animator = new Animator(animationDuration, 1, RepeatBehavior.REVERSE, this);
			animator.setResolution(5);
			
			animator.setDeceleration(1.0f);
			
			this.setBounds(0, 0, 10000, 1000);
			this.setOpaque(false);
		}
		
		@Override
		public void addMouseListener(MouseListener ml) {
			super.addMouseListener(ml);
			ma = ml;
		}
		
		public void show() {
			if (!animator.isRunning()) {
				reverseAnimator();
				animator.start();
			}
		}
		
		public void reverseAnimator() {
			if (status == false) {
				animator.setStartFraction(0.0f);
				animator.setStartDirection(Direction.FORWARD);
				status = true;
			} else {
				animator.setStartFraction(1.0f);
				animator.setStartDirection(Direction.BACKWARD);
				status = false;
			}
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
			Color c=new Color(46, 28, 77, alpha);
			g2.setPaint(c);  
			g2.fillRect(0, 0, 10000, 10000);
		}

		@Override
		public void begin() {
			if (status==true) {
				this.addMouseListener(ma);
			}
		}

		@Override
		public void end() {
			if (status==false) {
				this.removeMouseListener(ma);
			}
		}

		@Override
		public void repeat() {
		}

		@Override
		public void timingEvent(float arg0) {
			alpha = (int) (maxAlphaValue*arg0);
			TransitionPanel.this.repaint();
		}
	}
	
	private class LateralPanel extends JLayeredPane implements TimingTarget {
		private static final long serialVersionUID = 332812813329689766L;
		private LinkedList<RedButton> buttons = new LinkedList<RedButton>();
		
		private JPanel buttonsPanel;
		
		private JButton generador = new JButton();
		private JButton interactButton;
		
		private final int _INTERACTBUTTON_WIDTH = 30;
		private final int _LATERALPANEL_WIDTH = 180;
		
		public JButton getInteractButton() {
			return interactButton;
		}

		Animator animator;
		int animationDuration = 150;
		
		boolean status = true;

		public LateralPanel() {
			animator = new Animator(animationDuration, 1, RepeatBehavior.REVERSE, this);
			animator.setResolution(0);
			
			animator.setDeceleration(1.0f);
			
			// this: Atributos
			this.setBackground(new Color(0xD6C0B3));
			this.setBounds(0, _BARPANEL_HEIGHT, _LATERALPANEL_WIDTH + _INTERACTBUTTON_WIDTH, _HEIGHT - _BARPANEL_HEIGHT);
			this.setLayout(null);
			
			// buttonsPanel: Instanciar
			buttonsPanel = new JPanel();
			// buttonsPanel: Atributos
			buttonsPanel.setBackground(new Color(0xD6C0B3));
			buttonsPanel.setBounds(0, 0, _LATERALPANEL_WIDTH, _HEIGHT - _BARPANEL_HEIGHT);
			buttonsPanel.setLayout(null);
			buttonsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x493628)));
			this.add(buttonsPanel);
			
			//Botón generador
			this.generador.setBounds(20, this.getHeight()-40, 60, 30);
			this.generador.setBackground(Color.GREEN);
			this.generador.setBorder(null);
			this.generador.setText("$$$+");
			this.setFocusable(false);			
			this.add(generador, JLayeredPane.POPUP_LAYER);
			
			//Botón interacción
			interactButton = new JButton();
			interactButton.setText("????");
			interactButton.setBounds(buttonsPanel.getWidth(), 50, _INTERACTBUTTON_WIDTH, _INTERACTBUTTON_WIDTH);
			
			this.add(interactButton, JLayeredPane.POPUP_LAYER);
		};		
		
		public void newButton(String msg, ContentPanel panel) {
			RedButton button = new RedButton(panel);
			// Asigno el mensaje al Botón.
			button.setText(msg);
			// Asigno el título
			panel.setTitle(msg);
			// Agregar al Panel
			buttonsPanel.add(button);
			buttons.add(button);
			
			// Cuando se agrega un nuevo botón se requiere repintar el panel de nuevo.
			this.repaint();
		}
		public void resetButtons() {
			for (RedButton button : buttons) {
				button.resetButton();
			}
		}
		
		public void show() {
			if (!animator.isRunning()) {
				reverseAnimator();
				animator.start();
				
				this.setEnabled(!this.isEnabled());
			}
		}
		
		public void reverseAnimator() {
			if (status == false) {
				animator.setStartFraction(0.0f);
				animator.setStartDirection(Direction.FORWARD);
				status = true;
			} else {
				animator.setStartFraction(1.0f);
				animator.setStartDirection(Direction.BACKWARD);
				status = false;
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
				this.addMouseListener(new MouseAdapter() {
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
					public void mousePressed(MouseEvent e) {
						RedButton.this.boxColor = RedButton.this.shadowColor;
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

		@Override
		public void begin() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void end() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void repeat() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void timingEvent(float arg0) {
			this.setBounds((int) (arg0*175)-175, this.getY(), this.getWidth(), this.getHeight());
		}
		
		public LinkedList<RedButton> getButtons() {
			return buttons;
		}
	}	
	
	public int getBarPanelHeight() {
		return this._BARPANEL_HEIGHT;
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
		return this.windowBar.getExitButton();
	}
}
