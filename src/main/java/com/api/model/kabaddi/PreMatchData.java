package com.api.model.kabaddi;

import com.api.model.kabaddi.InMatchData.CapturePoints;
import com.api.model.kabaddi.InMatchData.RaidMapLocations;
import com.api.model.kabaddi.InMatchData.SkillPoints;
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
	 @JsonIgnoreProperties(ignoreUnknown = true)
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
	 @JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonIgnoreProperties(ignoreUnknown = true)
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
        
        @JsonProperty("players")
        public Players players;

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

		public Players getPlayers() {
			return players;
		}

		public void setPlayers(Players players) {
			this.players = players;
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Players {
        @JsonProperty("player")
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
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Player {
        @JsonProperty("player-id") 
         public String playerId;
        
        @JsonProperty("player-name")
        public String playerName;
        
        @JsonProperty("full-name") 
        public String fullName;
        
        @JsonProperty("short-name") 
        public String shortName;
        
        @JsonProperty("jersey-no") 
        public String jerseyNo;
        
        @JsonProperty("date-of-birth") 
        public String dateOfBirth;
        
        @JsonProperty("high_five")
        public String highFive;
        
        @JsonProperty("super_ten")
        public String superTen;
        
        @JsonProperty("matches") 
        public String matches;
        
        @JsonProperty("matches_raided") 
        public String matchesRaided;
        
        @JsonProperty("roles") 
        public Roles roles;
        
        @JsonProperty("impacts")
        public Impacts impacts;
        
        @JsonProperty("points") 
        public PointsPlayer points;
        
        @JsonProperty("raids") 
        public Raids raids;
        
        @JsonProperty("tackles") 
        public Tackles tackles;
        
        @JsonProperty("do-or-die") 
        public DoOrDie doOrDie;
        
        @JsonProperty("assists") 
        public String assists;
        
        @JsonProperty("raid-map-locations") 
        public RaidMapLocations raidMapLocations;
        
        @JsonProperty("performance-against-team") 
        public PerformanceAgainstTeam performanceAgainstTeam;

        
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


		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}


		public String getShortName() {
			return shortName;
		}


		public void setShortName(String shortName) {
			this.shortName = shortName;
		}


		public String getJerseyNo() {
			return jerseyNo;
		}


		public void setJerseyNo(String jerseyNo) {
			this.jerseyNo = jerseyNo;
		}


		public String getDateOfBirth() {
			return dateOfBirth;
		}


		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}


		public String getHighFive() {
			return highFive;
		}


		public void setHighFive(String highFive) {
			this.highFive = highFive;
		}


		public String getSuperTen() {
			return superTen;
		}


		public void setSuperTen(String superTen) {
			this.superTen = superTen;
		}


		public String getMatches() {
			return matches;
		}


		public void setMatches(String matches) {
			this.matches = matches;
		}


		public String getMatchesRaided() {
			return matchesRaided;
		}


		public void setMatchesRaided(String matchesRaided) {
			this.matchesRaided = matchesRaided;
		}


		public Roles getRoles() {
			return roles;
		}


		public void setRoles(Roles roles) {
			this.roles = roles;
		}


		public Impacts getImpacts() {
			return impacts;
		}


		public void setImpacts(Impacts impacts) {
			this.impacts = impacts;
		}


		public PointsPlayer getPoints() {
			return points;
		}


		public void setPoints(PointsPlayer points) {
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


		public String getAssists() {
			return assists;
		}


		public void setAssists(String assists) {
			this.assists = assists;
		}


		public RaidMapLocations getRaidMapLocations() {
			return raidMapLocations;
		}


		public void setRaidMapLocations(RaidMapLocations raidMapLocations) {
			this.raidMapLocations = raidMapLocations;
		}


		public PerformanceAgainstTeam getPerformanceAgainstTeam() {
			return performanceAgainstTeam;
		}


		public void setPerformanceAgainstTeam(PerformanceAgainstTeam performanceAgainstTeam) {
			this.performanceAgainstTeam = performanceAgainstTeam;
		}


		@Override
        public String toString() {
            return "Player [playerId=" + playerId + ", playerName=" + playerName + ", fullName=" + fullName
                    + ", shortName=" + shortName + ", jerseyNo=" + jerseyNo + ", dateOfBirth=" + dateOfBirth
                    + ", highFive=" + highFive + ", superTen=" + superTen + ", matches=" + matches
                    + ", matchesRaided=" + matchesRaided + ", assists=" + assists + "]";
        }
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PointsPlayer {
    	  
    	  @JsonProperty("total-points")
          public String totalPoints;

          @JsonProperty("raid-points")
          public RaidPointsPlayer raidPoints;

          @JsonProperty("tackle-points")
          public TacklePointsPlayer tacklePoints;

		public String getTotalPoints() {
			return totalPoints;
		}

		public void setTotalPoints(String totalPoints) {
			this.totalPoints = totalPoints;
		}

		public RaidPointsPlayer getRaidPoints() {
			return raidPoints;
		}

		public void setRaidPoints(RaidPointsPlayer raidPoints) {
			this.raidPoints = raidPoints;
		}

		public TacklePointsPlayer getTacklePoints() {
			return tacklePoints;
		}

		public void setTacklePoints(TacklePointsPlayer tacklePoints) {
			this.tacklePoints = tacklePoints;
		}

		@Override
		public String toString() {
			return "PointsPlayer [totalPoints=" + totalPoints + "]";
		}
          

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TacklePointsPlayer {
    	 @JsonProperty("total-tackle-points")
         public String totalTacklePoints;
    	 
    	 @JsonProperty("capture-points")
         public CapturePoints capturePoints;
    	 
    	 @JsonProperty("tackle-bonus-points")
         public String tackleBonusPoints;

		public String getTotalTacklePoints() {
			return totalTacklePoints;
		}

		public void setTotalTacklePoints(String totalTacklePoints) {
			this.totalTacklePoints = totalTacklePoints;
		}

		public CapturePoints getCapturePoints() {
			return capturePoints;
		}

		public void setCapturePoints(CapturePoints capturePoints) {
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
			return "TacklePointsPlayer [totalTacklePoints=" + totalTacklePoints + ", capturePoints=" + capturePoints
					+ ", tackleBonusPoints=" + tackleBonusPoints + "]";
		}
    	 
    	 
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CapturePoints {
    	 @JsonProperty("total-capture-points")
         public String totalCapturePoints;

         @JsonProperty("skill-points")
         public List<SkillPoints> skillPoints;
         
         @JsonProperty("tackle-bonus-points")
         public String totalBonusPoints;
         
 		public String getTotalCapturePoints() {
 			return totalCapturePoints;
 		}

 		public void setTotalCapturePoints(String totalCapturePoints) {
 			this.totalCapturePoints = totalCapturePoints;
 		}

 		public List<SkillPoints> getSkillPoints() {
 			return skillPoints;
 		}

 		public void setSkillPoints(List<SkillPoints> skillPoints) {
 			this.skillPoints = skillPoints;
 		}

 		public String getTotalBonusPoints() {
			return totalBonusPoints;
		}

		public void setTotalBonusPoints(String totalBonusPoints) {
			this.totalBonusPoints = totalBonusPoints;
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TouchPointsPlayer {
    	 @JsonProperty("total-touch-points")
         public String totalTouchPoints;
    	 
    	 @JsonProperty("skill-points")
         public List<SkillPoints> SkillPoints;

		public String getTotalTouchPoints() {
			return totalTouchPoints;
		}

		public void setTotalTouchPoints(String totalTouchPoints) {
			this.totalTouchPoints = totalTouchPoints;
		}

		public List<SkillPoints> getSkillPoints() {
			return SkillPoints;
		}

		public void setSkillPoints(List<SkillPoints> skillPoints) {
			SkillPoints = skillPoints;
		}

		@Override
		public String toString() {
			return "TouchPointsPlayer [totalTouchPoints=" + totalTouchPoints + ", SkillPoints=" + SkillPoints + "]";
		}
    	 
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RaidPointsPlayer {
    	 @JsonProperty("total-raid-points")
         public String totalRaidPoints;

         @JsonProperty("touch-points")
         public TouchPointsPlayer raidPoints;
         
         @JsonProperty("raid-bonus-points")
         public String raidBonusPoints;

		public String getTotalRaidPoints() {
			return totalRaidPoints;
		}

		public void setTotalRaidPoints(String totalRaidPoints) {
			this.totalRaidPoints = totalRaidPoints;
		}

		public TouchPointsPlayer getRaidPoints() {
			return raidPoints;
		}

		public void setRaidPoints(TouchPointsPlayer raidPoints) {
			this.raidPoints = raidPoints;
		}

		public String getRaidBonusPoints() {
			return raidBonusPoints;
		}

		public void setRaidBonusPoints(String raidBonusPoints) {
			this.raidBonusPoints = raidBonusPoints;
		}

		@Override
		public String toString() {
			return "RaidPointsPlayer [totalRaidPoints=" + totalRaidPoints + ", raidBonusPoints=" + raidBonusPoints
					+ "]";
		}
         
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
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
    
    @JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonIgnoreProperties(ignoreUnknown = true)
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
    
    @JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonIgnoreProperties(ignoreUnknown = true)
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
    
    @JsonIgnoreProperties(ignoreUnknown = true)
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
   
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Roles {
        @JsonProperty("role")
        public List<Role> role;

		public List<Role> getRole() {
			return role;
		}

		public void setRole(List<Role> role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "Roles [role=" + role + "]";
		}
        
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Role {
        @JsonProperty("role-id") 
        public String roleId;
        
        @JsonProperty("role-isprimary") 
        public String roleIsPrimary;
        
        @JsonProperty("rating") 
        public String rating;
        
        @JsonProperty("skill-id") 
        public String skillId;
        
        @JsonProperty("career-best-points") 
        public String careerBestPoints;

		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

		public String getRoleIsPrimary() {
			return roleIsPrimary;
		}

		public void setRoleIsPrimary(String roleIsPrimary) {
			this.roleIsPrimary = roleIsPrimary;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public String getSkillId() {
			return skillId;
		}

		public void setSkillId(String skillId) {
			this.skillId = skillId;
		}

		public String getCareerBestPoints() {
			return careerBestPoints;
		}

		public void setCareerBestPoints(String careerBestPoints) {
			this.careerBestPoints = careerBestPoints;
		}

		@Override
		public String toString() {
			return "Role [roleId=" + roleId + ", roleIsPrimary=" + roleIsPrimary + ", rating=" + rating + ", skillId="
					+ skillId + ", careerBestPoints=" + careerBestPoints + "]";
		}
        
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Impacts {
        @JsonProperty("against_six_or_more_antis") 
        public String againstSixOrMoreAntis;
        
        @JsonProperty("against_five_antis") 
        public String againstFiveAntis;
        
        @JsonProperty("against_four_antis") 
        public String againstFourAntis;
        
        @JsonProperty("against_three_or_less_antis") 
        public String againstThreeOrLessAntis;

		public String getAgainstSixOrMoreAntis() {
			return againstSixOrMoreAntis;
		}

		public void setAgainstSixOrMoreAntis(String againstSixOrMoreAntis) {
			this.againstSixOrMoreAntis = againstSixOrMoreAntis;
		}

		public String getAgainstFiveAntis() {
			return againstFiveAntis;
		}

		public void setAgainstFiveAntis(String againstFiveAntis) {
			this.againstFiveAntis = againstFiveAntis;
		}

		public String getAgainstFourAntis() {
			return againstFourAntis;
		}

		public void setAgainstFourAntis(String againstFourAntis) {
			this.againstFourAntis = againstFourAntis;
		}

		public String getAgainstThreeOrLessAntis() {
			return againstThreeOrLessAntis;
		}

		public void setAgainstThreeOrLessAntis(String againstThreeOrLessAntis) {
			this.againstThreeOrLessAntis = againstThreeOrLessAntis;
		}

		public Impacts() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Impacts [againstSixOrMoreAntis=" + againstSixOrMoreAntis + ", againstFiveAntis=" + againstFiveAntis
					+ ", againstFourAntis=" + againstFourAntis + ", againstThreeOrLessAntis=" + againstThreeOrLessAntis
					+ "]";
		}
        
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformanceAgainstTeam {
        @JsonProperty("team-id") 
        public String teamId;
        
        @JsonProperty("matches") 
        public String matches;
        
        @JsonProperty("points") 
        public com.api.model.kabaddi.InMatchData.Points points;
        
        @JsonProperty("raids") 
        public Raids raids;
        
        @JsonProperty("tackles") 
        public Tackles tackles;

        @Override
        public String toString() {
            return "PerformanceAgainstTeam [teamId=" + teamId + ", matches=" + matches + ", points=" + points
                    + ", raids=" + raids + ", tackles=" + tackles + "]";
        }
    }

}
