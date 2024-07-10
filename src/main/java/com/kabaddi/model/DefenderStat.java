package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefenderStat{

	@JsonProperty("DefenderId")
	int defenderId;
	
	@JsonProperty("DefenderName")
	String defenderName;
	
	@JsonProperty("TeamId")
	int teamId;
	
	@JsonProperty("TeamName")
	String teamName;
	
	@JsonProperty("TacklePoints")
	int tacklePoints;
	
	@JsonProperty("TackleSR")
	int tackleSR;
  
public DefenderStat() {
	super();
}

public int getDefenderId() {
	return defenderId;
}

public void setDefenderId(int defenderId) {
	this.defenderId = defenderId;
}

public String getDefenderName() {
	return defenderName;
}

public void setDefenderName(String defenderName) {
	this.defenderName = defenderName;
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

public int getTacklePoints() {
	return tacklePoints;
}

public void setTacklePoints(int tacklePoints) {
	this.tacklePoints = tacklePoints;
}

public int getTackleSR() {
	return tackleSR;
}

public void setTackleSR(int tackleSR) {
	this.tackleSR = tackleSR;
}

@Override
public String toString() {
	return "DefenderStat [defenderId=" + defenderId + ", defenderName=" + defenderName + ", teamId=" + teamId
			+ ", teamName=" + teamName + ", tacklePoints=" + tacklePoints + ", tackleSR=" + tackleSR + "]";
}

}