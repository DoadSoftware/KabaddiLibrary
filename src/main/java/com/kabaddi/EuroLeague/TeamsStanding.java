package com.kabaddi.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamsStanding {
	
	@JsonProperty("stage")
	private List<Stage> stage;

	public TeamsStanding() {
		super();
	}

	public List<Stage> getStage() {
		return stage;
	}

	public void setStage(List<Stage> stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return "TeamsStanding [stage=" + stage + "]";
	}	
	
}