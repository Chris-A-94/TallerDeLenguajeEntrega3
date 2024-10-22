package entregable1;

/**
 * Informa el balance de una cryptomoneda particular. Traduce el balance a USD.
 */
public class Saldo implements Comparable<Saldo>{
	private String sigla = "?";
	private Double cantMonedas = 0.0;
	public Saldo(String sigla, Double cantMonedas) {
		super();
		this.sigla = sigla;
		this.cantMonedas = cantMonedas;
	}
	public Saldo() {
		super();
	}
	public String getSigla() {
		return sigla;
	}
	public void setNombre(String sigla) {
		this.sigla = sigla;
	}
	public Double getCantMonedas() {
		return cantMonedas;
	}
	public void setCantMonedas(Double cantMonedas) {
		this.cantMonedas = cantMonedas;
	}
	public String toString(){
		String aux = (sigla +" "+cantMonedas);
		return aux;
	}
	public Double ConvertirADivisa(Double cantMonedas2) {
		// Aca se convierte a pesos.
		
		return 0.0;
	}
	
	@Override
	public int compareTo(Saldo s) {
		return Double.compare(s.getCantMonedas(), this.getCantMonedas());
	}
}
