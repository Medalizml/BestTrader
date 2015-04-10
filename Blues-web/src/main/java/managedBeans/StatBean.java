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

@ManagedBean(name = "statBean")
@SessionScoped
public class StatBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737412272371913653L;

	@EJB
	private StatServices statServices;
	private String test;
	public static  List<Customer> customers;
	private Date date;
	public static Customer customer= new Customer();
	private boolean open=false;
	@PostConstruct
	public void init() {
		setTest(statServices.getName());
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

}
