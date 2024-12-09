package modelos;

public enum TipoMoneda {
	FIAT ("FIAT"),
	CRIPTOMONEDA ("CRIPTOMONEDA"),
	UNDEFINED ("UNDEFINED");
	
	private final String name;
	
	private TipoMoneda(String s) {
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
	public static TipoMoneda fromString(String text) {
		for (TipoMoneda tipoMoneda : TipoMoneda.values()) {
			if (text.equalsIgnoreCase(tipoMoneda.toString())) {
				return tipoMoneda;
			}
		}
		
		return null;
	}
}
