package com.kabaddi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phase {
	 String phase_name;
	 ArrayList<PlayByTeams> team =new ArrayList<PlayByTeams>();
	public String getPhase_name() {
		return phase_name;
	}
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
	}
	public ArrayList<PlayByTeams> getTeam() {
		return team;
	}
	public void setTeam(ArrayList<PlayByTeams> team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "Phase [phase_name=" + phase_name + ", team=" + team + "]";
	}
	 
}
