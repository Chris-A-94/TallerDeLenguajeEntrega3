package vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import javax.swing.plaf.ColorUIResource;
import javax.swing.text.JTextComponent;

public class MenuVista extends JFrame implements Vista {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2999836563834513296L;
	public class MoveListener implements MouseListener, MouseMotionListener {
	        
	        private Point pressedPoint;
	        private Rectangle frameBounds;
	        private Date lastTimeStamp;
	        private JFrame frame;
	        
	        public MoveListener(JFrame frame) {
	        	this.frame = frame;
	        }
	
	        @Override
	        public void mouseClicked(MouseEvent event) {
	        }
	
	        @Override
	        public void mousePressed(MouseEvent event) {
	            this.frameBounds = frame.getBounds();
	            this.pressedPoint = event.getPoint();
	            this.lastTimeStamp = new Date();
	        }
	
	        @Override
	        public void mouseReleased(MouseEvent event) {
	            moveJFrame(event);
	        }
	
	        @Override
	        public void mouseEntered(MouseEvent event) {
	        }
	
	        @Override
	        public void mouseExited(MouseEvent event) {
	        }
	
	        @Override
	        public void mouseDragged(MouseEvent event) {
	            moveJFrame(event);
	        }
	
	        @Override
	        public void mouseMoved(MouseEvent event) {
	        }
	        
	        private void moveJFrame(MouseEvent event) {
	            Point endPoint = event.getPoint();
	
	            int xDiff = endPoint.x - pressedPoint.x;
	            int yDiff = endPoint.y - pressedPoint.y;
	
	            Date timestamp = new Date();
	
	            //One move action per 60ms to avoid frame glitching
	            if(Math.abs(timestamp.getTime() - lastTimeStamp.getTime()) > 20){ 
	                if((xDiff>0 || yDiff>0)||(xDiff<0 || yDiff<0)) {
	                    frameBounds.x += xDiff;
	                    frameBounds.y += yDiff;
	                    //System.out.println(frameBounds);
	                    frame.setBounds(frameBounds);
	                }
	                this.lastTimeStamp = timestamp;
	            }
	        }
	        
	    }
	
	private int _WIDTH 	= 1270,
				_HEIGHT = 720;
	
	private RedPanel redPanel;
	private GreenPanel greenPanel;
	private BluePanel bluePanel;
	
	private List<GreenPanel> listGreenPanel;
	
	private JLayeredPane mainPane;
	
	public MenuVista() {
		// Atributos
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(_WIDTH, _HEIGHT);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(0xE4E0E1));
		
		// 'Look and Feel' Settings
		UIManager.put("Button.disabledText", new ColorUIResource(new Color(0x5e8da8)));
		
		// LayoutManager
		this.setLayout(null);
		this.setShape(new RoundRectangle2D.Double(0, 0, _WIDTH, _HEIGHT, 25, 25));
		
		// Instanciación de los Componentes
		mainPane = new JLayeredPane();
		mainPane.setBounds(0, 0, _WIDTH, _HEIGHT);
		mainPane.setBackground(new Color(0xE4E0E1));
		mainPane.setOpaque(false);
		
		this.redPanel = new RedPanel();
		GreenPanel greenPanel1 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), null);
		GreenPanel greenPanel2 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel3 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel4 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel5 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());

		redPanel.newButton("Mis Activos", greenPanel1);
		redPanel.newButton("Visualizar Cryptos", greenPanel2);
		redPanel.newButton("Comprar Crypto", greenPanel3);
		redPanel.newButton("Swap Crypto", greenPanel4);
		redPanel.newButton("Mis transacciones", greenPanel5);
		
		this.bluePanel = new BluePanel(this, new Color(0x493628), _WIDTH, 40);
		
		ImageIcon icon = new ImageIcon("image.jpg");
