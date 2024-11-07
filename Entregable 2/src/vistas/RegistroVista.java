package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import controladores.ExitButton;

public class RegistroVista extends JFrame implements vista{
	private static final long serialVersionUID = 1L;
	private JLabel labelTit	=	new JLabel("Sign Up",SwingConstants.CENTER);
	private JLabel jlName 	= 	new JLabel("Nombre");
	private JLabel jlSur	= 	new JLabel("Apellido");
	private JLabel jlMail  	= 	new JLabel("Email");
	private JLabel jlPass 	= 	new JLabel("Contraseña");
	
	private JTextField textFieldName =	new JTextField("Joaquín Victor");
	private JTextField textFieldSur  =	new JTextField("Gonzales");
	private JTextField textFieldMail =	new JTextField("buenas@gmail.com");
	private JPasswordField passField =	new JPasswordField("Password");
	
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
		//CONSTANTES
		int dimX = this.getWidth();
		int dimY = this.getHeight();
		Color camposTextColor = new Color(0xD6C0B3);
		Color textColor = new Color(0xAB886D);
		int cons=40;
		//CONFIGURACIÓN DE IMÁGENES
		
		
		//CONFIGURACIÓN DE LAS ETIQUETAS
		int posIniY = dimY/4;
		int posIniX = dimX/2 - 60;
		
		//titulo
		labelTit.setSize(150, 50);
		labelTit.setLocation(dimX/2-75,posIniY-55);
		labelTit.setFont(new Font("Tahoma", Font.BOLD,30));
		labelTit.setForeground(new Color(0x291e17));
		
		//nombre
		jlName.setBounds(posIniX,posIniY,100,20);
		textFieldName.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		//apellido
		jlSur.setBounds(posIniX,posIniY+cons,100,20);
		textFieldName.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		//Mail
		jlMail.setBounds(posIniX,posIniY+cons*2,100,20);
		textFieldName.setFont(new Font("Tahoma",Font.PLAIN,12));
		
		//Contraseña
		jlPass.setBounds(posIniX,posIniY+cons*3,100,20);
		textFieldName.setFont(new Font("Tahoma",Font.PLAIN,12));
				
		
		
		//CONFIGURACIÓN DE LOS CAMPOS DE TEXTO
		int boxHeight=20;
		int boxWidth=130;
		posIniY = dimY/4 + 20;
		posIniX = dimX/2 - boxWidth/2;
		
		//nombre
		textFieldName.setBounds(posIniX,posIniY,boxWidth,boxHeight);
		textFieldName.setBackground(camposTextColor);
		textFieldName.setBorder(null);
		textFieldName.setFont(new Font("Tahoma",Font.PLAIN,12));
		textFieldName.setForeground(textColor);
		
		
		//apellido
		textFieldSur.setBounds(posIniX,posIniY+cons,boxWidth,boxHeight);
		textFieldSur.setBackground(camposTextColor);
		textFieldSur.setBorder(null);
		textFieldSur.setFont(new Font("Tahoma",Font.PLAIN,12));
		textFieldSur.setForeground(textColor);
		
		
		//mail
		textFieldMail.setBounds(posIniX,posIniY+2*cons,boxWidth,boxHeight);
		textFieldMail.setBackground(camposTextColor);
		textFieldMail.setBorder(null);
		textFieldMail.setFont(new Font("Tahoma",Font.PLAIN,12));
		textFieldMail.setForeground(textColor);
		//contraseña
		passField.setEchoChar('*');
		passField.setBounds(posIniX,posIniY+3*cons,boxWidth,boxHeight);
		passField.setBackground(camposTextColor);
		passField.setBorder(null);
		passField.setFont(new Font("Tahoma",Font.PLAIN,12));
		passField.setForeground(textColor);
		
		
		//CONFIGURACIÓN DE BOTONES
		botonSalir.setBounds(dimX-cons, 10, 30, 30);
		botonSalir.setBorder(null);
		botonSalir.setBackground(null);
		botonSalir.setFont(new Font("Arial", Font.BOLD,20));
		botonSalir.setFocusPainted(false);
		botonSalir.setForeground(textColor);
		ExitButton conEB = new ExitButton(this);
		   
		
		
		botonAceptar.setBounds(posIniX,posIniY+4*cons, boxWidth, boxHeight+10);
		botonAceptar.setBorder(null);
		botonAceptar.setBackground(new Color(0xAB886D));
		botonAceptar.setFont(new Font("Arial", Font.PLAIN,20));
		botonAceptar.setFocusPainted(false);
		
		
		//AGREGRO AL JFRAME
		this.add(this.labelTit);
		this.add(this.textFieldName);
		this.add(this.textFieldSur);
		this.add(this.textFieldMail);
		this.add(this.passField);
		this.add(this.jlSur);
		this.add(this.jlName);
		this.add(this.jlMail);
		this.add(this.jlPass);
		this.add(botonSalir);
		this.add(botonAceptar);
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
	@Override
	public List<JLabel> devolverEtiquetas() {
		List<JLabel> etiquetas = new LinkedList<JLabel>();
		etiquetas.add(jlName);
		etiquetas.add(jlMail);
		etiquetas.add(jlPass);
		etiquetas.add(jlSur);
		
		return etiquetas;
	}
	@Override
	public List<JTextComponent> devolverCamposTexto() {
		List<JTextComponent> campos = new LinkedList<JTextComponent>();
		campos.add(passField);
		campos.add(textFieldMail);
		campos.add(textFieldName);
		campos.add(textFieldSur);
		
		return campos;
	}
	@Override
	public List<JButton> devolverBotones() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JButton getExit() {
		return this.botonSalir;
	}
	@Override
	public void close() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
}