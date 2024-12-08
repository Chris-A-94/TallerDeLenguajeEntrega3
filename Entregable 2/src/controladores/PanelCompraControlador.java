package controladores;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entregable1.Billetera;
import entregable1.Coin;
import entregable1.Saldo;
import modelos.CompraExcepcion;
import vistas.PanelCompra;
import daos.CoinDAO;

public class PanelCompraControlador {
	
	private PanelCompra panelVista;
	private Coin criptoSeleccionada;
	private Billetera wallet;
	private Saldo currentSaldo;
	private Border defaultBorder;
	
	public PanelCompraControlador(PanelCompra panelVista, Coin criptoSeleccionada, Billetera wallet)
	{
		this.panelVista = panelVista;
		this.criptoSeleccionada = criptoSeleccionada;
		this.wallet = wallet;	
		this.defaultBorder = panelVista.getValor().getBorder();
		this.cargarOpciones();
		this.setListeners();
		this.visualizarSaldo(this.panelVista.getMonedasFiat().getSelectedItem().toString());		
	}
	
	private void cargarOpciones()
	{
		JComboBox<String> monedasFiat = this.panelVista.getMonedasFiat();
		List<String> mySaldo = new ArrayList<String>();
		for(Saldo aux: wallet.getArregloSaldo())
		{
			if(aux.getTipo().equals("FIAT"))
				mySaldo.add(aux.getSigla());
		}
		
		monedasFiat.setModel(new DefaultComboBoxModel<>(mySaldo.toArray(new String[0])));
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
		
		//listener para INPUT
		this.panelVista.getValor().getDocument().addDocumentListener(new DocumentListener() {
           
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				panelVista.getValor().setBorder(defaultBorder);
				this.checkStuff();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				panelVista.getValor().setBorder(defaultBorder);
				this.checkStuff();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			private void checkStuff()
			{
				if(checkValue(panelVista.getValor()))
				{
					String fiatSeleccionado = panelVista.getMonedasFiat().getSelectedItem().toString();
					Saldo aux = null;
					CoinDAO auxCoinFiat = new CoinDAO();
					Coin fiatCoin = auxCoinFiat.getCoin(fiatSeleccionado);
					for(Saldo s: wallet.getArregloSaldo())
					{
						if(s.getSigla().equals(fiatSeleccionado))
							aux = s;
					}
					
					double valorEnCuadro = Double.parseDouble(panelVista.getValor().getText());
					//if(aux.getCantMonedas() < Double.parseDouble(panelVista.getValor().getText()))
					if(aux.getCantMonedas() <valorEnCuadro)	
					{
						panelVista.getValor().setBorder(new LineBorder(Color.RED, 2));						
					}
					
					double valorEquivalente = (valorEnCuadro * fiatCoin.getPrecio()) / criptoSeleccionada.getPrecio();
					
					panelVista.getprecioActualizable().setText("Valor Equivalente: "+valorEquivalente+criptoSeleccionada.getNombre());
				}
				else
					panelVista.getValor().setBorder(new LineBorder(Color.RED, 2));
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
							String fiatSeleccionado = panelVista.getMonedasFiat().getSelectedItem().toString();
							Saldo aux = null;
							for(Saldo s: wallet.getArregloSaldo())
							{
								if(s.getSigla().equals(fiatSeleccionado))
									aux = s;
							}
							if(aux.getCantMonedas() < Double.parseDouble(panelVista.getValor().getText()))
							{								
								return;
							}
							try {
								//LLAMADO A COMPRA
								wallet.nuevaCompra(panelVista.getValor(),criptoSeleccionada,fiatSeleccionado);
							} catch (CompraExcepcion e1) {
								if(!e1.hayStock())
								{
									JOptionPane.showMessageDialog(null,"No hay stock de "+criptoSeleccionada.getNombre()+" por favor espere." ,"No Stock", JOptionPane.ERROR_MESSAGE);
									criptoSeleccionada.generarStock();
									JOptionPane.showMessageDialog(null,"Intente nuevamente." ,"Stock Generado", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									Object[] enEspaniol = {"Sí", "No"};
									int reintentar = JOptionPane.showOptionDialog (null,"Esta intentando comprar un monto muy alto. ¿Desea comprar el maximo posible en su lugar?","Monto Invalido",JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,enEspaniol,enEspaniol[0]);
									if(reintentar == 0)
									{
										JTextField auxText = new JTextField();
										Double valorAux = criptoSeleccionada.getStock() * criptoSeleccionada.getPrecio();
										CoinDAO fiatAux = new CoinDAO();
										if(!fiatSeleccionado.equalsIgnoreCase("usd"))
											valorAux /= fiatAux.getCoin(fiatSeleccionado).getPrecio();
										auxText.setText(valorAux.toString());
										try {
											wallet.nuevaCompra(auxText, criptoSeleccionada, fiatSeleccionado);
										} catch (CompraExcepcion e2) {
											//Aca no deberia nunca entrar al Catch
											e2.printStackTrace();
										}
									}
									else
										return;
								}
							}
							panelVista.dispose();
							cargarOpciones(); 
						}
						else
							JOptionPane.showMessageDialog(null,"Escriba unicamente numeros positivos, utilizando el punto como divisior decimal." ,"Numero Invalido", JOptionPane.ERROR_MESSAGE);
					
					}
				});
		
	}
	
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
		if(auxText.equals(""))
			return usable;
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

}
