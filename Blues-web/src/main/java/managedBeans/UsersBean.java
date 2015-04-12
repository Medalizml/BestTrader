package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.Services.CustomerServicesImpl;
import tn.esprit.Blues.UsersServices.UsersServices;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.Portfolio;

@ManagedBean
@SessionScoped
public class UsersBean {

	@EJB
	UsersServices user;
	private List<Customer> list;
	private List<Customer> list1;
	private Customer customerDet;
	private String name;
	private float bonus;
	private Portfolio portfolio;
	private Customer selectedCustomer=new Customer();

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public float getBonus() {
		return bonus;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UsersBean() {
		portfolio = new Portfolio();
	}

	public String doAddbonus() {
		Portfolio p;
		p = selectedCustomer.getPortfolio();
		p.setValue(p.getValue() + bonus);
		user.update(p);
		bonus = 0;
		return null;
	}
	
	public String doActivateUser(Customer customer){
		customer.setActive(true);
		user.update(customer);
		init();
		return null;
	}
	
	public String insert(Customer c) {
		setSelectedCustomer(c);
		System.out.println(c.getEmail());
		return "usersDetails";

	}

	public String doDeleteUser(Customer c) {
		user.remove(c);
		this.init();
		return null;
	}

	@PostConstruct
	public void init() {
		setList(user.findAll());
		setList1(user.findAllNoActif());
	}


	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public List<Customer> getList1() {
		return list1;
	}

	public void setList1(List<Customer> list1) {
		this.list1 = list1;
	}

	public Customer getCustomerDet() {
		return customerDet;
	}

	public void setCustomerDet(Customer customerDet) {
		this.customerDet = customerDet;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

}
