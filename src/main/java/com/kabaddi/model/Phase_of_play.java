package com.kabaddi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phase_of_play {
	List<Phase> Phase = new ArrayList<Phase>();

	public List<Phase> getPhase() {
		return Phase;
	}

	public void setPhase(List<Phase> phase) {
		Phase = phase;
	}

	@Override
	public String toString() {
		return "Phase_of_play [Phase=" + Phase + "]";
	}
	
}
