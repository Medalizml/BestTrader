package managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.Blues.EjbTimer.ParserServices;


@ManagedBean
@ApplicationScoped
public class ParserManagerBean {
	
	@EJB
	ParserServices parserServices;
	
	@PostConstruct
	public void init(){
		System.out.println(parserServices.readhtml());
		
	}

}
