package vistas;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import controladores.RegistroControlador;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.*;

public class logInPage extends JSplitPane{

	private JPanel leftPanel;
	private JPanel rightPanel;
	BasicSplitPaneDivider divider;
	
	public logInPage()
	{
		super(JSplitPane.HORIZONTAL_SPLIT);
		BasicSplitPaneUI ui = (BasicSplitPaneUI)this.getUI();
		divider = ui.getDivider();
		leftPanel = new panelIzquierdo(); //cada lado es su clase privada propia
		rightPanel = new panelDerecho();		
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setDividerLocation(0.5); 
        this.setResizeWeight(0.5);
        this.setEnabled(true);            
        
        this.inicializarFrame();
	}
	
	private void inicializarFrame()
	{
		JFrame ventana = new JFrame("Login Page");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(800, 400);      
        
        ventana.add(this);

        ventana.setVisible(true);
	}	
	
	//Clase panel derecho
	private class panelDerecho extends JPanel{
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
			
			
			this.Password.setFont(this.agusFont);
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
	}
	
	//Clase panel izquierdo
	private class panelIzquierdo extends JPanel{
		private String imageName = "Imagenes/logIn.png";
		private Image imagen;
		
		public panelIzquierdo()
		{
			super();
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
