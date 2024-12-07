package modelos;

public class CompraExcepcion extends Exception{

	private String mensajeRecibido;
	private boolean hayStock;
	
	public CompraExcepcion(String mensajeRecibido, boolean hayStock)
	{
		super(mensajeRecibido);
		this.mensajeRecibido = mensajeRecibido;
		this.hayStock = hayStock;
	}

	public String getMensajeRecibido() {
		return mensajeRecibido;
	}

	public boolean hayStock() {
		return hayStock;
	}
	
	
}
