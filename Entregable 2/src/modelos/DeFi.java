package modelos;

/**
 *	Representa una operación defi. Contiene el protocolo, la moneda en la que fue hecha, las fechas de inicio y fin y la ganancia del mismo. Cuando termina el plazo de tiempo se deshabilita.
 */
public class DeFi {
	private String sigla;
	private String protocolo;
	private Double intereses;
	private Double deposito;
	private Double ganancia;
	private Fecha fechaInicio;
	private Fecha fechaFin;
	private boolean habilitado;
	public DeFi(String sigla, String protocolo, Double intereses, Double deposito) {
		super();
		this.sigla = sigla;
		this.protocolo = protocolo;
		this.intereses = intereses;
		this.deposito = deposito;
		this.habilitado = true;
		//la fecha de inicio la obtiene automáticamente.
		//la fecha de fin se obtiene al hacer el DeFi.
		
	}
	public String getSigla()
	{ 
		return sigla;
	}
	
	public boolean getHabilitado()
	{
		return this.habilitado;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public Double getIntereses() {
		return intereses;
	}
	public void setIntereses(Double intereses) {
		this.intereses = intereses;
	}
	public Double getDeposito() {
		return deposito;
	}
	public void setDeposito(Double deposito) {
		this.deposito = deposito;
	}
	public String getFechaInicio() {
		return this.fechaInicio.toString();
	}
	public String getFechaFin()
	{
		return this.getFechaFin().toString();
	}
	public Double retirarDeposito()
	{
		Double deposito = this.deposito;
		this.deposito = 0.0; // Se borra afuera el que tenga deposito 0.
		
		return deposito;
	}
	public String toString()
	{
		String atributosString = (this.getProtocolo()+" ("+this.getSigla()+") "+"\n"+"Depósito:"+this.getDeposito()+"\n"+"Interés:"+this.getIntereses());
		atributosString.concat("\n"+this.fechaInicio.toString() +" - "+ this.getFechaFin().toString());
		return atributosString;
	}
	
}
