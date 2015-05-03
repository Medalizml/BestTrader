package entity;

import tn.esprit.Blues.entities.Quotation;

public class Intermediaire {

	Quotation quotation;
	Long nbrShares;
	Float value;
	public Intermediaire() {
	}
	public Quotation getQuotation() {
		return quotation;
	}
	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}
	public Long getNbrShares() {
		return nbrShares;
	}
	public void setNbrShares(Long nbrShares) {
		this.nbrShares = nbrShares;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	
	
	
	
	
	
}
