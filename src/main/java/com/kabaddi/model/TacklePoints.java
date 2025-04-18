package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TacklePoints {
	
	int totalTacklePoints;
	int capturePoints;
	int tackleBounsPoints;
	
	public TacklePoints() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TacklePoints(int totalTacklePoints) {
		super();
		this.totalTacklePoints = totalTacklePoints;
	}
	public int getTotalTacklePoints() {
		return totalTacklePoints;
	}
	public void setTotalTacklePoints(int totalTacklePoints) {
		this.totalTacklePoints = totalTacklePoints;
	}
	public int getCapturePoints() {
		return capturePoints;
	}
	public void setCapturePoints(int capturePoints) {
		this.capturePoints = capturePoints;
	}
	public int getTackleBounsPoints() {
		return tackleBounsPoints;
	}
	public void setTackleBounsPoints(int tackleBounsPoints) {
		this.tackleBounsPoints = tackleBounsPoints;
	}
	
	public TacklePoints(int totalTacklePoints, int capturePoints, int tackleBounsPoints) {
		super();
		this.totalTacklePoints = totalTacklePoints;
		this.capturePoints = capturePoints;
		this.tackleBounsPoints = tackleBounsPoints;
	}
	@Override
	public String toString() {
		return "TacklePoints [totalTacklePoints=" + totalTacklePoints + ", capturePoints=" + capturePoints
				+ ", tackleBounsPoints=" + tackleBounsPoints + "]";
	}
}