package controladores;

import java.util.List;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import daos.UsuarioDAO;
import entregable1.Usuario;
import vistas.RegistroVista;
import vistas.logInPage;

public class logInController {
	
	private logInPage frontEnd;
	private UsuarioDAO users;
	List<Usuario> usuarios;
	
	public logInController(logInPage login)
	{
		this.frontEnd = login;
		users = new UsuarioDAO();
		usuarios = users.devolverTabla(); //no me gusta esto, deberia haber un metodo "buscarUser" en el Dao
		this.agregarListeners();
	}
	
	private boolean userExist(String email)
	{
		boolean existe = false;
		for(Usuario user: this.usuarios)
		{
			if(user.getEmail().equals(email))
				existe = true;
		}
		return existe;
	}
	
	private void agregarListeners()
	{
		List<JButton> botones = this.frontEnd.devolverBotones();
		//boton Sign Up
				botones.get(0).addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	RegistroVista registroVista = new RegistroVista();
						RegistroControlador registroControlador = new RegistroControlador(registroVista);
					    ControladorTextField conTf = new ControladorTextField(registroVista);
					    botones.get(3).doClick(); //cierra la ventana
				    };		
			});
		//boton Log In
		botones.get(1).addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Presiono logi, re logi");
		    };		
	});
		//boton olvide password
				botones.get(2).addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        System.out.println("Presiono pass, re logi");
				    };		
			});

	}
}
