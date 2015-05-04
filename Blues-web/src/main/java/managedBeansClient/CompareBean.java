package managedBeansClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;







import ScreenersServices.ScreenersServices;
import clientServices.SectorServices;
import privateCompany.PrivateCompnayService;
import tn.esprit.Blues.entities.Company;
import tn.esprit.Blues.entities.Sector;

@SessionScoped
@ManagedBean
public class CompareBean {
	private Sector sector;
	private List<Sector> sectors;
	private String SectorN;
	private Company newCompany = null;
	private List<Company> companies;
	private List<Company> companies2;
	private Company newCompany2=null;
	private List<Date> listDate;
	private List<Float> closingPrice;
	private List<Integer> listDay = new ArrayList<>();
	private List<Integer> listMonth = new ArrayList<>();
	private List<Integer> listYear = new ArrayList<>();
	
	private List<Float> closingPrice2;
	
	private Date date;
	@EJB
	SectorServices services;
	@EJB
	PrivateCompnayService privateCompnayService;
	@EJB
	ScreenersServices screenersServices;
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public List<Sector> getSectors() {
		return sectors;
	}
	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}
	public String getSectorN() {
		return SectorN;
	}
	public void setSectorN(String sectorN) {
		SectorN = sectorN;
	}
	
	public Company getNewCompany() {
		return newCompany;
	}
	public void setNewCompany(Company newCompany) {
		this.newCompany = newCompany;
	}
	
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public List<Company> getCompanies2() {
		return companies2;
	}
	public void setCompanies2(List<Company> companies2) {
		this.companies2 = companies2;
	}
	
	public Company getNewCompany2() {
		return newCompany2;
	}
	public void setNewCompany2(Company newCompany2) {
		this.newCompany2 = newCompany2;
	}
	
	
	public List<Date> getListDate() {
		return listDate;
	}
	public void setListDate(List<Date> listDate) {
		this.listDate = listDate;
	}
	public List<Float> getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(List<Float> closingPrice) {
		this.closingPrice = closingPrice;
	}
	public List<Integer> getListDay() {
		return listDay;
	}
	public void setListDay(List<Integer> listDay) {
		this.listDay = listDay;
	}
	public List<Integer> getListMonth() {
		return listMonth;
	}
	public void setListMonth(List<Integer> listMonth) {
		this.listMonth = listMonth;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public List<Float> getClosingPrice2() {
		return closingPrice2;
	}
	public void setClosingPrice2(List<Float> closingPrice2) {
		this.closingPrice2 = closingPrice2;
	}
	
	
	public List<Integer> getListYear() {
		return listYear;
	}
	public void setListYear(List<Integer> listYear) {
		this.listYear = listYear;
	}
	@PostConstruct
	public void Init(){
		
		setSectors(privateCompnayService.findAllSector());
		setCompanies(services.findCompaniesBySector(1));
		setCompanies2(services.findCompaniesBySector(1));
		setSectorN(null);
		
	}
	public void SelectSector(AjaxBehaviorEvent e){
		System.out.println("sector change" );
		UISelectOne select=(UISelectOne) e.getComponent();
		System.out.println(select.getValue());
		setCompanies(services.findCompaniesBySector(Integer.parseInt((String)select.getValue())));
		System.out.println(companies.get(0).getName());
		
	}
	public void SelectSector1(AjaxBehaviorEvent e){

		
		UISelectOne select=(UISelectOne) e.getComponent();
		System.out.println(select.getValue());
		setCompanies2(services.findCompaniesBySector(Integer.parseInt((String)select.getValue())));
		System.out.println(companies.get(0).getName());
		
	}
	public void selectCompany(Company c){
		if(newCompany!=null){newCompany.setSelected(false);
		privateCompnayService.update(newCompany, newCompany.getQuotation());}
		System.out.println(c.getName());
		
		c.setSelected(true);
		setNewCompany(c);
		privateCompnayService.update(c, c.getQuotation());
		setClosingPrice(screenersServices.findAllClosePrice(c.getQuotation().getId()));
		setListDate(screenersServices.findAllDate(c.getQuotation().getId()));
		destribute();
		System.out.println("1");
		System.out.println(closingPrice);
		
		
	}
	public void selectCompany2(Company c){
		
		if(newCompany2!=null){newCompany2.setSelected(false);
		privateCompnayService.update(newCompany2, newCompany2.getQuotation());}
		System.out.println(c.getName());
		
		c.setSelected(true);
		setNewCompany2(c);
		privateCompnayService.update(c, c.getQuotation());
		setClosingPrice2(screenersServices.findAllClosePrice(c.getQuotation().getId()));
		System.out.println("2");
		System.out.println(closingPrice2);
		
		
	}
	public void destribute() {
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		String dateS;
		for (int i = 0; i < listDate.size(); i++) {
			date = listDate.get(i);
			dateS = sm.format(date);
			System.out.println(dateS);
			listDay.add(Integer.parseInt(dateS.substring(3, 5)));
			listMonth.add(Integer.parseInt(dateS.substring(0, 2)));
			listYear.add(Integer.parseInt(dateS.substring(6, 10)));

		}

	}
	
	

}
