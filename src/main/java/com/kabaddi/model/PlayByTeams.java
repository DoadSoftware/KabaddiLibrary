package com.kabaddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PlayByTeams {
	int team_id,total_points;
    String team_name;
	Points points;
	points_last_n_minutes points_last_n_minutes;
	Raids raids;
	Tackles tackles;
	Do_Or_Die do_or_die;
	
	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getTotal_points() {
		return total_points;
	}

	public void setTotal_points(int total_points) {
		this.total_points = total_points;
	}

	public PlayByTeams() {
		super();
	}

	public Points getPoints() {
		return points;
	}

	public void setPoints(Points points) {
		this.points = points;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public points_last_n_minutes getPoints_last_n_minutes() {
		return points_last_n_minutes;
	}

	public void setPoints_last_n_minutes(points_last_n_minutes points_last_n_minutes) {
		this.points_last_n_minutes = points_last_n_minutes;
	}

	public Raids getRaids() {
		return raids;
	}

	public void setRaids(Raids raids) {
		this.raids = raids;
	}

	public Tackles getTackles() {
		return tackles;
	}

	public void setTackles(Tackles tackles) {
		this.tackles = tackles;
	}

	public Do_Or_Die getDo_or_die() {
		return do_or_die;
	}

	public void setDo_or_die(Do_Or_Die do_or_die) {
		this.do_or_die = do_or_die;
	}

	@Override
	public String toString() {
		return "PlayByTeams [team_id=" + team_id + ", total_points=" + total_points + ", team_name=" + team_name
				+ ", points=" + points + ", points_last_n_minutes=" + points_last_n_minutes + ", raids=" + raids
				+ ", tackles=" + tackles + ", do_or_die=" + do_or_die + "]";
	}
}
