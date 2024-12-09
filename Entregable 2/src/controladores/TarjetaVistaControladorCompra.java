package controladores;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelos.Coin;
import modelos.Saldo;
import modelos.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import vistas.PanelCompra;
import vistas.TarjetaVista;

public class TarjetaVistaControladorCompra {
	
	private TarjetaVista panelMoneda;
	private Usuario user;
	
	public TarjetaVistaControladorCompra(TarjetaVista panelMoneda, Usuario user)
	{
		this.panelMoneda = panelMoneda;
		this.user = user;
		
		this.addListener();
	}
	
	private void addListener()
	{
		JButton comprar = this.panelMoneda.getBotonComprar();
		comprar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	//agregar aca error si se entra sin saldos FIAT
		    	boolean saldoFiat = false;
		    	for(Saldo s: user.getBilletera().getArregloSaldo())
		    	{
		    		if(s.getTipo().equals("FIAT"))
		    			saldoFiat = true;
		    	}
		    	if(!saldoFiat)
		    	{
		    		JOptionPane.showMessageDialog(null,"Genere USD o ARS antes de comprar criptomonedas." ,"No se encontro activo FIAT", JOptionPane.ERROR_MESSAGE);
		    		return;
		    	}
		    	PanelCompra myCompra = new PanelCompra();
		    	PanelCompraControlador compraControl = new PanelCompraControlador(myCompra,panelMoneda.getMoneda(),user.getBilletera());
		    };
		});
	}
}
