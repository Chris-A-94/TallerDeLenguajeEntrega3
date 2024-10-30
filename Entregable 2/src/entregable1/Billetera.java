package entregable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.sql.Time;
import java.time.*;
import daos.CoinDAO;

import daos.TransaccionDAO;


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
	private String userID;
	public Billetera(String userID)
	{
		this.balance = 0.0;
		this.divisa = "USD";
		//generar clave pública.
		this.Transacciones = new LinkedList<Transaccion>();
		this.defis = new LinkedList<DeFi>();
		tarjeta = null;
		arregloSaldo = new LinkedList<Saldo>();
		this.userID = userID;
	}
	
	private int choose(int limite)
	{
		Scanner in = new Scanner(System.in);
		int index = in.nextInt();
		while(index < 0 || index > limite)
		{
			System.out.println("Error, por favor elija un numero positivo menor que "+limite+".");
			index = in.nextInt();
		}
		return index;
	}
	
	public void swap(List<Coin> monedas)
	{
		if(this.arregloSaldo.isEmpty())
		{
			System.out.println("Error, usted no posee activos.");
			return;
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Usted posee las siguientes monedas: ");
		for(Saldo saldo: this.arregloSaldo)
			System.out.println(saldo.getSigla()+": "+saldo.getCantMonedas());
		System.out.println("Desea hacer swap entre: \n 1. Criptomonedas \n 2. Fiat currency \n 3. Cancelar");
		int opcion = in.nextInt();
		while(opcion < 1 || opcion > 3)
		{
			System.out.println("Valor invalido.");
			System.out.println("Desea hacer swap entre: \n 1. Criptomonedas \n 2. Fiat currency \n 3. Cancelar");
			opcion = in.nextInt();
		}
		List<Coin> monedasASwap = new LinkedList<Coin>();
		if(opcion == 1)
		{
			for(Coin moneda: monedas)
				if(moneda.getTipo().equals("CRIPTOMONEDA"))
					monedasASwap.add(moneda);
		}
		else if(opcion == 2)
		{
			for(Coin moneda: monedas)
				if(moneda.getTipo().equals("FIAT"))
					monedasASwap.add(moneda);
		}
		else
		{
			System.out.println("Operacion cancelada.");
			return;
		}
		swapping(monedasASwap);
	}

	
	private void swapping(List<Coin> monedas)
	{
		//Asumo que necesita tener saldo de la primera moneda, pero la segunda puede tener 0
		
		List<Saldo> arregloSaldoFiltrado = new LinkedList<Saldo>();
		
		//absolutamente horrible, si se agrega el Tipo a la construccion de this.arregloSaldo podria quedar mejor
		for(Saldo saldo: this.arregloSaldo)
			for(Coin moneda: monedas)
				if(saldo.getSigla().equals(moneda.getSigla()))
				{
					arregloSaldoFiltrado.add(saldo);
				}
				
		
		System.out.println("Usted posee las siguientes monedas de tipo "+monedas.get(0).getTipo().toString()+": ");
		for(int i = 0; i < arregloSaldoFiltrado.size(); i++)
		{
			System.out.println((i+1)+"."+arregloSaldoFiltrado.get(i).getSigla()+": "+arregloSaldoFiltrado.get(i).getCantMonedas());
		}
		System.out.println("Ingrese el indice del activo a swappear: ");
		int indexOne = choose(arregloSaldoFiltrado.size()) - 1;
		System.out.println("Ingrese el indice del activo que desea obtener: ");
		int indexTwo = choose(arregloSaldoFiltrado.size()) - 1;
		
		Saldo primeraMoneda = arregloSaldoFiltrado.get(indexOne);
		Saldo segundaMoneda = arregloSaldoFiltrado.get(indexTwo);
		
		if(primeraMoneda.getCantMonedas() == 0.00 &&
				segundaMoneda.getCantMonedas() == 0.00)
		{
			System.out.println("Error, usted no posee saldo en ningun activo elegido.");
			return;
		}
		
		Scanner in = new Scanner(System.in);
		System.out.println("Ingrese la cantidad de "+primeraMoneda.getSigla()+" a usar: ");
		double canToSwap = in.nextDouble();
		while(canToSwap > primeraMoneda.getCantMonedas() || canToSwap <= 0)
		{
			System.out.println("Error, monto invalido.");
			System.out.println("1. Cambiar monto. \n 2. Cancelar operacion.");
			int sophie = in.nextInt();
			if(sophie == 1)
			{
				System.out.println("Ingrese nuevo monto: ");
				canToSwap = in.nextDouble();
			}
			else if(sophie == 2)
				return;
			else
				System.out.println("Opcion invalida.");
		}
		
		//todo lo anterior fue chequeo del ingreso correcto de variables
		//a partir de aca es calculo y conversion
		
		Coin primeraCoin = null;
		Coin segundaCoin = null;
		
		for(Coin myCoins: monedas)
		{
			if(myCoins.getSigla().equals(primeraMoneda.getSigla()))
				primeraCoin = myCoins;
			if(myCoins.getSigla().equals(segundaMoneda.getSigla()))
				segundaCoin = myCoins;
		}
		
		//etimologia: "primero" es moneda a ser swapeada y "segundo" es el target
		//Coin en ingles es variable de tipo Coin, Moneda en español es de tipo saldo
		//good luck
		
		double dolarPrimeraMoneda = canToSwap * primeraCoin.getPrecio();
		double totalSegundaEnDolares = segundaCoin.getPrecio() * segundaCoin.getStock();
		
		if(dolarPrimeraMoneda > totalSegundaEnDolares)
		{
			System.out.println("Error, la moneda "+segundaCoin.getNombre()+" no tiene suficiente stock.");
			System.out.println("Abortando operacion");
			return;
		}
		double canToGet = dolarPrimeraMoneda / segundaCoin.getPrecio();
		
		segundaMoneda.setCantMonedas(segundaMoneda.getCantMonedas() + canToGet);
		primeraMoneda.setCantMonedas(primeraMoneda.getCantMonedas() - canToSwap);
		//el razonamiento que tomo es que la cantidad de monedas que swappea lo tramita con nosotros
		//nosotros le damos el extra, y tomamos lo que da, en vez de un cambio equivalente entre sus activos
		//el enunciado dice que hay que chequear stock, y eso no tendria sentido sino interactua con el lado del server
		
		
		primeraCoin.setStock(primeraCoin.getStock() + canToSwap);
		segundaCoin.setStock(segundaCoin.getStock() - canToGet);
		
		
		
		CoinDAO monedasDB = new CoinDAO();
		monedasDB.modificar(primeraCoin);
		monedasDB.modificar(segundaCoin);
		
		
		//transacciondatabase
		String dia = String.valueOf(java.time.ZonedDateTime.now().getDayOfMonth());
		String mes = java.time.ZonedDateTime.now().getMonth().toString();
		String year = String.valueOf(java.time.ZonedDateTime.now().getYear());
		
		TransaccionDAO archivarSwap = new TransaccionDAO();
		archivarSwap.guardar(new TransaccionSwap(dia, mes, year, this.userID, segundaCoin.getSigla(), canToGet, primeraCoin.getSigla(), canToSwap));
		
		System.out.println("Swap exitoso!");
		System.out.println("Su nuevo saldo de "+primeraCoin.getNombre()+" es de: "+primeraMoneda.getCantMonedas());
		System.out.println("Su nuevo saldo de "+segundaCoin.getNombre()+" es de: "+segundaMoneda.getCantMonedas());
	}

	public void comprar(String moneda,String fiat, List<Coin> monedas)
	{
		//Se obtiene el Saldo de la divisa fiat ingresda
				Scanner in = new Scanner(System.in);				
				Saldo enDivisa = null;		
				for(Saldo auxSaldo: this.arregloSaldo)
				{
					if(fiat.equalsIgnoreCase(auxSaldo.getSigla())) {
						enDivisa = auxSaldo;
					}
				}
				
				//Si el saldo es cero, se ofrecen opciones de cargar saldo
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
				//Se pide cuanto del saldo se usara en la operacion
				System.out.println("Su saldo en "+fiat+" es de: "+enDivisa.getCantMonedas()+".\n ¿Cuanto desea usar?");
				Double saldoEmitido = in.nextDouble();	
				//Si el saldo a gastar es mayor que el que se tiene, se ofrece gastar menos o cargar mas
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
					//obtengo monedas de la base de datos para poder modificarlas y reenviarlas
					CoinDAO monedasDB = new CoinDAO();
					LinkedList<Coin> monedasMem = new LinkedList<Coin>();
					monedasMem.addAll(monedas);
					Coin auxFiat = null;
					Coin auxMoneda = null;
					for(Coin monedaAux: monedasMem)
					{
						if(fiat.equalsIgnoreCase(monedaAux.getSigla()))
							auxFiat = monedaAux;
						if(moneda.equalsIgnoreCase(monedaAux.getSigla()))
							auxMoneda = monedaAux;
					}
					//precio de la criptomoneda expresada en el fiat ingresado
					double precio = (1 / auxFiat.getPrecio()) * auxMoneda.getPrecio();
					
					System.out.println("La moneda "+moneda+" vale "+precio+" "+fiat+".\n¿Desea proceder? y/n");
					String carga = in.next();
					if(carga.equals("y") || carga.equals("Y"))
						//Se tienen en cuenta los problemas de que el usuario intente comprar mas de lo que el sistema tiene
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
						for(Saldo saldos: this.arregloSaldo)
						{ 
							//Se agregan las monedas fiats de la compra, y se restan las vendidas al usuario en la memoria dinamica
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
						//mismo calculo de arriba pero para la database
						auxMoneda.setStock(auxMoneda.getStock() - monedasAComprar);
						auxFiat.setStock(saldoFinalFiat);
						
						// Búsqueda de la moneda
						List<Coin> table = monedasDB.devolverTabla();
						boolean existeCoin, existeFiat;
						existeCoin = existeFiat = false;
						for (Coin c : table) {
							if (c.getSigla().equals(auxMoneda.getSigla())) {
								existeCoin = true;
								continue;
							} else if (c.getSigla().equals(auxFiat.getSigla())){
								existeFiat = true;
							} else if (existeCoin && existeFiat) {
								break;
							}
						}
						
						if (existeCoin) {
							monedasDB.modificar(auxMoneda);
						}
						if (existeFiat) {
							monedasDB.modificar(auxFiat);
						}
						
						
						System.out.println("Se ha cargado "+monedasAComprar+" "+auxMoneda.getSigla()+" a su saldo.");
						System.out.println("Saldo actual en "+auxMoneda.getSigla()+" :"+auxMoneda.getStock());
						System.out.println("Saldo actual en "+auxFiat.getSigla()+": "+auxFiat.getStock());
				
				
				String dia = String.valueOf(java.time.ZonedDateTime.now().getDayOfMonth());
				String mes = java.time.ZonedDateTime.now().getMonth().toString();
				String year = String.valueOf(java.time.ZonedDateTime.now().getYear());
				
				TransaccionDAO guardarTransaccion = new TransaccionDAO();
				guardarTransaccion.guardar(new TransaccionCompra(dia,mes,year,this.userID,auxMoneda.getSigla(),auxFiat.getSigla(),saldoEmitido,monedasAComprar));
			}
			
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
