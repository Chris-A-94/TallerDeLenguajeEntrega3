package entregable1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Implementacion funcional de monitorio de precios de cryptomoneda. Actualiza los precios de las 4 monedas a traves de una llamada al servidor CoinGecko.
 */
public class MonitoreoCoin {
	
	private String apiURL;
	private ArrayList<ArrayList<String>> monedasValue;
	private int cantMonedas;
		
	public MonitoreoCoin()
	{
		this.apiURL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin%2Cethereum%2Ctether%2Cusd-coin%2Cdogecoin&vs_currencies=usd&x_cg_demo_api_key=CG-wqRNzUCU4erPp2ahwamhe1f7";
		monedasValue = new ArrayList<ArrayList<String>>();
		this.cantMonedas = 5;
		cargarNombre();
		updatePrices();
	}
	
	public ArrayList<ArrayList<String>> actualizarMonedas()
	{
		updatePrices();
		return this.monedasValue;
	}
	
	private void cargarNombre()
	{	
		for (int i = 0; i < cantMonedas; i++) {  
	        this.monedasValue.add(new ArrayList<String>());  
	    }
		
		this.monedasValue.get(0).add("bitcoin");
		this.monedasValue.get(1).add("dogecoin");
		this.monedasValue.get(2).add("etherum");
		this.monedasValue.get(3).add("usdt");
		this.monedasValue.get(4).add("usdc");
		for(int i = 0; i < monedasValue.size(); i++)
		{
			this.monedasValue.get(i).add("0");
		}
	}
	
	private void updatePrices()
	{
		
		try 
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
				//elimino data irrelevante
				String regex = "[\":}]";
			    String[] valoresRaw = Data.split(regex);
			    ArrayList<String> valores = new ArrayList<String>();
			    //aislo los numeros
			    for(String Raw: valoresRaw)
			    {
			    	System.out.print(Raw);
			    	if(Raw.matches("[0-9]+(\\.[0-9]+)?"))
			    		valores.add(Raw);
			    } 			    
			    
			
			    int cont = 0;
			    for(String attempt: valores)
			    {
			    	//carga de datos en forma de string
			    	System.out.println(attempt);
			    	this.monedasValue.get(cont).remove(1);
			    	this.monedasValue.get(cont).add(attempt);
			    	cont++;
			    }
				
				
			}
			
			
		}
		//capturamiento de posibles errores desde la web
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 
		catch (ProtocolException e) 
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   	} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		
	}
	
	

}