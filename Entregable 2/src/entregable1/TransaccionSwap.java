package entregable1;

public class TransaccionSwap extends Transaccion {
	
	public TransaccionSwap(Double monto, int dia, int mes, int year, String user_id) {
		super(monto, dia, mes, year, user_id);
		this.setTipo(TipoTransaccion.SWAP);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
