package com.kabaddi.model;

import javax.xml.bind.annotation.XmlAccessType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchStats {

  @JsonProperty("statsId")
  private int statsId;

  @JsonProperty("playerId")
  private int playerId;
  
  @JsonProperty("matchHalves")
  private String matchHalves;
  
  @JsonProperty("statsType")
  private String stats_type;

  @JsonProperty("statsCount")
  private int statsCount;

  @JsonProperty("totalMatchMilliSeconds")
  private long totalMatchMilliSeconds;
  
  @JsonProperty("TeamId")
  private int TeamId;
  
  @JsonProperty("offplayerId")
  private int offplayerId;
  
  @JsonProperty("onplayerId")
  private int onplayerId;
  
  @JsonProperty("raiderIn")
  private String raiderIn;
  
  @JsonProperty("home_point")
  private int home_point;
  
  @JsonProperty("away_point")
  private int away_point;

  @JsonIgnore
  private Player player;
  
public MatchStats() {
	super();
}

//public MatchStats(int statsId, int playerId, String matchHalves, String stats_type, int statsCount,
//		long totalMatchSeconds,int TeamId,String raiderIn) {
//	super();
//	this.statsId = statsId;
//	this.playerId = playerId;
//	this.matchHalves = matchHalves;
//	this.stats_type = stats_type;
//	this.statsCount = statsCount;
//	this.totalMatchSeconds = totalMatchSeconds;
//	this.TeamId = TeamId;
//	this.raiderIn = raiderIn;
//}

public MatchStats(int statsId,int playerId,String matchHalves, String stats_type,int statsCount,long totalMatchMilliSeconds,
		int offplayerId,int onplayerId,int TeamId,int home_point,int away_point,String raiderIn) {
	super();
	this.statsId = statsId;
	this.playerId = playerId;
	this.matchHalves = matchHalves;
	this.stats_type = stats_type;
	this.statsCount = statsCount;
	this.totalMatchMilliSeconds = totalMatchMilliSeconds;
	this.TeamId = TeamId;
	this.offplayerId = offplayerId;
	this.onplayerId = onplayerId;
	this.home_point = home_point;
	this.away_point = away_point;
	this.raiderIn = raiderIn;
}

public Player getPlayer() {
	return player;
}

public void setPlayer(Player player) {
	this.player = player;
}

public int getPlayerId() {
	return playerId;
}

public void setPlayerId(int playerId) {
	this.playerId = playerId;
}

public long getTotalMatchMilliSeconds() {
	return totalMatchMilliSeconds;
}

public void setTotalMatchMilliSeconds(long totalMatchMilliSeconds) {
	this.totalMatchMilliSeconds = totalMatchMilliSeconds;
}

public int getStatsId() {
	return statsId;
}

public void setStatsId(int statsId) {
	this.statsId = statsId;
}

public String getStats_type() {
	return stats_type;
}

public void setStats_type(String stats_type) {
	this.stats_type = stats_type;
}

public int getStatsCount() {
	return statsCount;
}

public void setStatsCount(int statsCount) {
	this.statsCount = statsCount;
}

public String getMatchHalves() {
	return matchHalves;
}

public void setMatchHalves(String matchHalves) {
	this.matchHalves = matchHalves;
}

public int getTeamId() {
	return TeamId;
}

public void setTeamId(int teamId) {
	TeamId = teamId;
}

public int getOffplayerId() {
	return offplayerId;
}

public void setOffplayerId(int offplayerId) {
	this.offplayerId = offplayerId;
}

public int getOnplayerId() {
	return onplayerId;
}

public void setOnplayerId(int onplayerId) {
	this.onplayerId = onplayerId;
}

public String getRaiderIn() {
	return raiderIn;
}

public void setRaiderIn(String raiderIn) {
	this.raiderIn = raiderIn;
}

public int getHome_point() {
	return home_point;
}

public void setHome_point(int home_point) {
	this.home_point = home_point;
}

public int getAway_point() {
	return away_point;
}

public void setAway_point(int away_point) {
	this.away_point = away_point;
}

}