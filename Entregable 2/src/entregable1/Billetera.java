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
	 *  respuesta: nada.
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
		arregloSaldo = new LinkedList<Saldo>();
	}

	public void comprar(String moneda,String fiat)
	{
		
		Scanner in = new Scanner(System.in);
		System.out.println("¿Cuanto desea comprar?");
		Double saldoEmitido = in.nextDouble();		
		
		Saldo enDivisa = new Saldo();
		int posSaldo = -1;
		for(Saldo auxSaldo: this.arregloSaldo)
		{
			if(fiat.equals(auxSaldo.getSigla()))
			{
				enDivisa = auxSaldo;
			}
			posSaldo++;	
		}
		
		if(enDivisa.getCantMonedas() == 0)
		{
			System.out.println("No tiene saldo en la divisa "+enDivisa.getSigla()+" ¿Desea cargar? Y/N");
			String carga = in.next();
			if(carga.equals("y") || carga.equals("Y"))
			{
				System.out.println("Ingrese nuevo saldo: ");
				Double mySaldo = in.nextDouble();
				enDivisa.setCantMonedas(mySaldo);
			}
			else
			{
				System.out.println("No puede comprar "+moneda+" sin saldo.");
				return;
			}
		}
		if(enDivisa.getCantMonedas() < saldoEmitido)
		{
			System.out.println("Su saldo actual en "+fiat+" es de "+enDivisa.getCantMonedas()+
					". Insuficiente para su compra.");
			System.out.println("1. Usar maximo saldo actual \n 2. Cambiar monto a comprar \n 3. Cargar mas \n 4. Cancelar");
			int i = in.nextInt();
			switch(i)
			{
			case 1: saldoEmitido = enDivisa.getCantMonedas();
				break;
			case 2: System.out.println("Saldo maximo: "+enDivisa.getCantMonedas()+". Ingrese monto menor: ");
					saldoEmitido = in.nextDouble();
					if( saldoEmitido > enDivisa.getCantMonedas())
					{
						System.out.println("No puede hacer esa transaccion.");
						return;
					}
				break;
			case 3: double requerido = saldoEmitido - enDivisa.getCantMonedas();
					System.out.println("Necesita "+requerido+" para realizar su compra."
					+ "Ingrese balance extra:");
					double newBalance = in.nextDouble();
					enDivisa.setCantMonedas(enDivisa.getCantMonedas() + newBalance);
					if(enDivisa.getCantMonedas() < saldoEmitido)
					{
						System.out.println("Saldo cargado, pero sigue siendo insuficiente. Error.");
						return;
					}				
					
				break;
				default:
				System.out.println("Error.");
				return;
			}
		}
			CoinDAO monedasDB = new CoinDAO();
			LinkedList<Coin> monedasMem = new LinkedList<Coin>();
			monedasMem.addAll(monedasDB.devolverTabla());
			Coin auxFiat = null;
			Coin auxMoneda = null;
			for(Coin monedaAux: monedasMem)
			{
				if(fiat.equals(monedaAux.getSigla()))
					auxFiat = monedaAux;
				if(moneda.equals(monedaAux.getSigla()))
					auxMoneda = monedaAux;
			}
			
			double precio = auxFiat.getPrecio() / auxMoneda.getPrecio();
			
			System.out.println("La moneda "+moneda+" vale "+precio+" "+fiat+".\n¿Desea proceder? y/n");
			String carga = in.next();
			if(carga.equals("y") || carga.equals("Y"))
				//DANGER
			{
				
				double enDolares = auxFiat.getPrecio() * saldoEmitido;
				double monedasAComprar = enDolares / auxMoneda.getPrecio();
				if (monedasAComprar > auxMoneda.getStock())
				{
					System.out.println("Stock insuficiente para el valor pedido.");
					System.out.println("1. Cambiar valor \n 2. Comprar maximo \n 3. Cancelar");
					int moveValor = in.nextInt();
					switch(moveValor)
					{
					case 1:
						double valorMaxDolar = auxMoneda.getPrecio() * auxMoneda.getStock();
						double valorMaxFiat = valorMaxDolar * auxFiat.getPrecio();
						System.out.println("Valor nuevo: (maximo: "+valorMaxFiat+")");
						saldoEmitido = in.nextDouble();
						if( saldoEmitido > valorMaxFiat)
						{
							System.out.println("Valor mas alto del posible. ERROR.");
							
							return;
						}
						enDolares = auxFiat.getPrecio() * saldoEmitido;
						monedasAComprar = enDolares / auxMoneda.getPrecio();
						break;
					case 2: monedasAComprar = auxMoneda.getStock();
						break;
					default: 
						
						return;					
					}
				}
				double saldoFinalCoin = 0.0;
				double saldoFinalFiat = 0.0;
				//this.generarArregloSaldo(); //conceptualmente mal, pero no carga los valores del foreach sino
				for(Saldo saldos: this.arregloSaldo)
				{ 
					//aca agrego las monedas compradas al arreglo saldo de la billetera
					if(saldos.getSigla().equals(moneda))
					{
						saldoFinalCoin = saldos.getCantMonedas() + monedasAComprar;
						saldos.setCantMonedas(saldoFinalCoin);
					}
					if(saldos.getSigla().equals(fiat))
					{
						saldoFinalFiat = saldos.getCantMonedas() - saldoEmitido;
						saldos.setCantMonedas(saldoFinalFiat);
					}
						
				}
				//modifico stock de coins en la DB
				auxMoneda.setStock(auxMoneda.getStock() - monedasAComprar);
				auxFiat.setStock(saldoFinalFiat);
				monedasDB.modificar(auxMoneda);
				monedasDB.modificar(auxFiat);
				System.out.println("Se ha cargado "+monedasAComprar+" "+auxMoneda.getNombre()+" a su saldo.");
				System.out.println("Saldo actual en "+auxMoneda.getNombre()+" :"+saldoFinalCoin);
				System.out.println("Saldo actual en"+auxFiat.getNombre()+": "+saldoFinalFiat);
				
				//FALTA DESCRIBIR LA TRANSACCION EN LA BASE DE DATOS
				
			}
			//DANGER
			else
			{
				System.out.println("Operacion cancelada.");
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
	public void agregarMoneda(Coin moneda,  Double cant) {
		// Si no hay ninguna lista, imprimir error y retornar
		if (arregloSaldo.equals(null)) {
			System.out.printf("ERROR::BILLETERA::ARREGLOSALDO_ES_NULL\n");
			return;
		}	
		
		// Insertar a la lista la nueva moneda
		this.arregloSaldo.add(new Saldo(moneda.getSigla(), cant));
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
	public void agregarSaldo(Saldo s) { //Acá habría que verificar que el saldo que se agrega corresponde a una moneda en la base de datos
		this.arregloSaldo.add(s);	
	}
}
