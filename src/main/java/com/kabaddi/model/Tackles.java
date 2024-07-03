package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tackles {
	
	int totalTackles;
	int superTackles;
	int successfulTackles;
	int unsuccessfulTackles;
	
	public Tackles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tackles(int totalTackles, int superTackles) {
		super();
		this.totalTackles = totalTackles;
		this.superTackles = superTackles;
	}
	
	public int getTotalTackles() {
		return totalTackles;
	}
	public void setTotalTackles(int totalTackles) {
		this.totalTackles = totalTackles;
	}
	public int getSuperTackles() {
		return superTackles;
	}
	public void setSuperTackles(int superTackles) {
		this.superTackles = superTackles;
	}
	public int getSuccessfulTackles() {
		return successfulTackles;
	}
	public void setSuccessfulTackles(int successfulTackles) {
		this.successfulTackles = successfulTackles;
	}
	public int getUnsuccessfulTackles() {
		return unsuccessfulTackles;
	}
	public void setUnsuccessfulTackles(int unsuccessfulTackles) {
		this.unsuccessfulTackles = unsuccessfulTackles;
	}

	@Override
	public String toString() {
		return "Tackles [totalTackles=" + totalTackles + ", superTackles=" + superTackles + ", successfulTackles="
				+ successfulTackles + ", unsuccessfulTackles=" + unsuccessfulTackles + "]";
	}
	
	
}