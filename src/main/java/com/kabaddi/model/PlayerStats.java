package com.kabaddi.model;

import java.util.List;

public class PlayerStats {
	
	String playerName;
	int playerId;
	String player_on_court;
	String player_raiding_now;
	String player_revival_order;
	
	int high_five;
	int super_ten;
	int matches;
	int matches_raided;
	
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

	public String getPlayer_revival_order() {
		return player_revival_order;
	}

	public void setPlayer_revival_order(String player_revival_order) {
		this.player_revival_order = player_revival_order;
	}

	public int getHigh_five() {
		return high_five;
	}

	public void setHigh_five(int high_five) {
		this.high_five = high_five;
	}

	public int getSuper_ten() {
		return super_ten;
	}

	public void setSuper_ten(int super_ten) {
		this.super_ten = super_ten;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getMatches_raided() {
		return matches_raided;
	}

	public void setMatches_raided(int matches_raided) {
		this.matches_raided = matches_raided;
	}

	@Override
	public String toString() {
		return "PlayerStats [playerName=" + playerName + ", playerId=" + playerId + ", player_on_court="
				+ player_on_court + ", player_raiding_now=" + player_raiding_now + ", raids=" + raids + ", tackles="
				+ tackles + ", do_or_die=" + do_or_die + "]";
	}

}