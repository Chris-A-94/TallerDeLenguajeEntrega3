package entregable1;

/**
 *  Esta clase se instancia cuando el usuario realiza una operación de compra, venta o "swap". contiene los datos de la transacción.
 */
public abstract class Transaccion {
	private Fecha fecha;
	private TipoTransaccion tipo = TipoTransaccion.UNDEFINED;
	private String user_id;
	
	public Transaccion(String dia, String mes, String year, String user_id) {
		this.fecha = new Fecha(dia, mes, year);
		this.user_id = user_id;
	}
	
	public String getFecha() {
		return fecha.toString();
	}
	public TipoTransaccion getTipo() {
		return tipo;
	}
	public String getUserID() {
		return this.user_id;
	}
	
	public void setFecha(String dia, String mes, String year) {
		this.fecha = new Fecha(dia,mes,year);
	}
	public void setTipo(TipoTransaccion tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString()
	{
		return new String("(" + this.fecha.toString() + ") ");
	}
}
