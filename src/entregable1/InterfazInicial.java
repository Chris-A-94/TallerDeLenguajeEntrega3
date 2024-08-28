package entregable1;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class InterfazInicial implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private String text;
	public InterfazInicial() {
		//Inicialización de variables de tipo GUI.
		frame = new JFrame();
		panel = new JPanel();
		//Configuración del panel.
		panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
		panel.setLayout(null);
		//Configuración del frame.
		frame.add(panel);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("CriptoApp");
		frame.setVisible(true);
		
	}
	public boolean registroIngreso()
	{
		boolean opcionesUsuario;
		
		JButton registro = new JButton("Registrarse");
		registro.setBounds(125, 125, 150, 20);
		JButton ingreso = new JButton("Ingresar");
		ingreso.setBounds(125, 150, 150, 20);
		//Configuración de botón.	
		registro.addActionListener(this);
		ingreso.addActionListener(this);
		//Agrego los botones al panel.
		panel.add(registro);
		panel.add(ingreso);
		
		return true;
		
	}
	
	private void generarInterfazRegistro()
	{
		JLabel texto = new JLabel("REGISTRARSE");
		texto.setBounds(125, 40, 100, 20);
		JTextField nombre = new JTextField("Nombre");
		nombre.setBounds(125, 80, 100, 20);
		JTextField apellido = new JTextField("Apellido");
		apellido.setBounds(125, 120, 100, 20);
		JTextField dirEmail = new JTextField("Email");
		dirEmail.setBounds(125, 160, 100, 20);
		JPasswordField password = new JPasswordField("Contraseña");
		password.setBounds(125, 200, 100, 20);
		panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
		panel.setLayout(null);
		panel.add(texto);
		panel.add(nombre);
		panel.add(apellido);
		panel.add(dirEmail);
		panel.add(password);

	}
	private void generarInterfazIngreso()
	{
		JLabel texto = new JLabel("INGRESAR");
		texto.setBounds(125, 40, 100, 20);
		JTextField dirEmail = new JTextField("Email");
		dirEmail.setBounds(125, 80, 100, 20);
		JPasswordField password = new JPasswordField("Contraseña");
		password.setBounds(125, 120, 100, 20);
		panel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
		panel.setLayout(null);
		panel.add(texto);
		panel.add(dirEmail);
		panel.add(password);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		text = e.getActionCommand();
		panel.removeAll();
		panel.updateUI();
		if (text == "Registrarse")
		{
			System.out.println("Se registró 'Registrarse'");  
			this.generarInterfazRegistro();
		}
		else
		{
			System.out.println("Se registró 'Ingresar'");  
			this.generarInterfazIngreso();
		}
	
		
		
		//frame.dispose();
	}
}
