package modelos;

public class SaldoExcepcion extends Exception{
	private String debugMsg;
	
	public SaldoExcepcion(String debugMsg)
	{
		this.debugMsg = debugMsg;
	}
	
	public String getDebugMsg()
	{
		return this.debugMsg;
	}

}
