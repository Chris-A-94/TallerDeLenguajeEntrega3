package controladores;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.TarjetaVista;

public class tarjetaVistaControladorCompra {
	
	private TarjetaVista panelMoneda;
	
	public tarjetaVistaControladorCompra(TarjetaVista panelMoneda)
	{
		this.panelMoneda = panelMoneda;
		this.addListener();
	}
	
	private void addListener()
	{
		JButton comprar = this.panelMoneda.getBotonComprar();
		comprar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("Working");
		    };
		});
	}

}
