package com.kabaddi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Api_pre_match {

	List<TeamPlayerStats> teamPlayerStats;

	public Api_pre_match() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Api_pre_match(List<TeamPlayerStats> teamPlayerStats) {
		super();
		this.teamPlayerStats = teamPlayerStats;
	}

	public List<TeamPlayerStats> getTeamPlayerStats() {
		return teamPlayerStats;
	}

	public void setTeamPlayerStats(List<TeamPlayerStats> teamPlayerStats) {
		this.teamPlayerStats = teamPlayerStats;
	}

	@Override
	public String toString() {
		return "Api_pre_match [teamPlayerStats=" + teamPlayerStats + "]";
	}
	
}