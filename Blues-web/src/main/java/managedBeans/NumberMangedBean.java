package managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.ReclamationService.ReclServices;

@ManagedBean
@ViewScoped
public class NumberMangedBean {
	private long number; 
	@EJB
	ReclServices reclServices;
	
	
	
	public long getNumber() {
		return number;
	}



	public void setNumber(long number) {
		this.number = number;
	}



	@PostConstruct
	public void init(){
		
		//number=reclServices.numberRec();
		
	}
	
}
