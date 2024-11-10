package vistas;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class WindowBarPanel extends JPanel {
	/**
	 * 
	 */
	private JButton exitButton;
	private Vista parent;

	WindowBarPanel(Vista parent, Color backgroundColor, int WIDTH, int HEIGHT) {
		class MouseBehaviour implements MouseListener {

			public MouseBehaviour() {
				newColor = backgroundColor;
				defaultColor = (Color) UIManager.get("Button.select");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				switchBackgroundColor();
				exitButton.setIcon(new ImageIcon("IconExitButtonShadow.png"));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				restoreBackgroundColor();
				exitButton.setIcon(new ImageIcon("IconExitButton.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				switchBackgroundColor();
				exitButton.setIcon(new ImageIcon("IconExitButtonLight.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				restoreBackgroundColor();
				exitButton.setIcon(new ImageIcon("IconExitButton.png"));
			}
			
			Color defaultColor, newColor;
			private void switchBackgroundColor() {
				UIManager.put("Button.select", newColor);
			}
			private void restoreBackgroundColor() {
				UIManager.put("Button.select", defaultColor);
			}
		}
		
		this.parent = parent;
		
		this.setBackground(backgroundColor);
		this.setBounds(0, 0, WIDTH, HEIGHT);
		
		this.setLayout(null);
		
		exitButton = new JButton();
		exitButton.setFocusable(false);
		exitButton.setBorder(null);
		
		exitButton.setIcon(new ImageIcon("IconExitButton.png"));
		exitButton.setBounds(WIDTH - 50, 7, exitButton.getIcon().getIconWidth(), exitButton.getIcon().getIconHeight());
		exitButton.setBackground(backgroundColor);
		
		exitButton.addMouseListener(new MouseBehaviour());
		exitButton.addActionListener(e -> {
			parent.callExit();
		});
		
		this.add(exitButton);
	}
	/*
	 * label.addMouseListener(new MouseListener(){
		 	// When the JFrame is being dragged, it shouldn't call any method.
			boolean enabled = true;
		 	@Override
            public void mouseEntered(MouseEvent me) {
		 		if (enabled) {
		 			System.out.printf("Hover over Component\n");
		 		}
            }
		 	@Override
		 	public void mousePressed(MouseEvent me) {
		 		System.out.printf("Click\n");
		 		enabled = false;
		 	}
		 	@Override
		 	public void mouseReleased(MouseEvent me) {
		 		System.out.printf("Released\n");
		 		enabled = true;
		 	}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	 */
	
	public JButton getExitButton() {
		return this.exitButton;
	};
}
