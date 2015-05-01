package managedBeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {
 
	public float sendGet(String pair) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		String url = "http://currencies.apps.grandtrunk.net/getlatest/"+"TND/"+pair;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		float price;
		try{
		price=Float.parseFloat(response.toString());}
		catch(Exception exception){
			price=(0f);
		}
		
		return price;

	}

}
