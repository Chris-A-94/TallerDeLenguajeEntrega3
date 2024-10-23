package entregable1;

/**
 *  Extension de clase Tarjeta. Conecta con una cryptomoneda para definir el saldo maximo, y traduce terminos a saldo en fiat.
 */
public class TarjetaDebito extends Tarjeta {
	
	//saldo maximo de la tarjeta obtenido de la clase Saldo
	private Saldo saldo;	
	
	//Double saldo lo obtiene del metodo elegirMonedaParaTarjetaDeDebito() que llama la billetera
	public TarjetaDebito(String sigla, boolean terminos, String serie, int codSeg
			,Fecha vencimiento, Saldo saldo)
	{
		super(sigla,terminos,serie,codSeg,vencimiento);
		this.saldo = saldo;
	}

	public Saldo getSaldo()
	{
		return this.saldo;
	}
	public void setSaldo(Saldo saldo)
	{
		this.saldo = saldo;
	}
	//metodo requerido al extender clase abstracta Tarjeta
	@Override
	public boolean cobrar(Double costo) {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString()
	{
		String aux = "";
	/*	aux = "Sigla: "+super.getSigla()+ " Serie: "+super.getSerie()+" Codigo Seguridad: "+
		super.getCodSeg()+ " Vencimiento: "+super.getVencimiento().toString()+" Saldo: "
				+this.getSaldo().ConvertirADivisa(this.getSaldo().getCantMonedas());
	*/	
		return aux;
		
	}
	
	
	
}