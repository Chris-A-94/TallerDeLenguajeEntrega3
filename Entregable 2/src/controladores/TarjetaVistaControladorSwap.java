package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entregable1.Coin;
import entregable1.Usuario;
import vistas.TarjetaVista;
import vistas.VistaSwap;

public class TarjetaVistaControladorSwap {
	private VistaSwap swapFrame;
	
	private List<Coin> listaMonedas;
	
	public TarjetaVistaControladorSwap(VistaSwap swapFrame, TarjetaVista tarjetaVista, List<Coin> listaMonedas, Usuario usuario) {
		// Asignar VistaSwap a la Tarjeta
		this.swapFrame = swapFrame;
		
		// Asignar List<Coin>
		this.listaMonedas = listaMonedas;
		
		// Asignar el comportamiento al botón SWAP de la Tarjeta
		tarjetaVista.getBotonSwap().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Hacer visible la ventana
				swapFrame.setVisible(true);
				
				// Asignar moneda
				swapFrame.setTargetCoin(tarjetaVista.getMoneda());
				// Actualizar los saldos disponibles para realizar SWAP
				swapFrame.asignarSaldosDisponibles(usuario.getBilletera().getArregloSaldo());
				
				// Actualizar
				actualizarVistaSwap(listaMonedas, swapFrame);
			}
		});
	}
	
	public static void actualizarVistaSwap(List<Coin> listaMonedas, VistaSwap swapFrame) {	
		// Busco la cantidad
		Double cantidad = obtenerCantidad(swapFrame);
		
		// Busco la moneda seleccionada
		String strMoneda = obtenerSeleccion(swapFrame);
		
		// Busco la moneda
		Coin moneda = buscarMoneda(listaMonedas, strMoneda);
		// Verificación
		if (moneda == null)
			return;
		
		swapFrame.actualizarConversion(cantidad * (moneda.getPrecio() / swapFrame.getTargetCoin().getPrecio()));
	}
	public static double obtenerCantidad(VistaSwap swapFrame) {
		// Obtengo la cantidad a convertir
		String strCantidad = swapFrame.getCantidad().getText();
		// Verificación
		if (strCantidad.isEmpty())
			strCantidad = "0";
		Double cantidad;
		try {
			 cantidad = Double.valueOf(strCantidad);
			 if (cantidad < 0.0)
				 cantidad = 0.0;
		} catch (NumberFormatException e) {
			cantidad = 0.0;
		}
		
		return cantidad;
	}
	
	public static String obtenerSeleccion(VistaSwap swapFrame) {
		// Obtengo la selección de la moneda
		String strMoneda = (String) swapFrame.getListMonedas().getSelectedItem();
		// Verificación
		if (strMoneda == null)
			return "undefined";
		strMoneda = strMoneda.split(" ")[0];
		
		return strMoneda;
	}
	
	public static Coin buscarMoneda(List<Coin> listaMonedas, String sigla) {
		for (Coin c : listaMonedas) {
			if (c.getSigla().equalsIgnoreCase(sigla))
				return c;
		}
		
		return null;
	}
}
