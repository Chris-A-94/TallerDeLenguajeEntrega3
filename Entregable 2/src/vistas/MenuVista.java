package vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuVista extends JFrame {
	
	private int _WIDTH 	= 720*2,
				_HEIGHT = 420*2;
	
	private JButton button;
	
	public MenuVista() {
		this.setTitle("Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		this.setSize(_WIDTH, _HEIGHT);
		
		this.setLayout(null);
		
		// Label
		ImageIcon icon = new ImageIcon("image.jpg");
		Image image = icon.getImage();
		icon = new ImageIcon(image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
		
		JLabel label = new JLabel();
		label.setText("Demostración");
		//label.setIcon(icon);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.LEFT);
		
		// Components
		JPanel redPanel = new JPanel();
		redPanel.setBackground(new Color(0xFF0000));
		redPanel.setBounds(0, 40, 180, _HEIGHT - 40);
		
		redPanel.setLayout(null);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(new Color(0x0000FF));
		bluePanel.setBounds(0, 0, _WIDTH, 40);
		JPanel greenPanel = new JPanel();
		greenPanel.setBackground(new Color(0x00FF00));
		greenPanel.setBounds(180, 40, _WIDTH - 180, _HEIGHT - 40);
		
		greenPanel.setLayout(new BorderLayout());
		greenPanel.add(label);
		
		this.button = new JButton();
		this.button.addActionListener(e -> {
			System.out.printf("Se presionó el botón.\n");
		});
		button.setText("Botón");
		button.setBounds(0, 0, 180, 40);
		button.setFocusable(false);
		button.setBorder(null);
		button.setBackground(new Color(0xFFFFFF));
		redPanel.add(button);
		
		// Add Components
		this.add(redPanel);
		this.add(bluePanel);
		this.add(greenPanel);
		this.setVisible(true);
	}
}
