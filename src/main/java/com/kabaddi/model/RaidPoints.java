package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RaidPoints {
	
	int totalRaidPoints;
	int touchPoints;
	int raidBounsPoints;
	
	public RaidPoints() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RaidPoints(int totalRaidPoints) {
		super();
		this.totalRaidPoints = totalRaidPoints;
	}
	public int getTotalRaidPoints() {
		return totalRaidPoints;
	}
	public void setTotalRaidPoints(int totalRaidPoints) {
		this.totalRaidPoints = totalRaidPoints;
	}
	public int getTouchPoints() {
		return touchPoints;
	}
	public void setTouchPoints(int touchPoints) {
		this.touchPoints = touchPoints;
	}
	public int getRaidBounsPoints() {
		return raidBounsPoints;
	}
	public void setRaidBounsPoints(int raidBounsPoints) {
		this.raidBounsPoints = raidBounsPoints;
	}
	
	public RaidPoints(int totalRaidPoints, int touchPoints, int raidBounsPoints) {
		super();
		this.totalRaidPoints = totalRaidPoints;
		this.touchPoints = touchPoints;
		this.raidBounsPoints = raidBounsPoints;
	}
	@Override
	public String toString() {
		return "RaidPoints [totalRaidPoints=" + totalRaidPoints + ", touchPoints=" + touchPoints + ", raidBounsPoints="
				+ raidBounsPoints + "]";
	}

}