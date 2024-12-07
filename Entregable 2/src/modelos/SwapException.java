package modelos;

public class SwapException extends Exception {
	String msg;
	public SwapException(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return this.msg;
	}
}
