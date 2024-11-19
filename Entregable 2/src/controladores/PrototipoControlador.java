package controladores;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumnModel;

import entregable1.Coin;
import vistas.MenuVista;

/*
 * Posible nombre: GestionMenuControlador
 */
public class PrototipoControlador {
	private MenuVista myMenu;
	
	public PrototipoControlador(List<Coin> monedas) {
		myMenu = new MenuVista();
		
		/*
		 *  ¡¡¡TEMPORAL!!!
		 */
		// Data to be displayed in the JTable
        String[][] data = new String[monedas.size()][4];
        
        for (int i = 0; i < monedas.size(); i++) {
        	Coin c = monedas.get(i);
        	data[i][0] = c.getSigla().toUpperCase();
        	data[i][1] = c.getNombre().toUpperCase();
        	data[i][2] = c.getPrecio().toString();
        	data[i][3] = c.getStock().toString();
        }
 
        // Column Names
        String[] columnNames = {"Sigla", "Nombre", "Precio", "Stock"};
 
        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        TableColumnModel columnModel = j.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        sp.setBounds(30, 40, 500, 300);
        
		myMenu.agregarPanel("Monedas", sp);
	}
}
