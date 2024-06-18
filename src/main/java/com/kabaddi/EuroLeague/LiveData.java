package com.kabaddi.EuroLeague;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiveData {

	@JsonProperty("matchDetails")
	private MatchDetails matchDetails;
	  
	@JsonProperty("event")
	private List<Events> event;
	
	@JsonProperty("preMatchPredictions")
	private List<PreMatchPredictions> preMatchPredictions;
	
	@JsonProperty("overallLivePredictions")
	private List<PreMatchPredictions> overallLivePredictions;
	
	@JsonProperty("livePredictions")
	private List<PreMatchPredictions> livePredictions;
	
	@JsonProperty("goal")
	private List<Goal> goal;
	
	@JsonProperty("card")
	private List<Card> card;
	
	@JsonProperty("substitute")
	private List<Substitute> substitute;
	
	@JsonProperty("missedPen")
	private List<MissedPen> missedPen;
	
	@JsonProperty("matchDetailsExtra")
	private MatchDetailsExtra matchDetailsExtra;

	@JsonProperty("VAR")
	private List<MissedPen> VAR;
	
	@JsonProperty("predictions")
	private List<Predictions> predictions;
	
	@JsonProperty("lineUp")
	private List<LineUp> lineUp;
	
	public MatchDetails getMatchDetails() {
		return matchDetails;
	}

	public void setMatchDetails(MatchDetails matchDetails) {
		this.matchDetails = matchDetails;
	}

	public List<Events> getEvent() {
		return event;
	}

	public void setEvent(List<Events> event) {
		this.event = event;
	}

	public List<Goal> getGoal() {
		return goal;
	}

	public void setGoal(List<Goal> goal) {
		this.goal = goal;
	}

	public List<Card> getCard() {
		return card;
	}

	public void setCard(List<Card> card) {
		this.card = card;
	}

	public List<Substitute> getSubstitute() {
		return substitute;
	}

	public void setSubstitute(List<Substitute> substitute) {
		this.substitute = substitute;
	}

	public List<LineUp> getLineUp() {
		return lineUp;
	}

	public void setLineUp(List<LineUp> lineUp) {
		this.lineUp = lineUp;
	}

	public MatchDetailsExtra getMatchDetailsExtra() {
		return matchDetailsExtra;
	}

	public void setMatchDetailsExtra(MatchDetailsExtra matchDetailsExtra) {
		this.matchDetailsExtra = matchDetailsExtra;
	}

	public List<PreMatchPredictions> getPreMatchPredictions() {
		return preMatchPredictions;
	}

	public void setPreMatchPredictions(List<PreMatchPredictions> preMatchPredictions) {
		this.preMatchPredictions = preMatchPredictions;
	}

	public List<PreMatchPredictions> getOverallLivePredictions() {
		return overallLivePredictions;
	}

	public void setOverallLivePredictions(List<PreMatchPredictions> overallLivePredictions) {
		this.overallLivePredictions = overallLivePredictions;
	}

	public List<PreMatchPredictions> getLivePredictions() {
		return livePredictions;
	}

	public void setLivePredictions(List<PreMatchPredictions> livePredictions) {
		this.livePredictions = livePredictions;
	}

	public List<MissedPen> getMissedPen() {
		return missedPen;
	}

	public void setMissedPen(List<MissedPen> missedPen) {
		this.missedPen = missedPen;
	}

	public List<MissedPen> getVAR() {
		return VAR;
	}

	public void setVAR(List<MissedPen> vAR) {
		VAR = vAR;
	}

	public List<Predictions> getPredictions() {
		return predictions;
	}

	public void setPredictions(List<Predictions> predictions) {
		this.predictions = predictions;
	}

	@Override
	public String toString() {
		return "LiveData [matchDetails=" + matchDetails + ", event=" + event + ", goal=" + goal + ", card=" + card
				+ ", substitute=" + substitute + ", lineUp=" + lineUp + ", matchDetailsExtra=" + matchDetailsExtra
				+ "]";
	}
	
}