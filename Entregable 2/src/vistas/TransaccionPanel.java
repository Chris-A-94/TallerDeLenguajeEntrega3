package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

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

    public TransaccionPanel() {
    	//COLORES
    	Color fondoFrame  		= new Color(0xE4E0E1);
		Color camposTextColor 	= new Color(0xD6C0B3);
		Color textColor 		= new Color(0xAB886D);
    	//Panel principal
    	this.setBounds(60, 10, 900, 400);
        this.setLayout(null);
        this.setBackground(fondoFrame);
        
        
        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(0, 1, 5, 5)); 
        contenedor.setPreferredSize(new Dimension(750, 350));
        contenedor.setBackground(fondoFrame);
        
        scrollPane = new JScrollPane(contenedor);
        scrollPane.setBounds(20, 20, 850, 350);
     // Personalizar barras
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
        scrollPane.getVerticalScrollBar().setBackground(fondoFrame );
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0xAB886D);
                this.trackColor = new Color(0xD6C0B3);
            }
        });
        this.add(scrollPane);
    }

    public void agregarTransaccion(String t) {
        JLabel aux = new JLabel(t);
        aux.setOpaque(true);
		aux.setBackground(new Color(0xD6C0B3));
		aux.setForeground(new Color(0x291e17));
		aux.setFont(new Font("Arial", Font.BOLD, 30));
		aux.setHorizontalAlignment(SwingConstants.CENTER);
        aux.setFont(new Font("Arial", Font.PLAIN, 14));
        contenedor.add(aux);
        contenedor.revalidate(); 
        contenedor.repaint();    
    }
}


