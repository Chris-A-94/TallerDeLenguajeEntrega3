package controladores;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import daos.TransaccionDAO;
import entregable1.Transaccion;
import vistas.TransaccionPanel;
import vistas.logInPage;

public class TransaccionControlador {
	//vista
	private TransaccionPanel tp;
	private List<Transaccion> listTrans;
	public TransaccionControlador(List<Transaccion> listTrans) {
		tp = new TransaccionPanel();
		this.listTrans = listTrans;
		for (Transaccion t : listTrans) {
			tp.agregarTransaccion(t.description());
		}
		tp.getRefresh().addMouseListener(new MouseControlRefresh());
	}
	public JPanel getPanel() {
		return tp;
	}
	private class MouseControlRefresh extends MouseAdapter{
		public MouseControlRefresh() {	
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Refresh");
		}
	}
}
