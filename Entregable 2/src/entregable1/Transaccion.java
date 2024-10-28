package entregable1;

/**
 *  Esta clase se instancia cuando el usuario realiza una operación de compra, venta o "swap". contiene los datos de la transacción.
 */
public abstract class Transaccion {
	private Fecha fecha;
	private TipoTransaccion tipo = TipoTransaccion.UNDEFINED;
	private Double monto;
	private String user_id;
	private String sigla;
	
	public Transaccion(Double monto, String dia, String mes, String year, String user_id, String sigla) {
		this.fecha = new Fecha(dia, mes, year);
		this.monto = monto;
		this.user_id = user_id;
		this.sigla = sigla;
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
	public String getSigla() {
		return this.sigla;
	}
	
	public void setFecha(String dia, String mes, String year) {
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
		return new String("(" + this.fecha.toString() + ") " + this.getSigla() + " " + this.getTipo().toString() + " : " + this.getMonto() + " USD");
	}
}
