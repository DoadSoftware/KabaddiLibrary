package com.kabaddi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class PlayByRaids {
	int raid_number;
	int raiding_team_id;
	int raiding_player_id;
	int raid_outcome_id,raid_half;
	List<PlayByTeams> team = new ArrayList<PlayByTeams>();
	
	public int getRaid_number() {
		return raid_number;
	}
	public void setRaid_number(int raid_number) {
		this.raid_number = raid_number;
	}
	public int getRaiding_team_id() {
		return raiding_team_id;
	}
	public void setRaiding_team_id(int raiding_team_id) {
		this.raiding_team_id = raiding_team_id;
	}
	public int getRaiding_player_id() {
		return raiding_player_id;
	}
	public void setRaiding_player_id(int raiding_player_id) {
		this.raiding_player_id = raiding_player_id;
	}
	public int getRaid_outcome_id() {
		return raid_outcome_id;
	}
	public void setRaid_outcome_id(int raid_outcome_id) {
		this.raid_outcome_id = raid_outcome_id;
	}
	public int getRaid_half() {
		return raid_half;
	}
	public void setRaid_half(int raid_half) {
		this.raid_half = raid_half;
	}
	
	public List<PlayByTeams> getTeam() {
		return team;
	}
	public void setTeam(List<PlayByTeams> team) {
		this.team = team;
	}

	public PlayByRaids() {
		super();
		
	}
	
	public PlayByRaids(int raid_number, int raiding_team_id, int raiding_player_id, int raid_outcome_id, int raid_half,
			List<PlayByTeams> team) {
		super();
		this.raid_number = raid_number;
		this.raiding_team_id = raiding_team_id;
		this.raiding_player_id = raiding_player_id;
		this.raid_outcome_id = raid_outcome_id;
		this.raid_half = raid_half;
		this.team = team;
	}
	@Override
	public String toString() {
		return "PlayByRaids [raid_number=" + raid_number + ", raiding_team_id=" + raiding_team_id
				+ ", raiding_player_id=" + raiding_player_id + ", raid_outcome_id=" + raid_outcome_id + ", raid_half="
				+ raid_half + ", team=" + team + "]";
	}
	
}	