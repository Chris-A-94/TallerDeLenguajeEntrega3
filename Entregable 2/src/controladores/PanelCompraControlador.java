package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import entregable1.Billetera;
import entregable1.Coin;
import entregable1.Saldo;
import vistas.PanelCompra;
import daos.CoinDAO;

public class PanelCompraControlador {
	
	private PanelCompra panelVista;
	private Coin criptoSeleccionada;
	private Billetera wallet;
	private Saldo currentSaldo;
	
	public PanelCompraControlador(PanelCompra panelVista, Coin criptoSeleccionada, Billetera wallet)
	{
		this.panelVista = panelVista;
		this.criptoSeleccionada = criptoSeleccionada;
		this.wallet = wallet;		
		this.visualizarSaldo(this.panelVista.getMonedasFiat().getSelectedItem().toString());
		this.setListeners();
	}
	
	private void setListeners()
	{
		//listener para boton CANCELAR
		this.panelVista.getCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelVista.dispose();
			}
		});
		//listener para Label visualizador de Saldo
		this.panelVista.getMonedasFiat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				visualizarSaldo(panelVista.getMonedasFiat().getSelectedItem().toString());
			}
		});
		
		//listener para COMPRAR
		this.panelVista.getComprar().addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if(checkValue(panelVista.getValor()))
						{
							wallet.nuevaCompra(panelVista.getValor(),criptoSeleccionada);
							panelVista.dispose();
						}
						else
							JOptionPane.showMessageDialog(null,"Escriba unicamente numeros, utilizando el punto como divisior decimal." ,"Numero Invalido", JOptionPane.ERROR_MESSAGE);
					
					}
				});
		
	}
	//no puedo llamarlo sin saber que esta seleccionado 
	//ahora mismo esta para ver si funciona nomas, solo actualiza USD
	private void visualizarSaldo(String fiatSigla)
	{
		JLabel saldo = panelVista.getCantidadYPrecio();		
		double precioMoneda;
		if(fiatSigla.equalsIgnoreCase("USD"))
			precioMoneda = this.criptoSeleccionada.getPrecio();
		else
			precioMoneda = this.criptoSeleccionada.getPrecio() * 1000;
		
		for(Saldo fiat: wallet.getArregloSaldo())
		{
			if(fiat.getSigla().equalsIgnoreCase(fiatSigla))
				this.currentSaldo = fiat;
		}
		
		saldo.setText("Valor "+this.criptoSeleccionada.getNombre()+":"+precioMoneda+""+this.currentSaldo.getSigla()+
				 " Saldo actual: "+this.currentSaldo.getCantMonedas()+""+this.currentSaldo.getSigla());
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
		usable = true;
		return usable;
	}

}
