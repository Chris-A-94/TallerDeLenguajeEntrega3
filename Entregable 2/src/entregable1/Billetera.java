package entregable1;

import java.util.LinkedList;
import java.util.List;

import daos.CoinDAO;


/**
 * Es una única billetera, almacena los saldos de todas las cryptomonedas y las claves, ademas de realizar operaciones sobre las mismas.
 */
public class Billetera {
	/*
	 *  ¿Qué sucede si se agrega una nueva moneda a la base de datos?
	 */
	
	private Double balance;
	private String divisa;
	private String CVU;
	private String clavePublica;
	private List<Transaccion> Transacciones;
	private List<Saldo> arregloMontos;
	private List<DeFi> defis;
	private Tarjeta tarjeta;
	public Billetera()
	{
		this.balance = 0.0;
		this.divisa = "USD";
		//generar clave pública.
		this.Transacciones = new LinkedList<Transaccion>();
		this.defis = new LinkedList<DeFi>();
		tarjeta = null;
	
		/*
		 *  Saldo
		 */
		arregloMontos = new LinkedList<Saldo>();
		
		
		/*
		 *  Monedas
		 */
		
		/*
		 * TODO: Agregar la lógica 
		 */
		
		CoinDAO myCoin = new CoinDAO();
		
		List<Coin> monedas = new LinkedList<Coin>();
		monedas.addAll(myCoin.devolverTabla());
		
		for (Coin moneda : monedas) {
			arregloMontos.add(new Saldo(moneda.getSigla(), 0.0));
		}
	}
	public String getTarjetaDebito() {
		return tarjeta.toString();
	}
	public void setTarjetaDebito(String sigla, boolean terminos, String serie, int codSeg,Fecha vencimiento, Saldo saldo) {
		tarjeta = new TarjetaDebito(sigla,terminos,serie,codSeg,vencimiento,saldo);
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getDivisa() {
		return divisa;
	}
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}
	public String getCVU() {
		return CVU;
	}
	public void setCVU(String CVU) {
		this.CVU = CVU;
	}
	public String getClavePublica() {
		return clavePublica;
	}
	public void setClavePublica(String clavePublica) {
		this.clavePublica = clavePublica;
	}
	public String historialTransacciones()
	{
		String historialString = "HISTORIAL DE TRANSACCIONES";
		historialString.concat("\n");
		for (Transaccion t:this.Transacciones)
		{
			historialString.concat(t.toString());
		}
		return historialString;
	}
	public String historialDeFi()
	{
		String historialDefiString = "HISTORIAL DE DEFI";
		historialDefiString.concat("\n");
		for (DeFi d:this.defis)
		{
			historialDefiString.concat(d.toString()+"\n");
		}
		return historialDefiString;
	}
	public String getSaldos()
	{
		String saldosString = "SALDOS";
		saldosString.concat("\n");
		
		for (Saldo saldo : this.arregloMontos) {
			saldosString.concat(saldo.toString() + '\n');
		}
		
//		for (i=0;i<4;i++)
//		{
//			saldosString.concat(this.arregloMontos[i].toString()+"\n");
//		}
		
		return saldosString;
	}
	
	public List<Saldo> getArregloMontos() {
		return this.arregloMontos;
	}
	
}
