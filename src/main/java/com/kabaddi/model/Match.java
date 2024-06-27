package com.kabaddi.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Match")
@XmlAccessorType(XmlAccessType.FIELD)
public class Match {

  @XmlElement(name = "homeSubstitutesPerTeam")
  private int homeSubstitutesPerTeam;

  @XmlElement(name = "awaySubstitutesPerTeam")
  private int awaySubstitutesPerTeam;
  
  @XmlElement(name = "matchFileTimeStamp")
  private String matchFileTimeStamp;

  @XmlElement(name = "matchResult")
  private String matchResult;

  @XmlElement(name = "matchStatus")
  private String matchStatus;
  
  @XmlElement(name = "matchFileName")
  private String matchFileName;

  @XmlElement(name = "tournament")
  private String tournament;

  @XmlElement(name = "matchIdent")
  private String matchIdent;
  
  @XmlElement(name = "homeTeamId")
  private int homeTeamId;

  @XmlElement(name = "awayTeamId")
  private int awayTeamId;
 
  @XmlElement(name = "homeTeamJerseyColor")
  private String homeTeamJerseyColor;

  @XmlElement(name = "awayTeamJerseyColor")
  private String awayTeamJerseyColor;
  
  @XmlElement(name = "homeTeamGKJerseyColor")
  private String homeTeamGKJerseyColor;

  @XmlElement(name = "awayTeamGKJerseyColor")
  private String awayTeamGKJerseyColor;

  @XmlElement(name = "groundId")
  private int groundId;

  @XmlElement(name = "venueName")
  private String venueName;

  @XmlElement(name = "homeTeamScore")
  private int homeTeamScore;

  @XmlElement(name = "awayTeamScore")
  private int awayTeamScore;

  @XmlTransient
  private String api_photo;
  
  @XmlTransient
  private Ground ground;
  
  @XmlTransient
  private Team homeTeam;

  @XmlTransient
  private Team awayTeam;
  
  @XmlTransient
  private Api_Match Api_Match;

  @XmlElementWrapper(name = "homeSquad")
  @XmlElement(name = "homeSquad")
  private List<Player> homeSquad;

  @XmlElementWrapper(name = "homeSubstitutes")
  @XmlElement(name = "homeSubstitutes")
  private List<Player> homeSubstitutes;
  
  @XmlElementWrapper(name = "awaySquad")
  @XmlElement(name = "awaySquad")
  private List<Player> awaySquad;

  @XmlElementWrapper(name = "awaySubstitutes")
  @XmlElement(name = "awaySubstitutes")
  private List<Player> awaySubstitutes;
  
  @XmlElementWrapper(name = "homeOtherSquad")
  @XmlElement(name = "homeOtherSquad")
  private List<Player> homeOtherSquad;

  @XmlElementWrapper(name = "awayOtherSquad")
  @XmlElement(name = "awayOtherSquad")
  private List<Player> awayOtherSquad;

  @XmlElementWrapper(name = "matchStats")
  @XmlElement(name = "matchStat")
  private List<MatchStats> matchStats;
  
  @XmlElement(name = "clock")
  private Clock clock;

