package managedBeans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import tn.esprit.Blues.entities.Bank;
import tn.esprit.Blues.entities.Share;
import Bank.BankService;

@ManagedBean
@SessionScoped
public class BankManagedBean {
	@EJB
	BankService bankService;

	public static Bank bank;
	private Bank newBank = new Bank();
	private List<Bank> BankTable;

	private Share share = new Share();
	private String dateBank;

	public BankManagedBean() {
		super();

	}

	@PostConstruct
	public void init() {
		setBankTable(bankService.findAllBank());
		setNewBank(new Bank());
		setBank(new Bank());

	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		BankManagedBean.bank = bank;
	}

	public List<Bank> getBankTable() {
		return BankTable;
	}

	public void setBankTable(List<Bank> BankTable) {
		this.BankTable = BankTable;
	}

	public void doRemoveBank(Bank c) {

		bankService.remove(c);
		init();
	}

	public String doput(Bank Bank) {
		BankManagedBean.bank = Bank;
		System.out.println(bank.getName());
		return "updateBank";
	}
	public String doDeatil(Bank Bank) {
		BankManagedBean.bank = Bank;
		
		return "Detail";
	}

	public String doAd() {
		return "addPrivate";
	}

	public String doUpdate() {
		bankService.update(bank, (Share) bank.getQuotation());
		return "bank";
	}

	public String doAdd() {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateBank = sdf.format(sdf.parse(dateBank));
			System.out.println(dateBank + "     1");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Date date = new Date(dateBank);

		Integer year = date.getYear();
		System.out.println(year);
		Integer month = date.getMonth();
		System.out.println(month);
		Integer day = date.getDate();
		System.out.println(day);
		java.sql.Date date1 = new java.sql.Date(year, month, day);
		
		try {
			DemoBean.upload();
			System.out.println("iciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		} catch (IOException e) {

			System.out
					.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			e.printStackTrace();
		}
		newBank.setLogo("resource/img/" + DemoBean.getFilename(DemoBean.file1));
		share.setCompany(newBank);
		newBank.setNature("Bank");
		newBank.setDateIncorporation(date1);
		bankService.add(newBank, share);
		
		//init();
		return "private?faces-redirect=true";
	}

	public String doShow(Bank bank) {
		BankManagedBean.bank = bank;
		return "currencyBank";
	}

	public Bank getNewBank() {
		return newBank;
	}

	public void setNewBank(Bank newBank) {
		this.newBank = newBank;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public String getDateBank() {
		return dateBank;
	}

	public void setDateBank(String dateBank) {
		this.dateBank = dateBank;
	}
	

}
