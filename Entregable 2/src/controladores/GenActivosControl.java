package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.UsuarioDAO;
import entregable1.Usuario;
import vistas.GenerarActivos;

public class GenActivosControl {
	
	private JButton generar;	
	private Usuario user;
	GenerarActivos abrirPanel;
	
	public GenActivosControl(JButton generar,Usuario user)
	{
		this.generar = generar;
		this.user = user;
		this.addListener();
	}
	
	private void addListener()
	{
		this.generar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirPanel = new GenerarActivos();	
				setComportamiento();
			}			
		});
		
		
	}
	
	private void setComportamiento()
	{
		JButton cancelar = abrirPanel.getCancelar();
		JButton aceptar = abrirPanel.getAceptar();
		JComboBox<String> opciones = abrirPanel.getOpciones();		
		JTextField valor = abrirPanel.getValor();	
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				abrirPanel.dispose();
			}
		});
		
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				leerData(opciones,valor);
				abrirPanel.dispose();
			}
		});
		
	}
	
	private void leerData(JComboBox<String> opciones,JTextField valor)
	{
		JOptionPane.showMessageDialog(null, "Se cargo "+valor.getText()+" de "+opciones.getSelectedItem().toString(), "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
	}
	
	

}
