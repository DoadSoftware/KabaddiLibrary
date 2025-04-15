package com.api.model.kabaddi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class InMatchData {

    @JsonProperty("in-match")
    public InMatch inMatch;
    public InMatchData(InMatch inMatch) {
		super();
		this.inMatch = inMatch;
	}

	public InMatch getInMatch() {
		return inMatch;
	}

	public void setInMatch(InMatch inMatch) {
		this.inMatch = inMatch;
	}

	public static class InMatch {
        @JsonProperty("team-players-statistics")
        public TeamPlayersStatistics teamPlayersStatistics;

        @JsonProperty("match-details")
        public MatchDetails matchDetails;

        @JsonProperty("phase-of-play")
        public PhaseOfPlay phase_of_play;

        @JsonProperty("play-by-play")
        public PlayByPlay play_by_play;

		public TeamPlayersStatistics getTeamPlayersStatistics() {
			return teamPlayersStatistics;
		}

		public void setTeamPlayersStatistics(TeamPlayersStatistics teamPlayersStatistics) {
			this.teamPlayersStatistics = teamPlayersStatistics;
		}

		public MatchDetails getMatchDetails() {
			return matchDetails;
		}

		public void setMatchDetails(MatchDetails matchDetails) {
			this.matchDetails = matchDetails;
		}

		public PhaseOfPlay getPhase_of_play() {
			return phase_of_play;
		}

		public void setPhase_of_play(PhaseOfPlay phase_of_play) {
			this.phase_of_play = phase_of_play;
		}

		public PlayByPlay getPlay_by_play() {
			return play_by_play;
		}

		public void setPlay_by_play(PlayByPlay play_by_play) {
			this.play_by_play = play_by_play;
		}

		@Override
		public String toString() {
			return "InMatch [teamPlayersStatistics=" + teamPlayersStatistics + ", matchDetails=" + matchDetails
					+ ", phase_of_play=" + phase_of_play + ", play_by_play=" + play_by_play + "]";
		}

		public InMatch() {
			super();
			// TODO Auto-generated constructor stub
		}
    }

    public static class TeamPlayersStatistics {
        public List<Team> team;

		public List<Team> getTeam() {
			return team;
		}

		public void setTeam(List<Team> team) {
			this.team = team;
		}

		@Override
		public String toString() {
			return "TeamPlayersStatistics [team=" + team + "]";
		}

		public TeamPlayersStatistics() {
			super();
			// TODO Auto-generated constructor stub
		}
    }

    public static class Team {
        @JsonProperty("team-name")
        public String teamName;

        @JsonProperty("team-id")
        public String teamId;

        @JsonProperty("no-of-players-on-court")
        public String noOfPlayersOnCourt;

        public Points points;

        @JsonProperty("points-last-n-minutes")
        public PointsLastNMinutes pointsLastNMinutes;

        public Raids raids;
        public Tackles tackles;

        @JsonProperty("do-or-die")
        public DoOrDie doOrDie;

        public Players players;

		public String getTeamName() {
			return teamName;
		}

		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}

		public String getTeamId() {
			return teamId;
		}

		public void setTeamId(String teamId) {
			this.teamId = teamId;
		}

		public String getNoOfPlayersOnCourt() {
			return noOfPlayersOnCourt;
		}

		public void setNoOfPlayersOnCourt(String noOfPlayersOnCourt) {
			this.noOfPlayersOnCourt = noOfPlayersOnCourt;
		}

		public Points getPoints() {
			return points;
		}

		public void setPoints(Points points) {
			this.points = points;
		}

		public PointsLastNMinutes getPointsLastNMinutes() {
			return pointsLastNMinutes;
		}

		public void setPointsLastNMinutes(PointsLastNMinutes pointsLastNMinutes) {
			this.pointsLastNMinutes = pointsLastNMinutes;
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

		public Players getPlayers() {
			return players;
		}

		public void setPlayers(Players players) {
			this.players = players;
		}

		@Override
		public String toString() {
			return "Team [teamName=" + teamName + ", teamId=" + teamId + ", noOfPlayersOnCourt=" + noOfPlayersOnCourt
					+ ", points=" + points + ", pointsLastNMinutes=" + pointsLastNMinutes + ", raids=" + raids
					+ ", tackles=" + tackles + ", doOrDie=" + doOrDie + ", players=" + players + "]";
		}

		public Team() {
			super();
			// TODO Auto-generated constructor stub
		}
    }

    public static class Points {
        @JsonProperty("total-points")
        public String totalPoints;

        @JsonProperty("tackle-points")
        public TacklePoints tacklePoints;

        @JsonProperty("raid-points")
        public RaidPoints raidPoints;

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

		public TacklePoints getTacklePoints() {
			return tacklePoints;
		}

		public void setTacklePoints(TacklePoints tacklePoints) {
			this.tacklePoints = tacklePoints;
		}

		public RaidPoints getRaidPoints() {
			return raidPoints;
		}

		public void setRaidPoints(RaidPoints raidPoints) {
			this.raidPoints = raidPoints;
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

		@Override
		public String toString() {
			return "Points [totalPoints=" + totalPoints + ", tacklePoints=" + tacklePoints + ", raidPoints="
					+ raidPoints + ", allOutPoints=" + allOutPoints + ", extraPoints=" + extraPoints + "]";
		}

		public Points() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class TacklePoints {
        @JsonProperty("total-tackle-points")
        public String totalTacklePoints;

        @JsonProperty("capture-points")
        public Object capturePoints; // can be string or object (based on team/player)

        @JsonProperty("tackle-bonus-points")
        public String tackleBonusPoints;

		public String getTotalTacklePoints() {
			return totalTacklePoints;
		}

		public void setTotalTacklePoints(String totalTacklePoints) {
			this.totalTacklePoints = totalTacklePoints;
		}

		public Object getCapturePoints() {
			return capturePoints;
		}

		public void setCapturePoints(Object capturePoints) {
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

    public static class CapturePoints {
        @JsonProperty("total-capture-points")
        public String totalCapturePoints;

        @JsonProperty("skill-points")
        public SkillPoints skillPoints;

		public String getTotalCapturePoints() {
			return totalCapturePoints;
		}

		public void setTotalCapturePoints(String totalCapturePoints) {
			this.totalCapturePoints = totalCapturePoints;
		}

		public SkillPoints getSkillPoints() {
			return skillPoints;
		}

		public void setSkillPoints(SkillPoints skillPoints) {
			this.skillPoints = skillPoints;
		}

		@Override
		public String toString() {
			return "CapturePoints [totalCapturePoints=" + totalCapturePoints + ", skillPoints=" + skillPoints + "]";
		}

		public CapturePoints() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class SkillPoints {
        @JsonProperty("skill-id")
        public String skillId;

        @JsonProperty("#text")
        public String text;

		public String getSkillId() {
			return skillId;
		}

		public void setSkillId(String skillId) {
			this.skillId = skillId;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return "SkillPoints [skillId=" + skillId + ", text=" + text + "]";
		}

		public SkillPoints() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class RaidPoints {
        @JsonProperty("total-raid-points")
        public String totalRaidPoints;

        @JsonProperty("touch-points")
        public Object touchPoints; // sometimes number, sometimes object

        @JsonProperty("raid-bonus-points")
        public String raidBonusPoints;

		public String getTotalRaidPoints() {
			return totalRaidPoints;
		}

		public void setTotalRaidPoints(String totalRaidPoints) {
			this.totalRaidPoints = totalRaidPoints;
		}

		public Object getTouchPoints() {
			return touchPoints;
		}

		public void setTouchPoints(Object touchPoints) {
			this.touchPoints = touchPoints;
		}

		public String getRaidBonusPoints() {
			return raidBonusPoints;
		}

		public void setRaidBonusPoints(String raidBonusPoints) {
			this.raidBonusPoints = raidBonusPoints;
		}

		@Override
		public String toString() {
			return "RaidPoints [totalRaidPoints=" + totalRaidPoints + ", touchPoints=" + touchPoints
					+ ", raidBonusPoints=" + raidBonusPoints + "]";
		}

		public RaidPoints() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class PointsLastNMinutes {
        public String five;
        public String ten;
		public String getFive() {
			return five;
		}
		public void setFive(String five) {
			this.five = five;
		}
		public String getTen() {
			return ten;
		}
		public void setTen(String ten) {
			this.ten = ten;
		}
		@Override
		public String toString() {
			return "PointsLastNMinutes [five=" + five + ", ten=" + ten + "]";
		}
		public PointsLastNMinutes() {
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

		public Raids() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Raids [totalRaids=" + totalRaids + ", superRaids=" + superRaids + ", successfulRaids="
					+ successfulRaids + ", unsuccessfulRaids=" + unsuccessfulRaids + ", emptyRaids=" + emptyRaids + "]";
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

		@Override
		public String toString() {
			return "Tackles [totalTackles=" + totalTackles + ", superTackles=" + superTackles + ", successfulTackles="
					+ successfulTackles + ", unsuccessfulTackles=" + unsuccessfulTackles + "]";
		}

		public Tackles() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class DoOrDie {
        @JsonProperty("total-raids")
        public String totalRaids;

        @JsonProperty("successfull-raids")
        public String successfulRaids;

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

		public String getSuccessfulRaids() {
			return successfulRaids;
		}

		public void setSuccessfulRaids(String successfulRaids) {
			this.successfulRaids = successfulRaids;
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
			return "DoOrDie [totalRaids=" + totalRaids + ", successfulRaids=" + successfulRaids + ", failedRaids="
					+ failedRaids + ", superRaids=" + superRaids + ", raidPoints=" + raidPoints + ", touchPoints="
					+ touchPoints + ", bonusPoints=" + bonusPoints + "]";
		}

		public DoOrDie() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class Players {
        public List<Player> player;

		public List<Player> getPlayer() {
			return player;
		}

		public void setPlayer(List<Player> player) {
			this.player = player;
		}

		@Override
		public String toString() {
			return "Players [player=" + player + "]";
		}

		public Players() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class Player {
        @JsonProperty("player-id")
        public String playerId;

        @JsonProperty("player-name")
        public String playerName;

        @JsonProperty("player-on-court")
        public String playerOnCourt;

        @JsonProperty("player-revival-order")
        public String playerRevivalOrder;

        public Points points;
        public Raids raids;
        public Tackles tackles;

        @JsonProperty("time-off-court")
        public String timeOffCourt;

        @JsonProperty("do-or-die")
        public DoOrDie doOrDie;

        @JsonProperty("avg-raid-time")
        public String avgRaidTime;

        @JsonProperty("raid-map-locations")
        public RaidMapLocations raidMapLocations;

		@Override
		public String toString() {
			return "Player [playerId=" + playerId + ", playerName=" + playerName + ", playerOnCourt=" + playerOnCourt
					+ ", playerRevivalOrder=" + playerRevivalOrder + ", points=" + points + ", raids=" + raids
					+ ", tackles=" + tackles + ", timeOffCourt=" + timeOffCourt + ", doOrDie=" + doOrDie
					+ ", avgRaidTime=" + avgRaidTime + ", raidMapLocations=" + raidMapLocations + "]";
		}

		public String getPlayerId() {
			return playerId;
		}

		public void setPlayerId(String playerId) {
			this.playerId = playerId;
		}

		public String getPlayerName() {
			return playerName;
		}

		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}

		public String getPlayerOnCourt() {
			return playerOnCourt;
		}

		public void setPlayerOnCourt(String playerOnCourt) {
			this.playerOnCourt = playerOnCourt;
		}

		public String getPlayerRevivalOrder() {
			return playerRevivalOrder;
		}

		public void setPlayerRevivalOrder(String playerRevivalOrder) {
			this.playerRevivalOrder = playerRevivalOrder;
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

		public String getTimeOffCourt() {
			return timeOffCourt;
		}

		public void setTimeOffCourt(String timeOffCourt) {
			this.timeOffCourt = timeOffCourt;
		}

		public DoOrDie getDoOrDie() {
			return doOrDie;
		}

		public void setDoOrDie(DoOrDie doOrDie) {
			this.doOrDie = doOrDie;
		}

		public String getAvgRaidTime() {
			return avgRaidTime;
		}

		public void setAvgRaidTime(String avgRaidTime) {
			this.avgRaidTime = avgRaidTime;
		}

		public RaidMapLocations getRaidMapLocations() {
			return raidMapLocations;
		}

		public void setRaidMapLocations(RaidMapLocations raidMapLocations) {
			this.raidMapLocations = raidMapLocations;
		}

		public Player() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class RaidMapLocations {
        public List<Location> location;

		public List<Location> getLocation() {
			return location;
		}

		public void setLocation(List<Location> location) {
			this.location = location;
		}

		@Override
		public String toString() {
			return "RaidMapLocations [location=" + location + "]";
		}

		public RaidMapLocations() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class Location {
        @JsonProperty("location-id")
        public String locationId;
        
        @JsonProperty("strong")
        public String strong;
        
        @JsonProperty("weak")
        public String weak;
        
		public String getLocationId() {
			return locationId;
		}
		public void setLocationId(String locationId) {
			this.locationId = locationId;
		}
		public String getStrong() {
			return strong;
		}
		public void setStrong(String strong) {
			this.strong = strong;
		}
		public String getWeak() {
			return weak;
		}
		public void setWeak(String weak) {
			this.weak = weak;
		}
		@Override
		public String toString() {
			return "Location [locationId=" + locationId + ", strong=" + strong + ", weak=" + weak + "]";
		}
    }
    
    public static class MatchDetails {
        public Result result;

		@Override
		public String toString() {
			return "MatchDetails [result=" + result + "]";
		}

		public MatchDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		} 
        
    }

    public static class PhaseOfPlay {
        public List<Phase> phase;

		public List<Phase> getPhase() {
			return phase;
		}

		public void setPhase(List<Phase> phase) {
			this.phase = phase;
		}

		@Override
		public String toString() {
			return "PhaseOfPlay [phase=" + phase + "]";
		}

		public PhaseOfPlay() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class Phase {
        @JsonProperty("phase-name")
        public String phaseName;

        public List<Team> team;

		public String getPhaseName() {
			return phaseName;
		}

		public void setPhaseName(String phaseName) {
			this.phaseName = phaseName;
		}

		public List<Team> getTeam() {
			return team;
		}

		public void setTeam(List<Team> team) {
			this.team = team;
		}

		public Phase() {
			super();
			// TODO Auto-generated constructor stub
		}
        
    }

    public static class PlayByPlay {
        public List<Raid> raid;

		public List<Raid> getRaid() {
			return raid;
		}

		public void setRaid(List<Raid> raid) {
			this.raid = raid;
		}

		@Override
		public String toString() {
			return "PlayByPlay [raid=" + raid + "]";
		}
        
    }

    public static class Raid {
        @JsonProperty("raid-number")
        public String raidNumber;

        @JsonProperty("raiding-team-id")
        public String raidingTeamId;

        @JsonProperty("raiding-player-id")
        public String raidingPlayerId;

        @JsonProperty("raid-outcome-id")
        public String raidOutcomeId;

        @JsonProperty("raid-half")
        public String raidHalf;

        public List<RaidTeam> team;

		public String getRaidNumber() {
			return raidNumber;
		}

		public void setRaidNumber(String raidNumber) {
			this.raidNumber = raidNumber;
		}

		public String getRaidingTeamId() {
			return raidingTeamId;
		}

		public void setRaidingTeamId(String raidingTeamId) {
			this.raidingTeamId = raidingTeamId;
		}

		public String getRaidingPlayerId() {
			return raidingPlayerId;
		}

		public void setRaidingPlayerId(String raidingPlayerId) {
			this.raidingPlayerId = raidingPlayerId;
		}

		public String getRaidOutcomeId() {
			return raidOutcomeId;
		}

		public void setRaidOutcomeId(String raidOutcomeId) {
			this.raidOutcomeId = raidOutcomeId;
		}

		public String getRaidHalf() {
			return raidHalf;
		}

		public void setRaidHalf(String raidHalf) {
			this.raidHalf = raidHalf;
		}

		public List<RaidTeam> getTeam() {
			return team;
		}

		public void setTeam(List<RaidTeam> team) {
			this.team = team;
		}

		@Override
		public String toString() {
			return "Raid [raidNumber=" + raidNumber + ", raidingTeamId=" + raidingTeamId + ", raidingPlayerId="
					+ raidingPlayerId + ", raidOutcomeId=" + raidOutcomeId + ", raidHalf=" + raidHalf + ", team=" + team
					+ "]";
		}

		public Raid() {
			super();
			// TODO Auto-generated constructor stub
		}
        
        
    }
    
    public static class Result {
        @JsonProperty("match-status-id")
        public String matchStatusId;

        @JsonProperty("winning-team-id")
        public String winningTeamId;

        @JsonProperty("match-tied")
        public String matchTied;

        @JsonProperty("winner-declared")
        public String winnerDeclared;

		public String getMatchStatusId() {
			return matchStatusId;
		}

		public void setMatchStatusId(String matchStatusId) {
			this.matchStatusId = matchStatusId;
		}

		public String getWinningTeamId() {
			return winningTeamId;
		}

		public void setWinningTeamId(String winningTeamId) {
			this.winningTeamId = winningTeamId;
		}

		public String getMatchTied() {
			return matchTied;
		}

		public void setMatchTied(String matchTied) {
			this.matchTied = matchTied;
		}

		public String getWinnerDeclared() {
			return winnerDeclared;
		}

		public void setWinnerDeclared(String winnerDeclared) {
			this.winnerDeclared = winnerDeclared;
		}

		public Result() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Result [matchStatusId=" + matchStatusId + ", winningTeamId=" + winningTeamId + ", matchTied="
					+ matchTied + ", winnerDeclared=" + winnerDeclared + "]";
		}
        
    }
    
    public static class RaidTeam {
        @JsonProperty("team-id")
        public String teamId;

        @JsonProperty("total-points")
        public String totalPoints;

        public Points points;

		public RaidTeam() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "RaidTeam [teamId=" + teamId + ", totalPoints=" + totalPoints + ", points=" + points + "]";
		}

		public String getTeamId() {
			return teamId;
		}

		public void setTeamId(String teamId) {
			this.teamId = teamId;
		}

		public String getTotalPoints() {
			return totalPoints;
		}

		public void setTotalPoints(String totalPoints) {
			this.totalPoints = totalPoints;
		}

		public Points getPoints() {
			return points;
		}

		public void setPoints(Points points) {
			this.points = points;
		}
        
    }

	public InMatchData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InMatchData [inMatch=" + inMatch + "]";
	}
    
}
