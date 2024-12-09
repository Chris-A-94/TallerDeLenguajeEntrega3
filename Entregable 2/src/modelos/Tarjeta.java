package modelos;

/**
 * Contiene los datos seriales de la tarjeta. Define comportamiento de subclases Debito y Credito.
 */
public abstract class Tarjeta {
	
	private String sigla;
	private boolean terminos;
	private String serie;
	private int codSeg;
	private Fecha vencimiento;
	
	public Tarjeta(String sigla, boolean terminos, String serie, int codSeg, Fecha vencimiento)
	{
		this.setSigla(sigla);
		this.setTerminos(terminos);
		this.setSerie(serie);
		this.setCodSeg(codSeg);
		this.setVencimiento(vencimiento);
	}
	//El metodo cobrar es aplicado en la Tarjeta de Debito y en la futura a implementar de Credito
	public abstract boolean cobrar(Double costo);
	
	
	//getters y setters
	public String getSigla()
	{
		return this.sigla;
	}
	public void setSigla(String sigla)
	{
		this.sigla = sigla;		
	}
	public boolean getTerminos()
	{
		return this.terminos;
	}
	public void setTerminos(boolean terminos)
	{
		this.terminos = terminos;
	}
	public String getSerie()
	{
		return this.serie;
	}
	public void setSerie(String serie)
	{
		this.serie = serie;
	}
	public int getCodSeg()
	{
		return this.codSeg;
	}
	public void setCodSeg(int codSeg)
	{
		this.codSeg = codSeg;
	}
	public Fecha getVencimiento()
	{
		return this.vencimiento;
	}
	public void setVencimiento(Fecha vencimiento)
	{
		this.vencimiento = vencimiento;
	}
	
}
