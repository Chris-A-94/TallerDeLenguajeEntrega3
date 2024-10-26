package entregable1;

/**
 *  Esta clase se instancia cuando el usuario realiza una operación de compra, venta o "swap". contiene los datos de la transacción.
 */
abstract class Transaccion {
	private Fecha fecha;
	private TipoTransaccion tipo = TipoTransaccion.UNDEFINED;
	private Double monto;
	
	public Transaccion(Double monto, int dia, int mes, int year) {
		this.fecha = new Fecha(dia, mes, year);
		this.monto = monto;
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
		return new String("El dia [" + this.fecha.toString() + "] se realizó una transferencia de tipo " + this.getTipo().toString() + ", donde: ");
	}
}
