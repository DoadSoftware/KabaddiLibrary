package com.kabaddi.model;

import java.util.List;

public class TeamPlayerStats {
	
	String teamName;
	int teamId;
	int no_of_players_on_court;
	
	int matches;
	int won;
	int lost;
	int tied;
	
	List<Points> points;
	List<Raids> raids;
	List<Tackles> tackles;
	List<Do_Or_Die> do_or_die;
	List<PlayerStats> playerStats;

	public TeamPlayerStats() {
		super();
	}

	public TeamPlayerStats(String teamName, int teamId, int no_of_players_on_court, List<Points> points,
			List<Raids> raids, List<Tackles> tackles, List<Do_Or_Die> do_or_die, List<PlayerStats> playerStats) {
		super();
		this.teamName = teamName;
		this.teamId = teamId;
		this.no_of_players_on_court = no_of_players_on_court;
		this.points = points;
		this.raids = raids;
		this.tackles = tackles;
		this.do_or_die = do_or_die;
		this.playerStats = playerStats;
	}
	
	public TeamPlayerStats(String teamName, int teamId, int matches, int won, int lost, int tied, List<Points> points,
			List<Raids> raids, List<Tackles> tackles, List<Do_Or_Die> do_or_die, List<PlayerStats> playerStats) {
		super();
		this.teamName = teamName;
		this.teamId = teamId;
		this.matches = matches;
		this.won = won;
		this.lost = lost;
		this.tied = tied;
		this.points = points;
		this.raids = raids;
		this.tackles = tackles;
		this.do_or_die = do_or_die;
		this.playerStats = playerStats;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getNo_of_players_on_court() {
		return no_of_players_on_court;
	}

	public void setNo_of_players_on_court(int no_of_players_on_court) {
		this.no_of_players_on_court = no_of_players_on_court;
	}

	public List<Points> getPoints() {
		return points;
	}

	public void setPoints(List<Points> points) {
		this.points = points;
	}

	public List<Raids> getRaids() {
		return raids;
	}

	public void setRaids(List<Raids> raids) {
		this.raids = raids;
	}

	public List<Tackles> getTackles() {
		return tackles;
	}

	public void setTackles(List<Tackles> tackles) {
		this.tackles = tackles;
	}

	public List<Do_Or_Die> getDo_or_die() {
		return do_or_die;
	}

	public void setDo_or_die(List<Do_Or_Die> do_or_die) {
		this.do_or_die = do_or_die;
	}

	public List<PlayerStats> getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(List<PlayerStats> playerStats) {
		this.playerStats = playerStats;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getTied() {
		return tied;
	}

	public void setTied(int tied) {
		this.tied = tied;
	}

	@Override
	public String toString() {
		return "TeamPlayerStats [teamName=" + teamName + ", teamId=" + teamId + ", no_of_players_on_court="
				+ no_of_players_on_court + ", points=" + points + ", raids=" + raids + ", tackles=" + tackles
				+ ", do_or_die=" + do_or_die + ", playerStats=" + playerStats + "]";
	}
	
}