//		Image image = icon.getImage();
//		icon = new ImageIcon(image.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH));
//		greenPanel5.addLabel("Demostración 1", icon);
		
		icon = new ImageIcon("0.gif");
		greenPanel2.addLabel("Demostración 2", icon);
		
		icon = new ImageIcon("1.gif");
		greenPanel3.addLabel("Demostración 3", icon);
		
		// Add window dragging when grabbing BluePanel
		MoveListener listener = new MoveListener(this);
		bluePanel.addMouseListener(listener);
		bluePanel.addMouseMotionListener(listener);
		
		// Temporal & Experimental. May not be implemented.
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.printf("1999\n");
			}
		};
		Timer timer = new Timer(1000, taskPerformer);
		timer.setRepeats(false);
		timer.start();
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
				RedPanel panel = MenuVista.this.redPanel;
				
				if (keyCode == KeyEvent.VK_C) {
					panel.setBounds(panel.getX() + 10, 
							panel.getY(), 
							panel.getWidth(), 
							panel.getHeight());
				} else if (keyCode == KeyEvent.VK_Z) {
					panel.setBounds(panel.getX() - 10, 
							panel.getY(), 
							panel.getWidth(), 
							panel.getHeight());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Setup layers
		this.mainPane.add(redPanel, JLayeredPane.POPUP_LAYER);
		this.mainPane.add(bluePanel, JLayeredPane.DRAG_LAYER);
		this.mainPane.add(greenPanel1, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(greenPanel2, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(greenPanel3, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(greenPanel4, JLayeredPane.DEFAULT_LAYER);
		this.mainPane.add(greenPanel5, JLayeredPane.DEFAULT_LAYER);
		
		// Add MainPane
		this.add(mainPane);
		
		// Default Panel
		greenPanel = greenPanel1;
		greenPanel.enablePanel();
		
		this.setVisible(true);
	}
	
	private class RedPanel extends JPanel {
		private static final long serialVersionUID = 332812813329689766L;
		LinkedList<RedButton> buttons = new LinkedList<RedButton>();
		
		public RedPanel() {
			this.setBackground(new Color(0xD6C0B3));
			this.setBounds(0, 40, 180, _HEIGHT - 40);
			this.setLayout(null);
			
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x493628)));
		};
		
		public void newButton(String msg, GreenPanel panel) {
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
				button.setEnabled(true);
				button.setBorder(null);
				button.setSelected(false);
				button.getPanel().setEnabled(false);
				button.getPanel().setVisible(false);
				greenPanel = button.getPanel();
				
				button.boxColor = button.defaultColor;
			}
		}
	
		private class RedButton extends JButton {
			private static final long serialVersionUID = 228929574725506575L;
			GreenPanel panelAsignado;
			
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

			RedButton(GreenPanel panel) {
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
					
					this.setEnabled(false);
					this.setSelected(true);
					this.boxColor = this.shadowColor;
					this.getPanel().setVisible(true);
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
			@Override
			public void repaint() {
				super.repaint();
			}
			
			public GreenPanel getPanel() {
				return this.panelAsignado;
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
	private class GreenPanel extends JPanel {
		private static final long serialVersionUID = -6824823559208147547L;

		private JPanel header, leftBorder;
		private JLabel title;
		
		public GreenPanel(Color backgroundColor, Color headerColor, LayoutManager layoutManager) {
			this.setBackground(backgroundColor);
			this.setOpaque(true);
			
			this.setBounds(0, 40, _WIDTH, _HEIGHT - 40);
			this.setLayout(layoutManager);
			
			this.leftBorder = new JPanel();
			this.leftBorder.setBounds(0, 0, 12, _HEIGHT - 40);
			this.leftBorder.setBackground(backgroundColor);
			this.leftBorder.setOpaque(true);
			this.add(leftBorder);
			
			this.header = new JPanel();
			this.header.setBounds(0, 0, _WIDTH - this.leftBorder.getWidth(), 40);
			this.header.setBackground(headerColor);
			this.header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x493628)));
			this.header.setLayout(null);
			this.add(header);
			
			this.disablePanel();
		}
		
		public void disablePanel() {
			this.setEnabled(false);
			this.setVisible(false);
		}
		
		public void enablePanel() {
			this.setEnabled(true);
			this.setVisible(true);
		}
		
		public void addLabel(String msg, ImageIcon image) {
			JLabel label = new JLabel();
			label.setText(msg);
			label.setIcon(image);
//			label.setVerticalTextPosition(JLabel.TOP);
//			label.setHorizontalTextPosition(JLabel.CENTER);
//			label.setVerticalAlignment(JLabel.TOP);
//			label.setHorizontalAlignment(JLabel.LEFT);
			
			label.setVisible(true);
			this.add(label);
		}
		
		public void setTitle(String title) {
			this.title = new JLabel(title);
			this.title.setFont(new Font("Nimbus Roman", Font.BOLD, 25));
			this.title.setBounds(25, 10, 1000, 40);
			this.header.add(this.title);
		}
		
		public JLabel getTitle() {
			return this.title;
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
	public JButton callExit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		return null;
	}
}
