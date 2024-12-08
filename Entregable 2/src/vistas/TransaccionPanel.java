package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class TransaccionPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contenedor; 
    private JScrollPane scrollPane;
    private JButton refresh = new JButton("Refresh");
    public TransaccionPanel() {
    	//COLORES
    	Color fondoFrame  		= new Color(0xE4E0E1);
		Color camposTextColor 	= new Color(0xD6C0B3);
		Color textColor 		= new Color(0xAB886D);
    	//Panel principal
    	this.setBounds(60, 10, 1300, 400);
        this.setLayout(null);
        this.setBackground(fondoFrame);
        
        
        contenedor = new JPanel();
        contenedor.setLayout((LayoutManager) new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        contenedor.setBackground(new Color(0xD6C0B3));
        scrollPane = new JScrollPane(contenedor);
        scrollPane.setBounds(20, 20, 900, 350);
        // Personalizar barras
        
        scrollPane.getVerticalScrollBar().setBackground(fondoFrame );
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0xAB886D);
                this.trackColor = new Color(0xD6C0B3);
            }
        });
        //botón refresh
        refresh.setBounds(1000,20, 100, 30);
        refresh.setBorder(null);
        refresh.setBackground(new Color(0xAB886D));
        refresh.setFont(new Font("Arial", Font.PLAIN,20));
        refresh.setFocusPainted(false);
        
        this.add(refresh);
        this.add(scrollPane);
    }

    public void agregarTransaccion(String t) {
        JLabel aux = new JLabel(t);
        aux.setOpaque(false);
		
		aux.setForeground(new Color(0x291e17));
		aux.setFont(new Font("Arial", Font.BOLD,20));
		aux.setHorizontalAlignment(SwingConstants.CENTER);
        contenedor.add(aux);

    }
    public JButton getRefresh() {
    	return refresh;
    }
    public void eraseLabels() {
    	contenedor.removeAll();
    }
}


