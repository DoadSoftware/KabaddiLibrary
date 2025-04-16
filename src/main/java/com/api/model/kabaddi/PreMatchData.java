package com.api.model.kabaddi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PreMatchData {

    @JsonProperty("pre-match")
    public PreMatch preMatch;

    public PreMatch getPreMatch() {
		return preMatch;
	}

	public void setPreMatch(PreMatch preMatch) {
		this.preMatch = preMatch;
	}
	
	public PreMatchData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PreMatchData [preMatch=" + preMatch + "]";
	}

	public static class PreMatch {
        @JsonProperty("team-players-statistics")
        public TeamPlayersStatistics teamPlayersStatistics;

		@Override
		public String toString() {
			return "PreMatch [teamPlayersStatistics=" + teamPlayersStatistics + "]";
		}

		public PreMatch() {
			super();
			// TODO Auto-generated constructor stub
		}

		public TeamPlayersStatistics getTeamPlayersStatistics() {
			return teamPlayersStatistics;
		}

		public void setTeamPlayersStatistics(TeamPlayersStatistics teamPlayersStatistics) {
			this.teamPlayersStatistics = teamPlayersStatistics;
		}
        
    }

    public static class TeamPlayersStatistics {
        @JsonProperty("team")
        public List<Team> team;

		public List<Team> getTeam() {
			return team;
		}

		public void setTeam(List<Team> team) {
			this.team = team;
		}

		public TeamPlayersStatistics() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "TeamPlayersStatistics [team=" + team + "]";
		}
        
    }

    public static class Team {
        @JsonProperty("team-id")
        public String teamId;

        @JsonProperty("team-name")
        public String teamName;

        @JsonProperty("matches")
        public String matches;

        @JsonProperty("won")
        public String won;

        @JsonProperty("lost")
        public String lost;

        @JsonProperty("tied")
        public String tied;

        @JsonProperty("points")
        public Points points;

        @JsonProperty("raids")
        public Raids raids;

        @JsonProperty("tackles")
        public Tackles tackles;

        @JsonProperty("do-or-die")
        public DoOrDie doOrDie;

		public String getTeamId() {
			return teamId;
		}

		public void setTeamId(String teamId) {
			this.teamId = teamId;
		}

		public String getTeamName() {
			return teamName;
		}

		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}

		public String getMatches() {
			return matches;
		}

		public void setMatches(String matches) {
			this.matches = matches;
		}

		public String getWon() {
			return won;
		}

		public void setWon(String won) {
			this.won = won;
		}

		public String getLost() {
			return lost;
		}

		public void setLost(String lost) {
			this.lost = lost;
		}

		public String getTied() {
			return tied;
		}

		public void setTied(String tied) {
			this.tied = tied;
		}

		public Points getPoints() {
			return points;
		}

		public void setPoints(Points points) {
			this.points = points;
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

		public DoOrDie getDoOrDie() {
			return doOrDie;
		}

		public void setDoOrDie(DoOrDie doOrDie) {
			this.doOrDie = doOrDie;
		}

		@Override
		public String toString() {
			return "Team [teamId=" + teamId + ", teamName=" + teamName + ", matches=" + matches + ", won=" + won
					+ ", lost=" + lost + ", tied=" + tied + ", points=" + points + ", raids=" + raids + ", tackles="
					+ tackles + ", doOrDie=" + doOrDie + "]";
		}

		public Team() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class Points {
        @JsonProperty("total-points")
        public String totalPoints;

        @JsonProperty("raid-points")
        public RaidPoints raidPoints;

        @JsonProperty("tackle-points")
        public TacklePoints tacklePoints;

        @JsonProperty("all-out-points")
        public String allOutPoints;

        @JsonProperty("extra-points")
        public String extraPoints;

		public String getTotalPoints() {
			return totalPoints;
		}

		public void setTotalPoints(String totalPoints) {
			this.totalPoints = totalPoints;
		}

		public RaidPoints getRaidPoints() {
			return raidPoints;
		}

		public void setRaidPoints(RaidPoints raidPoints) {
			this.raidPoints = raidPoints;
		}

		public TacklePoints getTacklePoints() {
			return tacklePoints;
		}

		public void setTacklePoints(TacklePoints tacklePoints) {
			this.tacklePoints = tacklePoints;
		}

		public String getAllOutPoints() {
			return allOutPoints;
		}

		public void setAllOutPoints(String allOutPoints) {
			this.allOutPoints = allOutPoints;
		}

		public String getExtraPoints() {
			return extraPoints;
		}

		public void setExtraPoints(String extraPoints) {
			this.extraPoints = extraPoints;
		}

		public Points() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Points [totalPoints=" + totalPoints + ", raidPoints=" + raidPoints + ", tacklePoints="
					+ tacklePoints + ", allOutPoints=" + allOutPoints + ", extraPoints=" + extraPoints + "]";
		}
        
    }

    public static class RaidPoints {
        @JsonProperty("total-raid-points")
        public String totalRaidPoints;

        @JsonProperty("touch-points")
        public String touchPoints;

        @JsonProperty("raid-bonus-points")
        public String raidBonusPoints;

		@Override
		public String toString() {
			return "RaidPoints [totalRaidPoints=" + totalRaidPoints + ", touchPoints=" + touchPoints
					+ ", raidBonusPoints=" + raidBonusPoints + "]";
		}

		public RaidPoints() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getTotalRaidPoints() {
			return totalRaidPoints;
		}

		public void setTotalRaidPoints(String totalRaidPoints) {
			this.totalRaidPoints = totalRaidPoints;
		}

		public String getTouchPoints() {
			return touchPoints;
		}

		public void setTouchPoints(String touchPoints) {
			this.touchPoints = touchPoints;
		}

		public String getRaidBonusPoints() {
			return raidBonusPoints;
		}

		public void setRaidBonusPoints(String raidBonusPoints) {
			this.raidBonusPoints = raidBonusPoints;
		}
        
    }

    public static class TacklePoints {
        @JsonProperty("total-tackle-points")
        public String totalTacklePoints;

        @JsonProperty("capture-points")
        public String capturePoints;

        @JsonProperty("tackle-bonus-points")
        public String tackleBonusPoints;

		public String getTotalTacklePoints() {
			return totalTacklePoints;
		}

		public void setTotalTacklePoints(String totalTacklePoints) {
			this.totalTacklePoints = totalTacklePoints;
		}

		public String getCapturePoints() {
			return capturePoints;
		}

		public void setCapturePoints(String capturePoints) {
			this.capturePoints = capturePoints;
		}

		public String getTackleBonusPoints() {
			return tackleBonusPoints;
		}

		public void setTackleBonusPoints(String tackleBonusPoints) {
			this.tackleBonusPoints = tackleBonusPoints;
		}

		@Override
		public String toString() {
			return "TacklePoints [totalTacklePoints=" + totalTacklePoints + ", capturePoints=" + capturePoints
					+ ", tackleBonusPoints=" + tackleBonusPoints + "]";
		}

		public TacklePoints() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class Raids {
        @JsonProperty("total-raids")
        public String totalRaids;

        @JsonProperty("super-raids")
        public String superRaids;

        @JsonProperty("successful-raids")
        public String successfulRaids;

        @JsonProperty("unsuccessful-raids")
        public String unsuccessfulRaids;

        @JsonProperty("empty-raids")
        public String emptyRaids;

		public String getTotalRaids() {
			return totalRaids;
		}

		public void setTotalRaids(String totalRaids) {
			this.totalRaids = totalRaids;
		}

		public String getSuperRaids() {
			return superRaids;
		}

		public void setSuperRaids(String superRaids) {
			this.superRaids = superRaids;
		}

		public String getSuccessfulRaids() {
			return successfulRaids;
		}

		public void setSuccessfulRaids(String successfulRaids) {
			this.successfulRaids = successfulRaids;
		}

		public String getUnsuccessfulRaids() {
			return unsuccessfulRaids;
		}

		public void setUnsuccessfulRaids(String unsuccessfulRaids) {
			this.unsuccessfulRaids = unsuccessfulRaids;
		}

		public String getEmptyRaids() {
			return emptyRaids;
		}

		public void setEmptyRaids(String emptyRaids) {
			this.emptyRaids = emptyRaids;
		}

		@Override
		public String toString() {
			return "Raids [totalRaids=" + totalRaids + ", superRaids=" + superRaids + ", successfulRaids="
					+ successfulRaids + ", unsuccessfulRaids=" + unsuccessfulRaids + ", emptyRaids=" + emptyRaids + "]";
		}

		public Raids() {
			super();
			// TODO Auto-generated constructor stub
		}
        
        
    }

    public static class Tackles {
        @JsonProperty("total-tackles")
        public String totalTackles;

        @JsonProperty("super-tackles")
        public String superTackles;

        @JsonProperty("successful-tackles")
        public String successfulTackles;

        @JsonProperty("unsuccessful-tackles")
        public String unsuccessfulTackles;

		@Override
		public String toString() {
			return "Tackles [totalTackles=" + totalTackles + ", superTackles=" + superTackles + ", successfulTackles="
					+ successfulTackles + ", unsuccessfulTackles=" + unsuccessfulTackles + "]";
		}

		public Tackles() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getTotalTackles() {
			return totalTackles;
		}

		public void setTotalTackles(String totalTackles) {
			this.totalTackles = totalTackles;
		}

		public String getSuperTackles() {
			return superTackles;
		}

		public void setSuperTackles(String superTackles) {
			this.superTackles = superTackles;
		}

		public String getSuccessfulTackles() {
			return successfulTackles;
		}

		public void setSuccessfulTackles(String successfulTackles) {
			this.successfulTackles = successfulTackles;
		}

		public String getUnsuccessfulTackles() {
			return unsuccessfulTackles;
		}

		public void setUnsuccessfulTackles(String unsuccessfulTackles) {
			this.unsuccessfulTackles = unsuccessfulTackles;
		}
        
        
    }

    public static class DoOrDie {
        @JsonProperty("total-raids")
        public String totalRaids;

        @JsonProperty("successfull-raids")
        public String successfullRaids;

        @JsonProperty("failed-raids")
        public String failedRaids;

        @JsonProperty("super-raids")
        public String superRaids;

        @JsonProperty("raid-points")
        public String raidPoints;

        @JsonProperty("touch-points")
        public String touchPoints;

        @JsonProperty("bonus-points")
        public String bonusPoints;

		public String getTotalRaids() {
			return totalRaids;
		}

		public void setTotalRaids(String totalRaids) {
			this.totalRaids = totalRaids;
		}

		public String getSuccessfullRaids() {
			return successfullRaids;
		}

		public void setSuccessfullRaids(String successfullRaids) {
			this.successfullRaids = successfullRaids;
		}

		public String getFailedRaids() {
			return failedRaids;
		}

		public void setFailedRaids(String failedRaids) {
			this.failedRaids = failedRaids;
		}

		public String getSuperRaids() {
			return superRaids;
		}

		public void setSuperRaids(String superRaids) {
			this.superRaids = superRaids;
		}

		public String getRaidPoints() {
			return raidPoints;
		}

		public void setRaidPoints(String raidPoints) {
			this.raidPoints = raidPoints;
		}

		public String getTouchPoints() {
			return touchPoints;
		}

		public void setTouchPoints(String touchPoints) {
			this.touchPoints = touchPoints;
		}

		public String getBonusPoints() {
			return bonusPoints;
		}

		public void setBonusPoints(String bonusPoints) {
			this.bonusPoints = bonusPoints;
		}

		@Override
		public String toString() {
			return "DoOrDie [totalRaids=" + totalRaids + ", successfullRaids=" + successfullRaids + ", failedRaids="
					+ failedRaids + ", superRaids=" + superRaids + ", raidPoints=" + raidPoints + ", touchPoints="
					+ touchPoints + ", bonusPoints=" + bonusPoints + "]";
		}

		public DoOrDie() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }
}
