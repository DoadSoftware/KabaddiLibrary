package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clock {

  private String matchHalves;
  private String matchTimeStatus;
  private long matchTotalMilliSeconds;

public String getMatchHalves() {
	return matchHalves;
}

public void setMatchHalves(String matchHalves) {
	this.matchHalves = matchHalves;
}

public String getMatchTimeStatus() {
	return matchTimeStatus;
}

public void setMatchTimeStatus(String matchTimeStatus) {
	this.matchTimeStatus = matchTimeStatus;
}

public long getMatchTotalMilliSeconds() {
	return matchTotalMilliSeconds;
}

public void setMatchTotalMilliSeconds(long matchTotalMilliSeconds) {
	this.matchTotalMilliSeconds = matchTotalMilliSeconds;
}

public Clock() {
	super();
	this.matchHalves = "";
	this.matchTimeStatus = "";
	this.matchTotalMilliSeconds = 0;
}

}