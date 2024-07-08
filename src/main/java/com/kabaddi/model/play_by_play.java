package com.kabaddi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class play_by_play {
	List<PlayByRaids> PlayByRaids = new ArrayList<PlayByRaids>();

	public play_by_play() {
		super();
	}

	public List<PlayByRaids> getPlayByRaids() {
		return PlayByRaids;
	}

	public void setPlayByRaids(List<PlayByRaids> playByRaids) {
		PlayByRaids = playByRaids;
	}

	@Override
	public String toString() {
		return "play_by_play [PlayByRaids=" + PlayByRaids + "]";
	}
	
}
