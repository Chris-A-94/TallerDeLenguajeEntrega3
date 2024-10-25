package entregable1;

public enum TipoTransaccion {
	COMPRA ("COMPRA"),
	SWAP ("SWAP"),
	UNDEFINED ("UNDEFINED");
	
	private final String name;
	
	private TipoTransaccion (String s) {
		name = s;
	}
	
	public boolean equals(String text) {
		if (this.toString().equalsIgnoreCase(text))
			return true;
		
		return false;
	}
	
	public String toString() {
		return this.name;
	}
	
	/*
	 * No requiere una instancia
	 */
	public static TipoTransaccion fromString(String text) {
		for (TipoTransaccion tipoTransaccion : TipoTransaccion.values()) {
			if (text.equalsIgnoreCase(tipoTransaccion.toString()))
				return tipoTransaccion;
		}
		
		return null;
	}
}
