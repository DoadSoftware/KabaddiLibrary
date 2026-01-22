package com.kabaddi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Api_In_match {
	
	List<TeamPlayerStats> teamPlayerStats;
	//DELETEplay_by_play play_by_play;
	//Phase_of_play phase_of_play;
	
	public Api_In_match() {
		super();
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

	@Override
	public String toString() {
		return "Api_In_match [teamPlayerStats=" + teamPlayerStats + "]";
	}

}