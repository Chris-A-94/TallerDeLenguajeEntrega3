package controladores;

import java.util.List;

import javax.swing.JButton;
import javax.swing.text.JTextComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import daos.UsuarioDAO;
import decoradores.ControladorTextField;
import entregable1.Usuario;
import vistas.MenuVista;
import vistas.RegistroVista;
import vistas.logInPage;

public class logInController {
	
	private logInPage frontEnd;
	private UsuarioDAO users;
	private List<Usuario> usuarios;
	private String Email;
	private String Password;
	
	public logInController(logInPage login)
	{
		this.frontEnd = login;
		users = new UsuarioDAO();
		usuarios = users.devolverTabla(); //no me gusta esto, deberia haber un metodo "buscarUser" en el Dao
		this.actualizarData();
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
				    	frontEnd.callExit();
				    	RegistroVista registroVista = new RegistroVista();
						//RegistroControlador registroControlador = new RegistroControlador(registroVista);
					    ControladorTextField conTf = new ControladorTextField(registroVista);					    
				    };		
			});
		//boton Log In
		botones.get(1).addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	actionLogIn();
		    };		
	});
		//boton olvide password
				botones.get(2).addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        System.out.println("Pos que mal, porque esto capaz no lo implemento");
				    };		
			});

	}
	
	private void actualizarData()
	{
		List<JTextComponent> data = this.frontEnd.devolverData();
		this.Email = data.get(0).getText();
		this.Password = data.get(1).getText();		
	}
	
	private void actionLogIn()
	{
		this.actualizarData();
		if(!this.userExist(this.Email))
		{
			System.err.println("User inexistente");
		}
		else
		{
			this.frontEnd.callExit();
			MenuVista menuVista = new MenuVista();
		}
	}
}
