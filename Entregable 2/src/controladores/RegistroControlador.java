package controladores;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import daos.UsuarioDAO;
import modelos.Sistema;
import modelos.Usuario;
import vistas.RegistroVista;
import vistas.logInPage;

public class RegistroControlador {
	private RegistroVista vista;
	private UsuarioDAO userDAO = new UsuarioDAO();
	private Usuario user;
	private Sistema sistema;
	public RegistroControlador(Sistema sistema) {
		this.vista = new RegistroVista();
		this.vista.getBotonAceptar().addMouseListener(new MouseControlAceptar());
		this.vista.devolverAtras().addMouseListener(new MouseControlBack());
		this.vista.termsButton().addMouseListener(new ButtonTermsControl());
		this.sistema = sistema;
	}
	
	private boolean verificarCampos() {
		if ((this.validarMail(vista.getMail()) == 0) && vista.termsAcepted() && validarContraseña() && allField())
			return true;
		else
		{
			
	       return false;
		}
			
	}
	private boolean validarContraseña() {
		if (!vista.confirmarContraseña()) {
			vista.errorContraseña();
			return false;
		}
		else
		{
			vista.repaint();
			return true;
		}
			
	}
	private int validarMail(String mail) {
		if (!mail.contains("@")) 
		{
			System.out.println("Error: Mail inválido");
			vista.errorMail();
			return 1;
		}
		else
		{
			String[] tokens = mail.split("@");
			if (!(tokens[1].equals("gmail.com") || tokens[1].equals("hotmail.com") || tokens[1].equals("yahoo.com"))) {
				System.out.println("Error: Mail inválido");
				vista.errorMail();
				return 2;
			}
			
			vista.repaint();
			return 0;
		}
			
	}
	public boolean allField() {
		boolean aux = true;
		for (JTextComponent tc : vista.devolverCamposTexto()) {
			if (tc.getForeground().equals(new Color(0xAB886D))) {
				aux = false;
				tc.setBorder(new LineBorder(Color.RED,2));
			}
			else
				tc.setBorder(null);
		}
		return aux;
	}
	
	private class MouseControlAceptar extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (verificarCampos()) {
				user = vista.crearUsuario();
				System.out.println(user.toString());
				
				if (userDAO.guardar(user)) {
					System.out.println("SE GUARDO");
					vista.dispose();
					@SuppressWarnings("unused")
					PrototipoControlador pc = new PrototipoControlador(sistema,user);
				}
				else
				{
					vista.errorMail();
					
				}
			}
						
		}

		
	}
	private class ButtonTermsControl extends MouseAdapter{
		public ButtonTermsControl() {
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Reader reader = null;
			String text = "";
			int data;
			try {
				reader = new FileReader("src/Imagenes/terminos");
				data = reader.read();
			while(data != -1){
				char dataChar = (char) data;
				text = text.concat(String.valueOf(dataChar));
				data = reader.read();
			}
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} finally {
			if (reader!=null)
				try {
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			vista.launchTerms(text);

		}
	}
	private class MouseControlBack extends MouseAdapter{
		public MouseControlBack() {
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			vista.dispose();
			logInPage lip = new logInPage();
			@SuppressWarnings("unused")
			logInController lic = new logInController(lip,sistema);
		}
	
	}
}

	

