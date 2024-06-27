package com.kabaddi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event implements Comparable<Event> {

  @XmlElement(name = "eventNumber")
  private int eventNumber;

  @XmlElement(name = "eventPlayerId")
  private int eventPlayerId;
  
  @XmlElement(name = "eventMatchHalves")
  private String eventMatchHalves;
  
  @XmlElement(name = "statsId")
  private int statsId;

  @XmlElement(name = "eventLog")
  private String eventLog;
  
  @XmlElement(name = "eventType")
  private String eventType;
  
  @XmlElement(name = "offPlayerId")
  private int offPlayerId;
  
  @XmlElement(name = "onPlayerId")
  private int onPlayerId;

  @XmlElement(name = "eventScore")
  private int eventScore;
  
  @XmlElement(name = "eventTeamId")
  private int eventTeamId;
  
  @XmlElement(name = "BonusPoint")
  private String BonusPoint;
  
  @XmlElement(name = "SuperTackelPoint")
  private String SuperTackelPoint;
  
  @XmlElement(name = "SuperRaidPoint")
  private String SuperRaidPoint;
  
  @XmlElement(name = "AllOutPoint")
  private String AllOutPoint;
  
  @XmlElement(name = "TouchesPoint")
  private int TouchesPoint;
  
  @XmlElement(name = "totalHomePoints")
  private int totalHomePoints;
  
  @XmlElement(name = "totalAwayPoints")
  private int totalAwayPoints;
  
public Event() {
	super();
}

public Event(int eventNumber, int eventPlayerId, String eventMatchHalves, int statsId, String eventLog,
		String eventType, int offPlayerId, int onPlayerId, int eventScore,int eventTeamId) {
	super();
	this.eventNumber = eventNumber;
	this.eventPlayerId = eventPlayerId;
	this.eventMatchHalves = eventMatchHalves;
	this.statsId = statsId;
	this.eventLog = eventLog;
	this.eventType = eventType;
	this.offPlayerId = offPlayerId;
	this.onPlayerId = onPlayerId;
	this.eventScore = eventScore;
	this.eventTeamId = eventTeamId;
}

public Event(int eventNumber, int eventTeamId, String eventMatchHalves, String BonusPoint, String SuperTackelPoint,
		String SuperRaidPoint,String AllOutPoint,int TouchesPoint,String eventLog,String eventType, int totalHomePoints,int totalAwayPoints) {
	super();
	this.eventNumber = eventNumber;
	this.eventTeamId = eventTeamId;
	this.eventMatchHalves = eventMatchHalves;
	this.BonusPoint = BonusPoint;
	this.SuperTackelPoint = SuperTackelPoint;
	this.eventLog = eventLog;
	this.eventType = eventType;
	this.SuperRaidPoint = SuperRaidPoint;
	this.AllOutPoint = AllOutPoint;
	this.TouchesPoint = TouchesPoint;
	this.totalHomePoints = totalHomePoints;
	this.totalAwayPoints = totalAwayPoints;
}


public int getEventScore() {
	return eventScore;
}

public void setEventScore(int eventScore) {
	this.eventScore = eventScore;
}


public String getEventLog() {
	return eventLog;
}

public void setEventLog(String eventLog) {
	this.eventLog = eventLog;
}

public int getEventNumber() {
	return eventNumber;
}

public void setEventNumber(int eventNumber) {
	this.eventNumber = eventNumber;
}

public int getEventPlayerId() {
	return eventPlayerId;
}

public void setEventPlayerId(int eventPlayerId) {
	this.eventPlayerId = eventPlayerId;
}

public String getEventType() {
	return eventType;
}

public void setEventType(String eventType) {
	this.eventType = eventType;
}

public String getEventMatchHalves() {
	return eventMatchHalves;
}

public void setEventMatchHalves(String eventMatchHalves) {
	this.eventMatchHalves = eventMatchHalves;
}

public int getStatsId() {
	return statsId;
}

public void setStatsId(int statsId) {
	this.statsId = statsId;
}

public int getOffPlayerId() {
	return offPlayerId;
}

public void setOffPlayerId(int offPlayerId) {
	this.offPlayerId = offPlayerId;
}

public int getOnPlayerId() {
	return onPlayerId;
}

public void setOnPlayerId(int onPlayerId) {
	this.onPlayerId = onPlayerId;
}

public int getEventTeamId() {
	return eventTeamId;
}

public void setEventTeamId(int eventTeamId) {
	this.eventTeamId = eventTeamId;
}

public String getBonusPoint() {
	return BonusPoint;
}

public void setBonusPoint(String bonusPoint) {
	BonusPoint = bonusPoint;
}

public String getSuperTackelPoint() {
	return SuperTackelPoint;
}

public void setSuperTackelPoint(String superTackelPoint) {
	SuperTackelPoint = superTackelPoint;
}

public String getSuperRaidPoint() {
	return SuperRaidPoint;
}

public void setSuperRaidPoint(String superRaidPoint) {
	SuperRaidPoint = superRaidPoint;
}

public String getAllOutPoint() {
	return AllOutPoint;
}

public void setAllOutPoint(String allOutPoint) {
	AllOutPoint = allOutPoint;
}

public int getTouchesPoint() {
	return TouchesPoint;
}

public void setTouchesPoint(int touchesPoint) {
	TouchesPoint = touchesPoint;
}

public int getTotalHomePoints() {
	return totalHomePoints;
}

public void setTotalHomePoints(int totalHomePoints) {
	this.totalHomePoints = totalHomePoints;
}

public int getTotalAwayPoints() {
	return totalAwayPoints;
}

public void setTotalAwayPoints(int totalAwayPoints) {
	this.totalAwayPoints = totalAwayPoints;
}

@Override
public int compareTo(Event evnt) {
	return (int) (this.getEventNumber()-evnt.getEventNumber());
}

}