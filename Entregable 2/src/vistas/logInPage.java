package vistas;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.text.JTextComponent;
import decoradores.ControladorTextField;
import decoradores.ExitButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.*;

public class logInPage extends JSplitPane implements Vista{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//constantes
	Color camposTextColor = new Color(0xD6C0B3), textColor = new Color(0xAB886D);
	//instancia
	private panelIzquierdo leftPanel;
	private panelDerecho rightPanel;
	BasicSplitPaneDivider divider;
	private JButton botonSalir;
	private JFrame ventana;
	private JPanel mainPanel;	
	
	public logInPage()
	{
		super(JSplitPane.HORIZONTAL_SPLIT);
		BasicSplitPaneUI ui = (BasicSplitPaneUI)this.getUI();
		divider = ui.getDivider();
		leftPanel = new panelIzquierdo(); //cada lado es su clase privada propia
		rightPanel = new panelDerecho();	
		this.setBorder(null);
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);	
		this.setExit();
		this.setResizeWeight(0.5);
		this.setEnabled(false);       
		this.setDividerSize(3);
		this.inicializarFrame();
		ExitButton.asignarComportamiento(this);
		this.setDividerLocation(0.6);
		//ControladorTextField conTf = new ControladorTextField(this);	//setea el funcionamiento visual de los botones solo con existir 
	}
	
	
	
	private void inicializarFrame()
	{
		
		mainPanel = new JPanel(new BorderLayout());		
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		titleBar.add(this.botonSalir);
		titleBar.setBorder(null);
		titleBar.setBackground(new Color(0xE4E0E1));
		mainPanel.add(titleBar, BorderLayout.NORTH);
		mainPanel.add(this, BorderLayout.CENTER);
		mainPanel.setBackground(new Color(0xE4E0E1));
		
		this.ventana = new JFrame("Login Page");
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setSize(1000, 400);      
		this.ventana.add(mainPanel);    
		this.ventana.setLocationRelativeTo(null); 
		this.ventana.setUndecorated(true);	        
		this.ventana.setResizable(false);
		this.ventana.setBackground(new Color(0, 0, 0, 0));
		this.ventana.setShape(new RoundRectangle2D.Double(0, 0, 1000, 400, 25, 25));
		this.ventana.getContentPane().setBackground(new Color(0xE4E0E1));
			
		this.ventana.setVisible(true);
		
	}	
	
	private void setExit()
	{
		//seteo el boton
		this.botonSalir  = new JButton("X");		
		this.botonSalir.setBounds(rightPanel.getWidth()-40, 10, 30, 30);
		this.botonSalir.setBorder(null);
		this.botonSalir.setBackground(null);
		this.botonSalir.setFont(new Font("Arial", Font.BOLD,20));
		this.botonSalir.setFocusPainted(false);
		this.botonSalir.setForeground(new Color(0xAB886D));
		//ROTURA DE MVC, ARREGLAR LUEGO
		this.botonSalir.addActionListener(e -> ventana.dispose());	
		
		
		this.rightPanel.add(botonSalir);
	}
		
	//Clase panel derecho
	private class panelDerecho extends JPanel implements Vista {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton login;
		private JButton signin;
		private JButton forgotPass;
		private JLabel nEmail,nPasword;
		private JTextField Email;
		private JPasswordField Password;
		
		private Image emblema;
		private JLabel welcomeMessage;	
		private Font agusFont;
		private Color botColor;
		
		public panelDerecho()
		{
			
			//a llenar
			this.setLayout(new GridBagLayout()); 
			this.agusFont = new Font("Tahoma",Font.PLAIN,14);
			this.setPreferredSize(new Dimension(400, 400));
			this.setBackground(new Color(0xE4E0E1));
			botColor = new Color(0xAB886D);
			setMessage();
			setForms();
			setButtons();
			setEmblema();
			ControladorTextField conTf = new ControladorTextField(this);	
			
		}
		
		private void setEmblema()
		{
			URL imgURL = this.getClass().getClassLoader().getResource("Imagenes/tutucaBowl.png");	
			
			if(imgURL == null)
			{
				System.err.println("Simbolo no encontrado.");
				return;
			}
			else
			{
				try
				{
					emblema = ImageIO.read(imgURL);
				}
				catch (IOException ex) {
					ex.printStackTrace();
					}
			}
		
			if(this.emblema != null)
			{
				//podria cargar emblema directamente con repaint(), pero quiero posicionarlo con gridbagconstraints
				ImageIcon icono = new ImageIcon(emblema.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
				JLabel	imagenMem = new JLabel(icono);
				GridBagConstraints imagePos = new GridBagConstraints();
	            imagePos.gridx = 0;
	            imagePos.gridy = 1;	       
	            imagePos.gridwidth = 2;
	            imagePos.insets = new Insets(10, 10, 10, 10);
	            this.add(imagenMem,imagePos);
			}		
						    
		}

		
		private void setMessage()
		{
			this.welcomeMessage = new JLabel("Bienvenido!");
			this.welcomeMessage.setSize(150, 50);			
			this.welcomeMessage.setFont(new Font("Tahoma", Font.BOLD,30));
			this.welcomeMessage.setForeground(new Color(0x291e17));
			
			GridBagConstraints messagePos = new GridBagConstraints();
			messagePos.gridy = 0;
			messagePos.gridwidth = 2;
			
			this.add(welcomeMessage,messagePos);
			
			
		}
		
		private void setForms()
		{
			//inicializacion
			this.nEmail = new JLabel("Email: ");
			this.nPasword = new JLabel("Password: ");
			this.Email = new JTextField("example@mail.com");
			this.Password = new JPasswordField("Contraseña");
			
			//personalizacion
			this.Email.setFont(this.agusFont);
			this.Email.setBorder(null);
			this.Email.setFont(this.agusFont);
			this.Email.setForeground(new Color(0xAB886D));
			this.Email.setBackground(new Color(0xD6C0B3));
			this.Email.setPreferredSize(new Dimension(150, 20));
			
			this.Password.setFont(this.agusFont);
			this.Password.setBorder(null);
			this.Password.setFont(new Font("Tahoma",Font.PLAIN,12));
			this.Password.setForeground(new Color(0xAB886D));
			this.Password.setBackground(new Color(0xD6C0B3));
			this.Password.setPreferredSize(new Dimension(150, 20));
			this.Password.setEchoChar('*');
			
			this.nEmail.setFont(new Font("Tahoma",Font.BOLD,12));
			this.nPasword.setFont(new Font("Tahoma",Font.BOLD,12));
			
			
			//posicionamiento
			GridBagConstraints posEmail = new GridBagConstraints();
			GridBagConstraints posPassword = new GridBagConstraints();
			GridBagConstraints posnEmail = new GridBagConstraints();
			GridBagConstraints posnPassword = new GridBagConstraints();
			
			posnEmail.gridy = 2;
			posnEmail.gridx = 0;
			posnEmail.insets = new Insets(10,10,0,0);
			posEmail.gridy = 2;
			posEmail.gridx = 1;
			posEmail.insets = new Insets(10,10,0,0);
			
			posnPassword.gridy = 3;
			posnPassword.gridx = 0;
			posnPassword.insets = new Insets(10,10,0,0);
			posPassword.gridy = 3;
			posPassword.gridx = 1;
			posPassword.insets = new Insets(10,10,0,0);
			//agregado al JPanel
			this.add(nEmail,posnEmail);
			this.add(Email,posEmail);
			this.add(nPasword,posnPassword);			
			this.add(Password,posPassword);
		}
		
		private void setButtons()
		{
			//nota: si implementas una subclase que simule un boton los podes personalizar mucho mas
			login = new JButton("Log in");
			signin = new JButton("Sign in");
			forgotPass = new JButton("Olvido su contraseña?");
			
			//personalizacion de los botones
			this.login.setPreferredSize(new Dimension(75, 25));
			this.login.setBorder(null);
			this.login.setBackground(botColor);
			this.login.setFont(new Font("Tahoma",Font.BOLD,14));
			this.login.setFocusPainted(false);
			
			this.signin.setPreferredSize(new Dimension(75, 25));
			this.signin.setBorder(null);
			
			this.signin.setFont(new Font("Tahoma",Font.BOLD,14));
			this.signin.setFocusPainted(false);
			this.signin.setBackground(botColor);
			
			this.forgotPass.setPreferredSize(new Dimension(200, 25));
			this.forgotPass.setBorder(null);
			this.forgotPass.setBackground(botColor);
			this.forgotPass.setFont(new Font("Tahoma",Font.BOLD,14));
			this.forgotPass.setFocusPainted(false);
			
			GridBagConstraints posLog = new GridBagConstraints();
			posLog.gridy = 5;
			posLog.gridx = 0;
			posLog.anchor = GridBagConstraints.SOUTH;
			posLog.insets = new Insets(10, 35, 0, 0); //top left bottom right
			
			GridBagConstraints posSign = new GridBagConstraints();
			posSign.gridy = 5;
			posSign.gridx = 1;
			posSign.anchor = GridBagConstraints.SOUTH;
			posSign.insets = new Insets(10, 0, 0, 0);
			
			GridBagConstraints posForgot = new GridBagConstraints();
			posForgot.gridy = 6;
			posForgot.gridx = 0;
			posForgot.gridwidth = 2;
			posForgot.anchor = GridBagConstraints.SOUTH;
			posForgot.insets = new Insets(10, 5, 10, 5);
			
			//agrego a la UI
			this.add(login, posLog);
			this.add(signin, posSign);
			this.add(forgotPass, posForgot);			
				
		}

		public List<JLabel> devolverEtiquetas() {
			// TODO Auto-generated method stub
			return null;
		}

		public List<JTextComponent> devolverCamposTexto() {
			List<JTextComponent> campos = new LinkedList<JTextComponent>();
			campos.add(Email);
			campos.add(Password);
			return campos;
		}

		public List<JButton> devolverBotones() {
			List<JButton> misBotones = new LinkedList<JButton>();
			misBotones.add(this.signin);
			misBotones.add(this.login);
			misBotones.add(this.forgotPass);
			return misBotones;
		}

		public void callExit() {
			// TODO Auto-generated method stub

		}

		

		public JButton getExit() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public JTextField getEmailField() {
			return this.Email;
		}
		public JPasswordField getPasswordField() {
			return this.Password;
		}
		
		
		/*de stackOverflow
		
		private static class RoundedBorder implements Border {

		    private int radius;


		    RoundedBorder(int radius) {
		        this.radius = radius;
		    }


		    public Insets getBorderInsets(Component c) {
		        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		    }


		    public boolean isBorderOpaque() {
		        return true;
		    }


		    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		    }
		}*/
	}
	
	//Clase panel izquierdo
	private class panelIzquierdo extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String imageName = "Imagenes/fondo1.png";
		private Image imagen;
		
		public panelIzquierdo()
		{
			super();
			this.setBackground(new Color(0xE4E0E1));
			cargarImagen();
			this.setSize(getPreferredSize());
		}
		
		private void cargarImagen()
		{
			
			URL imgURL = this.getClass().getClassLoader().getResource(imageName);			
			if(imgURL == null)
			{
				System.err.println("No se encuentra la imagen.");
			}
			else
			{
				try
				{
					imagen = ImageIO.read(imgURL);
				}
				catch (IOException ex) {
					ex.printStackTrace();
					}
			}
			
			repaint();		
		}
		@Override
        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		    if (imagen != null) {
		        // Obtiene las dimensiones del panel
		        int panelWidth = getWidth();
		        int panelHeight = getHeight();

		        // Escala la imagen al tamaño del panel
		        Image scaledImage = imagen.getScaledInstance(1275 / 2, 720 / 2, Image.SCALE_SMOOTH);

		        // Dibuja la imagen escalada
		        g.drawImage(scaledImage, 0, 0, this);

		        // Dibuja el texto
		        
//		        String text = "Billetera Tutuca";		      
//		        g.setFont(new Font("Tahoma", Font.BOLD,30));
//		        g.setColor(Color.black);
//		        Graphics2D graphics2D = (Graphics2D) g;		      
//		        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//		                RenderingHints.VALUE_ANTIALIAS_ON); 		       
//		        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//		                RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
//		        graphics2D.drawString(text, 120, 30); // Dibuja el texto centrado
		        
//		        String text1 = "Compra y venta de cripto segura y confiable";		      
//		        g.setFont(new Font("Tahoma", Font.BOLD,13));		 
//		        g.setColor(Color.BLACK);
//		        graphics2D = (Graphics2D) g;		      
//		        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//		                RenderingHints.VALUE_ANTIALIAS_ON); 		       
//		        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//		                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		        graphics2D.drawString(text1, 80, 350); // Dibuja el texto centrado
		    } else {
		        System.err.println("La imagen no se ha cargado."); // Mensaje de depuración
		    }
		}
	}
	
	public List<JButton> devolverBotones()
	{
		List<JButton> misBotones = ((panelDerecho) this.rightPanel).devolverBotones();
		misBotones.add(this.botonSalir);
		return misBotones;
	}
	
	public List<JTextComponent> devolverData()
	{
		List<JTextComponent> data = ((panelDerecho) this.rightPanel).devolverCamposTexto();
		return data;
	}
	public void callExit()
	{
		this.botonSalir.doClick();
	}



	@Override
	public List<JLabel> devolverEtiquetas() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<JTextComponent> devolverCamposTexto() {
		return null;
	}
	
	public JTextField getEmailField() {
		return this.rightPanel.getEmailField();
	}
	public JTextField getPasswordField() {
		return this.rightPanel.getPasswordField();
	}



	@Override
	public JButton getExit() {
		return this.botonSalir;
	}
	public void errorUsuario() {
		this.rightPanel.Email.setBorder(new LineBorder(Color.RED,2));
	}
	public void errorPassword() {
		this.rightPanel.Password.setBorder(new LineBorder(Color.RED,2));
	}
}
