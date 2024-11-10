package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel {
	private JPanel header, leftBorder;
	private JLabel title;
	
	public ContentPanel(MenuVista parent, Color backgroundColor, Color headerColor, LayoutManager layoutManager) {
		this.setBackground(backgroundColor);
		this.setOpaque(true);
		
		this.setBounds(180, 40, parent.getWidth(), parent.getHeight() - 40);
		this.setLayout(layoutManager);
		
		this.leftBorder = new JPanel();
		this.leftBorder.setBounds(0, 0, 12, parent.getWidth() - 40);
		this.leftBorder.setBackground(backgroundColor);
		this.leftBorder.setOpaque(true);
		this.add(leftBorder);
		
		this.header = new JPanel();
		this.header.setBounds(0, 0, parent.getWidth() - this.leftBorder.getWidth(), 40);
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
//		label.setVerticalTextPosition(JLabel.TOP);
//		label.setHorizontalTextPosition(JLabel.CENTER);
//		label.setVerticalAlignment(JLabel.TOP);
//		label.setHorizontalAlignment(JLabel.LEFT);
		
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
