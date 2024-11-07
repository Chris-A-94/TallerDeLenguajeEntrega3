package vistas;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.text.JTextComponent;

import controladores.ControladorTextField;
import controladores.RegistroControlador;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.*;

public class logInPage extends JSplitPane {

	private JPanel leftPanel;
	private JPanel rightPanel;
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
		
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);	
		this.setExit();
        this.setResizeWeight(0.5);
        this.setEnabled(true);            
        this.inicializarFrame();
        this.setDividerLocation(0.6);
        ControladorTextField conTf = new ControladorTextField((vista) rightPanel);
	
	}
	
	private void inicializarFrame()
	{
		
		mainPanel = new JPanel(new BorderLayout());		
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		titleBar.add(this.botonSalir);
		titleBar.setBorder(null);
		mainPanel.add(titleBar, BorderLayout.NORTH);
		mainPanel.add(this, BorderLayout.CENTER);
		
		this.ventana = new JFrame("Login Page");
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ventana.setSize(800, 400);      
		this.ventana.add(mainPanel);    
		this.ventana.setLocationRelativeTo(null); 
		this.ventana.setUndecorated(true);	        
		this.ventana.setResizable(false);
		this.ventana.setBackground(new Color(0, 0, 0, 0));
		ventana.setShape(new RoundRectangle2D.Double(0, 0, 800, 400, 25, 25));
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
	private class panelDerecho extends JPanel implements vista{
		private JButton login;
		private JButton signin;
		private JButton forgotPass;
		private JLabel nEmail,nPasword;
		private JTextField Email, Password;
		private Image emblema;
		private JLabel welcomeMessage;	
		private Font agusFont;
		
		public panelDerecho()
		{
			//a llenar
			this.setLayout(new GridBagLayout()); 
			this.agusFont = new Font("Tahoma",Font.PLAIN,12);
			this.setPreferredSize(new Dimension(400, 400));
			setMessage();
			setForms();
			setButtons();
			setEmblema();
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
			this.Password = new JTextField("     Contraseña     ");
			
			//personalizacion
			this.Email.setFont(this.agusFont);
			this.Email.setBorder(null);
			this.Email.setFont(new Font("Tahoma",Font.PLAIN,12));
			this.Email.setForeground(new Color(0xAB886D));
			this.Email.setBackground(new Color(0xD6C0B3));
			
			this.Password.setFont(this.agusFont);
			this.Password.setBorder(null);
			this.Password.setFont(new Font("Tahoma",Font.PLAIN,12));
			this.Password.setForeground(new Color(0xAB886D));
			this.Password.setBackground(new Color(0xD6C0B3));
			
			this.nEmail.setFont(this.agusFont);
			this.nPasword.setFont(this.agusFont);
			
			
			//posicionamiento
			GridBagConstraints posEmail = new GridBagConstraints();
			GridBagConstraints posPassword = new GridBagConstraints();
			GridBagConstraints posnEmail = new GridBagConstraints();
			GridBagConstraints posnPassword = new GridBagConstraints();
			
			posnEmail.gridy = 2;
			posnEmail.gridx = 0;
			posEmail.gridy = 2;
			posEmail.gridx = 1;
			
			posnPassword.gridy = 3;
			posnPassword.gridx = 0;
			posPassword.gridy = 3;
			posPassword.gridx = 1;
			
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
			
			GridBagConstraints posLog = new GridBagConstraints();
			posLog.gridy = 5;
			posLog.gridx = 0;
			posLog.anchor = GridBagConstraints.SOUTH;
			posLog.insets = new Insets(10, 5, 10, 5);
			
			GridBagConstraints posSign = new GridBagConstraints();
			posSign.gridy = 5;
			posSign.gridx = 1;
			posSign.anchor = GridBagConstraints.SOUTH;
			posSign.insets = new Insets(10, 5, 10, 5);
			
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

		@Override
		public List<JLabel> devolverEtiquetas() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<JTextComponent> devolverCamposTexto() {
			List<JTextComponent> campos = new LinkedList<JTextComponent>();
			campos.add(Email);
			campos.add(Password);
			return campos;
		}

		@Override
		public List<JButton> devolverBotones() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	//Clase panel izquierdo
	private class panelIzquierdo extends JPanel{
		private String imageName = "Imagenes/warning.png";
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
		        Image scaledImage = imagen.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

		        // Dibuja la imagen escalada
		        g.drawImage(scaledImage, 0, 0, this);

		        // Dibuja el texto
		        FontMetrics metrics = g.getFontMetrics(g.getFont());
		        String text = "Maneje sus finanzas de la mejor manera";
		        int textWidth = metrics.stringWidth(text);
		        int textX = (panelWidth - textWidth) / 2; // Centra el texto horizontalmente
		        g.setColor(Color.red);
		        g.drawString(text, textX, 30); // Dibuja el texto centrado		        
		    } else {
		        System.err.println("La imagen no se ha cargado."); // Mensaje de depuración
		    }
		}
	}
	
}
