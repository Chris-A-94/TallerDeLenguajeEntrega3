package vistas;

import daos.CoinDAO;
import entregable1.Coin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GenerarActivos extends JFrame implements Vista{
	
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
		this.pack();		
		this.getContentPane().setBackground(new Color(0xDAD6D7));
		
		this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 30, 30));	
		WindowBarPanel wbp = new WindowBarPanel(this,new Color(0xDAD6D7),0,0,false);
		this.add(wbp);
		
		this.setVisible(true);
	}
	
	private void personalizarComps()
	{
		//botones
		this.aceptar.setPreferredSize(new Dimension(75, 40));
		this.aceptar.setBorder(null);
		this.aceptar.setBackground(new Color(0xAB886D));
		this.aceptar.setFont(new Font("Tahoma",Font.BOLD,14));		
		this.aceptar.setFocusPainted(false);
				
		this.cancelar.setPreferredSize(new Dimension(75, 40));		
		this.cancelar.setBorder(null);
		this.cancelar.setBackground(new Color(0xAB886D));
		this.cancelar.setFont(new Font("Tahoma",Font.BOLD,14));
		this.cancelar.setFocusPainted(false);	
		
		//Textfield de ingresar inputs		
		this.valor.setPreferredSize(new Dimension(200, 25));
		this.valor.setForeground(Color.BLACK);
		this.valor.setBackground(new Color(0xD6C0B3));
		this.valor.setFont(new Font("Tahoma",Font.PLAIN,12));
		this.valor.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//opciones	
		this.opciones.setPreferredSize(new Dimension(200,25));
		this.opciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.opciones.setBackground(new Color(0xD6C0B3));	
		
		//panel
		this.elPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));			
		
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
		mensaje1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    mensaje2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel botones = new JPanel();
		botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));
		botones.setMaximumSize(new Dimension(300,60));
		botones.setAlignmentX(Component.CENTER_ALIGNMENT);
		botones.add(Box.createHorizontalGlue());
		botones.add(aceptar);
		botones.add(Box.createRigidArea(new Dimension(5, 0)));
		botones.add(cancelar);
		botones.add(Box.createHorizontalGlue());
		botones.setBackground(new Color(0xDAD6D7));
		
		this.personalizarComps();
		
		Component emptySpace = Box.createRigidArea(new Dimension(0, 20));
		
		this.elPanel.add(mensaje1);
		this.elPanel.add(emptySpace);
		this.elPanel.add(valor);
		this.elPanel.add(emptySpace);
		this.elPanel.add(mensaje2);
		this.elPanel.add(emptySpace);
		this.elPanel.add(opciones);
		this.elPanel.add(emptySpace);
		this.elPanel.add(botones);	
		this.elPanel.setBackground(new Color(0xDAD6D7));
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

	@Override
	public List<JLabel> devolverEtiquetas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JTextComponent> devolverCamposTexto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JButton> devolverBotones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void callExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JButton getExit() {
		// TODO Auto-generated method stub
		return null;
	}

}
