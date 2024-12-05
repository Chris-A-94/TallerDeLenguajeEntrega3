package controladores;

import javax.swing.JButton;

import entregable1.Coin;
import entregable1.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
		this.actualizarData();
	}
	
	private void addListener()
	{
		JButton comprar = this.panelMoneda.getBotonComprar();
		comprar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	//agregar aca error si se entra sin saldos FIAT
		    	PanelCompra myCompra = new PanelCompra();
		    	PanelCompraControlador compraControl = new PanelCompraControlador(myCompra,panelMoneda.getMoneda(),user.getBilletera());
		    };
		});
	}
	private void actualizarData()
	{
		Timer repetidor = new Timer();
		TimerTask tarea = new TareaTimer();
		repetidor.schedule(tarea, 0, 1000);
		
	}
	
	private class TareaTimer extends TimerTask
	{

		@Override
		public void run() {			
			DecimalFormat numberFormat = new DecimalFormat("#.000000");
			Coin moneda = panelMoneda.getMoneda();
			panelMoneda.getTextContent().setText(" Precio: " + numberFormat.format(moneda.getPrecio()).toString() + "\n Stock: " + numberFormat.format(moneda.getStock()).toString());
		}
		
	}

}
