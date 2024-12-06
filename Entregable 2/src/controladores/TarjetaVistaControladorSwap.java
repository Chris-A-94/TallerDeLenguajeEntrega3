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
		// Obtengo la cantidad a convertir
		String strCantidad = swapFrame.getCantidad().getText();
		// Verificación
		if (strCantidad.isEmpty())
			strCantidad = "0";
		Double cantidad;
		try {
			 cantidad = Double.valueOf(strCantidad);
		} catch (NumberFormatException e) {
			cantidad = 0.0;
		}
		
		// Verificación
		if (cantidad == null)
			cantidad = 0.0;
		
		// Obtengo la selección de la moneda
		String strMoneda = (String) swapFrame.getListMonedas().getSelectedItem();
		// Verificación
		if (strMoneda == null)
			return;
		strMoneda = strMoneda.split(" ")[0];
		
		// Busco la moneda
		Coin moneda = buscarMoneda(listaMonedas, strMoneda);
		// Verificación
		if (moneda == null)
			return;
		
		swapFrame.actualizarConversion(cantidad * (moneda.getPrecio() / swapFrame.getTargetCoin().getPrecio()));
	}
	
	public static Coin buscarMoneda(List<Coin> listaMonedas, String sigla) {
		for (Coin c : listaMonedas) {
			if (c.getSigla().equalsIgnoreCase(sigla))
				return c;
		}
		
		return null;
	}
}
