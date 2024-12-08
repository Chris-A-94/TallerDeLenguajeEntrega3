package entregable1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import daos.ActivosDAO;
import daos.CoinDAO;

import daos.TransaccionDAO;
import modelos.CompraExcepcion;
import modelos.SaldoExcepcion;
import modelos.SwapException;


/**
 * Es una única billetera, almacena los saldos de todas las cryptomonedas y las claves, ademas de realizar operaciones sobre las mismas.
 */
public class Billetera {
	/*
	 *  ¿Qué sucede si se agrega una nueva moneda a la base de datos?
	 *  respuesta: nada.
	 */
	private List<Saldo> arregloSaldo;
	private List<DeFi> defis;
	private Tarjeta tarjeta;
	private String userID;
	private Double balance;
	private String divisa;
	private String CVU;
	private String clavePublica;
	private List<Transaccion> Transacciones;
	
	public void nuevaCompra(JTextField valor, Coin moneda,String siglaFiat) throws CompraExcepcion
	{
		if(Math.abs(moneda.getStock() - 0.0) < 1e-12)
			throw new CompraExcepcion("No hay stock en la moneda "+moneda.getNombre(),false);
		//te quedaste en mandar sistema.getmonedas al panelcompracontrolador para enviarlo aca
		double saldoAGastar = Double.parseDouble(valor.getText()); //valor a restar del Saldo
		double monedaAObtener = 0.0; //valor a sumar al Saldo cripto y restar del Stock cripto
		
		Saldo aUsar = null;
		Saldo monedaACargar = null;		
		for(Saldo s: this.arregloSaldo)
		{
			if(siglaFiat.equalsIgnoreCase(s.getSigla()))
				aUsar = s;
			if(moneda.getSigla().equalsIgnoreCase(s.getSigla()))
				monedaACargar = s;				
							
		}
				
		CoinDAO monedaAux = new CoinDAO();
		double precioSaldo = monedaAux.getCoin(siglaFiat).getPrecio(); //1 si es dolar, 1000 si es peso
		//divido el valor ingresado por el precio del saldo para tenerlo en USD, y luego por el precio de la moneda para saber el N° de monedas
		monedaAObtener = (saldoAGastar  * precioSaldo )/ moneda.getPrecio();
		
		if(monedaAObtener > moneda.getStock())
			throw new CompraExcepcion("Compra superior al stock en sistema",true);
		
		double comision = monedaAObtener * 0.03;
		double total = monedaAObtener - comision;
		Object[] enEspaniol = {"Sí", "No"};
		
		int respuesta = JOptionPane.showOptionDialog (null,"¿Desea cargar "+monedaAObtener+moneda.getSigla()+"?\n"
				+"Se descontara un 3% de comision. Total Compra: "+total,"Confirmar Operacion",JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,enEspaniol,enEspaniol[0]);
		
		if (respuesta == 0) 
		{			
			monedaAObtener = total;
			ActivosDAO saldoDB = new ActivosDAO();
			if(monedaACargar == null)
			{
				monedaACargar = new Saldo(this.userID,moneda.getTipo(),moneda.getSigla(),monedaAObtener);
				this.arregloSaldo.add(monedaACargar);
				saldoDB.guardar(monedaACargar);
			}
				
			else
			{
				monedaACargar.setCantMonedas(monedaACargar.getCantMonedas() + monedaAObtener);
				saldoDB.modificar(monedaACargar);
			}
				
			aUsar.setCantMonedas(aUsar.getCantMonedas() - saldoAGastar);
			//modifico stock en db y array
			moneda.setStock(moneda.getStock() - monedaAObtener);
			CoinDAO actualizarDB = new CoinDAO();
			actualizarDB.modificar(moneda);
			//creo transaccion
			
			String dia = String.valueOf(java.time.ZonedDateTime.now().getDayOfMonth());
			String mes = String.valueOf(java.time.ZonedDateTime.now().getMonth().getValue());
			String year = String.valueOf(java.time.ZonedDateTime.now().getYear());
			TransaccionDAO guardarTransaccion = new TransaccionDAO();
			guardarTransaccion.guardar(new TransaccionCompra(dia,mes,year,this.userID,moneda.getSigla(),siglaFiat,monedaAObtener,saldoAGastar));
			this.agregarTransaccion(new TransaccionCompra(dia,mes,year,this.userID,moneda.getSigla(),siglaFiat,monedaAObtener,saldoAGastar));
        } 
		else if (respuesta == JOptionPane.NO_OPTION) 
            return;
		
		JOptionPane.showMessageDialog(null,"Se ha cargado "+monedaAObtener+" en "+moneda.getNombre()
				,"Operacion Exitosa!", JOptionPane.INFORMATION_MESSAGE);
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

	public void cargarActivos(String eleccion, double saldoNuevo) throws SaldoExcepcion
	{
		if(this.arregloSaldo == null)
			throw new SaldoExcepcion("Billetera::CargarActivos:: el arreglo saldo no esta inicializado");
		
		
		ActivosDAO databaseActivos = new ActivosDAO();
		boolean encontro = false;
		for(Saldo mySaldo: this.arregloSaldo)
		{
			if(mySaldo.getSigla().equals(eleccion))
			{
				mySaldo.setCantMonedas(mySaldo.getCantMonedas() + saldoNuevo);
				databaseActivos.modificar(mySaldo);
				encontro = true;
			}				
		}
		if(!encontro)
		{
			CoinDAO nombres = new CoinDAO();
			TipoMoneda myTipo = null;
			for(Coin moneda: nombres.devolverTabla())
			{
				if(moneda.getSigla().equals(eleccion))
				{
					myTipo = moneda.getTipo();					
				}
					
			}
			Saldo nuevoSaldo = new Saldo(this.getUserID(),myTipo,eleccion
					,saldoNuevo);
			arregloSaldo.add(nuevoSaldo);
			databaseActivos.guardar(nuevoSaldo);
		}
		JOptionPane.showMessageDialog(null, "Se cargo "+saldoNuevo+" de "+eleccion, "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
		
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