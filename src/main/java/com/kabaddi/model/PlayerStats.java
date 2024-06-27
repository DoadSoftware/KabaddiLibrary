package com.kabaddi.model;

import java.util.List;

public class PlayerStats {
	
	String playerName;
	int playerId;
	String player_on_court;
	String player_raiding_now;
	
	List<Points> points;
	List<Raids> raids;
	List<Tackles> tackles;
	List<Do_Or_Die> do_or_die;
	
	public PlayerStats() {
		super();
	}

	public PlayerStats(String playerName, int playerId, List<Points> points,List<Raids> raids,
			List<Tackles> tackles, List<Do_Or_Die> do_or_die) {
		super();
		this.playerName = playerName;
		this.playerId = playerId;
		this.points = points;
		this.raids = raids;
		this.tackles = tackles;
		this.do_or_die = do_or_die;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayer_on_court() {
		return player_on_court;
	}
	public void setPlayer_on_court(String player_on_court) {
		this.player_on_court = player_on_court;
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

	public String getPlayer_raiding_now() {
		return player_raiding_now;
	}

	public void setPlayer_raiding_now(String player_raiding_now) {
		this.player_raiding_now = player_raiding_now;
	}
	
	public List<Points> getPoints() {
		return points;
	}

	public void setPoints(List<Points> points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "PlayerStats [playerName=" + playerName + ", playerId=" + playerId + ", player_on_court="
				+ player_on_court + ", player_raiding_now=" + player_raiding_now + ", raids=" + raids + ", tackles="
				+ tackles + ", do_or_die=" + do_or_die + "]";
	}

}