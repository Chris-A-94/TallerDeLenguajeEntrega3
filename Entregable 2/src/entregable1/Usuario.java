package entregable1;
import java.util.Date;

/**
 * Es un usuario, contiene toda la información del usuario, incluyendo sus datos personales, de seguridad y billetera.
 */
public class Usuario implements Comparable<Usuario> {
	private String nombre;
	private String apellido;
	private String pais;
	private Date fechaNac;
	private Billetera billetera;
	private boolean habilitado;
	private Seguridad seguridad;
	private AltaBaja historialBajas;
	private String DNI;
	private String email;
	private String contraseña;
	public Usuario(String DNI,String nombre, String apellido, String pais, String email, String contraseña) {
		super();
		
		this.nombre = nombre;
		this.DNI = DNI;
		this.apellido = apellido;
		this.pais = pais;
		this.habilitado = true;
		this.email = email;
		this.billetera = new Billetera(DNI);
		this.contraseña = contraseña;
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
	public String getfechaNac() {
		return fechaNac.toString();
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
	public void setfechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
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
	public void setDNI(String DNI) {
		this.DNI = DNI;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int compareTo(Usuario u) {
		return this.DNI.compareTo(u.getDNI());
	}
	public String toString() {
		String aux = (" DNI: "+ this.DNI +"\n Nombre completo: "+this.nombre+" "+this.apellido+"\n eMail: "+this.email+"\n Pais/Residencia: "+this.pais);
		
		
		
		return aux;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
}
