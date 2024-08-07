package com.kabaddi.model;

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
  
  private play_by_play play_by_play;
  private Phase_of_play phase_of_play;
  
  private Clock clock;
  
public Api_Match() {
	super();
	// TODO Auto-generated constructor stub
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

public play_by_play getPlay_by_play() {
	return play_by_play;
}

public void setPlay_by_play(play_by_play play_by_play) {
	this.play_by_play = play_by_play;
}

public Phase_of_play getPhase_of_play() {
	return phase_of_play;
}

public void setPhase_of_play(Phase_of_play phase_of_play) {
	this.phase_of_play = phase_of_play;
}

@Override
public String toString() {
	return "Api_Match [matchFileName=" + matchFileName + ", homeTeamScore=" + homeTeamScore + ", awayTeamScore="
			+ awayTeamScore + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeTeamStats=" + homeTeamStats
			+ ", awayTeamStats=" + awayTeamStats + ", play_by_play=" + play_by_play + ", phase_of_play=" + phase_of_play
			+ ", clock=" + clock + "]";
}

}