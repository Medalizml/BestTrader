package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.entities.Bank;
import tn.esprit.Blues.entities.Currency;
import tn.esprit.Blues.entities.Currencybank;
import Bank.BankService;

@ManagedBean
@ViewScoped
public class CurrencyBankManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3483523607561101988L;
	@EJB
	BankService bankService;
	
	public boolean render=false;
	Currencybank currencybank=new Currencybank();
	private List<Currencybank> currencybanks;
	private List<Currency> currencies;
	private Integer currencyN=new Integer(1);

	@PostConstruct
	public void init() {
		setCurrencybanks(bankService.findBybank(BankManagedBean.bank.getId()));
		setCurrencies(bankService.FindAll());
	}

	public Currencybank getCurrencybank() {
		return currencybank;
	}

	public void setCurrencybank(Currencybank currencybank) {
		this.currencybank = currencybank;
	}

	public String putCurr(Currencybank currencybank) {
		this.currencybank = currencybank;
		return "updateCurr";
	}

	public String doRemoveCurr(Currencybank currencybank) {
		System.out.println(currencybank);
		bankService.removeCurr(currencybank);
		init();
		System.out.println("biennnnnn");
		return "currencyBank";

	}
	
	public String AddCurrencyBank(){
		System.out.println(currencybank.getBuyPrice());
		System.out.println(BankManagedBean.bank.getName());
		System.out.println(currencyN);
		currencybank.setBank(BankManagedBean.bank);
		currencybank.setCurrency(bankService.FindCurrById(currencyN));
		bankService.addCurrencyBank(currencybank);
		init();
		
		return "currencyBank";
	}

	public List<Currencybank> getCurrencybanks() {
		return currencybanks;
	}

	public void setCurrencybanks(List<Currencybank> currencybanks) {
		this.currencybanks = currencybanks;
	}
	public void openAdd(){
		render=true;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public Integer getCurrencyN() {
		return currencyN;
	}

	public void setCurrencyN(Integer currencyN) {
		this.currencyN = currencyN;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	public String updateCurrency(Currencybank currencybank){
		System.out.println(currencybank.getId());
		bankService.updateCu(currencybank);
		render=false;
		
		return null;
	}

	
	

}
