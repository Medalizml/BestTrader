package managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import tn.esprit.Blues.entities.Administrator;
import tn.esprit.Blues.entities.User;
import authservice.AuthentificationServiceI;

@SessionScoped
@ManagedBean(name = "auth")
public class Authentification {
	@EJB
	AuthentificationServiceI authentificationServiceI;
	User user;
	Administrator admin;
	boolean isLoggedIn = false;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PostConstruct
	public void init() {
		user = new User();
		admin=new Administrator();
	}

	public String doAuthentificate() {

		user = authentificationServiceI.authentificate(user.getEmail(),
				user.getPassword());
		if (user instanceof Administrator) {
			
			
			isLoggedIn = true;
			admin=authentificationServiceI.getAdminByUser(user.getId());
			System.out.println(admin.getProfilePicture());
			System.out.println(isLoggedIn);
			return "welcome?faces-redirect=true";
			
		} else {
			return null;
		}

	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String doLogOut(){
		admin=new Administrator();
		user=new User();
		isLoggedIn=false;
		System.out.println(isLoggedIn);
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		System.out.println(origRequest.getContextPath());
		return origRequest.getContextPath();
		
	}
}
