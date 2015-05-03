package managedBeansClient;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="cur")
@SessionScoped
public class CurrencyParsingBean {
	Map<String,StringBuffer> map=new HashMap<String,StringBuffer>();
	
	
	@PostConstruct
	public void init()
	{	CurrencyParsing currencyParsing=new CurrencyParsing();
		try {
			setMap(currencyParsing.PairCurrency());
			//map.putAll(currencyParsing.PairCurrency());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
	}


	public Map<String, StringBuffer> getMap() {
		return map;
	}


	public void setMap(Map<String, StringBuffer> map) {
		this.map = map;
	}
}
