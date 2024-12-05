package controladores;

import java.util.LinkedList;
import java.util.List;

import entregable1.Coin;
import entregable1.TipoMoneda;
import vistas.PanelMonedasVista;
import vistas.TarjetaVista;

public class PanelMonedasControlador {
	PanelMonedasVista panelMonedas;
	
	public PanelMonedasVista getPanelMonedas() {
		return panelMonedas;
	}

	List<TarjetaVista> listaTarjetas;
	
	public List<TarjetaVista> getListaTarjetas() {
		return listaTarjetas;
	}

	public PanelMonedasControlador(List<Coin> listaMonedas, int width, int height) {
		// Instanciar PanelMonedasVista
		panelMonedas = new PanelMonedasVista(width, height);
		
		// Instanciar lista de TarjetaVista
		listaTarjetas = new LinkedList<TarjetaVista>();
		
		// Agregar monedas
		for (Coin c : listaMonedas) {
			if (c.getTipo().equals(TipoMoneda.CRIPTOMONEDA))
			{
				agregarMoneda(c);
			}
		}
	}
	
	public void agregarMoneda(Coin c) {
		TarjetaVista tarjeta = new TarjetaVista(c);
		listaTarjetas.add(tarjeta);
		
		// Agregar Tarjeta al panel
		panelMonedas.agregarMoneda(tarjeta);
	}
}
