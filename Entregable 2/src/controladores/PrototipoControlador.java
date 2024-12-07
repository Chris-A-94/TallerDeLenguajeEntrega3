package controladores;

import entregable1.Sistema;
import entregable1.Usuario;
import vistas.*;

/*
 * Posible nombre: GestionMenuControlador
 */
public class PrototipoControlador {
	private MenuVista myMenu;
	private Usuario user;
	
	public PrototipoControlador(Sistema sistema, Usuario user) {
		myMenu = new MenuVista();
		GenActivosControl activos = new GenActivosControl(myMenu.getGenerador(),user);
		this.user = user;
		
        //Paneles agustín
      	TransaccionControlador tc = new TransaccionControlador(user.getBilletera().getTransacciones());
        myMenu.agregarPanel("Mi historial", tc.getPanel());
		
		/*
		 * 1° Panel: Criptomonedas
		 */
        // Instanciar PanelMonedasControlador
		PanelMonedasControlador controladorPanelMonedas = new PanelMonedasControlador(sistema.getMonedas(), user, myMenu.getPreferredContentWidth(), myMenu.getPreferredContentHeight());
		// Agregar panel
		myMenu.agregarPanel("Criptomonedas",controladorPanelMonedas.getLayeredPane());
		
		/*
		 * 2° Panel: Mis Activos
		 */
		MisActivosControlador misActivos = new MisActivosControlador(myMenu.getPreferredContentWidth(), sistema, user);
		myMenu.agregarPanel("Mis Activos", misActivos.getMisActivosVista());
	}
	
	
	
}
