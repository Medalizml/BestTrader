package managedBeansClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "cur")
@SessionScoped
public class CurrencyParsingBean {
	
	CurrencyParsing currencyParsing=new CurrencyParsing();
	List<Float> currList=new ArrayList<Float>();
	Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
	private List<Integer> listDay = new ArrayList<>();
	private List<Integer> listMonth = new ArrayList<>();
	private List<Integer> listYear = new ArrayList<>();
	private Date date;
	

	

	@PostConstruct
	public void init() {
		CurrencyParsing currencyParsing = new CurrencyParsing();
		try {
			setMap(currencyParsing.PairCurrency());
			// map.putAll(currencyParsing.PairCurrency());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public void destribute() {
		List<Date> list= currencyParsing.listeDesDates();
		System.out.println(list);
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		String dateS;
		for (int i = 0; i < list.size(); i++) {
			date = list.get(i);
			dateS = sm.format(date);
			System.out.println(dateS);
			listDay.add(Integer.parseInt(dateS.substring(0, 2)));
			listMonth.add(Integer.parseInt(dateS.substring(3, 5)));
			listYear.add(Integer.parseInt(dateS.substring(6, 10)));

		}
	}

	public String goToScreeners(String pair) {
		try {
			System.out.println(pair);
			setCurrList(currencyParsing.getCurrHist(pair));
			destribute();
			
			System.out.println(currList);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return "currencyScreen";
	}

	public Map<String, StringBuffer> getMap() {
		return map;
	}

	public void setMap(Map<String, StringBuffer> map) {
		this.map = map;
	}

	public List<Float> getCurrList() {
		return currList;
	}

	public void setCurrList(List<Float> currList) {
		this.currList = currList;
	}

	public List<Integer> getListDay() {
		return listDay;
	}

	public void setListDay(List<Integer> listDay) {
		this.listDay = listDay;
	}

	public List<Integer> getListMonth() {
		return listMonth;
	}

	public void setListMonth(List<Integer> listMonth) {
		this.listMonth = listMonth;
	}

	public List<Integer> getListYear() {
		return listYear;
	}

	public void setListYear(List<Integer> listYear) {
		this.listYear = listYear;
	}

}
