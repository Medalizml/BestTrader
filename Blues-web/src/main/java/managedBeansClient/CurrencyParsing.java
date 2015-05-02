package managedBeansClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyParsing {
 List<String> list=new ArrayList<String>();
 public void remplirList(){
	 list.add("TND/USD");
	 list.add("TND/EUR");
	 list.add("TND/GBP");
	 list.add("TND/JPY");
	 list.add("EUR/USD");
	 list.add("EUR/JPY");
	 list.add("EUR/TRY");
	 list.add("EUR/SEK");
	 list.add("EUR/PLN");
	 list.add("EUR/NZD");
	 list.add("EUR/NOK");
	 list.add("USD/CAD");
	 
 }

	// HTTP GET request
	private StringBuffer sendGet(String pair) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		String url = "http://currencies.apps.grandtrunk.net/getlatest/" + pair;

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
		//System.out.println(response.toString());
		return response;

	}
	
	/**
	public static List<Date> listeDesDates ()
	{
		String listeDates ="";
		List<Date> dates=new ArrayList<Date>();
		try{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date= formatter.parse("01-01-2014");
		Date date2=new Date();
		GregorianCalendar caldeb = new GregorianCalendar();
		caldeb.setTime(date);
		GregorianCalendar calfin = new GregorianCalendar();
		calfin.setTime(date2);
		
 
 
		while (caldeb.before(calfin))
		{
			
			
		dates.add(caldeb.getTime());
		caldeb.add(GregorianCalendar.DATE,1);
 
 
 
		}	
		
		}
		catch (ParseException e) 
		{e.printStackTrace();}
	
		return dates;
	}
	
	
	
	/**
	private String sendGet1(String pair) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		String inputLine=null;
		StringBuffer response = new StringBuffer();
		for(int x=0;x<listeDesDates().size();x++){
			int day=listeDesDates().get(x).getDate();
			int mounth=listeDesDates().get(x).getMonth()+1;
			int year=listeDesDates().get(x).getYear()+1900;
			String date=year+"-"+mounth+"-"+day;
			//System.out.println(date);
		String url = "http://currencies.apps.grandtrunk.net/getrate/"+date+"/"+pair;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		
		

//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
		//response.append(in.readLine());
		inputLine=in.readLine();
		in.close();
		
		// print result
		System.out.println(inputLine);
		}
		return inputLine;

	}
	**/

	// HTTP POST request

	public static void main(String[] args) throws Exception {
			
		
		Map<String,StringBuffer> map=new HashMap<>();
		//Map<String,String> map1=new HashMap<>();
		CurrencyParsing http = new CurrencyParsing();
		http.remplirList();
		for(int i=0;i<http.list.size();i++)
		{
		StringBuffer j = http.sendGet(http.list.get(i));
		/**String f=http.sendGet1(http.list.get(i)); **/
		
		map.put(http.list.get(i),j);
		//screeners pair of currency
		//map1.put(http.list.get(i),f);
		}
		System.out.println(map);}
//		/** for(int i=0;i<http.list.size();i++)
//		http.sendGet1(http.list.get(i));
//		**/
//		
//		
//	}
//	
	
	public Map<String, StringBuffer> PairCurrency() throws Exception
	{
		 
		Map<String,StringBuffer> map=new HashMap<>();
		//Map<String,String> map1=new HashMap<>();
		CurrencyParsing http = new CurrencyParsing();
		http.remplirList();
		for(int i=0;i<http.list.size();i++)
		{
		StringBuffer j = http.sendGet(http.list.get(i));
		/**String f=http.sendGet1(http.list.get(i)); **/
		
		map.put(http.list.get(i),j);
		//screeners pair of currency
		//map1.put(http.list.get(i),f);
		}
		System.out.println(map);
		return map;
	}
	

}