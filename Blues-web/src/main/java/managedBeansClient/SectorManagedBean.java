package managedBeansClient;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Sector;
import clientServices.SectorServices;
import clientServices.homeServices;


@ViewScoped
@ManagedBean(name="sector")
public class SectorManagedBean {
	
	@EJB
	SectorServices services;
	@EJB
	homeServices home;
	
	private List<Company> companies1;
	private List<Company> companies2;
	private List<Company> companies3;
	private List<Company> companies4;
	private List<Company> companies5;
	private List<Company> companies6;
	private List<Company> companies7;
	private List<Company> companies8;
	private List<Company> companies9;
	private List<Company> companies10;
	private List<Company> companies11;
	private List<Company> companies12;
	private List<Company> companies13;
	private List<Company> companies14;
	private List<Sector> sectors;
	public List<Company> getCompanies1() {
		return companies1;
	}
	public void setCompanies1(List<Company> companies1) {
		this.companies1 = companies1;
	}
	public List<Company> getCompanies2() {
		return companies2;
	}
	public void setCompanies2(List<Company> companies2) {
		this.companies2 = companies2;
	}
	public List<Company> getCompanies3() {
		return companies3;
	}
	public void setCompanies3(List<Company> companies3) {
		this.companies3 = companies3;
	}
	public List<Company> getCompanies4() {
		return companies4;
	}
	public void setCompanies4(List<Company> companies4) {
		this.companies4 = companies4;
	}
	public List<Company> getCompanies5() {
		return companies5;
	}
	public void setCompanies5(List<Company> companies5) {
		this.companies5 = companies5;
	}
	public List<Company> getCompanies6() {
		return companies6;
	}
	public void setCompanies6(List<Company> companies6) {
		this.companies6 = companies6;
	}
	public List<Company> getCompanies7() {
		return companies7;
	}
	public void setCompanies7(List<Company> companies7) {
		this.companies7 = companies7;
	}
	public List<Company> getCompanies8() {
		return companies8;
	}
	public void setCompanies8(List<Company> companies8) {
		this.companies8 = companies8;
	}
	public List<Company> getCompanies9() {
		return companies9;
	}
	public void setCompanies9(List<Company> companies9) {
		this.companies9 = companies9;
	}
	public List<Company> getCompanies10() {
		return companies10;
	}
	public void setCompanies10(List<Company> companies10) {
		this.companies10 = companies10;
	}
	public List<Company> getCompanies11() {
		return companies11;
	}
	public void setCompanies11(List<Company> companies11) {
		this.companies11 = companies11;
	}
	public List<Company> getCompanies12() {
		return companies12;
	}
	public void setCompanies12(List<Company> companies12) {
		this.companies12 = companies12;
	}
	public List<Company> getCompanies13() {
		return companies13;
	}
	public void setCompanies13(List<Company> companies13) {
		this.companies13 = companies13;
	}
	public List<Company> getCompanies14() {
		return companies14;
	}
	public void setCompanies14(List<Company> companies14) {
		this.companies14 = companies14;
	}


	
	
	
	
	
	
	
	public List<Sector> getSectors() {
		return sectors;
	}
	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	
	
	@PostConstruct
	void init(){
		setSectors(home.findAllSectors());
		setCompanies1(services.findCompaniesBySector(1));
		setCompanies2(services.findCompaniesBySector(2));
		setCompanies3(services.findCompaniesBySector(3));
		setCompanies4(services.findCompaniesBySector(4));
		setCompanies5(services.findCompaniesBySector(5));
		setCompanies6(services.findCompaniesBySector(6));
		setCompanies7(services.findCompaniesBySector(7));
		setCompanies8(services.findCompaniesBySector(8));
		setCompanies9(services.findCompaniesBySector(9));
		setCompanies10(services.findCompaniesBySector(10));
		setCompanies11(services.findCompaniesBySector(11));
		setCompanies12(services.findCompaniesBySector(12));
		setCompanies13(services.findCompaniesBySector(13));
		setCompanies14(services.findCompaniesBySector(14));

		
	}

}
