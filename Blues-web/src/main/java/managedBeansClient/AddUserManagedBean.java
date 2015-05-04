package managedBeansClient;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import managedBeans.DemoBean;
import tn.esprit.Blues.UsersServices.UsersServices;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.Portfolio;

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
		Portfolio p=new Portfolio();
		if(passConfirm.equals(customer.getPassword())){
//			try{
//			customer.setProfilePicture("resource/img/" + DemoBean.getFilename(DemoBean.file1));}
//			catch(Exception exception){
//				exception.printStackTrace();
//			}
		customer.setActive(false);
		customer.setProfilePicture("resource/img/" + DemoBean.getFilename(DemoBean.file1));
		p.setValue(10000);
		p.setSharesNumber(0);
		p.setGain(0);
		p.setCustomer(customer);
		customer.setPortfolio(p);
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
