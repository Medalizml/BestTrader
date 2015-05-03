package managedBeansClient;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tn.esprit.Blues.UsersServices.UsersServices;
import tn.esprit.Blues.entities.Customer;

@ManagedBean
@RequestScoped
public class AddUserManagedBean {
@EJB
UsersServices user;


	private Customer customer;
	private String passConfirm;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String doAdd() {
		System.out.println("test");
		FacesContext fc=FacesContext.getCurrentInstance();
		
		if(passConfirm.equals(customer.getPassword())){
		customer.setActive(false);
		user.add(customer);
		ExternalContext ec=fc.getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath()+"/login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		else{
			fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"password does not match",null));
			}
		return null;
	}
	
	@PostConstruct
	void init(){
		
		customer = new Customer();
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

	
}
