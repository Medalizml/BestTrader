package managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

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
	private String bonus;
	private Customer selectedCustomer = new Customer();

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String doAddbonus() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (isFloat(bonus)) {
			Portfolio p;
			p = selectedCustomer.getPortfolio();
			p.setValue(p.getValue() + Float.parseFloat(bonus));
			user.update(p);
			bonus = "0";
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Succes!", null));
		} else {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error! :please enter a valid number", null));
		}
		bonus = "0";
		return null;
	}

	public boolean isFloat(String chaine) {
		try {
			Float.parseFloat(chaine);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public String doActivateUser(Customer customer) {
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

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

}