  @XmlTransient
  private List<Event> events;

public Clock getClock() {
	return clock;
}

public void setClock(Clock clock) {
	this.clock = clock;
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

public List<MatchStats> getMatchStats() {
	return matchStats;
}

public void setMatchStats(List<MatchStats> matchStats) {
	this.matchStats = matchStats;
}

public int getHomeSubstitutesPerTeam() {
	return homeSubstitutesPerTeam;
}

public void setHomeSubstitutesPerTeam(int homeSubstitutesPerTeam) {
	this.homeSubstitutesPerTeam = homeSubstitutesPerTeam;
}

public int getAwaySubstitutesPerTeam() {
	return awaySubstitutesPerTeam;
}

public void setAwaySubstitutesPerTeam(int awaySubstitutesPerTeam) {
	this.awaySubstitutesPerTeam = awaySubstitutesPerTeam;
}

public String getMatchFileTimeStamp() {
	return matchFileTimeStamp;
}

public void setMatchFileTimeStamp(String matchFileTimeStamp) {
	this.matchFileTimeStamp = matchFileTimeStamp;
}

public String getMatchResult() {
	return matchResult;
}

public void setMatchResult(String matchResult) {
	this.matchResult = matchResult;
}

public String getMatchStatus() {
	return matchStatus;
}

public void setMatchStatus(String matchStatus) {
	this.matchStatus = matchStatus;
}

public String getMatchFileName() {
	return matchFileName;
}

public void setMatchFileName(String matchFileName) {
	this.matchFileName = matchFileName;
}

public String getTournament() {
	return tournament;
}

public void setTournament(String tournament) {
	this.tournament = tournament;
}

public String getMatchIdent() {
	return matchIdent;
}

public void setMatchIdent(String matchIdent) {
	this.matchIdent = matchIdent;
}

public int getHomeTeamId() {
	return homeTeamId;
}

public void setHomeTeamId(int homeTeamId) {
	this.homeTeamId = homeTeamId;
}

public int getAwayTeamId() {
	return awayTeamId;
}

public void setAwayTeamId(int awayTeamId) {
	this.awayTeamId = awayTeamId;
}

public int getGroundId() {
	return groundId;
}

public void setGroundId(int groundId) {
	this.groundId = groundId;
}

public String getVenueName() {
	return venueName;
}

public void setVenueName(String venueName) {
	this.venueName = venueName;
}

public Ground getGround() {
	return ground;
}

public void setGround(Ground ground) {
	this.ground = ground;
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

public List<Player> getHomeSquad() {
	return homeSquad;
}

public void setHomeSquad(List<Player> homeSquad) {
	this.homeSquad = homeSquad;
}

public List<Player> getHomeSubstitutes() {
	return homeSubstitutes;
}

public void setHomeSubstitutes(List<Player> homeSubstitutes) {
	this.homeSubstitutes = homeSubstitutes;
}

public List<Player> getAwaySquad() {
	return awaySquad;
}

public void setAwaySquad(List<Player> awaySquad) {
	this.awaySquad = awaySquad;
}

public List<Player> getAwaySubstitutes() {
	return awaySubstitutes;
}

public void setAwaySubstitutes(List<Player> awaySubstitutes) {
	this.awaySubstitutes = awaySubstitutes;
}

public List<Player> getHomeOtherSquad() {
	return homeOtherSquad;
}

public void setHomeOtherSquad(List<Player> homeOtherSquad) {
	this.homeOtherSquad = homeOtherSquad;
}

public List<Player> getAwayOtherSquad() {
	return awayOtherSquad;
}

public void setAwayOtherSquad(List<Player> awayOtherSquad) {
	this.awayOtherSquad = awayOtherSquad;
}

public List<Event> getEvents() {
	return events;
}

public void setEvents(List<Event> events) {
	this.events = events;
}

public String getHomeTeamJerseyColor() {
	return homeTeamJerseyColor;
}

public void setHomeTeamJerseyColor(String homeTeamJerseyColor) {
	this.homeTeamJerseyColor = homeTeamJerseyColor;
}

public String getAwayTeamJerseyColor() {
	return awayTeamJerseyColor;
}

public void setAwayTeamJerseyColor(String awayTeamJerseyColor) {
	this.awayTeamJerseyColor = awayTeamJerseyColor;
}

public String getApi_photo() {
	return api_photo;
}

public void setApi_photo(String api_photo) {
	this.api_photo = api_photo;
}

public String getHomeTeamGKJerseyColor() {
	return homeTeamGKJerseyColor;
}

public void setHomeTeamGKJerseyColor(String homeTeamGKJerseyColor) {
	this.homeTeamGKJerseyColor = homeTeamGKJerseyColor;
}

public String getAwayTeamGKJerseyColor() {
	return awayTeamGKJerseyColor;
}

public void setAwayTeamGKJerseyColor(String awayTeamGKJerseyColor) {
	this.awayTeamGKJerseyColor = awayTeamGKJerseyColor;
}

public Api_Match getApi_Match() {
	return Api_Match;
}

public void setApi_Match(Api_Match api_Match) {
	Api_Match = api_Match;
}


}