package com.kabaddi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Api_In_match {
	
	List<TeamPlayerStats> teamPlayerStats;
	play_by_play play_by_play;
	Phase_of_play phase_of_play;
	
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
		return "Api_In_match [teamPlayerStats=" + teamPlayerStats + ", play_by_play=" + play_by_play
				+ ", phase_of_play=" + phase_of_play + "]";
	}
	
}