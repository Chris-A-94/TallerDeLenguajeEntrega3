package vistas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class MenuVista extends JFrame {
	
	public class MotionPanel extends JPanel{
	    private Point initialClick;
	    private JFrame parent;

	    public MotionPanel(final JFrame parent){
	    this.parent = parent;

	    addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });

	    addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {

	            // get location of Window
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;

	            // Determine how much the mouse moved since the initial click
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;

	            // Move window to this position
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            parent.setLocation(X, Y);
	        }
	    });
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
		
		// 'Look and Feel' Settings
		UIManager.put("Button.disabledText", new ColorUIResource(Color.GRAY));
		
		// LayoutManager
		this.setLayout(null);
		
		// Instanciación de los Componentes
		RedPanel redPanel = new RedPanel();
		GreenPanel greenPanel1 = new GreenPanel(new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel2 = new GreenPanel(new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel3 = new GreenPanel(new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel4 = new GreenPanel(new Color(0xE4E0E1), new BorderLayout());
		GreenPanel greenPanel5 = new GreenPanel(new Color(0xE4E0E1), new BorderLayout());
		MotionPanel bluePanel = new MotionPanel(this);
		
		
		bluePanel.setBackground(new Color(0x493628));
		bluePanel.setBounds(0, 0, _WIDTH, 40);
		
		ImageIcon icon = new ImageIcon("image.jpg");
		Image image = icon.getImage();
		icon = new ImageIcon(image.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH));
		greenPanel1.addLabel("Demostración 1", icon);
		
		icon = new ImageIcon("0.gif");
		greenPanel2.addLabel("Demostración 2", icon);
		
		icon = new ImageIcon("1.gif");
		greenPanel3.addLabel("Demostración 3", icon);
		
		icon = new ImageIcon("2.gif");
		greenPanel4.addLabel("Demostración 4", icon);
		
		icon = new ImageIcon("3.png");
		image = icon.getImage();
		icon = new ImageIcon(image.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH));
		greenPanel5.addLabel("Demostración 5", icon);
		
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
		
		this.setVisible(true);
	}
	
	private class RedPanel extends JPanel {
		private static final long serialVersionUID = 332812813329689766L;
		LinkedList<RedButton> buttons = new LinkedList<RedButton>();
		
		public RedPanel() {
			this.setBackground(new Color(0xD6C0B3));
			this.setBounds(0, 40, 180, _HEIGHT - 40);
			this.setLayout(null);
			
		};
		
		public void newButton(String msg, GreenPanel panel) {
			RedButton button = new RedButton(panel);
			// Asigno el mensaje al Botón.
			button.setText(msg);
			
			// Atributos
			button.setBounds(0, 40*buttons.size(), 180, 40);
			button.setFocusable(false);
			//button.setBorder(null);
			button.setBackground(new Color(0xFFFFFF));
			button.setForeground(new Color(0x291e17));
			button.setOpaque(false);
			
			// ActionListener
			button.addActionListener(e -> {
				resetButtons();
				button.setEnabled(false);
				button.getPanel().setVisible(true);
			});
			
			// Agregar al Panel
			this.add(button);
			buttons.add(button);
		}
		public void resetButtons() {
			for (RedButton button : buttons) {
				button.setEnabled(true);
				button.getPanel().setEnabled(false);
				button.getPanel().setVisible(false);
			}
		}
	
		private class RedButton extends JButton {
			private static final long serialVersionUID = 228929574725506575L;
			JPanel panelAsignado;
			RedButton(JPanel panel) {
				this.panelAsignado = panel;
				//this.setFont(new Font("Standard Symbols PS", Font.PLAIN, 20));
			}
			
			public JPanel getPanel() {
				return this.panelAsignado;
			}
		}
	}
	private class GreenPanel extends JPanel {
		private static final long serialVersionUID = -6824823559208147547L;

		public GreenPanel(Color backgroundColor, LayoutManager layoutManager) {
			this.setBackground(backgroundColor);
			this.setOpaque(true);
			
			this.setBounds(180, 40, _WIDTH - 180, _HEIGHT - 40);
			
			this.setLayout(layoutManager);
			
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
			label.setVerticalAlignment(JLabel.TOP);
			label.setHorizontalAlignment(JLabel.LEFT);
			label.setVerticalTextPosition(JLabel.TOP);
			label.setHorizontalTextPosition(JLabel.CENTER);
			
			label.setVisible(true);
			this.add(label);
		}
	}
}
