package com.kabaddi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Api_Match{

  private String matchFileName;
  private int homeTeamScore;
  private int awayTeamScore;
  
  private Team homeTeam;
  private Team awayTeam;
  
  private TeamPlayerStats homeTeamStats;
  private TeamPlayerStats awayTeamStats;
  
  private List<PlayByRaids> playByRaids;
  private List<Phase> phase;
  
//  private Phase_of_play phase_of_play;
  
  private Clock clock;
  
public List<PlayByRaids> getPlayByRaids() {
	return playByRaids;
}

public void setPlayByRaids(List<PlayByRaids> playByRaids) {
	this.playByRaids = playByRaids;
}

public List<Phase> getPhase() {
	return phase;
}

public void setPhase(List<Phase> phase) {
	this.phase = phase;
}

public String getMatchFileName() {
	return matchFileName;
}

public void setMatchFileName(String matchFileName) {
	this.matchFileName = matchFileName;
}

public int getHomeTeamScore() {
	return homeTeamScore;
}
public void setHomeTeamScore(int homeTeamScore) {
	this.homeTeamScore = homeTeamScore;
}
public int getAwayTeamScore() {
	return awayTeamScore;
}
public void setAwayTeamScore(int awayTeamScore) {
	this.awayTeamScore = awayTeamScore;
}
public Team getHomeTeam() {
	return homeTeam;
}
public void setHomeTeam(Team homeTeam) {
	this.homeTeam = homeTeam;
}
public Team getAwayTeam() {
	return awayTeam;
}
public void setAwayTeam(Team awayTeam) {
	this.awayTeam = awayTeam;
}
public TeamPlayerStats getHomeTeamStats() {
	return homeTeamStats;
}
public void setHomeTeamStats(TeamPlayerStats homeTeamStats) {
	this.homeTeamStats = homeTeamStats;
}
public TeamPlayerStats getAwayTeamStats() {
	return awayTeamStats;
}
public void setAwayTeamStats(TeamPlayerStats awayTeamStats) {
	this.awayTeamStats = awayTeamStats;
}
public Clock getClock() {
	return clock;
}
public void setClock(Clock clock) {
	this.clock = clock;
}

@Override
public String toString() {
	return "Api_Match [matchFileName=" + matchFileName + ", homeTeamScore=" + homeTeamScore + ", awayTeamScore="
			+ awayTeamScore + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeTeamStats=" + homeTeamStats
			+ ", awayTeamStats=" + awayTeamStats + ", playByRaids=" + playByRaids + ", phase=" + phase + ", clock="
			+ clock + "]";
}

}