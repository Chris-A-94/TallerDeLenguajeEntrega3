package entregable1;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Cada instancia de Coin representa una única moneda. Tiene el precio, nombre y stock de la moneda.
 */
public class Coin implements Comparable<Coin>{
	private String nombre;
	private String tipo;
	private String sigla;
	private Double precio;
	private Double stock = 0.0;
	public Coin(String nombre, String sigla, String tipo,Double precio) {

		this.nombre = nombre;
		this.sigla = sigla;
		this.precio = precio;
		this.tipo = tipo;
		this.generarStock();
	}
	public Coin(String nombre, String sigla, String tipo,Double precio,Double stock) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.precio = precio;
		this.tipo = tipo;
		this.stock = stock;
	}

	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public boolean generarStock()
	{
		if (this.stock != 0)
		{
			Scanner in = new Scanner(System.in);
			String resp;
			System.out.printf("El stock de ["+ this.nombre+"] tiene ya un valor asignado "+this.stock+"\n¿Desea cambiarlo? (y) SI | (n) NO\n");
			resp = in.next();
			if (!resp.equals("y"))
				return false;
			
		}
		Random stock = new Random();
		Double minimo = 1000.0;
		Double maximo = 9000.0;
		//se cambia el stock que ya estaba.
		this.stock = minimo + (maximo - minimo) * stock.nextDouble();
		return true;
	}
	public String toString() {
		return ("Nombre: "+this.nombre+" \nSigla: "+ this.sigla+ "\nTipo: "+this.tipo +"\nPrecio (USD): "+this.precio+ "\nStock: "+this.stock);
	}
	
	@Override
	public int compareTo(Coin c) { //COMPARA MONEDAS POR PRECIO...
		return (int) (c.getPrecio()-this.getPrecio());
	}
}
