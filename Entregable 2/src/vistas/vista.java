package vistas;

import java.util.List;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public interface vista {
	public List<JLabel> devolverEtiquetas();
	public List<JTextComponent> devolverCamposTexto();
	public List<JButton> devolverBotones();
	public JButton getExit();
	public void close();
}
