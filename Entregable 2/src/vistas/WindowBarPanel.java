package vistas;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
class WindowBarPanel extends JPanel {
	public class MoveListener implements MouseListener, MouseMotionListener {
        
        private Point pressedPoint;
        private Rectangle frameBounds;
        private Date lastTimeStamp;
        private JFrame frame;
        
        private int updateSpeed;
        
        public MoveListener(JFrame frame) {
        	this.frame = frame;
        	
        	if (System.getProperty("os.name").equals("Linux"))
        		updateSpeed = 20;
        	else
        		updateSpeed = 1;
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
            if(Math.abs(timestamp.getTime() - lastTimeStamp.getTime()) > updateSpeed){ 
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

	private JButton exitButton;
	private Vista parent;

	WindowBarPanel(Vista parent, Color backgroundColor, int WIDTH, int HEIGHT, boolean includeExitButton) {
		this.parent = parent;
		
		// Add window dragging when grabbing BluePanel
		MoveListener listener = new MoveListener((JFrame) parent);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		
		
		this.setBackground(backgroundColor);
		this.setBounds(0, 0, WIDTH, HEIGHT);
		
		this.setLayout(null);
		if (includeExitButton) {
			class MouseBehaviour implements MouseListener {
				public MouseBehaviour() {
					newColor = backgroundColor;
					defaultColor = (Color) UIManager.get("Button.select");
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
					switchBackgroundColor();
					exitButton.setIcon(new ImageIcon("IconExitButtonPress.png"));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					restoreBackgroundColor();
					exitButton.setIcon(new ImageIcon("IconExitButton.png"));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					switchBackgroundColor();
					exitButton.setIcon(new ImageIcon("IconExitButtonHover.png"));
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
			
			exitButton = new JButton();
			exitButton.setFocusable(false);
			exitButton.setBorder(null);
			
			exitButton.setIcon(new ImageIcon("IconExitButton.png"));
			exitButton.setBounds(WIDTH - 40, 7, exitButton.getIcon().getIconWidth(), exitButton.getIcon().getIconHeight());
			exitButton.setBackground(backgroundColor);
			
			exitButton.addMouseListener(new MouseBehaviour());
			this.add(exitButton);
		}
	}
	
	public JButton getExitButton() {
		return this.exitButton;
	};
}
