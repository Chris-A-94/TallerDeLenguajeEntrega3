package controladores;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entregable1.Saldo;
import entregable1.Sistema;
import entregable1.Usuario;
import vistas.MisActivosVista;

public class MisActivosControlador {
	MisActivosVista misActivosVista;
	
	Usuario usuario;
	public MisActivosControlador(int width, Sistema sistema, Usuario usuario) {
		this.usuario = usuario;
		if (sistema == null || usuario == null) {
			System.out.println("MisActivosControlador::Sistema o Usuario es igual a null");
			return;
		}
		misActivosVista = new MisActivosVista(width, sistema, usuario);
		misActivosVista.devolverExportar().addMouseListener(new ExportListener());
		
	}
	public MisActivosVista getMisActivosVista() {
		return misActivosVista;	
	}
	private class ExportListener extends MouseAdapter {
		public ExportListener() {	
		
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			FileDialog dialog = new FileDialog((Frame) null, "Selecciona un directorio", FileDialog.LOAD);
	        dialog.setDirectory("C:\\");
	        dialog.setVisible(true);
	        String directorio = dialog.getDirectory();
	        if (directorio != null) {
	            System.out.println("Directorio seleccionado: " + directorio);
	            crearCSV(directorio+"\\"+dialog.getFile()+".csv");
	        } else {
	            System.out.println("Selección cancelada.");
	        }
		}
		public boolean crearCSV(String directorio){
			BufferedWriter out = null;
			try {
				out = new BufferedWriter (new FileWriter(directorio));
				for (Saldo s:usuario.getBilletera().getArregloSaldo()) {
					out.write(s.getSigla());
					out.append(';');
					out.write(String.valueOf(s.getCantMonedas()));
					out.newLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}
	}
}
