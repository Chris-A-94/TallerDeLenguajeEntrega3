package controladores;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JLayeredPane;

import entregable1.Coin;
import entregable1.TipoMoneda;
import entregable1.Usuario;
import vistas.PanelMonedasVista;
import vistas.TarjetaVista;

public class PanelMonedasControlador {
	final int _POPUP_HEIGHT = 75;
	
	JLayeredPane layeredPane;
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	PanelMonedasVista panelMonedas;
	public PanelMonedasVista getPanelMonedas() {
		return panelMonedas;
	}

	List<TarjetaVista> listaTarjetas;
	
	public List<TarjetaVista> getListaTarjetas() {
		return listaTarjetas;
	}

	public PanelMonedasControlador(List<Coin> listaMonedas, Usuario user, int width, int height) {
		// Instanciar JLayeredPane principal
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(0, 0, width, height);
		
		// Instanciar PanelMonedasVista
		panelMonedas = new PanelMonedasVista(width, height);
		panelMonedas.setBounds(0, 0, width, height);
		
		// Instanciar lista de TarjetaVista
		listaTarjetas = new LinkedList<TarjetaVista>();
		
		// Agregar monedas
		for (Coin c : listaMonedas) {
			if (c.getTipo().equals(TipoMoneda.CRIPTOMONEDA))
			{
				agregarMoneda(c);
			}
		}
		
		// Asignar comportamiento a las tarjetas
		this.getListaTarjetas().forEach(t -> {
			new TarjetaVistaControladorCompra(t, user);
			new TarjetaVistaControladorSwap(t, user);
		});
		
		// Agregar PanelMonedasVista al JLayeredPane
		layeredPane.add(panelMonedas, JLayeredPane.DEFAULT_LAYER);
	}
	
	public void agregarMoneda(Coin c) {
		// Instanciar Tarjeta
		TarjetaVista tarjeta = new TarjetaVista(c);
		// Agregar Tarjeta a la lista
		listaTarjetas.add(tarjeta);
		
		// Agregar Tarjeta al panel
		panelMonedas.agregarMoneda(tarjeta);
	}
}
