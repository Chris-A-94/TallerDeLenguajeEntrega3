package entregable1;
public class TransaccionCompra extends Transaccion {
	
	String siglaMoneda, siglaFiat;
	Double monto, cant;
	
	
	public TransaccionCompra(String dia, String mes, String year, String user_id, String siglaMoneda, String siglaFiat, Double monto, Double cant) {
		super(dia, mes, year, user_id);
		this.setTipo(TipoTransaccion.COMPRA);
		this.siglaMoneda = siglaMoneda;
		this.siglaFiat = siglaFiat;
		this.monto = monto;
		this.cant = cant;
		// TODO Auto-generated constructor stub
	}
	
	public Double getMonto() {
		return this.monto;
	}
	public Double getCant() {
		return this.cant;
	}
	public String getSiglaMoneda() {
		return this.siglaMoneda;
	}
	public String getSiglaFiat() {
		return siglaFiat;
	}
	
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public void setCant(Double cant) {
		this.cant = cant;
	}
	public void setSiglaMoneda(String siglaMoneda) {
		this.siglaMoneda = siglaMoneda;
	}
	public void setSiglaFiat(String siglaFiat) {
		this.siglaFiat = siglaFiat;
	}
	
	@Override
	public String toString() {
		return this.getCant() + "-" + this.getSiglaMoneda() + "-" + this.getMonto() + "-" + this.getSiglaFiat();
	}
}
