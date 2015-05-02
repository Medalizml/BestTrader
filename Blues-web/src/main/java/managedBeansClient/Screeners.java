package managedBeansClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.esprit.Blues.Services.CustomerServices;
import tn.esprit.Blues.entities.Bank;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.Pricehistory;
import tn.esprit.Blues.entities.Quotation;
import ScreenersServices.ScreenersServices;

@ManagedBean
@SessionScoped
public class Screeners {

	@ManagedProperty(value = "#{list1}")
	private List<Integer> list;

	@EJB
	CustomerServices services;
	@EJB
	ScreenersServices screenersServices;

	private List<Customer> articles;
	private List<Date> listDate;
	private List<Company> listCompany;
	private List<Company> listPublic;
	private List<Bank> listBank;
	private Company company = new Company();
	private List<Float> opningPrice;
	private List<Float> closingPrice;
	private List<Float> lowestPrice;
	private List<Float> highestPrice;
	private List<Float> listVolume;
	private int valeur;
	private List<Integer> listDay = new ArrayList<>();
	private List<Integer> listMonth = new ArrayList<>();
	private List<Integer> listYear = new ArrayList<>();
	public List<Integer> getListYear() {
		return listYear;
	}

	public void setListYear(List<Integer> listYear) {
		this.listYear = listYear;
	}

	private Date date;
	

	public String detailsCompany(Company c) {
		setCompany(c);
		setOpningPrice(screenersServices.findAllPrice(c.getQuotation().getId()));
		setClosingPrice(screenersServices.findAllClosePrice(c.getQuotation().getId()));
		setLowestPrice(screenersServices.findAllLowPrice(c.getQuotation().getId()));
		setHighestPrice(screenersServices.findAllHighPrice(c.getQuotation().getId()));
		setListVolume(screenersServices.findAllVolume(c.getQuotation().getId()));
		setListDate(screenersServices.findAllDate(c.getQuotation().getId()));
		destribute();
		return "companyScreene";

	}
	
	public void destribute() {
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		String dateS;
		for (int i = 0; i < listDate.size(); i++) {
			date = listDate.get(i);
			dateS = sm.format(date);
			listDay.add(Integer.parseInt(dateS.substring(3, 5)));
			listMonth.add(Integer.parseInt(dateS.substring(0, 2)));
			listYear.add(Integer.parseInt(dateS.substring(6, 10)));

		}

	}

	@PostConstruct
	public void Init() {
		setList(services.findAllID());			
		setListCompany(screenersServices.findAllPrivate());
		setListPublic(screenersServices.findAllPublic());
		setListBank(screenersServices.findAllBank());

	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		if (list == null) {
			list = new ArrayList<Integer>();
		}
		this.list = list;
	}

	public List<Customer> getArticles() {
		return articles;
	}

	public void setArticles(List<Customer> articles) {
		this.articles = articles;
	}

	public List<Date> getListDate() {
		return listDate;
	}

	public void setListDate(List<Date> listDate) {
		this.listDate = listDate;
	}

	public List<Company> getListCompany() {
		return listCompany;
	}

	public void setListCompany(List<Company> listCompany) {
		this.listCompany = listCompany;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public List<Float> getOpningPrice() {
		return opningPrice;
	}

	public void setOpningPrice(List<Float> opningPrice) {
		this.opningPrice = opningPrice;
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

	public List<Float> getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(List<Float> closingPrice) {
		this.closingPrice = closingPrice;
	}

	public List<Float> getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(List<Float> lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public List<Float> getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(List<Float> highestPrice) {
		this.highestPrice = highestPrice;
	}

	public List<Company> getListPublic() {
		return listPublic;
	}

	public void setListPublic(List<Company> listPublic) {
		this.listPublic = listPublic;
	}

	public List<Bank> getListBank() {
		return listBank;
	}

	public void setListBank(List<Bank> listBank) {
		this.listBank = listBank;
	}

	public List<Float> getListVolume() {
		return listVolume;
	}

	public void setListVolume(List<Float> listVolume) {
		this.listVolume = listVolume;
	}

}
