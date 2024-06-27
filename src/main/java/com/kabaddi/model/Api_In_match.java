package com.kabaddi.model;

import java.util.List;

public class Api_In_match {
	
	List<TeamPlayerStats> teamPlayerStats;
	
	public Api_In_match() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Api_In_match(List<TeamPlayerStats> teamPlayerStats) {
		super();
		this.teamPlayerStats = teamPlayerStats;
	}

	public List<TeamPlayerStats> getTeamPlayerStats() {
		return teamPlayerStats;
	}

	public void setTeamPlayerStats(List<TeamPlayerStats> teamPlayerStats) {
		this.teamPlayerStats = teamPlayerStats;
	}
}