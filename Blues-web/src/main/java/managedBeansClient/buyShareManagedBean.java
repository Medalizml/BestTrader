package managedBeansClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.UsersServices.UsersServices;
import tn.esprit.Blues.entities.Customer;
import tn.esprit.Blues.entities.Operation;
import tn.esprit.Blues.entities.Share;
import clientServices.BuyServices;
import clientServices.ProfileServices;
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

	@EJB
	UsersServices services;
	@EJB
	ProfileServices profile;

	private List<Share> shares;

	private List<sellAction> buyShares = new ArrayList<sellAction>();

	private String holder;

	private boolean opMessage = false;

	@ManagedProperty("#{auth.customer}")
	Customer customer = new Customer();
	
	private float opValue;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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
		setHolder("type number of shares");
		remplirList();

	}

	public void remplirList() {
		for (int i = 0; i < shares.size(); i++) {
			sellAction action = new sellAction();
			action.setOpVolume(null);
			action.setShare(shares.get(i));
			buyShares.add(action);
		}
	}

	public void buyShare(sellAction sellAction) {
		if (sellAction.getOpVolume() * sellAction.getShare().getClosingPrice() < customer
				.getPortfolio().getValue()) {
			Operation operation = new Operation();
			operation.setDateOperation(new Date());
			operation.setNumberShares(sellAction.getOpVolume().intValue());
			operation.setPortfolio(customer.getPortfolio());
			operation.setQuotation(sellAction.getShare());
			operation.setSharePrice(sellAction.getShare().getClosingPrice());
			customer.getPortfolio().setValue(
					customer.getPortfolio().getValue()
							- sellAction.getOpVolume()
							* sellAction.getShare().getClosingPrice());
			this.opValue=sellAction.getOpVolume() * sellAction.getShare().getClosingPrice();
			sellAction.setOpVolume(null);
			setOpMessage(true);
			try {
				services.update(customer.getPortfolio());
				profile.addOperation(operation);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public boolean isOpMessage() {
		return opMessage;
	}

	public void setOpMessage(boolean opMessage) {
		this.opMessage = opMessage;
	}

	public float getOpValue() {
		return opValue;
	}

	public void setOpValue(float opValue) {
		this.opValue = opValue;
	}

}
