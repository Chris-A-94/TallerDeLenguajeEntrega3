package controladores;

import javax.swing.JButton;

import entregable1.Coin;
import entregable1.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import vistas.TarjetaVista;

public class tarjetaVistaControladorCompra {
	
	private TarjetaVista panelMoneda;
	private Usuario user;
	
	public tarjetaVistaControladorCompra(TarjetaVista panelMoneda, Usuario user)
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
		    	user.getBilletera().nuevaCompra();
		    };
		});
	}

}
