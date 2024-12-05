package controladores;

import java.util.List;

import javax.swing.JPanel;

import entregable1.Transaccion;
import vistas.TransaccionPanel;

public class TransaccionControlador {
	//vista
	private TransaccionPanel tp;
	private List<Transaccion> listTrans;
	public TransaccionControlador(List<Transaccion> listTrans) {
		tp = new TransaccionPanel();
		this.listTrans = listTrans;
		for (Transaccion t : listTrans) {
			tp.agregarTransaccion(t.toString());
		}
	}
	public JPanel getPanel() {
		return tp;
	}
}
