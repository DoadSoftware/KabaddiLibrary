package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsNminutes {
	
	int five;
	int ten;
	
	public PointsNminutes() {
		super();
	}

	public int getFive() {
		return five;
	}
	public void setFive(int five) {
		this.five = five;
	}

	public int getTen() {
		return ten;
	}
	public void setTen(int ten) {
		this.ten = ten;
	}

}