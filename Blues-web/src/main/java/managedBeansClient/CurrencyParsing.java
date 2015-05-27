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
	List<String> list = new ArrayList<String>();

	public void remplirList() {
		list.add("TND/USD");
		list.add("TND/EUR");
		list.add("TND/GBP");
		list.add("EUR/USD");
		list.add("EUR/JPY");
		list.add("EUR/CAD");
		list.add("GBP/USD");
		list.add("GBP/USD");
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
		return response;

	}

	public static List<Date> listeDesDates() {
		List<Date> dates = new ArrayList<Date>();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse("01-03-2015");
			Date date2 = new Date();
			GregorianCalendar caldeb = new GregorianCalendar();
			caldeb.setTime(date);
			GregorianCalendar calfin = new GregorianCalendar();
			calfin.setTime(date2);

			while (caldeb.before(calfin)) {

				dates.add(caldeb.getTime());
				caldeb.add(GregorianCalendar.DATE, 1);

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dates;
	}

	public Map<String, StringBuffer> PairCurrency() throws Exception {

		Map<String, StringBuffer> map = new HashMap<>();
		CurrencyParsing http = new CurrencyParsing();
		http.remplirList();
		for (int i = 0; i < http.list.size(); i++) {
			StringBuffer j = http.sendGet(http.list.get(i));

			map.put(http.list.get(i), j);
		}

		return map;
	}

	@SuppressWarnings("deprecation")
	public List<Float> getCurrHist(String pair) throws Exception {
		List<Float> l = new ArrayList<Float>();
		String USER_AGENT = "Mozilla/5.0";
		String inputLine = null;
		System.out.println("dkhaaaaaaaaalt");
		for (int x = 0; x < listeDesDates().size(); x++) {
			float value;
			int day = listeDesDates().get(x).getDate();
			int mounth = listeDesDates().get(x).getMonth() + 1;
			int year = listeDesDates().get(x).getYear() + 1900;
			String date = year + "-" + mounth + "-" + day;
			String url = "http://currencies.apps.grandtrunk.net/getrate/"
					+ date + "/" + pair;

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			inputLine = in.readLine();
			try {
				value = Float.parseFloat(inputLine);
			} catch (Exception exception) {
				value = (1f);
			}
			l.add(value);

			in.close();

		}
		
		return l;
	}

}