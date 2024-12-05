package vistas;

import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.CoinDAO;
import entregable1.Coin;

public class PanelCompra extends JFrame{
	
	private JPanel elPanel;
	private JComboBox<String> monedasFiat;
	private JLabel cantidadYPrecio;
	private JButton comprar;
	private JButton cancelar;
	private JTextField valor;	
	
	public JLabel getCantidadYPrecio() {
		return cantidadYPrecio;
	}

	public void setCantidadYPrecio(JLabel cantidadYPrecio) {
		this.cantidadYPrecio = cantidadYPrecio;
	}

	public JPanel getElPanel() {
		return elPanel;
	}

	public JComboBox<String> getMonedasFiat() {
		return monedasFiat;
	}

	public JButton getComprar() {
		return comprar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public JTextField getValor() {
		return valor;
	}

	public PanelCompra()
	{
		this.setTitle("Comprar Criptomonedas");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.elPanel = new JPanel();
		this.elPanel.setLayout(new BoxLayout(elPanel, BoxLayout.Y_AXIS));	
		this.organizarComps();
		this.add(elPanel);		
		this.setVisible(true);

	}
	
	private void organizarComps()
	{
		this.comprar = new JButton("Comprar");
		this.cancelar = new JButton("Cancelar");
		this.valor = new JTextField();
		List<String> monedas = new LinkedList<String>();
		CoinDAO coinsDB = new CoinDAO();
		for(Coin aux: coinsDB.devolverTabla())
		{
			if(aux.getTipo().equals("FIAT"))
				monedas.add(aux.getSigla());
		}			
		this.monedasFiat = new JComboBox<>(monedas.toArray(new String[0]));
		JLabel mensaje1 = new JLabel("Ingrese cantidad de monedas deseadas: ");
		JLabel mensaje2 = new JLabel("Elija dinero a utilizar: ");
		this.cantidadYPrecio = new JLabel(""); //llenado por el controlador
		
		this.elPanel.add(mensaje1);
		this.elPanel.add(valor);
		this.elPanel.add(mensaje2);
		this.elPanel.add(monedasFiat);
		this.elPanel.add(cantidadYPrecio);
		this.elPanel.add(comprar);
		this.elPanel.add(cancelar);
		
	}

}
