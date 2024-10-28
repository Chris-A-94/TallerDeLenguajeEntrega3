package entregable1;

public class TransaccionSwap extends Transaccion {
	
	public TransaccionSwap(Double monto, String dia, String mes, String year, String user_id, String sigla) {
		super(monto, dia, mes, year, user_id, sigla);
		this.setTipo(TipoTransaccion.SWAP);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
