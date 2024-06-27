package com.kabaddi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;


@SuppressWarnings("unused")
@Entity
@Table(name = "Medals")
public class Medal
{
  @Id
  @Column(name = "MatchNumber")
  private int matchNumber;

  @Column(name = "MatchFileName")
  private String matchFileName;

  @Column(name = "Team1")
  private int team1;
  
  @Column(name = "Team2")
  private int team2;

  @Column(name = "Team3")
  private int team3;

  public Medal() {
		super();
  }

public int getMatchNumber() {
	return matchNumber;
}

public void setMatchNumber(int matchNumber) {
	this.matchNumber = matchNumber;
}

public String getMatchFileName() {
	return matchFileName;
}

public void setMatchFileName(String matchFileName) {
	this.matchFileName = matchFileName;
}

public int getTeam1() {
	return team1;
}

public void setTeam1(int team1) {
	this.team1 = team1;
}

public int getTeam2() {
	return team2;
}

public void setTeam2(int team2) {
	this.team2 = team2;
}

public int getTeam3() {
	return team3;
}

public void setTeam3(int team3) {
	this.team3 = team3;
}

}