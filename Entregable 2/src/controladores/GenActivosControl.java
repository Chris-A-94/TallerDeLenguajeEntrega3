package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.ActivosDAO;
import daos.CoinDAO;
import daos.UsuarioDAO;
import entregable1.Coin;
import entregable1.Saldo;
import entregable1.TipoMoneda;
import entregable1.Usuario;
import modelos.SaldoExcepcion;
import vistas.GenerarActivos;

public class GenActivosControl {
	
	private JButton generar;	
	private Usuario user;
	private GenerarActivos abrirPanel;
	
	public GenActivosControl(JButton generar,Usuario user)
	{
		this.generar = generar;
		this.user = user;
		this.addListener();
	}
	
	private void addListener()
	{
		this.generar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirPanel = new GenerarActivos();	
				setComportamiento();
			}			
		});
		
		
	}
	
	private void setComportamiento()
	{
		JButton cancelar = abrirPanel.getCancelar();
		JButton aceptar = abrirPanel.getAceptar();
		JComboBox<String> opciones = abrirPanel.getOpciones();		
		JTextField valor = abrirPanel.getValor();	
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				abrirPanel.dispose();
			}
		});
		
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(checkValue(valor))
				{					
					cargarSaldo(opciones.getSelectedItem().toString(),valor);
					abrirPanel.dispose();
				}
				else
					JOptionPane.showMessageDialog(null,"Escriba unicamente numeros positivos, utilizando el punto como divisior decimal." ,"Numero Invalido", JOptionPane.ERROR_MESSAGE);
			}
		});		
	}
	
	private boolean checkValue(JTextField valor)
	{
		String auxText = valor.getText();
		boolean usable = false;
		int pointCounter = 0;
		int pointPlace = -1;
		for(int i = 0; i < auxText.length(); i++)
		{
			if(auxText.charAt(i) == '.')
			{
				pointCounter++;
				pointPlace = i;
			}			
		}
		if(pointCounter > 1)
			return usable;
		
		for(int i = 0; i < auxText.length(); i++)
		{
			if(i == pointPlace)
				continue;
			if(!Character.isDigit(auxText.charAt(i)))
				return usable;
		}
		if(Double.parseDouble(auxText) < 0)
			return usable;
		usable = true;
		return usable;
	}
	
	private void cargarSaldo(String eleccion,JTextField valor)
	{
		if (valor.getText().equals(""))
			return;
		double saldoNuevo = Double.parseDouble(valor.getText());		
		if(eleccion.equals("Todo"))
		{		
			//solo agrega a monedas pre-existentes en la billetera
			CoinDAO aux = new CoinDAO();
			List<Coin> allCoins = aux.devolverTabla();
			for(Coin moneda: allCoins)
			{
				try {
					user.getBilletera().cargarActivos(moneda.getSigla(), saldoNuevo);
				} catch (SaldoExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,""+e.getDebugMsg(),"Error Critico",JOptionPane.ERROR_MESSAGE);
				}	
			}
						
		}		
		else
		{
			try {
				user.getBilletera().cargarActivos(eleccion, saldoNuevo);
			} catch (SaldoExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,""+e.getDebugMsg(),"Error Critico",JOptionPane.ERROR_MESSAGE);
			}	
		}
			
		
	}	
	

}
