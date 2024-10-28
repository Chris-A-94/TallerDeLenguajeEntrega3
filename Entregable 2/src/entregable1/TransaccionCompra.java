package entregable1;
import java.util.Date;
public class TransaccionCompra extends Transaccion {
	
	public TransaccionCompra(Double monto, String dia, String mes, String year, String user_id, String sigla) {
		super(monto, dia, mes, year, user_id, sigla);
		this.setTipo(TipoTransaccion.COMPRA);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
