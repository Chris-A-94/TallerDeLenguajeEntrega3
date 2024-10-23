package entregable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
	private List<Saldo> arregloSaldo;
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
		
		generarArregloSaldo();
	}
	
	public double cargarSaldoUSD(String fiat)
	{
		//funcion para cargar saldo USD en memoria para comprar monedas
		//a futuro se podria agregar a la db
		System.out.println("¿Cuanto saldo desea cargar?");
		Scanner in = new Scanner(System.in);
		double saldo = in.nextDouble();
		if(!(saldo >= 0.0))
		{
			while(!(saldo >= 0.0))
			{
				System.out.println("Saldo no valido, ingrese numero mayor a cero.");
				saldo = in.nextDouble();
			}
		}
		
		CoinDAO myCoin = new CoinDAO();
		List<Coin> monedas = new LinkedList<Coin>();
		monedas.addAll(myCoin.devolverTabla());
		Coin aux = null;
		for(Coin moneda: monedas)
		{
			if(fiat.equals(moneda.getSigla()))
				aux = moneda;
		}
		
		
		Saldo saldoConvertido = new Saldo(aux.getSigla(),saldo,aux.getPrecio(),aux.getNombre());
		
		
		
		Double conversion = saldoConvertido.ConvertirADolar(saldo);
		
		this.balance += conversion;
		
			
		System.out.println("Saldo cargado. Saldo actual: "+this.balance);
		return this.balance;
	}
	public void comprar(String moneda,String fiat)
	{
		
		Scanner in = new Scanner(System.in);
		if( this.balance == 0.0)
		{
			System.out.println("Su balance actual es cero. ¿Desea cargar? y/n");			
			String aux = in.next();
			if(aux.equals("y") || aux.equals("Y"))
				cargarSaldoUSD(fiat);
			else
			{
				System.out.println("No puede comprar sin saldo.");
				
				return;
			}
		}
		System.out.println("¿Cuanto de su balance desea usar? Balance actual: "+this.balance);
		double aUsar = in.nextDouble();
		if(aUsar > this.balance)
		{
			
			System.out.println("Saldo insuficiente.");
			return;
		}
		
		CoinDAO myCoin = new CoinDAO(); // (DB API)
		List<Coin> monedas = new LinkedList<Coin>();
		monedas.addAll(myCoin.devolverTabla());
		
		Coin aux = null;
		for(Coin nioc: monedas)
		{
			if(moneda.equals(nioc.getSigla()))
				aux = nioc;
		}
		
		System.out.println("La moneda "+aux.getNombre()+" vale "+aux.getPrecio()+". Desea proceder? Y/N");
		String proceder = in.next();
		if(proceder.equals("Y") || proceder.equals("y"))
		{
			Double monedasAComprar = aUsar / aux.getPrecio();
			if (monedasAComprar > aux.getStock())
			{
				System.out.println("Stock insuficiente para el valor pedido.");
				System.out.println("1. Cambiar valor \n 2. Comprar maximo \n 3. Cancelar");
				int moveValor = in.nextInt();
				switch(moveValor)
				{
				case 1:
					double valorMax = aux.getPrecio() * aux.getStock();
					System.out.println("Valor nuevo en USD: (maximo: "+valorMax+")");
					aUsar = in.nextDouble();
					if( aUsar > valorMax)
					{
						System.out.println("Valor mas alto del posible. ERROR.");
						
						return;
					}
					monedasAComprar = aUsar / aux.getPrecio();
					break;
				case 2: monedasAComprar = aux.getStock();
					break;
				default: 
					
					return;					
				}
			}
			double saldoFinal = 0;
			for(Saldo saldos: this.arregloSaldo)
			{
				//aca agrego las monedas compradas al arreglo saldo de la billetera
				if(saldos.getSigla().equals(moneda))
				{
					saldoFinal = saldos.getCantMonedas() + monedasAComprar;
					saldos.setCantMonedas(saldoFinal);
				}
					
			}
			//modifico stock de coins en la DB
			aux.setStock(aux.getStock() - monedasAComprar);
			myCoin.modificar(aux);
			this.balance -= aUsar;
			System.out.println("Se ha cargado "+monedasAComprar+" "+aux.getNombre()+" a su saldo.");
			System.out.println("Saldo actual en "+aux.getNombre()+" :"+saldoFinal);
			System.out.println("Saldo actual en USD: "+this.balance);
			
			//FALTA DESCRIBIR LA TRANSACCION EN LA BASE DE DATOS
			
		}
		else
		{
			System.out.println("Operacion cancelada");
			
			return;
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
	public void generarArregloSaldo() {
		CoinDAO myCoin = new CoinDAO(); // (DB API)
		// Se instancia una nueva lista de objetos 'Saldo'
		arregloSaldo = new LinkedList<Saldo>();
		
		
		// Se exportan las monedas
		List<Coin> monedas = new LinkedList<Coin>();
		monedas.addAll(myCoin.devolverTabla());
		
		// Se instancia un objeto 'Saldo' por cada moneda
		for (Coin moneda : monedas) {
			this.arregloSaldo.add(new Saldo(moneda.getSigla(), 0.0));
		}
	}
	public void agregarMoneda(Coin moneda) {
		// Si no hay ninguna lista, imprimir error y retornar
		if (arregloSaldo.equals(null)) {
			System.out.printf("ERROR::BILLETERA::ARREGLOSALDO_ES_NULL\n");
			return;
		}	
		
		// Insertar a la lista la nueva moneda
		this.arregloSaldo.add(new Saldo(moneda.getSigla(), 0.0));
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
		
		for (Saldo saldo : this.arregloSaldo) {
			saldosString.concat(saldo.toString() + '\n');
		}
		
//		for (i=0;i<4;i++)
//		{
//			saldosString.concat(this.arregloMontos[i].toString()+"\n");
//		}
		
		return saldosString;
	}
	
	public List<Saldo> getArregloSaldo() {
		return this.arregloSaldo;
	}
	
}
