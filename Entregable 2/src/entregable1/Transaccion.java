package entregable1;

/**
 *  Esta clase se instancia cuando el usuario realiza una operación de compra, venta o "swap". contiene los datos de la transacción.
 */
public class Transaccion {
	private Fecha fecha;
	private String tipo;
	private Double monto;
	public Transaccion(String tipo, Double monto,int dia,int mes,int año) {
		super();
		this.fecha = new Fecha(dia,mes,año);
		this.tipo = tipo;
		this.monto = monto;
	}
	public String getFecha() {
		return fecha.toString();
	}
	public void setFecha(int dia,int mes,int año) {
		this.fecha = new Fecha(dia,mes,año);
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String toString()
	{
		String aux = (fecha.toString()+" "+this.monto+" "+this.tipo);
		return aux;
	}
}
