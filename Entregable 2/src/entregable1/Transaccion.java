package entregable1;

/**
 *  Esta clase se instancia cuando el usuario realiza una operación de compra, venta o "swap". contiene los datos de la transacción.
 */
public abstract class Transaccion {
	private Fecha fecha;
	private TipoTransaccion tipo = TipoTransaccion.UNDEFINED;
	private Double monto;
	private String user_id;
	
	public Transaccion(Double monto, int dia, int mes, int year, String user_id) {
		this.fecha = new Fecha(dia, mes, year);
		this.monto = monto;
		this.user_id = user_id;
	}
	
	public String getFecha() {
		return fecha.toString();
	}
	public TipoTransaccion getTipo() {
		return tipo;
	}
	public Double getMonto() {
		return monto;
	}
	public String getUserID() {
		return this.user_id;
	}
	
	public void setFecha(int dia, int mes, int year) {
		this.fecha = new Fecha(dia,mes,year);
	}
	public void setTipo(TipoTransaccion tipo) {
		this.tipo = tipo;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	@Override
	public String toString()
	{
		return new String("(" + this.fecha.toString() + ") " + this.getTipo().toString() + " : " + this.getMonto() + " USD");
	}
}
