package managedBeansClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import managedBeans.CurrencyConverter;
import tn.esprit.Blues.entities.Share;
import clientServices.QuotationsServices;

@ViewScoped
@ManagedBean(name = "quotations")
public class QuotationsManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 233486237126285584L;

	private List<Share> shares;
	private List<Share> sharesConE = new ArrayList<Share>();
	private List<Share> sharesConD = new ArrayList<Share>();
	private List<Share> sharesConP = new ArrayList<Share>();
	private List<Share> sharesConT;
	private String selectedCurr="TND";

	@EJB
	QuotationsServices quotationsServices;

	@PostConstruct
	public void init() {
		setShares(quotationsServices.getQuot());
		System.out.println(shares);
		setSharesConT(quotationsServices.getQuot());
		System.out.println("sharequtT" + sharesConT);
		convertCurr();

	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public String getSelectedCurr() {
		return selectedCurr;
	}

	public void setSelectedCurr(String selectedCurr) {
		this.selectedCurr = selectedCurr;
	}

	public void convertCurr() {
		CurrencyConverter converter = new CurrencyConverter();

		float changeD;
		float changeE;
		float changeP;
		try {
			changeD = converter.sendGet("USD");
		} catch (Exception e) {
			changeD = (1f);
			e.printStackTrace();
		}
		try {
			changeP = converter.sendGet("GBP");
		} catch (Exception e) {
			changeP = (1f);
			e.printStackTrace();
		}
		try {
			changeE = converter.sendGet("EUR");
		} catch (Exception e) {
			changeE = (1f);
			e.printStackTrace();
		}
//		System.out.println(changeD);
//		System.out.println(changeE);
//		System.out.println(changeP);
		for (int i = 0; i < sharesConT.size(); i++) {

			Share share2 = new Share();
			Share share3 = new Share();
			Share share4 = new Share();

			
			
			share2.setClosingPrice(sharesConT.get(i).getClosingPrice()
					* changeD);

			share2.setHighestPrice(sharesConT.get(i).getHighestPrice()
					* changeD);
			share2.setLowestPrice(sharesConT.get(i).getLowestPrice() * changeD);
			share2.setOpningPrice(sharesConT.get(i).getOpningPrice() * changeD);

			share3.setClosingPrice(sharesConT.get(i).getClosingPrice() * changeE);
			share3.setHighestPrice(sharesConT.get(i).getHighestPrice() * changeE);
			share3.setLowestPrice(sharesConT.get(i).getLowestPrice() * changeE);
			share3.setOpningPrice(sharesConT.get(i).getOpningPrice() * changeE);

			share4.setClosingPrice(sharesConT.get(i).getClosingPrice() * changeP);

			share4.setHighestPrice(sharesConT.get(i).getHighestPrice() * changeP);
			share4.setLowestPrice(sharesConT.get(i).getLowestPrice() * changeP);
			share4.setOpningPrice(sharesConT.get(i).getOpningPrice() * changeP);
			
			share4.setCompany(sharesConT.get(i).getCompany());
			share4.setEstimation(sharesConT.get(i).getEstimation());
			share4.setVolume(sharesConT.get(i).getVolume());
			
			share3.setCompany(sharesConT.get(i).getCompany());
			share3.setEstimation(sharesConT.get(i).getEstimation());
			share3.setVolume(sharesConT.get(i).getVolume());
			
			share2.setCompany(sharesConT.get(i).getCompany());
			share2.setEstimation(sharesConT.get(i).getEstimation());
			share2.setVolume(sharesConT.get(i).getVolume());
			
			
			sharesConE.add(share3);
			sharesConD.add(share2);
			sharesConP.add(share4);
		}

//		System.out.println(sharesConT);
//		System.out.println(sharesConD);
//		System.out.println(sharesConE);
//		System.out.println(sharesConP);

	}

	public void setList() {
		if (selectedCurr.equals("TND")) {
			setShares(sharesConT);
			System.out.println("TND");
		} else if (selectedCurr.equals("EUR")) {
			setShares(sharesConE);
			System.out.println("EUR");
		} else if (selectedCurr.equals("USD")) {
			setShares(sharesConD);
			System.out.println("USD");
		} else if (selectedCurr.equals("GBP")) {
			setShares(sharesConP);
			System.out.println("GBP");
		}
		
	}

	public List<Share> getSharesConE() {
		return sharesConE;
	}

	public void setSharesConE(List<Share> sharesConE) {
		this.sharesConE = sharesConE;
	}

	public List<Share> getSharesConD() {
		return sharesConD;
	}

	public void setSharesConD(List<Share> sharesConD) {
		this.sharesConD = sharesConD;
	}

	public List<Share> getSharesConP() {
		return sharesConP;
	}

	public void setSharesConP(List<Share> sharesConP) {
		this.sharesConP = sharesConP;
	}

	public List<Share> getSharesConT() {
		return sharesConT;
	}

	public void setSharesConT(List<Share> sharesConT) {
		this.sharesConT = sharesConT;
	}

}
