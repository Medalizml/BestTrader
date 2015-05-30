package managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.Blues.StatServices.StatServices;
import tn.esprit.Blues.entities.Customer;
import StatAdminServices.StatAdminServices;

@ManagedBean(name = "statBean")
@SessionScoped
public class StatBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737412272371913653L;

	@EJB
	private StatServices statServices;
	@EJB
	private StatAdminServices services;
	private String test;
	public static  List<Customer> customers;
	private Date date;
	public static Customer customer= new Customer();
	private boolean open=false;
	private List<Float> benefices; 
	private List<Integer> buy;
	private List<Integer> sell;
	private List<Float> buyPrice;
	private List<Float> sellPrice;
	private List<Integer> shares;
	private List<Float> Price;
	
	
	@PostConstruct
	public void init() {
		//setTest(statServices.getName());
		setCustomers(statServices.getList());

	}
	
	public boolean doOpen(){
		open=true;
		System.out.println(open);
		return open;
	}
	public void doClose(){
	open=false;	
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		StatBean.customer = customer;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		StatBean.customers = customers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String put(Customer customer){
		StatBean.customer=customer;
		setBenefices(services.findBenefice(customer.getPortfolio().getId()));
		setBuy(services.findBuy(customer.getPortfolio().getId()));
		setSell(services.findSell(customer.getPortfolio().getId()));
		setBuyPrice(services.findPriceBuy(customer.getPortfolio().getId()));
		setSellPrice(services.findPriceSell(customer.getPortfolio().getId()));
		setShares(services.findNumberShares(customer.getPortfolio().getId()));
		setPrice(services.findPrice(customer.getPortfolio().getId()));
		System.out.println(customer.getFirstName());
		return "userCharts";
	}
	public Integer getRank(){
		return statServices.getOrderedList().indexOf(customer)+1;
		
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<Float> getBenefices() {
		return benefices;
	}

	public void setBenefices(List<Float> benefices) {
		this.benefices = benefices;
	}

	public List<Integer> getBuy() {
		return buy;
	}

	public void setBuy(List<Integer> buy) {
		this.buy = buy;
	}

	public List<Integer> getSell() {
		return sell;
	}

	public void setSell(List<Integer> sell) {
		this.sell = sell;
	}

	public List<Float> getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(List<Float> buyPrice) {
		this.buyPrice = buyPrice;
	}

	public List<Float> getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(List<Float> sellPrice) {
		this.sellPrice = sellPrice;
	}

	public List<Integer> getShares() {
		return shares;
	}

	public void setShares(List<Integer> shares) {
		this.shares = shares;
	}

	public List<Float> getPrice() {
		return Price;
	}

	public void setPrice(List<Float> price) {
		Price = price;
	}

}
