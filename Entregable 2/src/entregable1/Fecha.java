package entregable1;

/**
 * Guarda fecha como DD/MM/AAAA.
 */
public class Fecha {
	private String dia;
	private String mes;
	private String año;
	public Fecha(String dia, String mes, String año) {
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAño() {
		return año;
	}
	public void setAño(String año) {
		this.año = año;
	}
	public String toString()
	{
		String fechaString = (this.dia+"/"+ this.mes+"/"+ this.año); //DD/MM/AAAA
		return fechaString;
	}
}
