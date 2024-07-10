package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RaiderStat{

	@JsonProperty("RaiderId")
	int raiderId;
	
	@JsonProperty("RaiderName")
	String raiderName;
  
	@JsonProperty("TeamId")
	int teamId;
  
	@JsonProperty("TeamName")
	String teamName;
  
	@JsonProperty("RaidPoints")
	int raidPoints;
  
	@JsonProperty("RaidSR")
	int raidSR;
  
public RaiderStat() {
	super();
}

public int getRaiderId() {
	return raiderId;
}

public void setRaiderId(int raiderId) {
	this.raiderId = raiderId;
}

public String getRaiderName() {
	return raiderName;
}

public void setRaiderName(String raiderName) {
	this.raiderName = raiderName;
}

public int getTeamId() {
	return teamId;
}

public void setTeamId(int teamId) {
	this.teamId = teamId;
}

public String getTeamName() {
	return teamName;
}

public void setTeamName(String teamName) {
	this.teamName = teamName;
}

public int getRaidPoints() {
	return raidPoints;
}

public void setRaidPoints(int raidPoints) {
	this.raidPoints = raidPoints;
}

public int getRaidSR() {
	return raidSR;
}

public void setRaidSR(int raidSR) {
	this.raidSR = raidSR;
}

@Override
public String toString() {
	return "RaiderStat [raiderId=" + raiderId + ", raiderName=" + raiderName + ", teamId=" + teamId + ", teamName="
			+ teamName + ", raidPoints=" + raidPoints + ", raidSR=" + raidSR + "]";
}

}