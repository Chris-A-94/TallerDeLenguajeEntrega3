package entregable1;

/**
 * Informa el balance de una cryptomoneda particular. Traduce el balance a USD.
 */
public class Saldo implements Comparable<Saldo>{
	private int id;
	private String user_id;
	private String tipo = "?";
	private String sigla = "?";
	private Double cantMonedas = 0.0;

	
	public Saldo(String sigla, Double cantMonedas)
	{
		super();
		this.sigla = sigla;
		this.cantMonedas = cantMonedas;
	}

	public Saldo()
	{
		
	}

	public Saldo(int id, String user_id, String tipo, String sigla, Double cantMonedas) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.tipo = tipo;
		this.sigla = sigla;
		this.cantMonedas = cantMonedas;

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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}



