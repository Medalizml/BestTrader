package managedBeansClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.entities.Share;
import clientServices.BuyServices;
import entity.sellAction;

@ManagedBean
@ViewScoped
public class buyShareManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6464197612507811833L;

	@EJB
	BuyServices buyServices;

	private List<Share> shares;
	
	private List<sellAction> buyShares=new ArrayList<sellAction>();

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public List<sellAction> getbuyShares() {
		return buyShares;
	}

	public void setbuyShares(List<sellAction> buyShares) {
		this.buyShares = buyShares;
	}


	@PostConstruct
	public void init() {
		setShares(buyServices.getShares());
		remplirList();

	}
	
	public void remplirList(){
		for(int i=0;i<shares.size();i++){
			sellAction action=new sellAction();
			action.setOpVolume(0f);
			action.setShare(shares.get(i));
			buyShares.add(action);
		}
	}

}
