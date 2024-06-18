package com.kabaddi.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown  = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class teamData {
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("side")
	private String side; 
	
	@JsonProperty("id")	
	private String id;
	
	@JsonProperty("stat")
	private List<Stat> stat;
	  
	@JsonProperty("player")	
	private List<TeamPlayerRanking> player;

	public teamData() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Stat> getStat() {
		return stat;
	}

	public void setStat(List<Stat> stat) {
		this.stat = stat;
	}

	public List<TeamPlayerRanking> getPlayer() {
		return player;
	}

	public void setPlayer(List<TeamPlayerRanking> player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "teamData [name=" + name + ", side=" + side + ", id=" + id + ", stat=" + stat + ", player=" + player
				+ "]";
	}
	
}
