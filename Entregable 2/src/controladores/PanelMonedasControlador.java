package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLayeredPane;

import entregable1.Coin;
import entregable1.TipoMoneda;
import entregable1.Usuario;
import vistas.PanelMonedasVista;
import vistas.TarjetaVista;
import vistas.VistaSwap;

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
	
	// Declaración y Instanciación
	VistaSwap swapFrame = new VistaSwap();
	
	TarjetaVistaControladorCompra tarjetaControladorCompra;
	TarjetaVistaControladorSwap tarjetaControladorSwap;

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
			tarjetaControladorCompra = new TarjetaVistaControladorCompra(t, user);
			tarjetaControladorSwap = new TarjetaVistaControladorSwap(swapFrame, t, listaMonedas, user);
		});
		
		// Comportamiento a la VistaSwap
		asignarComportamientoVistaSwap(listaMonedas, user);
		
		// Agregar PanelMonedasVista al JLayeredPane
		layeredPane.add(panelMonedas, JLayeredPane.DEFAULT_LAYER);
	}
	
	private void asignarComportamientoVistaSwap(List<Coin> listaMonedas, Usuario user) {
		// Asignar el comportamiento al seleccionar una opción
		swapFrame.getListMonedas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TarjetaVistaControladorSwap.actualizarVistaSwap(listaMonedas, swapFrame);
			}
		});
		// Asignar el comportamiento al modificar la cantidad
		swapFrame.getCantidad().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TarjetaVistaControladorSwap.actualizarVistaSwap(listaMonedas, swapFrame);
			}
		});
		
		// Asignar el comportamiento al botón Confirmar de la VistaSwap
		swapFrame.getConfirmar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO:
				// LLAMAR OPERACIÓN SWAP
			}
		});
		
		// Asignar el comportamiento al botón Cancelar de la VistaSwap
		swapFrame.getCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ocultar ventana
				swapFrame.setVisible(false);
			}
		});
	}
	
	private void agregarMoneda(Coin c) {
		// Instanciar Tarjeta
		TarjetaVista tarjeta = new TarjetaVista(c);
		// Agregar Tarjeta a la lista
		listaTarjetas.add(tarjeta);
		
		// Agregar Tarjeta al panel
		panelMonedas.agregarMoneda(tarjeta);
	}
}
