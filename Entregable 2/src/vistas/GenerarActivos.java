package vistas;

import javax.swing.JButton;

import daos.CoinDAO;
import entregable1.Coin;

import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class GenerarActivos extends JFrame{
	
	private JComboBox<String> opciones;
	private JButton aceptar;
	private JButton cancelar;
	private JTextField valor;
	private JPanel elPanel;
	
	public GenerarActivos()
	{
		this.setTitle("Generar Activos");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.elPanel = new JPanel();
		this.elPanel.setLayout(new BoxLayout(elPanel, BoxLayout.Y_AXIS));
		
		this.setComponentes();
		this.add(elPanel);
		
		this.setVisible(true);
	}
	
	private void setComponentes()
	{
		this.cancelar = new JButton("Cancelar");
		this.aceptar = new JButton("Aceptar");
		this.valor = new JTextField();
		CoinDAO nombres = new CoinDAO();
		List<String> monedas = new LinkedList<String>();
		for(Coin moneda: nombres.devolverTabla())
		{
			monedas.add(moneda.getSigla());
		}
		monedas.add("Todo");
		this.opciones = new JComboBox<>(monedas.toArray(new String[0]));
		JLabel mensaje1 = new JLabel("Valor:");
		JLabel mensaje2 = new JLabel("Elija moneda: ");
		
		
		this.elPanel.add(mensaje1);
		this.elPanel.add(valor);
		this.elPanel.add(mensaje2);
		this.elPanel.add(opciones);
		this.elPanel.add(aceptar);
		this.elPanel.add(cancelar);
	}

	public JComboBox<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(JComboBox<String> opciones) {
		this.opciones = opciones;
	}

	public JButton getAceptar() {
		return aceptar;
	}

	public void setAceptar(JButton aceptar) {
		this.aceptar = aceptar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

	public JTextField getValor() {
		return valor;
	}

	public void setValor(JTextField valor) {
		this.valor = valor;
	}

}
