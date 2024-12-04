package vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class ContentPanel extends JPanel {
	private JPanel header, leftBorder, content;
	private JLabel title;
	
	public ContentPanel(MenuVista parent, Color backgroundColor, Color headerColor, LayoutManager layoutManager) {
		this.setBackground(backgroundColor);
		this.setOpaque(true);
		
		this.setBounds(5, parent.getBarPanelHeight(), parent.getWidth() - 5, parent.getHeight() - parent.getBarPanelHeight());
		this.setLayout(null);
		
		this.leftBorder = new JPanel();
		this.leftBorder.setBounds(0, 0, 12, parent.getWidth() - 40);
		this.leftBorder.setBackground(backgroundColor);
		this.leftBorder.setOpaque(true);
		super.add(leftBorder);
		
		this.header = new JPanel();
		this.header.setBounds(0, 0, parent.getWidth() - this.leftBorder.getWidth(), 40);
		this.header.setBackground(headerColor);
		this.header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x493628)));
		this.header.setLayout(null);
		super.add(header);
		
		this.content = new JPanel();
		this.content.setBounds(12, 40, this.getWidth() - 20, this.getHeight() - 40);
		this.content.setBackground(backgroundColor);
		this.content.setLayout(layoutManager);
		super.add(content);
		
		this.disablePanel();
	}
	public int getContentX() {
		return this.content.getX();
	}
	public int getContentY() {
		return this.content.getY();
	}
	public int getContentWidth() {
		return this.content.getWidth();
	}
	public int getContentHeight() {
		return this.content.getHeight();
	}
	
	@Override
	public Component add(Component panel) {
		this.content.add(panel);
		
		return panel;
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
		
		label.setVisible(true);
		this.add(label);
	}
	
	public void setTitle(String title) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C059-Roman.otf")).deriveFont(25.0f);
		} catch (FontFormatException | IOException e) {
			font = null;
			e.printStackTrace();
		}
		
		// Cálculo de la posición del texto
		FontMetrics metrics = getFontMetrics(font);
		int dY = ((header.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
		
		this.title = new JLabel(title);
		this.title.setFont(font);
		this.title.setBounds(20, dY - 10, metrics.stringWidth(title), metrics.getHeight());
		this.header.add(this.title);
	}
	
	public JLabel getTitle() {
		return this.title;
	}
}
