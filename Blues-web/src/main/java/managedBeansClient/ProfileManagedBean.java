package managedBeansClient;

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
import tn.esprit.Blues.entities.Quotation;
import clientServices.ProfileServices;
import entity.Intermediaire;
import entity.OperationAction;


@ViewScoped
@ManagedBean(name="profile")
public class ProfileManagedBean {

	@EJB
	UsersServices services;
	@EJB
	ProfileServices profile;
	@ManagedProperty("#{auth.customer}")
	public Customer customer;
	public List<Quotation> quotations;
	public Long nbrShares;
	public Float value;
	public Operation operation;
	public List<Intermediaire> intermediaires;
	public List<OperationAction> actions;
	public String color;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<OperationAction> getActions() {
		return actions;
	}

	public void setActions(List<OperationAction> actions) {
		this.actions = actions;
	}

	public List<Intermediaire> getIntermediaires() {
		return intermediaires;
	}

	public void setIntermediaires(List<Intermediaire> intermediaires) {
		this.intermediaires = intermediaires;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Long getNbrShares() {
		return nbrShares;
	}

	public void setNbrShares(Long nbrShares) {
		this.nbrShares = nbrShares;
	}

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void doAddOperation(Quotation q){
		Date d= new Date();
		operation.setDateOperation(d);
		operation.setSharePrice(q.getClosingPrice());
		operation.setQuotation(q);
		operation.setNumberShares(-operation.getNumberShares());
		profile.addOperation(operation);
		customer.getPortfolio().setValue( customer.getPortfolio().getValue() - operation.getNumberShares()*q.getClosingPrice());
		services.update(customer.getPortfolio());
		this.init();
	}
	
	public List<Intermediaire> remplirIntermediaire(){
		List<Intermediaire> l = new ArrayList<Intermediaire>();
		
		for (int i = 0; i < quotations.size(); i++) {
		
			Intermediaire o = new Intermediaire();
			o.setQuotation(quotations.get(i));
			o.setNbrShares(profile.getNumberOfShares(customer.getPortfolio().getId(),quotations.get(i).getId()));
			o.setValue( o.getNbrShares() * o.getQuotation().getClosingPrice() );
			
			l.add(i, o);
		}
		
		
		return l;
	}
	public List<OperationAction> remplirOperationAction(){
		
		List<OperationAction> l = new ArrayList<OperationAction>();
		List<Operation> o = profile.getMyOperations(customer.getPortfolio().getId());
		
		for (int i = 0; i < o.size(); i++) {
			
			OperationAction a = new OperationAction();
			
			a.setOperation(o.get(i));
			if(o.get(i).getNumberShares()<0)
			{
				a.setAction("Sell");
				a.setColor("red");
				a.getOperation().setNumberShares(-o.get(i).getNumberShares());
			}
			else {
				a.setAction("Buy");
				a.setColor("green");
			}
			a.setValue( a.getOperation().getNumberShares() * a.getOperation().getSharePrice());
			l.add(a);
		}
		
		
		return l;
	}
	public void desactivate()
	{
		customer.setActive(false);
		services.update(customer);
	}
	public void updateProfile()
	{
		
		services.update(customer);
	}
	@PostConstruct
	public void init(){
		operation = new Operation();
		operation.setPortfolio(customer.getPortfolio());
		setColor("red");
		setQuotations(profile.getMyQuotations(customer.getPortfolio().getId()));
		setIntermediaires(this.remplirIntermediaire());
		setActions(this.remplirOperationAction());
	}
	
	
	
	
}
