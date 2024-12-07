package entregable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import daos.CoinDAO;

import daos.TransaccionDAO;
import modelos.SwapException;


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
	
	public void nuevaCompra(JTextField valor, Coin moneda)
	{
		double parsedValue = Double.parseDouble(valor.getText());
		
		JOptionPane.showMessageDialog(null,"Se ha cargado "+parsedValue+" en "+moneda.getNombre()
				,"Compra Realizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public List<Transaccion> getTransacciones() {
		return Transacciones;
	}



	public List<DeFi> getDefis() {
		return defis;
	}



	public Tarjeta getTarjeta() {
		return tarjeta;
	}



	public String getUserID() {
		return userID;
	}
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
	
	
	
	private Saldo buscarSaldo(String sigla) {
		for (Saldo s : this.arregloSaldo) {
			if (s.getSigla().equals(sigla))
				return s;
		}
		
		return null;
	}
	
	public void swap(List<Coin> monedas, Coin target, Coin from, Double cantidad) throws SwapException {
		if (target == null) {
			throw new SwapException("Billetera::Exception::target_es_null");
		}
		if (from == null) {
			throw new SwapException("Billetera::Exception::from_es_null");
		}
		
		if(this.arregloSaldo.isEmpty()) {
			throw new SwapException("Billetera::Exception::arregloSaldo_está_vacío");
		}
		
		if (cantidad <= 0.0) {
			throw new SwapException("Billetera::Exception::la_cantidad_a_convertir_es_inválida");
		}
		
		// fromSaldo
		Saldo fromSaldo = this.buscarSaldo(from.getSigla());
		if (fromSaldo == null) {
			throw new SwapException("Billetera::Exception::el_saldo_no_existe");
		}
		
		if (fromSaldo.getCantMonedas() < cantidad) {
			throw new SwapException("Billetera::Exception::saldo_insuficiente");
		}
		
		// targetSaldo
		Saldo targetSaldo = this.buscarSaldo(target.getSigla());
		if (targetSaldo == null) {
			targetSaldo = new Saldo(this.userID, target.getTipo(), target.getSigla(), 0.0);
			this.agregarSaldo(targetSaldo);
		}
		
		swapping(target, from, targetSaldo, fromSaldo, cantidad);
	}

	
	private void swapping(Coin target, Coin from, Saldo targetSaldo, Saldo fromSaldo, Double cantidad) throws SwapException {
		// Se hace el calculo de la Conversión
		double monto = from.getPrecio() * cantidad;
		double cantidadConversion = monto / target.getPrecio();
		
		// Comprobación del Stock
		if (target.getStock() < cantidadConversion) {
			throw new SwapException("Billetera::Exception::no_hay_stock_suficiente");
		}
		
		// Se realiza la operación sobre la Billetera
		fromSaldo.setCantMonedas(fromSaldo.getCantMonedas() - cantidad);
		targetSaldo.setCantMonedas(targetSaldo.getCantMonedas() + cantidadConversion);
		
		// Se realiza la operación sobre la Moneda
		from.setStock(from.getStock() + cantidad); // Aumenta el Stock
		target.setStock(target.getStock() - cantidadConversion); // Decrementa el Stock
		
		
//		// Se modifica el Stock
//		CoinDAO monedasDB = new CoinDAO();
//		monedasDB.modificar(monedaSaldoSeleccionado);
//		monedasDB.modificar(monedaAConvertir);
////		
////		
//		//transacciondatabase
//		String dia = String.valueOf(java.time.ZonedDateTime.now().getDayOfMonth());
//		String mes = java.time.ZonedDateTime.now().getMonth().toString();
//		String year = String.valueOf(java.time.ZonedDateTime.now().getYear());
////		
//		TransaccionDAO archivarSwap = new TransaccionDAO();
//		archivarSwap.guardar(new TransaccionSwap(dia, mes, year, this.userID, monedaSaldoSeleccionado.getSigla(), cantSwap, monedaAConvertir.getSigla(), cantidadConversion));
////		
//		System.out.println("Swap exitoso!");
//		System.out.println("Su nuevo saldo de "+saldoSeleccionado.getSigla()+" es de: "+saldoSeleccionado.getCantMonedas());
//		System.out.println("Su nuevo saldo de "+saldoSwap.getSigla()+" es de: "+saldoSwap.getCantMonedas());
	}

	public void comprar(Coin moneda, String fiat, List<Coin> monedas)
	{
	//Se obtiene el Saldo de la divisa fiat ingresda
			Scanner in = new Scanner(System.in);				
			Saldo fiatSaldo = null;
			
			for(Saldo auxSaldo: this.arregloSaldo)
			{
				if ( fiat.equalsIgnoreCase(auxSaldo.getSigla()) ) {
					fiatSaldo = auxSaldo;
					break;
				}
			}
			
			//Si el saldo es cero, se ofrecen opciones de cargar saldo
			if(fiatSaldo.getCantMonedas() == 0)
			{
				System.out.println("No tiene saldo en la divisa "+fiatSaldo.getSigla()+" ¿Desea cargar? Y/N");
				String carga = in.next();
				if(carga.equals("y") || carga.equals("Y"))
				{
					System.out.println("Ingrese nuevo saldo: ");
					Double mySaldo = in.nextDouble();
					fiatSaldo.setCantMonedas(mySaldo);
				}
				else
				{
					System.out.println("No puede comprar "+moneda.getSigla()+" sin saldo.");
					return;
				}
			}
			//Se pide cuanto del saldo se usara en la operacion
			System.out.println("Su saldo en "+fiat+" es de: "+fiatSaldo.getCantMonedas()+".\n ¿Cuanto desea usar?");
			System.out.print(": ");
			Double saldoEmitido = in.nextDouble();	
			//Si el saldo a gastar es mayor que el que se tiene, se ofrece gastar menos o cargar mas
			if(fiatSaldo.getCantMonedas() < saldoEmitido)
			{
				System.out.println("Su saldo actual en "+fiat+" es de "+fiatSaldo.getCantMonedas()+
						". Insuficiente para su compra.");
				System.out.println("1. Usar maximo saldo actual \n 2. Cambiar monto a comprar \n 3. Cargar mas \n 4. Cancelar");
				int i = in.nextInt();
				switch(i)
				{
				case 1: saldoEmitido = fiatSaldo.getCantMonedas();
					break;
				case 2: System.out.println("Saldo maximo: "+fiatSaldo.getCantMonedas()+". Ingrese monto menor: ");
						saldoEmitido = in.nextDouble();
						if( saldoEmitido > fiatSaldo.getCantMonedas())
						{
							System.out.println("No puede hacer esa transaccion.");
							return;
						}
					break;
				case 3: double requerido = saldoEmitido - fiatSaldo.getCantMonedas();
						System.out.println("Necesita "+requerido+" para realizar su compra."
						+ "Ingrese balance extra:");
						double newBalance = in.nextDouble();
						fiatSaldo.setCantMonedas(fiatSaldo.getCantMonedas() + newBalance);
						if(fiatSaldo.getCantMonedas() < saldoEmitido)
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
			LinkedList<Coin> monedasMem = new LinkedList<Coin>();
			monedasMem.addAll(monedas);
			Coin auxFiat = null;
			Coin auxMoneda = null;
			for(Coin monedaAux: monedasMem)
			{
				if(fiat.equalsIgnoreCase(monedaAux.getSigla()))
					auxFiat = monedaAux;
				if(moneda.getSigla().equalsIgnoreCase(monedaAux.getSigla()))
					auxMoneda = monedaAux;
			}
			//precio de la criptomoneda expresada en el fiat ingresado
			double precio = (1 / auxFiat.getPrecio()) * auxMoneda.getPrecio();
			
			System.out.println("1 "+moneda.getSigla()+" equivale a "+precio+" "+fiat+".");
			System.out.println("Se cobrara una comision del 2.5%");
			double compraTotal= saldoEmitido/precio;
			double compraConComision = compraTotal - compraTotal * 0.025;
			System.out.println("Compra total: "+compraTotal+ " " + moneda.getSigla() +"\nCon comision aplicada: "+compraConComision +" " + moneda.getSigla());
			System.out.println(".\n¿Desea proceder? y/n");
			System.out.print(": ");
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
				Saldo coinSaldo = null;
				
				//aplico comision
				monedasAComprar -= monedasAComprar * 0.025;
				
				boolean seEncontroLaCripto = false;
				for(Saldo saldos: this.arregloSaldo)
				{
					//Se agregan las monedas fiats de la compra, y se restan las vendidas al usuario en la memoria dinamica
					if(saldos.getSigla().equalsIgnoreCase(moneda.getSigla()))
					{
						saldoFinalCoin = saldos.getCantMonedas() + monedasAComprar;
						saldos.setCantMonedas(saldoFinalCoin);
						coinSaldo = saldos;
						seEncontroLaCripto = true;
					}
					if(saldos.getSigla().equalsIgnoreCase(fiat))
					{
						saldoFinalFiat = saldos.getCantMonedas() - saldoEmitido;								
						saldos.setCantMonedas(saldoFinalFiat);
					}
				}
				if (!seEncontroLaCripto) {
					coinSaldo = new Saldo(this.userID, moneda.getTipo(), moneda.getSigla(), monedasAComprar);
					this.arregloSaldo.add(coinSaldo);
				}
				
				//mismo calculo de arriba pero para la database
				auxMoneda.setStock(auxMoneda.getStock() - monedasAComprar);
				auxFiat.setStock(saldoEmitido + auxFiat.getStock());
						
				System.out.println("Se ha cargado "+monedasAComprar+" "+auxMoneda.getSigla()+" a su saldo.");
				System.out.println("Saldo actual de "+coinSaldo.getSigla()+": "+coinSaldo.getCantMonedas());
				System.out.println("Saldo actual de "+fiatSaldo.getSigla()+": "+fiatSaldo.getCantMonedas());
				
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
	public void agregarMoneda(Coin moneda,  Double cant) {
		// Si no hay ninguna lista, imprimir error y retornar
		if (arregloSaldo.equals(null)) {
			System.out.printf("ERROR::BILLETERA::ARREGLOSALDO_ES_NULL\n");
			return;
		}	
		
		// Insertar a la lista la nueva moneda
		this.arregloSaldo.add(new Saldo(this.userID, moneda.getTipo(), moneda.getSigla(), cant));
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
	public void agregarTransaccion(Transaccion t) {
		this.Transacciones.add(t);
	}
	public List<Saldo> getArregloSaldo() {
		return this.arregloSaldo;
	}
	public void agregarSaldo(Saldo s) { //Acá habría que verificar que el saldo que se agrega corresponde a una moneda en la base de datos
		this.arregloSaldo.add(s);	
	}
}