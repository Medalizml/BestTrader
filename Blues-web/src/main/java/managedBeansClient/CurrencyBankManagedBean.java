package managedBeansClient;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.entities.Bank;
import tn.esprit.Blues.entities.Currencybank;
import Bank.BankService;

@ManagedBean(name="cbank")
@ViewScoped
public class CurrencyBankManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7145657198472900738L;

	@EJB
	BankService bankService;

	List<Currencybank> currencybank1;
	List<Currencybank> currencybank2;
	List<Currencybank> currencybank3;
	List<Currencybank> currencybank4;
	List<Currencybank> currencybank5;
	List<Currencybank> currencybank6;
	List<Currencybank> currencybank7;
	List<Currencybank> currencybank8;
	List<Currencybank> currencybank9;
	List<Currencybank> currencybank10;

	List<Bank> banks;

	@PostConstruct
	public void init() {
		setBanks(bankService.findAllBank());
		remplirTabCurr();

	}
	
	
	public void remplirTabCurr(){
		setCurrencybank1(banks.get(0).getCurrencybanks());
		setCurrencybank2(banks.get(1).getCurrencybanks());
		setCurrencybank3(banks.get(2).getCurrencybanks());
		setCurrencybank4(banks.get(3).getCurrencybanks());
		setCurrencybank5(banks.get(4).getCurrencybanks());
		setCurrencybank6(banks.get(5).getCurrencybanks());
		setCurrencybank7(banks.get(6).getCurrencybanks());
		setCurrencybank8(banks.get(7).getCurrencybanks());
		setCurrencybank9(banks.get(8).getCurrencybanks());
		setCurrencybank10(banks.get(9).getCurrencybanks());
	}

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	public List<Currencybank> getCurrencybank1() {
		return currencybank1;
	}

	public void setCurrencybank1(List<Currencybank> currencybank1) {
		this.currencybank1 = currencybank1;
	}

	public List<Currencybank> getCurrencybank2() {
		return currencybank2;
	}

	public void setCurrencybank2(List<Currencybank> currencybank2) {
		this.currencybank2 = currencybank2;
	}

	public List<Currencybank> getCurrencybank3() {
		return currencybank3;
	}

	public void setCurrencybank3(List<Currencybank> currencybank3) {
		this.currencybank3 = currencybank3;
	}

	public List<Currencybank> getCurrencybank4() {
		return currencybank4;
	}

	public void setCurrencybank4(List<Currencybank> currencybank4) {
		this.currencybank4 = currencybank4;
	}

	public List<Currencybank> getCurrencybank5() {
		return currencybank5;
	}

	public void setCurrencybank5(List<Currencybank> currencybank5) {
		this.currencybank5 = currencybank5;
	}

	public List<Currencybank> getCurrencybank6() {
		return currencybank6;
	}

	public void setCurrencybank6(List<Currencybank> currencybank6) {
		this.currencybank6 = currencybank6;
	}

	public List<Currencybank> getCurrencybank7() {
		return currencybank7;
	}

	public void setCurrencybank7(List<Currencybank> currencybank7) {
		this.currencybank7 = currencybank7;
	}

	public List<Currencybank> getCurrencybank8() {
		return currencybank8;
	}

	public void setCurrencybank8(List<Currencybank> currencybank8) {
		this.currencybank8 = currencybank8;
	}

	public List<Currencybank> getCurrencybank9() {
		return currencybank9;
	}

	public void setCurrencybank9(List<Currencybank> currencybank9) {
		this.currencybank9 = currencybank9;
	}

	public List<Currencybank> getCurrencybank10() {
		return currencybank10;
	}

	public void setCurrencybank10(List<Currencybank> currencybank10) {
		this.currencybank10 = currencybank10;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

}
