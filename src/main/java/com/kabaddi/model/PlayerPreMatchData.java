package com.kabaddi.model;

public class PlayerPreMatchData {
	
	private Player player;
	
	private int teamId;
	private int playerId;
	private int highFive;
	private int superTen;
	private int matches;
	
	private int totalPoints;
	private int totalRaidPoints;
	private int totalTacklePoints;
	
	private int totalRaids;
	private int superRaids;
	private int successfullRaids;
	private int unsuccessfullRaids;
	
	private int totalTackles;
	private int superTackle;
	private int successfullTackles;
	private int unsuccessfullTackles;
	
	
	public PlayerPreMatchData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PlayerPreMatchData(int teamId,int playerId, Player player, int highFive, int superTen, int matches, int totalPoints,
			int totalRaidPoints, int totalTacklePoints, int totalRaids, int superRaids, int successfullRaids,
			int unsuccessfullRaids, int totalTackles, int superTackle, int successfullTackles,
			int unsuccessfullTackles) {
		super();
		this.teamId = teamId;
		this.playerId = playerId;
		this.player = player;
		this.highFive = highFive;
		this.superTen = superTen;
		this.matches = matches;
		this.totalPoints = totalPoints;
		this.totalRaidPoints = totalRaidPoints;
		this.totalTacklePoints = totalTacklePoints;
		this.totalRaids = totalRaids;
		this.superRaids = superRaids;
		this.successfullRaids = successfullRaids;
		this.unsuccessfullRaids = unsuccessfullRaids;
		this.totalTackles = totalTackles;
		this.superTackle = superTackle;
		this.successfullTackles = successfullTackles;
		this.unsuccessfullTackles = unsuccessfullTackles;
	}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getHighFive() {
		return highFive;
	}
	public void setHighFive(int highFive) {
		this.highFive = highFive;
	}
	public int getSuperTen() {
		return superTen;
	}
	public void setSuperTen(int superTen) {
		this.superTen = superTen;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public int getTotalRaidPoints() {
		return totalRaidPoints;
	}
	public void setTotalRaidPoints(int totalRaidPoints) {
		this.totalRaidPoints = totalRaidPoints;
	}
	public int getTotalTacklePoints() {
		return totalTacklePoints;
	}
	public void setTotalTacklePoints(int totalTacklePoints) {
		this.totalTacklePoints = totalTacklePoints;
	}
	public int getTotalRaids() {
		return totalRaids;
	}
	public void setTotalRaids(int totalRaids) {
		this.totalRaids = totalRaids;
	}
	public int getSuperRaids() {
		return superRaids;
	}
	public void setSuperRaids(int superRaids) {
		this.superRaids = superRaids;
	}
	public int getSuccessfullRaids() {
		return successfullRaids;
	}
	public void setSuccessfullRaids(int successfullRaids) {
		this.successfullRaids = successfullRaids;
	}
	public int getUnsuccessfullRaids() {
		return unsuccessfullRaids;
	}
	public void setUnsuccessfullRaids(int unsuccessfullRaids) {
		this.unsuccessfullRaids = unsuccessfullRaids;
	}
	public int getTotalTackles() {
		return totalTackles;
	}
	public void setTotalTackles(int totalTackles) {
		this.totalTackles = totalTackles;
	}
	public int getSuperTackle() {
		return superTackle;
	}
	public void setSuperTackle(int superTackle) {
		this.superTackle = superTackle;
	}
	public int getSuccessfullTackles() {
		return successfullTackles;
	}
	public void setSuccessfullTackles(int successfullTackles) {
		this.successfullTackles = successfullTackles;
	}
	public int getUnsuccessfullTackles() {
		return unsuccessfullTackles;
	}
	public void setUnsuccessfullTackles(int unsuccessfullTackles) {
		this.unsuccessfullTackles = unsuccessfullTackles;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
