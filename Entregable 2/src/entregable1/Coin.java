package entregable1;
import java.util.Random;

/**
 * Cada instancia de Coin representa una única moneda. Tiene el precio, nombre y stock de la moneda.
 */
public class Coin {
	private String nombre;
	private String sigla;
	private Double precio;
	private Double stock;
	public Coin(String nombre, String sigla, Double precio) {

		this.nombre = nombre;
		this.sigla = sigla;
		this.precio = precio;
		generarStock();
<<<<<<< HEAD
=======
	}
	public Coin(String string, String string2, int indexOf) {
		// TODO Auto-generated constructor stub
>>>>>>> rama-A
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	private void generarStock()
	{
		Random stock = new Random();
		Double minimo = 1000.0;
		Double maximo = 9000.0;
		
		this.stock = minimo + (maximo - minimo) * stock.nextDouble();
	}
	
}
