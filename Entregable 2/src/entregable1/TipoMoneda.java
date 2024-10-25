package entregable1;

public enum TipoMoneda {
	FIAT ("FIAT"),
	CRIPTOMONEDA ("CRIPTOMONEDA"),
	UNDEFINED ("UNDEFINED");
	
	private final String name;
	
	private TipoMoneda(String s) {
		name = s;
	}
	
	public String toString() {
		return this.name;
	}
}
