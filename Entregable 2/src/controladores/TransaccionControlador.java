package controladores;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import modelos.Billetera;
import modelos.Transaccion;
import vistas.TransaccionPanel;

public class TransaccionControlador {
	//vista
	private TransaccionPanel tp;
	private List<Transaccion> listTrans;
	public TransaccionControlador(Billetera wallet) {
		this.listTrans = wallet.getTransacciones();
		tp = new TransaccionPanel();
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
			tp.eraseLabels();
			
			for(int i = listTrans.size()-1;i>=0;i--) {
				tp.agregarTransaccion(listTrans.get(i).description());
			}
		}
	}
}
