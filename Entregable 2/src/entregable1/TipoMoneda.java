package entregable1;

public enum TipoMoneda {
	FIAT ("FIAT"),
	CRIPTOMONEDA ("CRIPTOMONEDA"),
	UNDEFINED ("UNDEFINED");
	
	private final String name;
	
	private TipoMoneda(String s) {
		name = s;
	}
	
	public boolean equals(String text) {
		if (name.equalsIgnoreCase(text)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return this.name;
	}
	
	public static TipoMoneda fromString(String text) {
		for (TipoMoneda tipoMoneda : TipoMoneda.values()) {
			if (tipoMoneda.toString().equalsIgnoreCase(text)) {
				return tipoMoneda;
			}
		}
		
		return null;
	}
	
	
}
