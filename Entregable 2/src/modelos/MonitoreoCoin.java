package modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import entregable1.Coin;
import entregable1.TipoMoneda;

/**
 * Implementacion funcional de monitorio de precios de cryptomoneda. Actualiza los precios de las monedas indicadas en listaIDMonedas a traves de varias llamadas al servidor CoinGecko.
 */
public class MonitoreoCoin {
	
	// Advertencia: Formatear el texto antes de usar
	private static String apiURL = new String("https://api.coingecko.com/api/v3/coins/%s?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false");	
	// Descripción de la request:
	//	id: 			'%s' (Especificar con String.format(apiURL, "id")),
	//  localization: 	false,
	//  tickers: 		false,
	//  market_data: 	true,
	//  community_data: false,
	//  developer_data: false,
	//  sparkline: 		false
	
	private static List<String> listaIDMonedas = new LinkedList<String>(); // Almacena las IDs de las monedas a buscar
	private static List<Coin> listaMonedas = new LinkedList<Coin>(); // (anteriormente monedasPrincipales) Almacena una lista de todas las monedas
	
	private static List<JSONObject> objectList = new LinkedList<JSONObject>(); // Almacena una lista de los objetos recibidos de la request
	
	public static void updateMonedas() throws IOException { // (anteriormente updateMonedasPrincipales)
		// Limpiar lista de objetos
		objectList.clear();
		
		// Para cada elemento dentro de listaIDMonedas
		for (String c_str : listaIDMonedas) {
			
			// GET
			String identifier = String.format(apiURL, c_str);
			//APIKey cambiada el 3/12 Key anterior: CG-i5sa7aWM1m6mbdLbPPKHvBWq
			HttpRequest request = HttpRequest.newBuilder()  
				    .uri(URI.create(identifier))
				    .header("accept", "application/json")
				    .header("x-cg-demo-api-key", "CG-wqRNzUCU4erPp2ahwamhe1f7")
				    .method("GET", HttpRequest.BodyPublishers.noBody())
				    .build();
			HttpResponse<String> response;
			
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());			
				if (response.statusCode() == 200) {
					// 200: OK
					objectList.add(new JSONObject(response.body()));
				} else {
					System.out.printf("response.statusCode(): %d\n", response.statusCode());
				}
				
			} catch (IOException | InterruptedException e) {
				// Error
				e.printStackTrace();
			}
		}
		
		// Si se actualizó la lista de objetos
		if (objectList.size() > 0) {
			listaMonedas.clear();
			
			// Agregar a la lista de monedas
			objectList.forEach(obj -> {
				listaMonedas.add(parseCoin(obj));
			});
		}
	}
	
	private static Coin parseCoin(JSONObject object) {
		Coin moneda = new Coin(object.getString("name"),
				object.getString("symbol"),
				TipoMoneda.CRIPTOMONEDA,
				object.getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"),
				object.getJSONObject("image").getString("thumb"),
				object.getJSONObject("image").getString("small"),
				object.getJSONObject("image").getString("large"));
		
		return moneda;
	}
	
	public static boolean existeMoneda(String name)
	{
		for (Coin c : listaMonedas) {
			if (c.getNombre().equalsIgnoreCase(name) || c.getSigla().equalsIgnoreCase(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static List<Coin> getListaMonedas() {
		return listaMonedas;
	}
	public static List<String> getListaIDMonedas()
	{	
		return listaIDMonedas;
	}
	
	public static void setListaIDMonedas(List<String> monedas) {
		listaIDMonedas.addAll(monedas); 
	}	
	public static Coin getParticularCoin(String Nombre)
	{
		for(Coin auxCoin: listaMonedas)
		{
			if(Nombre.equals(auxCoin.getNombre()))
				return auxCoin;
		}
		return null;
	}
	
// 	Nota: Adaptar el código, van a ser necesarios en un futuro...
//	
//	public List<Double> getPrices()
//	{
//		try {
//			this.updateMonedasPrincipales();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		List<String> IDS = this.getIDS();
//		List<Double> precios = new ArrayList<Double>();
//		for(String ID: IDS)
//		{
//			JSONObject moneda = this.monedasPrincipales.getJSONObject(ID);
//			precios.add(moneda.getDouble("usd"));
//		}
//		return precios;		
//	}
//	
//	public Double getPrice(String name)
//	{
//		if(!coinExists(name))
//			return -1.0; //excepcion a futuro
//		
//		try {
//			this.updateMonedasPrincipales();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		JSONObject moneda = this.monedasPrincipales.getJSONObject(name);
//		Double precio = moneda.getDouble("usd");
//		return precio;
//	}
	public String toString()
	{
		if (listaMonedas.size() < 0) {
			return "Lista de monedas vacía!!!";
		}
		
		// IDs y precios ahora están encapsulados en una instancia de 'Coin'
		String aRetorno = "";
		for(int i = 0; i < listaMonedas.size(); i++)
		{
			aRetorno += "La moneda "+listaMonedas.get(i).getSigla()+" cuesta: "+listaMonedas.get(i).getPrecio()+" dolares.\n";
		}
		return aRetorno;
	}
}