package vistas;

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

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
		
		
		//AGREGRO AL JFRAME
		this.add(this.textFieldTit);
		this.add(this.textFieldName);
		this.add(this.textFieldSur);
		this.add(this.textFieldMail);
		this.add(this.passField);
		//
		this.setVisible(true);
		
	}
	public JLabel getTextFieldTit() {
		return textFieldTit;
	}
	public JTextField getTextFieldName() {
		return textFieldName;
	}
	public JTextField getTextFieldSur() {
		return textFieldSur;
	}
	public JTextField getTextFieldMail() {
		return textFieldMail;
	}
	public JPasswordField getPassField() {
		return passField;
	}
	public JButton getBotonAceptar() {
		return botonAceptar;
	}
	public JButton getBotonAtras() {
		return botonAtras;
	}
	public List<JTextField> devolverCampos(){
		List<JTextField> listaCampos = new LinkedList<JTextField>();
		listaCampos.add(textFieldName);
		listaCampos.add(textFieldSur);
		listaCampos.add(textFieldMail);
		return listaCampos;
	}
}
