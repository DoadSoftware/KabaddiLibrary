package com.kabaddi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerInfo{

	@JsonProperty("RaiderStat")
	List<RaiderStat> raiderStat;
	
	@JsonProperty("DefenderStat")
	List<DefenderStat> defenderStat;
  
public PlayerInfo() {
	super();
}

public List<RaiderStat> getRaiderStat() {
	return raiderStat;
}

public void setRaiderStat(List<RaiderStat> raiderStat) {
	this.raiderStat = raiderStat;
}

public List<DefenderStat> getDefenderStat() {
	return defenderStat;
}

public void setDefenderStat(List<DefenderStat> defenderStat) {
	this.defenderStat = defenderStat;
}

@Override
public String toString() {
	return "PlayerInfo [raiderStat=" + raiderStat + ", defenderStat=" + defenderStat + "]";
}

}