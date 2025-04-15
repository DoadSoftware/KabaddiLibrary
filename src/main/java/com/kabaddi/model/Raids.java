package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Raids {
	
	int totalRaids;
	int superRaids;
	int successfulRaids;
	int unsuccessfulRaids;
	int emptyRaids;
	
	public Raids() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Raids(int totalRaids, int superRaids) {
		super();
		this.totalRaids = totalRaids;
		this.superRaids = superRaids;
	}
	
	public Raids(int totalRaids, int superRaids, int successfulRaids, int unsuccessfulRaids, int emptyRaids) {
		super();
		this.totalRaids = totalRaids;
		this.superRaids = superRaids;
		this.successfulRaids = successfulRaids;
		this.unsuccessfulRaids = unsuccessfulRaids;
		this.emptyRaids = emptyRaids;
	}

	public int getTotalRaids() {
		return totalRaids;
	}
	public void setTotalRaids(int totalRaids) {
		this.totalRaids = totalRaids;
	}
	public int getSuperRaids() {
		return superRaids;
	}
	public void setSuperRaids(int superRaids) {
		this.superRaids = superRaids;
	}
	public int getSuccessfulRaids() {
		return successfulRaids;
	}
	public void setSuccessfulRaids(int successfulRaids) {
		this.successfulRaids = successfulRaids;
	}
	public int getUnsuccessfulRaids() {
		return unsuccessfulRaids;
	}
	public void setUnsuccessfulRaids(int unsuccessfulRaids) {
		this.unsuccessfulRaids = unsuccessfulRaids;
	}
	public int getEmptyRaids() {
		return emptyRaids;
	}
	public void setEmptyRaids(int emptyRaids) {
		this.emptyRaids = emptyRaids;
	}

	@Override
	public String toString() {
		return "Raids [totalRaids=" + totalRaids + ", superRaids=" + superRaids + ", successfulRaids=" + successfulRaids
				+ ", unsuccessfulRaids=" + unsuccessfulRaids + ", emptyRaids=" + emptyRaids + "]";
	}
	
}