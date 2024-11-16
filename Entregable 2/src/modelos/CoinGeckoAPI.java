package modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject; // Necesita agregar la librería org.json para trabajar con JSON

import entregable1.Coin;
import entregable1.TipoMoneda;

public class CoinGeckoAPI {
	public static List<Coin> getInitialData(List<String> listaMonedas) {
		List<Coin> monedas = new LinkedList<Coin>();
		
		for (String c_str : listaMonedas) {
			// GET
			String identifier = String.format("https://api.coingecko.com/api/v3/coins/%s?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false", c_str);
			
			HttpRequest request = HttpRequest.newBuilder()
				    .uri(URI.create(identifier))
				    .header("accept", "application/json")
				    .header("x-cg-demo-api-key", "CG-i5sa7aWM1m6mbdLbPPKHvBWq")
				    .method("GET", HttpRequest.BodyPublishers.noBody())
				    .build();
			HttpResponse<String> response;
			
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());			
				if (response.statusCode() == 200) {
					monedas.add(parseCoin(response));
				}
				
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return monedas;
	}
	
	private static Coin parseCoin(HttpResponse<String> response) {	
		JSONObject object = new JSONObject(response.body());
		Coin moneda = new Coin(object.getString("name"),
				object.getString("symbol"),
				TipoMoneda.CRIPTOMONEDA,
				object.getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"));
		
		return moneda;
	}
	
}
