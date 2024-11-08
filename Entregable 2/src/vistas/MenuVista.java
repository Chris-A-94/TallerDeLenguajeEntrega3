package vistas;

import java.awt.*;
import java.awt.event.MouseAdapter;
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
	
	public MenuVista() {
		// Atributos
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(_WIDTH, _HEIGHT);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(0x493628));
		
		// 'Look and Feel' Settings
		UIManager.put("Button.disabledText", new ColorUIResource(new Color(0x5e8da8)));
		
		// LayoutManager
		this.setLayout(null);
		this.setShape(new RoundRectangle2D.Double(0, 0, _WIDTH, _HEIGHT, 25, 25));
		
		// Instanciación de los Componentes
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
		
		BluePanel bluePanel = new BluePanel(this, new Color(0x493628), _WIDTH, 40);
		
		ImageIcon icon = new ImageIcon("image.jpg");
		Image image = icon.getImage();
//		icon = new ImageIcon(image.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH));
//		greenPanel5.addLabel("Demostración 1", icon);
		
		icon = new ImageIcon("0.gif");
		greenPanel2.addLabel("Demostración 2", icon);
		
		icon = new ImageIcon("1.gif");
		greenPanel3.addLabel("Demostración 3", icon);
		
		// Add Components
		this.add(redPanel);
		this.add(bluePanel);
		this.add(greenPanel1);
		this.add(greenPanel2);
		this.add(greenPanel3);
		this.add(greenPanel4);
		this.add(greenPanel5);
		
		greenPanel1.enablePanel();
		
		// BluePanel makes the JFrame move
		MoveListener listener = new MoveListener(this);
		bluePanel.addMouseListener(listener);
		bluePanel.addMouseMotionListener(listener);
		
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
				
				button.boxColor = button.defaultColor;
			}
		}
	
		private class RedButton extends JButton {
			private static final long serialVersionUID = 228929574725506575L;
			JPanel panelAsignado;
			
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

			RedButton(JPanel panel) {
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
				this.setFont(new Font("Times New Roman", Font.PLAIN, 17));
				
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
			
			public JPanel getPanel() {
				return this.panelAsignado;
			}
			
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent (g);
				Graphics2D g2 = (Graphics2D)g;
				
				// Render Hints
				g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	            
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
				g2.setPaint(new Color(0x000000));
				g2.drawString(this.getText(), dX, dY);
			}
		}
	}
	private class GreenPanel extends JPanel {
		private static final long serialVersionUID = -6824823559208147547L;

		private JPanel header, leftBorder;
		
		public GreenPanel(Color backgroundColor, Color headerColor, LayoutManager layoutManager) {
			this.setBackground(backgroundColor);
			this.setOpaque(true);
			
			this.setBounds(180, 40, _WIDTH - 180, _HEIGHT - 40);
			this.setLayout(layoutManager);
			
			this.leftBorder = new JPanel();
			this.leftBorder.setBounds(0, 0, 12, _HEIGHT - 40);
			this.leftBorder.setBackground(backgroundColor);
			this.leftBorder.setOpaque(true);
			this.add(leftBorder);
			
			this.header = new JPanel();
			this.header.setBounds(0, 0, _WIDTH - 180, 40);
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
			JLabel label = new JLabel(title);
			label.setFont(new Font("system-ui", Font.BOLD, 25));
			label.setBounds(25, 10, 1000, 25);
			this.header.add(label);
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
