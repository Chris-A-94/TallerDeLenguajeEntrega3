package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.JTextComponent;
import decoradores.ControladorTextField;
import decoradores.ExitButton;
import entregable1.Usuario;

public class RegistroVista extends JFrame implements Vista{
	private static final long serialVersionUID = 1L;
	private JLabel labelTit	=	new JLabel("Sign Up",SwingConstants.CENTER);
	private JLabel jlName 	= 	new JLabel("Nombre");
	private JLabel jlSur	= 	new JLabel("Apellido");
	private JLabel jlMail  	= 	new JLabel("Email");
	private JLabel jlPass 	= 	new JLabel("Contraseña");
	private JLabel jlCon 	= 	new JLabel("Confirmar");
	private JLabel jlDNI 	= 	new JLabel("DNI");
	
	private JTextField textFieldName 	=	new JTextField("Joaquín Victor");
	private JTextField textFieldSur  	=	new JTextField("Gonzales");
	private JTextField textFieldMail 	=	new JTextField("buenas@gmail.com");
	private JPasswordField passField 	=	new JPasswordField("Password");
	private JPasswordField passConfirm 	= 	new JPasswordField("Password");
	private JTextField textFieldDNI		=	new JTextField("43912162");
	
	private JButton botonAceptar = new JButton("Aceptar");
	private JButton botonSalir 	 = new JButton("X");
	private BasicArrowButton prevPageButton = new BasicArrowButton(BasicArrowButton.WEST);
	private JCheckBox terminos 	= new JCheckBox("Acepto los términos y condiciones");
	
