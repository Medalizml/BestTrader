package entity;

import tn.esprit.Blues.entities.Share;

public class sellAction {
	
	Share share;
	Float opVolume;
	public Share getShare() {
		return share;
	}
	public void setShare(Share share) {
		this.share = share;
	}
	public Float getOpVolume() {
		return opVolume;
	}
	public void setOpVolume(Float opVolume) {
		this.opVolume = opVolume;
	}
	public sellAction(Share share, Float opVolume) {
		super();
		this.share = share;
		this.opVolume = opVolume;
	}
	public sellAction() {
		super();
	}
	
	
	
	

}
