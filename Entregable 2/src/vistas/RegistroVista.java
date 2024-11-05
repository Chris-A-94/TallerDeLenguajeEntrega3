package vistas;

import java.awt.GridLayout;

import javax.swing.*;

public class RegistroVista extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textFieldTit	 =	new JLabel("Sign in");
	private JTextField textFieldName =	new JTextField("Nombre");
	private JTextField textFieldSur  =	new JTextField("Apellido");
	private JTextField textFieldMail =	new JTextField("Email");
	private JPasswordField passField =	new JPasswordField("Contraseña");
	
	private JButton botonAceptar = new JButton("Aceptar");
	private JButton botonAtras = new JButton("Atrás");
	public RegistroVista() {
		
		//CONFIGURACION DEL JFRAME
		this.setTitle("Sign in"); //Titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setSize(400,150); //Tamaño del frame
		this.setLocationRelativeTo(null); 
		this.setLayout(new GridLayout(6,1));
		//CONFIGURACIÓN DE LOS CAMPOS DE TEXTO
		passField.setEchoChar('*');
		this.add(this.textFieldTit);
		this.add(this.textFieldName);
		this.add(this.textFieldSur);
		this.add(this.textFieldMail);
		this.add(this.passField);
		//
		this.setVisible(true);
		
	}
}