	public RegistroVista() {
		//COLORES
		Color fondoFrame  		= new Color(0xE4E0E1);
		Color camposTextColor 	= new Color(0xD6C0B3);
		Color textColor 		= new Color(0xAB886D);
		//CONSTANTES
		int dimX = 330;
		int dimY = 500;
		int cons=40;
		//CONFIGURACION DEL JFRAME
		this.setTitle("Sign in"); //Titulo del frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setSize(dimX,dimY); //Tamaño del frame
		this.setLocationRelativeTo(null); 
		this.setLayout(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.getContentPane().setBackground(fondoFrame);
		ControladorTextField ct = new ControladorTextField(this);
		
		// LayoutManager	
		this.setShape(new RoundRectangle2D.Double(0, 0, dimX, dimY, 25, 25));	
		//MOVER VENTANA (CODIGOD E FRAN)
		WindowBarPanel wbp = new WindowBarPanel(this,fondoFrame,dimX,dimY,false);		
		//CONFIGURACIÓN DE IMÁGENES
		
		//CONFIGURACIÓN DE LAS ETIQUETAS
		int posIniY = dimY/4;
		int posIniX = dimX/2 - 60;
		
		//titulo
		labelTit.setSize(150, 50);
		labelTit.setLocation(dimX/2-75,posIniY-75);
		labelTit.setFont(new Font("Tahoma", Font.BOLD,30));
		labelTit.setForeground(new Color(0x291e17));
		
		//nombre
		jlName.setBounds(posIniX,posIniY,100,20);
		
		//apellido
		jlSur.setBounds(posIniX,posIniY+cons,100,20);
		
		//Mail
		jlMail.setBounds(posIniX,posIniY+cons*2,100,20);
		
		//Contraseña
		jlPass.setBounds(posIniX,posIniY+cons*3,100,20);
		
		//Confirmar
		jlCon.setBounds(posIniX,posIniY+cons*4,200,20);
		
		//DNI
		jlDNI.setBounds(posIniX,posIniY+cons*5,200,20);
		
		//CONFIGURACIÓN DE LOS CAMPOS DE TEXTO
		int boxHeight=20;
		int boxWidth=140;
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
		//Confirmar
		passConfirm.setEchoChar('*');
		passConfirm.setBounds(posIniX,posIniY+4*cons,boxWidth,boxHeight);
		passConfirm.setBackground(camposTextColor);
		passConfirm.setBorder(null);
		passConfirm.setFont(new Font("Tahoma",Font.PLAIN,12));
		passConfirm.setForeground(textColor);		
		//DNI
		textFieldDNI.setBounds(posIniX,posIniY+5*cons,boxWidth,boxHeight);
		textFieldDNI.setBackground(camposTextColor);
		textFieldDNI.setBorder(null);
		textFieldDNI.setFont(new Font("Tahoma",Font.PLAIN,12));
		textFieldDNI.setForeground(textColor);
		//Terminos y condiciones
		terminos.setBounds(posIniX-43,posIniY+6*cons,boxWidth+86,boxHeight);
		terminos.setBackground(camposTextColor);
		
		//CONFIGURACIÓN DE BOTONES
		botonSalir.setBounds(dimX-cons, 10, 30, 30);
		botonSalir.setBorder(null);
		botonSalir.setBackground(null);
		botonSalir.setFont(new Font("Arial", Font.BOLD,20));
		botonSalir.setFocusPainted(false);
		botonSalir.setForeground(textColor);
		
		prevPageButton.setBounds(10, 10, 30, 30);
		prevPageButton.setBorder(null);
		prevPageButton.setBackground(null);
		prevPageButton.setForeground(textColor);
		
		botonAceptar.setBounds(posIniX,posIniY+7*cons, boxWidth, boxHeight+10);
		botonAceptar.setBorder(null);
		botonAceptar.setBackground(new Color(0xAB886D));
		botonAceptar.setFont(new Font("Arial", Font.PLAIN,20));
		botonAceptar.setFocusPainted(false);
		
		
		//AGREGRO AL JFRAME
		ExitButton.asignarComportamiento(this);
		this.add(this.labelTit);
		this.add(this.textFieldName);
		this.add(this.textFieldSur);
		this.add(this.textFieldMail);
		this.add(this.passField);
		this.add(this.passConfirm);
		this.add(this.textFieldDNI);
		this.add(this.jlSur);
		this.add(this.jlName);
		this.add(this.jlMail);
		this.add(this.jlPass);
		this.add(this.jlCon);
		this.add(this.jlDNI);
		this.add(botonSalir);
		this.add(botonAceptar);
		this.add(prevPageButton);
		this.add(terminos);
		this.add(wbp);
		
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

	
	public List<JTextComponent> devolverCampos(){
		List<JTextComponent> listaCampos = new LinkedList<JTextComponent>();
		listaCampos.add(textFieldName);
		listaCampos.add(textFieldSur);
		listaCampos.add(textFieldMail);
		listaCampos.add(textFieldDNI);
		listaCampos.add(passField);
		listaCampos.add(passConfirm);
		
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
		etiquetas.add(jlCon);
		etiquetas.add(jlDNI);
		return etiquetas;
	}
	@Override
	public List<JTextComponent> devolverCamposTexto() {
		List<JTextComponent> campos = new LinkedList<JTextComponent>();
		campos.add(passField);
		campos.add(textFieldMail);
		campos.add(textFieldName);
		campos.add(textFieldSur);
		campos.add(textFieldDNI);
		campos.add(passConfirm);
		return campos;
	}
	@Override
	public List<JButton> devolverBotones() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void callExit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}
	@Override
	public JButton getExit() {
		
		return botonSalir;
	}
	public BasicArrowButton devolverAtras() {
		return this.prevPageButton;
	}

	
	public boolean confirmarContraseña() {
		if (passField.getText().equals(passConfirm.getText()))
			return true;
		else
			return false;
	}
	public void errorMail() {
		this.textFieldMail.setBorder(new LineBorder(Color.RED,2));
		
	}

	public void errorContraseña() {
		this.passConfirm.setBorder(new LineBorder(Color.RED,2));
	
	}
	@Override
	public void repaint() {
		passConfirm.setBorder(null);
		textFieldMail.setBorder(null);
	}
	public String getMail() {
		return this.getTextFieldMail().getText();
	}
	public Usuario crearUsuario() {
		Usuario aux = new Usuario(textFieldDNI.getText(), textFieldName.getText(), textFieldSur.getText(), "Argentina" , textFieldMail.getText(), passField.getText());
		return aux;
	}
	public boolean termsAcepted() {
		if (this.terminos.isSelected())
			return true;
		else
			return false;
	}
	
	
}