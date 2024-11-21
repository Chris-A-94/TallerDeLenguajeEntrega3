package controladores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import daos.UsuarioDAO;
import entregable1.Sistema;
import entregable1.Usuario;
import modelos.MonitoreoCoin;
import vistas.MenuVista;
import vistas.RegistroVista;

public class RegistroControlador {
	private RegistroVista vista;
	private UsuarioDAO userDAO = new UsuarioDAO();
	private Usuario user;
	private Sistema sistema;
	public RegistroControlador(Sistema sistema) {
		this.vista = new RegistroVista();
		this.vista.getBotonAceptar().addMouseListener(new MouseControlAceptar());
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
	
	class MouseControlAceptar implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (verificarCampos()) {
				user = vista.crearUsuario();
				System.out.println(user.toString());
				
				if (userDAO.guardar(user)) {
					System.out.println("SE GUARDO");
					vista.dispose();
					PrototipoControlador pc = new PrototipoControlador(sistema,user);
				}
				else
				{
					vista.errorMail();
					
				}
			}
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class FocusControlTexts implements FocusListener{
		private JTextComponent tf;
		private String firstValue;
		public FocusControlTexts(JTextComponent tf) {
			this.tf = tf;
			firstValue = tf.getText();
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			Color color = tf.getForeground();
			tf.setBackground(new Color(0xf5ded0));
			if (color.equals(new Color(0xAB886D))) {	
				this.tf.setText(null);
				this.tf.setForeground(new Color(0x291e17));
			}
			
			return;
		}

		@Override
		public void focusLost(FocusEvent e) {
			tf.setBackground(new Color(0xD6C0B3));
			if (tf.getText().equals("")) {
				tf.setText(firstValue);
				tf.setForeground(new Color(0xAB886D));
			if ((firstValue.equals("buenas@gmail.com"))) {
				vista.errorMail();
				
			}	
			}
		}
		
	}
	
	/*
	 * Controlador del botón de salida.
	 */
	
	}

	

