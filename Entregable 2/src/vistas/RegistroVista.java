package vistas;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class RegistroVista extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelTit	 =	new JLabel("Sign Up",SwingConstants.CENTER);
	private JTextField textFieldName =	new JTextField("Nombre");
	private JTextField textFieldSur  =	new JTextField("Apellido");
	private JTextField textFieldMail =	new JTextField("Email");
	private JPasswordField passField =	new JPasswordField("Contraseña");
	
	private JButton botonAceptar = new JButton("Aceptar");
	private JButton botonSalir 	 = new JButton("X");
	private JButton botonAtras 	 = new JButton("Atrás");
	public RegistroVista() {
		
		//CONFIGURACION DEL JFRAME
		this.setTitle("Sign in"); //Titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setSize(300,350); //Tamaño del frame
		this.setLocationRelativeTo(null); 
		this.setLayout(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(0xE4E0E1));
		//CONFIGURACIÓN DE LOS CAMPOS DE TEXTO
		
		int dimX = this.getWidth();
		int dimY = this.getHeight();
		Color camposTextColor = new Color(0xD6C0B3);
		int cons=40;
		int posIni = dimY/4 + 40;
		//titulo
		labelTit.setSize(150, 50);
		labelTit.setLocation(dimX/2-75,dimY/4-40);
		labelTit.setFont(new Font("Tahoma", Font.BOLD,30));
		labelTit.setForeground(new Color(0x291e17));
		
		//nombre
		textFieldName.setBounds(dimX/2-50,posIni,100,20);
		textFieldName.setBackground(camposTextColor);
		textFieldName.setBorder(null);
		textFieldName.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		//apellido
		textFieldSur.setBounds(dimX/2-50,posIni+cons,100,20);
		textFieldSur.setBackground(camposTextColor);
		textFieldSur.setBorder(null);
		textFieldSur.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		
		//mail
		textFieldMail.setBounds(dimX/2-50,posIni+2*cons,100,20);
		textFieldMail.setBackground(camposTextColor);
		textFieldMail.setBorder(null);
		textFieldMail.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		//contraseña
		passField.setEchoChar('*');
		passField.setBounds(dimX/2-50,posIni+3*cons,100,20);
		passField.setBackground(camposTextColor);
		passField.setBorder(null);
		passField.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		//CONFIGURACIÓN DE BOTONES
		botonSalir.setBounds(dimX-cons, 10, 30, 30);
		botonSalir.setBorder(null);
		botonSalir.setBackground(null);
		botonSalir.setFont(new Font("Arial", Font.BOLD,20));
		//AGREGRO AL JFRAME
		this.add(this.labelTit);
		this.add(this.textFieldName);
		this.add(this.textFieldSur);
		this.add(this.textFieldMail);
		this.add(this.passField);
		this.add(botonSalir);
		//
		this.setVisible(true);
		
	}
	public JLabel getlabelTit() {
		return labelTit;
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
	public List<JTextComponent> devolverCampos(){
		List<JTextComponent> listaCampos = new LinkedList<JTextComponent>();
		listaCampos.add(textFieldName);
		listaCampos.add(textFieldSur);
		listaCampos.add(textFieldMail);
		listaCampos.add(passField);
		return listaCampos;
	}
	public JButton getBotonSalir() {
		return botonSalir;
	}
}
