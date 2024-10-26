package entregable1;

public class TransaccionCompra extends Transaccion {
	
	public TransaccionCompra(Double monto, int dia, int mes, int year, String user_id) {
		super(monto, dia, mes, year, user_id);
		this.setTipo(TipoTransaccion.COMPRA);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
