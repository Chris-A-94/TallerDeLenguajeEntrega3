package vistas;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.*;

public class logInPage extends JSplitPane{

	private JPanel leftPanel;
	private JPanel rightPanel;
	
	public logInPage()
	{
		super(JSplitPane.HORIZONTAL_SPLIT);
		leftPanel = new panelIzquierdo();
		rightPanel = new JPanel();
		rightPanel.add(new JButton("Login"));
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setDividerLocation(0.5); 
        this.setResizeWeight(0.5);
        this.setEnabled(false);
        
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
