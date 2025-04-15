package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Do_Or_Die {
	
	int totalRaids;
	int successfullRaids;
	int failedRaids;
	int superRaids;
	int raidPoints;
	int touchPoints;
	int bonusPoints;
	
	public Do_Or_Die() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Do_Or_Die(int totalRaids, int successfullRaids, int failedRaids, int superRaids, int raidPoints,
			int touchPoints, int bonusPoints) {
		super();
		this.totalRaids = totalRaids;
		this.successfullRaids = successfullRaids;
		this.failedRaids = failedRaids;
		this.superRaids = superRaids;
		this.raidPoints = raidPoints;
		this.touchPoints = touchPoints;
		this.bonusPoints = bonusPoints;
	}

	public Do_Or_Die(int totalRaids) {
		super();
		this.totalRaids = totalRaids;
	}
	public int getTotalRaids() {
		return totalRaids;
	}
	public void setTotalRaids(int totalRaids) {
		this.totalRaids = totalRaids;
	}
	public int getSuccessfullRaids() {
		return successfullRaids;
	}
	public void setSuccessfullRaids(int successfullRaids) {
		this.successfullRaids = successfullRaids;
	}
	public int getFailedRaids() {
		return failedRaids;
	}
	public void setFailedRaids(int failedRaids) {
		this.failedRaids = failedRaids;
	}
	public int getSuperRaids() {
		return superRaids;
	}
	public void setSuperRaids(int superRaids) {
		this.superRaids = superRaids;
	}
	public int getRaidPoints() {
		return raidPoints;
	}
	public void setRaidPoints(int raidPoints) {
		this.raidPoints = raidPoints;
	}
	public int getTouchPoints() {
		return touchPoints;
	}
	public void setTouchPoints(int touchPoints) {
		this.touchPoints = touchPoints;
	}
	public int getBonusPoints() {
		return bonusPoints;
	}
	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
	@Override
	public String toString() {
		return "Do_Or_Die [totalRaids=" + totalRaids + ", successfullRaids=" + successfullRaids + ", failedRaids="
				+ failedRaids + ", superRaids=" + superRaids + ", raidPoints=" + raidPoints + ", touchPoints="
				+ touchPoints + ", bonusPoints=" + bonusPoints + "]";
	}

}