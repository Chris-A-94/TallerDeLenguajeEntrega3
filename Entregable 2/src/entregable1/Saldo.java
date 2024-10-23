package entregable1;

/**
 * Informa el balance de una cryptomoneda particular. Traduce el balance a USD.
 */
public class Saldo implements Comparable<Saldo>{
	
	private String sigla = "?";
	private Double cantMonedas = 0.0;
	private Double precio;
	private String nombre;
	
	public Saldo(String sigla, Double cantMonedas)
	{
		super();
		this.sigla = sigla;
		this.cantMonedas = cantMonedas;
	}
	public Saldo(String sigla, Double cantMonedas, Double precio, String nombre)
	{
		super();
		this.sigla = sigla;
		this.cantMonedas = cantMonedas;
		this.precio = precio;
		this.nombre = nombre;
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
	public Double ConvertirADolar(Double fiat) {
		// Aca se convierte a pesos.
		return null;
	}
	public Double dolarADivisa(Double dolar) {
		// Aca se convierte a pesos.
		return null;
	}
	
	
	@Override
	public int compareTo(Saldo s) {
		return Double.compare(s.getCantMonedas(), this.getCantMonedas());
	}
}
