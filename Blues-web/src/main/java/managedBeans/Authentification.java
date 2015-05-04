package managedBeans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tn.esprit.Blues.entities.Administrator;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.User;
import authservice.AuthentificationServiceI;

@SessionScoped
@ManagedBean(name = "auth")
public class Authentification {
	@EJB
	AuthentificationServiceI authentificationServiceI;
	
	private Administrator admin;
	private Customer customer;
	boolean isLoggedIn ;
	private User user;
	private boolean ispressed=false;
	


	public boolean isIspressed() {
		return ispressed;
	}

	public void setIspressed(boolean ispressed) {
		this.ispressed = ispressed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PostConstruct
	public void init() {
		
		isLoggedIn = false;
		user = new User();
		admin=new Administrator();
		customer=new Customer();
	}

	public String doAuthentificate() {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				if(authentificationServiceI.authentificate(user.getEmail(),
						user.getPassword())!=null){
		user = authentificationServiceI.authentificate(user.getEmail(),
				user.getPassword());
		if (user instanceof Administrator) {
			
			
			isLoggedIn = true;
			admin=authentificationServiceI.getAdminByUser(user.getId());
			System.out.println(admin.getProfilePicture());
			System.out.println(isLoggedIn);
			try {
				ec.redirect(ec.getRequestContextPath() + "/admin/welcome.jsf" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "admin/welcome?faces-redirect=true";
			
		} else if(user instanceof Customer) {
			isLoggedIn = true;
			System.out.println("hello"+customer.getFirstName());
			customer=authentificationServiceI.getClientByuser(user.getId());
			
			try {
				ec.redirect(ec.getRequestContextPath() + "/login.jsf" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		else{
			
			return null;
			
		}}else{
			ispressed=true;
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"login or password are not correct",null));
			return null;
		}

	}

	public boolean isLoggedIn() {
		
		System.out.println(isLoggedIn);
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		System.out.println(isLoggedIn+"setlogged in");
		this.isLoggedIn = isLoggedIn;
	}
	public String doLogOut(){
		admin=new Administrator();
		user=new User();
		isLoggedIn=false;
		ispressed=false;
		System.out.println(isLoggedIn +"dologout");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/login.jsf" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
