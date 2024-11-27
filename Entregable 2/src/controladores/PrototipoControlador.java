package controladores;

import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import entregable1.Coin;
import entregable1.Sistema;
import entregable1.TipoMoneda;
import entregable1.Usuario;
import vistas.*;

/*
 * Posible nombre: GestionMenuControlador
 */
public class PrototipoControlador {
	private MenuVista myMenu;
	private Usuario user;
	
	public PrototipoControlador(Sistema sistema, Usuario user) {
		myMenu = new MenuVista();
		GenActivosControl activos = new GenActivosControl(myMenu.getGenerador(),user);
		this.user = user;
		
		/*
		 *  ¡¡¡TEMPORAL!!!
		 */
		// Data to be displayed in the JTable
		List<Coin> monedas = sistema.getMonedas();
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
        @SuppressWarnings("serial")
		JTable j = new JTable(data, columnNames) {
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        TableColumnModel columnModel = j.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //Paneles agustín
      	TransaccionControlador tc = new TransaccionControlador(user.getBilletera().getTransacciones());
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
		myMenu.agregarPanel("Transacciones", tc.getPanel());
        sp.setBounds(30, 40, 500, 300);
        
        myMenu.agregarPanel("Monedas", sp);
        
        /*
         * 2° Panel de Prueba, esta vez implementado un layout.
         * 
         */
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		panel.setBounds(0, 0, 100, 100);;
		
		JLabel label = new JLabel("?)))))");
		panel.add(label);
		
		myMenu.agregarPanel("Prueba", panel);
		
		/*
		 * 3° Panel
		 */
		//System.out.println(sistema.getMonedas().get(0).toString());
		PanelMonedasVista panelMonedas = new PanelMonedasVista();
		
		for (Coin c : sistema.getMonedas()) {
			if (c.getTipo().equals(TipoMoneda.CRIPTOMONEDA))
			{
				TarjetaVista aux = new TarjetaVista(c);
				tarjetaVistaControladorCompra aux2 = new tarjetaVistaControladorCompra(aux,this.user);
				panelMonedas.agregarMoneda(aux);
			}
		}
		
		myMenu.agregarPanel("Monedas 2 (mejor)",panelMonedas);
		
		/*
		 * 4° Panel: Mis Activos
		 */
		// Hard-coded
		user.getBilletera().agregarMoneda(sistema.getMonedas().get(0), 100.0);
		user.getBilletera().agregarMoneda(sistema.getMonedas().get(1), 100.0);
		user.getBilletera().agregarMoneda(sistema.getMonedas().get(2), 100.0);
		user.getBilletera().agregarMoneda(sistema.getMonedas().get(3), 100.0);
		//
		MisActivosVista misActivos = new MisActivosVista(myMenu.getPreferredContentWidth(), sistema, user);
		
		myMenu.agregarPanel("Mis Activos", misActivos);
	}
	
	
	
}
