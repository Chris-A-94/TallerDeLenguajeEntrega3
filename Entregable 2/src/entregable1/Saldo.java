package entregable1;

/**
 * Informa el balance de una cryptomoneda particular. Traduce el balance a USD.
 */
public class Saldo implements Comparable<Saldo>{
	private int id;
	private String sigla = "?";
	private Double cantMonedas = 0.0;
	private String tipo = "?";
	
	public Saldo() {
		super();
	}
	public Saldo(String sigla2, Double cantidad) {
		// TODO Auto-generated constructor stub
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}

}
