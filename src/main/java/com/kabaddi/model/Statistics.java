package com.kabaddi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Statistics")
public class Statistics
{
  @Id
  @Column(name="STATISTICSID")
  private Integer statistics_id;
  
  @Column(name="PLAYERID")
  private Integer player_id;
  
  @Column(name="MATCHES")
  private Integer matches;
  
  @Column(name="Info1")
  private Integer info1;
  
  @Column(name="Info2")
  private Integer info2;
  

public Integer getInfo1() {
	return info1;
}

public void setInfo1(Integer info1) {
	this.info1 = info1;
}

public Integer getInfo2() {
	return info2;
}

public void setInfo2(Integer info2) {
	this.info2 = info2;
}

public Integer getStatistics_id() {
	return statistics_id;
}

public void setStatistics_id(Integer statistics_id) {
	this.statistics_id = statistics_id;
}

public Integer getPlayer_id() {
	return player_id;
}

public void setPlayer_id(Integer player_id) {
	this.player_id = player_id;
}

public Integer getMatches() {
	return matches;
}

public void setMatches(Integer matches) {
	this.matches = matches;
}
  
}