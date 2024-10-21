package entregable1;

import java.util.LinkedList;

/**
 * Es un usuario, contiene toda la información del usuario, incluyendo sus datos personales, de seguridad y billetera.
 */
public class Usuario {
	private String nombre;
	private String apellido;
	private String pais;
	private Fecha fecha;
	private Billetera billetera;
	private boolean habilitado;
	private Seguridad seguridad;
	private AltaBaja historialBajas;
	private String DNI;
	private String email;
	public Usuario()
	{
		
	}
	public Usuario(String DNI,String nombre, String apellido, String pais, String email) {
		super();
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.pais = pais;
		this.habilitado = true;
		this.email = email;
		this.billetera = new Billetera();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getFecha() {
		return fecha.toString();
	}
	public String getSeguridad() {
		return seguridad.toString();
	}
	public void setSeguridad(String contraseña,String dirMail) {
		//this.seguridad = new Seguridad(contraseña,dirMail/*clavePrivada*/);
	}
	public String getHistorialBajas() {
		return historialBajas.toString();
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	public Billetera getBilletera() {
		return billetera;
	}
	public void setBilletera(Billetera billetera) {
		this.billetera = billetera;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
