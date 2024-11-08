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

import javax.swing.*;

import javax.swing.plaf.ColorUIResource;

public class MenuVista extends JFrame {
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
		RedPanel redPanel = new RedPanel();
		GreenPanel greenPanel1 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), null);
		GreenPanel greenPanel2 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel3 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel4 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel5 = new GreenPanel(new Color(0xE4E0E1), new Color(0xE4E0E1), new BorderLayout());
		
		BluePanel bluePanel = new BluePanel(new Color(0x493628), _WIDTH, 40);
		bluePanel.getExitButton().addActionListener(e -> {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
		
		ImageIcon icon = new ImageIcon("image.jpg");
		Image image = icon.getImage();
//		icon = new ImageIcon(image.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH));
//		greenPanel5.addLabel("Demostración 1", icon);
		
		icon = new ImageIcon("0.gif");
		greenPanel2.addLabel("Demostración 2", icon);
		
		icon = new ImageIcon("1.gif");
		greenPanel3.addLabel("Demostración 3", icon);
//		
//		icon = new ImageIcon("2.gif");
//		greenPanel4.addLabel("Demostración 4", icon);
		
		redPanel.newButton("", greenPanel1);
		redPanel.newButton("Mis Activos", greenPanel1);
		redPanel.newButton("Visualizar Cryptos", greenPanel2);
		redPanel.newButton("Comprar Crypto", greenPanel3);
		redPanel.newButton("Swap Crypto", greenPanel4);
		redPanel.newButton("Mis transacciones", greenPanel5);

		
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
			
			// ActionListener
			button.addActionListener(e -> {
				resetButtons();
				
				button.setEnabled(false);
				button.setOpaque(true);
				//button.setBorder(BorderFactory.createLoweredBevelBorder());
				button.getPanel().setVisible(true);
			});
			
			// Agregar al Panel
			this.add(button);
			buttons.add(button);
		}
		public void resetButtons() {
			for (RedButton button : buttons) {
				button.setEnabled(true);
				button.setOpaque(false);
				button.setBorder(null);
				button.getPanel().setEnabled(false);
				button.getPanel().setVisible(false);
			}
		}
	
		private class RedButton extends JButton {
			private static final long serialVersionUID = 228929574725506575L;
			JPanel panelAsignado;
			
			RedButton(JPanel panel) {
				this.panelAsignado = panel;
				this.setFont(new Font("system-ui", Font.ITALIC, 15));
				this.setBounds(0, 40*buttons.size(), 180, 40);
				this.setFocusable(false);
				this.setBorder(null);
				this.setBackground(new Color(0xB3C9D6));
				this.setForeground(new Color(0x291e17));
				this.setOpaque(false);
			}
			
			public JPanel getPanel() {
				return this.panelAsignado;
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
}
