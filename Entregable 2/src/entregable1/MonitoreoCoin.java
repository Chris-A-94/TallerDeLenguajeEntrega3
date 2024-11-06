package entregable1;
import java.io.BufferedReader;
import org.json.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implementacion funcional de monitorio de precios de cryptomoneda. Actualiza los precios de las 4 monedas a traves de una llamada al servidor CoinGecko.
 */
public class MonitoreoCoin {
	
	private String apiURL;	
	private JSONObject monedasPrincipales;
		
	public MonitoreoCoin()
	{
		this.apiURL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin%2Cethereum%2Ctether%2Cusd-coin%2Cdogecoin&vs_currencies=usd&x_cg_demo_api_key=CG-wqRNzUCU4erPp2ahwamhe1f7";	
		try {
			updateMonedasPrincipales();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateMonedasPrincipales() throws IOException
	{
		//carga la URL y se conecta con coingecko
		URL direccion = new URL(apiURL);	
		//openConnection retorna una URLConection, que es clase padre de HttpURLConnection
		HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
		
		//seteo la conexion para conseguir data en formato JSON
		conexion.setRequestMethod("GET");
		conexion.setRequestProperty("Accept", "application/json");
		if(conexion.getResponseCode() == 200)
		{
			//Obtengo la InputStream de data y la convierto a una clase con metodos de lectura rapidos
			BufferedReader rawData = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String Data = rawData.readLine();			
			JSONTokener interpret = new JSONTokener(Data);					
			this.monedasPrincipales = new JSONObject(interpret);						
		}
		
	}

	public List<String> getIDS()
	{
		Set<String> keys = (Set<String>) this.monedasPrincipales.keySet();
		List<String> names = new ArrayList<String>(keys);		
		return names;
	}
	
	public List<Double> getPrices()
	{
		try {
			this.updateMonedasPrincipales();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> IDS = this.getIDS();
		List<Double> precios = new ArrayList<Double>();
		for(String ID: IDS)
		{
			JSONObject moneda = this.monedasPrincipales.getJSONObject(ID);
			precios.add(moneda.getDouble("usd"));
		}
		return precios;		
	}
	
	private boolean coinExists(String name)
	{
		List<String> names = this.getIDS();
		boolean exists = false;
		for(String aux: names)
			if(aux.equals(name))
				exists = true;
		return exists;
	}			
	
	
	public Double getPrice(String name)
	{
		if(!coinExists(name))
			return -1.0; //excepcion a futuro
		
		try {
			this.updateMonedasPrincipales();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject moneda = this.monedasPrincipales.getJSONObject(name);
		Double precio = moneda.getDouble("usd");
		return precio;
	}		
	
	public String toString()
	{
		List<String> IDs = this.getIDS();
		List<Double> precios = this.getPrices();
		String aRetorno = "";
		if(IDs.size() != precios.size())
		{
			System.err.println("Error fatal.");
			return null;
		}
		for(int i = 0; i < precios.size(); i++)
		{
			aRetorno += "\n La moneda "+IDs.get(i)+" cuesta: "+precios.get(i)+" dolares.";
		}
		return aRetorno;
	}

}