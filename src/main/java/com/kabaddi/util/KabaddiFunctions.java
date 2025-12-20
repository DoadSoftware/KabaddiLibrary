package com.kabaddi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.compress.harmony.pack200.NewAttributeBands.Integral;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.api.model.kabaddi.InMatchData;
import com.api.model.kabaddi.InMatchData.PlayByPlay;
import com.api.model.kabaddi.InMatchData.Raid;
import com.api.model.kabaddi.InMatchData.RaidTeam;
import com.api.model.kabaddi.PreMatchData;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kabaddi.model.PlayerComparison;
import com.kabaddi.model.Api_Match;
import com.kabaddi.model.Api_pre_match;
import com.kabaddi.model.Configurations;
import com.kabaddi.model.Do_Or_Die;
import com.kabaddi.model.Fixture;
import com.kabaddi.model.LeagueTeam;
import com.kabaddi.model.Match;
import com.kabaddi.model.MatchStats;
import com.kabaddi.model.Phase;
import com.kabaddi.model.Phase_of_play;
import com.kabaddi.model.PlayByRaids;
import com.kabaddi.model.PlayByTeams;
import com.kabaddi.model.Player;
import com.kabaddi.model.PlayerPreMatchData;
import com.kabaddi.model.PlayerStats;
import com.kabaddi.model.Points;
import com.kabaddi.model.PointsNminutes;
import com.kabaddi.model.RaidPoints;
import com.kabaddi.model.Raids;
import com.kabaddi.model.TacklePoints;
import com.kabaddi.model.Tackles;
import com.kabaddi.model.Team;
import com.kabaddi.model.TeamPlayerStats;
import com.kabaddi.model.play_by_play;
import com.kabaddi.service.KabaddiService;

public class KabaddiFunctions {
	
	public static Map<String, Map<String, Object>> ReadExcel(String Path) {

        Map<String, Map<String, Object>> dataMap = new LinkedHashMap<>();

        try (InputStream inputStream = new FileInputStream(Path);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); 
            org.apache.poi.ss.usermodel.Row headerRow = sheet.getRow(0); // Read the header row

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { 
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    String key = getCellValueAsString(row.getCell(0)).trim();
                    if (!key.isEmpty()) {
                        Map<String, Object> rowData = new LinkedHashMap<>();

                        for (int j = 1; j < row.getLastCellNum(); j++) {
                            String header = getCellValueAsString(headerRow.getCell(j)).trim();
                            Object cellValue = getCellValue(row.getCell(j));
                            if (cellValue != null && !cellValue.toString().isEmpty()) {
                                rowData.put(header, cellValue);
                            }
                        }
                        dataMap.put(key, rowData);
                    }
                }
            }

            // Print the HashMap
//            dataMap.forEach((key, value) -> {
//                System.out.println(key + " : " + value);
//            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("dataMap = " + dataMap);
		return dataMap;
    }
	public static Map<String, Object> Read_Excel(String Path) {

        Map<String, Object> rowData = new LinkedHashMap<>();

        try (InputStream inputStream = new FileInputStream(Path);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); 
            org.apache.poi.ss.usermodel.Row headerRow = sheet.getRow(0); // Read the header row
            
            for (int i = 0; i <= sheet.getLastRowNum(); i++) { 
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                if (row != null && ((org.apache.poi.ss.usermodel.Row) row).getCell(0) != null) {
                	for (int j = 0; j < row.getLastCellNum(); j++) {
                            String header = getCellValueAsString(headerRow.getCell(j)).trim();
                            Object cellValue = getCellValue(row.getCell(j));
                            if (cellValue != null && !cellValue.toString().isEmpty()) {
                                rowData.put(header, cellValue);
                            }
                    }
                }
            }

            // Print the HashMap
            rowData.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
		return rowData;
    }
	private static String getCellValueAsString(Cell cell) {
	        Object value = getCellValue(cell);
	        return value == null ? "" : value.toString();
	    }
	private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == (long) numericValue) {
                        return (long) numericValue; 
                    } else {
                        return numericValue;
                    }
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Unknown cell type";
        }
    }
	public static List<PlayByRaids> LastFiveRaids(int player_id, List<PlayByRaids> PlayerRaidsList) {
	    List<PlayByRaids> raids = new ArrayList<>();

	    for (int i = PlayerRaidsList.size() - 1; i >= 0; i--) {
	        if (player_id == PlayerRaidsList.get(i).getRaiding_player_id()) {
	            PlayByRaids raid = new PlayByRaids();
	            raid.setRaid_half(PlayerRaidsList.get(i).getRaid_half());
	            raid.setRaid_number(PlayerRaidsList.get(i).getRaid_number());
	            raid.setRaid_outcome_id(PlayerRaidsList.get(i).getRaid_outcome_id());
	            raid.setRaiding_player_id(PlayerRaidsList.get(i).getRaiding_player_id());
	            raid.setRaiding_team_id(PlayerRaidsList.get(i).getRaiding_team_id());

	            for (int j = 0; j < PlayerRaidsList.get(i).getTeam().size(); j++) {
	                if (PlayerRaidsList.get(i).getTeam().get(j).getTeam_id() == PlayerRaidsList.get(i).getRaiding_team_id()) {
	                    raid.getTeam().add(PlayerRaidsList.get(i).getTeam().get(j));
	                }
	            }
	            
	            raids.add(raid);
	        }
	    }

	    return raids;
	}

	public static Match SwapMatch(Match match) throws Exception {
		Match swapMatch = new Match();
		
		//clock
		swapMatch.setClock(match.getClock());
		
		swapMatch.setHomeTeamId(match.getAwayTeamId());
		swapMatch.setAwayTeamId(match.getHomeTeamId());
		
		//scores
		swapMatch.setHomeTeamScore(match.getAwayTeamScore());
		swapMatch.setAwayTeamScore(match.getHomeTeamScore());
		//team
		swapMatch.setHomeTeam(match.getAwayTeam());
		swapMatch.setAwayTeam(match.getHomeTeam());
		
		swapMatch.getApi_Match().setHomeTeam(match.getHomeTeam());
		swapMatch.getApi_Match().setAwayTeam(match.getAwayTeam());
		
		swapMatch.getApi_Match().setHomeTeamStats(match.getApi_Match().getAwayTeamStats());
		swapMatch.getApi_Match().setAwayTeamStats(match.getApi_Match().getHomeTeamStats());
		
		return swapMatch;
	}
	
	public static void setMatch(Api_Match match,Match session_match)throws Exception {
		
		InMatchData mch = new ObjectMapper().readValue(new File(KabaddiUtil.KABADDI_DIRECTORY + KabaddiUtil.DESTINATION_DIRECTORY +
				session_match.getMatchId() +"-in-match" + KabaddiUtil.JSON_EXTENSION), InMatchData.class);
		//TEAMS
		match.setHomeTeam(session_match.getHomeTeam());
		match.setAwayTeam(session_match.getAwayTeam());
		
		//team STATS
		match.setHomeTeamStats(new TeamPlayerStats());
		match.setAwayTeamStats(new TeamPlayerStats());
		
		setTeamStats(match.getHomeTeamStats() , mch.getInMatch().getTeamPlayersStatistics().getTeam().get(0));
		setTeamStats(match.getAwayTeamStats() , mch.getInMatch().getTeamPlayersStatistics().getTeam().get(1));
		
		if(match.getPlay_by_play()==null) {
			match.setPlay_by_play(new play_by_play());
		}
		match.getPlay_by_play().setPlayByRaids( new ArrayList<PlayByRaids>());
		setPlayByRaid(match ,mch);
		
		if(match.getPhase_of_play()==null) {
			match.setPhase_of_play(new Phase_of_play());
		}
		setPhaseOfPlay(match ,mch);
	}
	
	public static void setMatchApi(Api_Match match, Match session_match)throws Exception {
		
		Api_Match mch = new ObjectMapper().readValue(new File(KabaddiUtil.KABADDI_DIRECTORY + KabaddiUtil.DESTINATION_DIRECTORY +
				session_match.getMatchId() +"-in-match" + KabaddiUtil.JSON_EXTENSION), Api_Match.class);
		//TEAMS
		match.setHomeTeam(session_match.getHomeTeam());
		match.setAwayTeam(session_match.getAwayTeam());
		
		//team STATS
		match.setHomeTeamStats(mch.getHomeTeamStats());
		match.setAwayTeamStats(mch.getAwayTeamStats());
		match.setPlay_by_play(mch.getPlay_by_play());
		match.setPhase_of_play(mch.getPhase_of_play());
		
	}
	
	
	private static void setPhaseOfPlay(Api_Match match, InMatchData mch) {
		if (match.getPhase_of_play() == null || mch.getInMatch() == null || 
		        mch.getInMatch().getPhase_of_play() == null || 
		        mch.getInMatch().getPhase_of_play().getPhase() == null) {
		        return;
		  }
		match.getPhase_of_play().setPhase(new ArrayList<Phase>());
		
		for(com.api.model.kabaddi.InMatchData.Phase phase :mch.getInMatch().getPhase_of_play().getPhase()) {
			Phase pse = new Phase();
			pse.setPhase_name(phase.getPhaseName());
			pse.setTeam(new ArrayList<PlayByTeams>());
			
			if (phase.getTeam() != null) {
				 
			 for (com.api.model.kabaddi.InMatchData.Team tm : phase.getTeam()) {
				 
				 PlayByTeams team = new PlayByTeams();
				 	
				 team.setTeam_id(Integer.valueOf(tm.getTeamId()));
				 	team.setTeam_name(tm.getTeamName());
				 	team.setTotal_points(Integer.valueOf(tm.getPoints().getTotalPoints()));
				 	 
				 	Points point = new Points();
				 	 
	                if (tm.getPoints() != null) {
	                    point.setAll_out_points(tm.getPoints().getAllOutPoints() != null ? Integer.parseInt(tm.getPoints().getAllOutPoints()) : 0);
	                    point.setTotalPoints(tm.getPoints().getTotalPoints() != null ? Integer.parseInt(tm.getPoints().getTotalPoints()) : 0);
	                    point.setExtra_points(tm.getPoints().getExtraPoints() != null ? Integer.parseInt(tm.getPoints().getExtraPoints()) : 0);
	                    point.setTotalPoints(tm.getPoints().getTotalPoints() != null ? Integer.parseInt(tm.getPoints().getTotalPoints()) : 0);

	                    point.setRaid_points(new ArrayList<>());
	                    if (tm.getPoints().getRaidPoints() != null) {
	                        point.getRaid_points().add(new RaidPoints(
	                            tm.getPoints().getRaidPoints().getTotalRaidPoints() != null ? Integer.parseInt(tm.getPoints().getRaidPoints().getTotalRaidPoints()) : 0,
	                            tm.getPoints().getRaidPoints().getTouchPoints() != null ? Integer.parseInt(tm.getPoints().getRaidPoints().getTouchPoints()) : 0,
	                            tm.getPoints().getRaidPoints().getRaidBonusPoints() != null ? Integer.parseInt(tm.getPoints().getRaidPoints().getRaidBonusPoints()) : 0
	                        ));
	                    }

	                    point.setTackle_points(new ArrayList<>());
	                    if (tm.getPoints().getTacklePoints() != null) {
	                        point.getTackle_points().add(new TacklePoints(
	                            tm.getPoints().getTacklePoints().getTotalTacklePoints() != null ? Integer.parseInt(tm.getPoints().getTacklePoints().getTotalTacklePoints()) : 0,
	                            tm.getPoints().getTacklePoints().getCapturePoints() != null ? Integer.parseInt(tm.getPoints().getTacklePoints().getCapturePoints()) : 0,
	                            tm.getPoints().getTacklePoints().getTackleBonusPoints() != null ? Integer.parseInt(tm.getPoints().getTacklePoints().getTackleBonusPoints()) : 0
	                        ));
	                    }
	                }
		            team.setPoints(point);	
		            pse.getTeam().add(team);
				} 
			 }
			match.getPhase_of_play().getPhase().add(pse);
		}
	}
	private static void setPlayByRaid(Api_Match match, InMatchData mch) {
	    if (match.getPlay_by_play() == null || mch.getInMatch() == null || 
	        mch.getInMatch().getPlay_by_play() == null || 
	        mch.getInMatch().getPlay_by_play().getRaid() == null) {
	        return;
	    }

	    match.getPlay_by_play().setPlayByRaids(new ArrayList<>());

	    for (Raid raid : mch.getInMatch().getPlay_by_play().getRaid()) {
	        PlayByRaids raids = new PlayByRaids();

	        raids.setRaid_number(raid.getRaidNumber() != null ? Integer.parseInt(raid.getRaidNumber()) : 0);
	        raids.setRaid_half(raid.getRaidHalf() != null ? Integer.parseInt(raid.getRaidHalf()) : 0);
	        raids.setRaid_outcome_id(raid.getRaidOutcomeId() != null ? Integer.parseInt(raid.getRaidOutcomeId()) : 0);
	        raids.setRaiding_player_id(raid.getRaidingPlayerId() != null ? Integer.parseInt(raid.getRaidingPlayerId()) : 0);
	        raids.setRaiding_team_id(raid.getRaidingTeamId() != null ? Integer.parseInt(raid.getRaidingTeamId()) : 0);

	        raids.setTeam(new ArrayList<>());

	        if (raid.getTeam() != null) {
	            for (RaidTeam tm : raid.getTeam()) {
	                PlayByTeams team = new PlayByTeams();
	                team.setTeam_id(tm.getTeamId() != null ? Integer.parseInt(tm.getTeamId()) : 0);
	                team.setTotal_points(tm.getTotalPoints() != null ? Integer.parseInt(tm.getTotalPoints()) : 0);

	                Points point = new Points();
	                if (tm.getPoints() != null) {
	                    point.setAll_out_points(tm.getPoints().getAllOutPoints() != null ? Integer.parseInt(tm.getPoints().getAllOutPoints()) : 0);
	                    point.setTotalPoints(tm.getPoints().getTotalPoints() != null ? Integer.parseInt(tm.getPoints().getTotalPoints()) : 0);
	                    point.setExtra_points(tm.getPoints().getExtraPoints() != null ? Integer.parseInt(tm.getPoints().getExtraPoints()) : 0);
	                    point.setTotalPoints(tm.getPoints().getTotalPoints() != null ? Integer.parseInt(tm.getPoints().getTotalPoints()) : 0);

	                    point.setRaid_points(new ArrayList<>());
	                    if (tm.getPoints().getRaidPoints() != null) {
	                        point.getRaid_points().add(new RaidPoints(
	                            tm.getPoints().getRaidPoints().getTotalRaidPoints() != null ? Integer.parseInt(tm.getPoints().getRaidPoints().getTotalRaidPoints()) : 0,
	                            tm.getPoints().getRaidPoints().getTouchPoints() != null ? Integer.parseInt(tm.getPoints().getRaidPoints().getTouchPoints()) : 0,
	                            tm.getPoints().getRaidPoints().getRaidBonusPoints() != null ? Integer.parseInt(tm.getPoints().getRaidPoints().getRaidBonusPoints()) : 0
	                        ));
	                    }

	                    point.setTackle_points(new ArrayList<>());
	                    if (tm.getPoints().getTacklePoints() != null) {
	                        point.getTackle_points().add(new TacklePoints(
	                            tm.getPoints().getTacklePoints().getTotalTacklePoints() != null ? Integer.parseInt(tm.getPoints().getTacklePoints().getTotalTacklePoints()) : 0,
	                            tm.getPoints().getTacklePoints().getCapturePoints() != null ? Integer.parseInt(tm.getPoints().getTacklePoints().getCapturePoints()) : 0,
	                            tm.getPoints().getTacklePoints().getTackleBonusPoints() != null ? Integer.parseInt(tm.getPoints().getTacklePoints().getTackleBonusPoints()) : 0
	                        ));
	                    }
	                }

	                team.setPoints(point);
	                raids.getTeam().add(team);
	            }
	        }

	        match.getPlay_by_play().getPlayByRaids().add(raids);
	    }
	}

	public static void setPreMatch(Api_pre_match match,Match session_match)throws Exception {
		PreMatchData mch = null ;
		if(session_match.getCategoryType().equalsIgnoreCase("MEN")) {
			mch = new ObjectMapper().readValue(new File(KabaddiUtil.KABADDI_DIRECTORY + KabaddiUtil.DESTINATION_DIRECTORY +"pre-match_76m" 
					+ KabaddiUtil.JSON_EXTENSION), PreMatchData.class);
		}else  if(session_match.getCategoryType().equalsIgnoreCase("WOMEN")) {
			mch = new ObjectMapper().readValue(new File(KabaddiUtil.KABADDI_DIRECTORY + KabaddiUtil.DESTINATION_DIRECTORY +"pre-match_77m" 
					+ KabaddiUtil.JSON_EXTENSION), PreMatchData.class);
		}
		if(mch == null) {
			return;
		}
		match.setTeamPlayerStats(new ArrayList<TeamPlayerStats>());
		
		for(com.api.model.kabaddi.PreMatchData.Team tm :mch.getPreMatch().getTeamPlayersStatistics().getTeam()) {
			TeamPlayerStats team = new TeamPlayerStats();
			team.setTeamId(Integer.valueOf(tm.getTeamId()));
			team.setTeamName(tm.getTeamName());
			 
			Points point = new Points();
			 
			 team.setPoints(new ArrayList<Points>());
			 point.setRaid_points(new ArrayList<RaidPoints>());
			 point.setTackle_points(new ArrayList<TacklePoints>());
			 team.setRaids(new ArrayList<Raids>());
			 team.setTackles(new ArrayList<Tackles>());
			 team.setDo_or_die(new ArrayList<Do_Or_Die>());

			 team.setMatches(Integer.valueOf(tm.getMatches()));
			 team.setWon(Integer.valueOf(tm.getWon()));
			 team.setLost(Integer.valueOf(tm.getLost()));
			 team.setTied(Integer.valueOf(tm.getTied()));
			 
			 point.setAll_out_points(Integer.valueOf(tm.getPoints().getAllOutPoints()));
			 point.setTotalPoints(Integer.valueOf(tm.getPoints().getTotalPoints()));
			 point.setExtra_points(Integer.valueOf(tm.getPoints().getExtraPoints()));
			 
			 point.getRaid_points().add(new RaidPoints(
					 Integer.valueOf(tm.getPoints().getRaidPoints().getTotalRaidPoints()),
					 Integer.valueOf(tm.getPoints().getRaidPoints().getTouchPoints()), 
					 Integer.valueOf(tm.getPoints().getRaidPoints().getRaidBonusPoints())));
			 
			 point.getTackle_points().add(new TacklePoints(
					Integer.parseInt(tm.getPoints().getTacklePoints().getTotalTacklePoints()),
				    Integer.parseInt(tm.getPoints().getTacklePoints().getCapturePoints()),
				    Integer.parseInt(tm.getPoints().getTacklePoints().getTackleBonusPoints())));

			team.getPoints().add(point);
			 
			team.getRaids().add(new Raids(
					 Integer.parseInt(tm.getRaids().getTotalRaids()) ,
					 Integer.parseInt(tm.getRaids().getSuperRaids()) ,
					 Integer.parseInt(tm.getRaids().getSuccessfulRaids()) ,
					 Integer.parseInt(tm.getRaids().getUnsuccessfulRaids()) ,
					 Integer.parseInt(tm.getRaids().getEmptyRaids()) ));
			 
			team.getTackles().add(new Tackles(
					 Integer.parseInt(tm.getTackles().getTotalTackles()) ,
					 Integer.parseInt(tm.getTackles().getSuperTackles()) ,
					 Integer.parseInt(tm.getTackles().getSuccessfulTackles()) ,
					 Integer.parseInt(tm.getTackles().getUnsuccessfulTackles()) 
					 ));
			 
			team.getDo_or_die().add(new Do_Or_Die(
					 Integer.parseInt(tm.getDoOrDie().getTotalRaids()) ,
					 Integer.parseInt(tm.getDoOrDie().getSuccessfullRaids()) ,
					 Integer.parseInt(tm.getDoOrDie().getFailedRaids()) ,
					 Integer.parseInt(tm.getDoOrDie().getSuperRaids()) ,
					 Integer.parseInt(tm.getDoOrDie().getRaidPoints()) ,
					 Integer.parseInt(tm.getDoOrDie().getTouchPoints()) ,
					 Integer.parseInt(tm.getDoOrDie().getBonusPoints()) 
					 ));
			match.getTeamPlayerStats().add(team);
			match.getTeamPlayerStats().get(match.getTeamPlayerStats().size()-1).setPlayerStats(new ArrayList<PlayerStats>());
			if(tm.getPlayers()!=null && tm.getPlayers().getPlayer()!=null) {
				for(com.api.model.kabaddi.PreMatchData.Player ply :tm.getPlayers().getPlayer()) {
					 PlayerStats PlayerStats = new PlayerStats();
					 
					 PlayerStats.setPlayerId(Integer.valueOf(ply.getPlayerId()));
					 PlayerStats.setHigh_five(Integer.valueOf(ply.getHighFive()));
					 PlayerStats.setSuper_ten(Integer.valueOf(ply.getSuperTen()));
					 PlayerStats.setMatches(Integer.valueOf(ply.getMatches()));
					 PlayerStats.setMatches(Integer.valueOf(ply.getMatches()));
					 PlayerStats.setMatches_raided(Integer.valueOf(ply.getMatchesRaided()));
					 
					 point = new Points();
					 
					 PlayerStats.setPoints(new ArrayList<Points>());
					 point.setRaid_points(new ArrayList<RaidPoints>());
					 point.setTackle_points(new ArrayList<TacklePoints>());
					 PlayerStats.setRaids(new ArrayList<Raids>());
					 PlayerStats.setTackles(new ArrayList<Tackles>());
					 PlayerStats.setDo_or_die(new ArrayList<Do_Or_Die>());

					 point.setTotalPoints(Integer.valueOf(ply.getPoints().getTotalPoints()));
					 
					 point.getRaid_points().add( new RaidPoints(
							 Integer.valueOf(ply.getPoints().getRaidPoints().getTotalRaidPoints()),
							 Integer.valueOf(ply.getPoints().getRaidPoints().getRaidPoints().getTotalTouchPoints()), 
							 Integer.valueOf(ply.getPoints().getRaidPoints().getTotalRaidPoints())));
					 
					 point.getTackle_points().add(  new TacklePoints(
							Integer.parseInt(ply.getPoints().getTacklePoints().getTotalTacklePoints()),
						    Integer.parseInt((ply.getPoints().getTacklePoints().getCapturePoints().getTotalCapturePoints())),
						    Integer.parseInt(ply.getPoints().getTacklePoints().getTackleBonusPoints())));

					 PlayerStats.getPoints().add(point);
					 
					 PlayerStats.getRaids().add(new Raids(
							 Integer.parseInt(ply.getRaids().getTotalRaids()) ,
							 Integer.parseInt(ply.getRaids().getSuperRaids()) ,
							 Integer.parseInt(ply.getRaids().getSuccessfulRaids()) ,
							 Integer.parseInt(ply.getRaids().getUnsuccessfulRaids()) ,
							 Integer.parseInt(ply.getRaids().getEmptyRaids()) ));
					 
					 PlayerStats.getTackles().add(new Tackles(
							 Integer.parseInt(ply.getTackles().getTotalTackles()) ,
							 Integer.parseInt(ply.getTackles().getSuperTackles()) ,
							 Integer.parseInt(ply.getTackles().getSuccessfulTackles()) ,
							 Integer.parseInt(ply.getTackles().getUnsuccessfulTackles()) 
							 ));
					 
					 PlayerStats.getDo_or_die().add(new Do_Or_Die(
							 Integer.parseInt(ply.getDoOrDie().getTotalRaids()) ,
							 Integer.parseInt(ply.getDoOrDie().getSuccessfullRaids()) ,
							 Integer.parseInt(ply.getDoOrDie().getFailedRaids()) ,
							 Integer.parseInt(ply.getDoOrDie().getSuperRaids()) ,
							 Integer.parseInt(ply.getDoOrDie().getRaidPoints()) ,
							 Integer.parseInt(ply.getDoOrDie().getTouchPoints()) ,
							 Integer.parseInt(ply.getDoOrDie().getBonusPoints()) 
							 ));
					 
					 match.getTeamPlayerStats().get(match.getTeamPlayerStats().size()-1).getPlayerStats().add(PlayerStats);
				 }	
			} 
		}
	}
	public static void setTeamStats(TeamPlayerStats team ,com.api.model.kabaddi.InMatchData.Team tm)throws Exception {
		team.setTeamId(Integer.valueOf(tm.getTeamId()));
		team.setTeamName(tm.getTeamName());
		team.setNo_of_players_on_court(Integer.valueOf(tm.getNoOfPlayersOnCourt()));
		 
		Points point = new Points();
		 
		team.setPoints(new ArrayList<Points>());
		point.setRaid_points(new ArrayList<RaidPoints>());
		point.setTackle_points(new ArrayList<TacklePoints>());
		team.setRaids(new ArrayList<Raids>());
		team.setTackles(new ArrayList<Tackles>());
		team.setDo_or_die(new ArrayList<Do_Or_Die>());

		 
		 point.setAll_out_points(Integer.valueOf(tm.getPoints().getAllOutPoints()));
		 point.setTotalPoints(Integer.valueOf(tm.getPoints().getTotalPoints()));
		 point.setExtra_points(Integer.valueOf(tm.getPoints().getExtraPoints()));
		 
		 point.getRaid_points().add(new RaidPoints(
				 Integer.valueOf(tm.getPoints().getRaidPoints().getTotalRaidPoints()),
				 Integer.valueOf(tm.getPoints().getRaidPoints().getTouchPoints()), 
				 Integer.valueOf(tm.getPoints().getRaidPoints().getRaidBonusPoints())));
		 
		 point.getTackle_points().add(new TacklePoints(
				Integer.parseInt(tm.getPoints().getTacklePoints().getTotalTacklePoints()),
			    Integer.parseInt(tm.getPoints().getTacklePoints().getCapturePoints()),
			    Integer.parseInt(tm.getPoints().getTacklePoints().getTackleBonusPoints())));

		team.getPoints().add(point);
		 
		team.setPointsNminutes(new PointsNminutes(
				 Integer.parseInt(tm.getPointsLastNMinutes().getFive()) ,
				 Integer.parseInt(tm.getPointsLastNMinutes().getTen())));
			
		team.getRaids().add(new Raids(
				 Integer.parseInt(tm.getRaids().getTotalRaids()) ,
				 Integer.parseInt(tm.getRaids().getSuperRaids()) ,
				 Integer.parseInt(tm.getRaids().getSuccessfulRaids()) ,
				 Integer.parseInt(tm.getRaids().getUnsuccessfulRaids()) ,
				 Integer.parseInt(tm.getRaids().getEmptyRaids()) ));
		 
		team.getTackles().add(new Tackles(
				 Integer.parseInt(tm.getTackles().getTotalTackles()) ,
				 Integer.parseInt(tm.getTackles().getSuperTackles()) ,
				 Integer.parseInt(tm.getTackles().getSuccessfulTackles()) ,
				 Integer.parseInt(tm.getTackles().getUnsuccessfulTackles()) 
				 ));
		 
		team.getDo_or_die().add(new Do_Or_Die(
				 Integer.parseInt(tm.getDoOrDie().getTotalRaids()) ,
				 Integer.parseInt(tm.getDoOrDie().getSuccessfulRaids()) ,
				 Integer.parseInt(tm.getDoOrDie().getFailedRaids()) ,
				 Integer.parseInt(tm.getDoOrDie().getSuperRaids()) ,
				 Integer.parseInt(tm.getDoOrDie().getRaidPoints()) ,
				 Integer.parseInt(tm.getDoOrDie().getTouchPoints()) ,
				 Integer.parseInt(tm.getDoOrDie().getBonusPoints()) 
				 ));
		
		 team.setPlayerStats(new ArrayList<PlayerStats>());
		 
		 for(com.api.model.kabaddi.InMatchData.Player ply :tm.getPlayers().getPlayer()) {
			 
			 PlayerStats PlayerStats = new PlayerStats();
			 PlayerStats.setPlayerId(Integer.valueOf(ply.getPlayerId()));
			 PlayerStats.setPlayer_on_court(ply.getPlayerOnCourt());
			 PlayerStats.setAvg_raid_time(Integer.valueOf(ply.getAvgRaidTime()));
			 PlayerStats.setPlayer_on_court((ply.getPlayerOnCourt()==null ? "" : ply.getPlayerOnCourt()));
			 PlayerStats.setPlayer_revival_order((ply.getPlayerRevivalOrder()==null ?"":ply.getPlayerRevivalOrder()));
			 PlayerStats.setTime_off_court(Integer.valueOf(ply.getTimeOffCourt()));

			 point = new Points();
			 
			 PlayerStats.setPoints(new ArrayList<Points>());
			 point.setRaid_points(new ArrayList<RaidPoints>());
			 point.setTackle_points(new ArrayList<TacklePoints>());
			 PlayerStats.setRaids(new ArrayList<Raids>());
			 PlayerStats.setTackles(new ArrayList<Tackles>());
			 PlayerStats.setDo_or_die(new ArrayList<Do_Or_Die>());

			 point.setTotalPoints(Integer.valueOf(ply.getPoints().getTotalPoints()));
			 
			 point.getRaid_points().add( new RaidPoints(
					 Integer.valueOf(ply.getPoints().getRaidPoints().getTotalRaidPoints()),
					 Integer.valueOf(ply.getPoints().getRaidPoints().getTouchPoints().getTotalTouchPoints()), 
					 Integer.valueOf(ply.getPoints().getRaidPoints().getTotalRaidPoints())));
			 
			 point.getTackle_points().add(  new TacklePoints(
					Integer.parseInt(ply.getPoints().getTacklePoints().getTotalTacklePoints()),
				    Integer.parseInt((ply.getPoints().getTacklePoints().getCapturePoints().getTotalCapturePoints())),
				    Integer.parseInt(ply.getPoints().getTacklePoints().getTackleBonusPoints())));

			 PlayerStats.getPoints().add(point);
			 
			 PlayerStats.getRaids().add(new Raids(
					 Integer.parseInt(ply.getRaids().getTotalRaids()) ,
					 Integer.parseInt(ply.getRaids().getSuperRaids()) ,
					 Integer.parseInt(ply.getRaids().getSuccessfulRaids()) ,
					 Integer.parseInt(ply.getRaids().getUnsuccessfulRaids()) ,
					 Integer.parseInt(ply.getRaids().getEmptyRaids()) ));
			 
			 PlayerStats.getTackles().add(new Tackles(
					 Integer.parseInt(ply.getTackles().getTotalTackles()) ,
					 Integer.parseInt(ply.getTackles().getSuperTackles()) ,
					 Integer.parseInt(ply.getTackles().getSuccessfulTackles()) ,
					 Integer.parseInt(ply.getTackles().getUnsuccessfulTackles()) 
					 ));
			 
			 PlayerStats.getDo_or_die().add(new Do_Or_Die(
					 Integer.parseInt(ply.getDoOrDie().getTotalRaids()) ,
					 Integer.parseInt(ply.getDoOrDie().getSuccessfulRaids()) ,
					 Integer.parseInt(ply.getDoOrDie().getFailedRaids()) ,
					 Integer.parseInt(ply.getDoOrDie().getSuperRaids()) ,
					 Integer.parseInt(ply.getDoOrDie().getRaidPoints()) ,
					 Integer.parseInt(ply.getDoOrDie().getTouchPoints()) ,
					 Integer.parseInt(ply.getDoOrDie().getBonusPoints()) 
					 ));
			 
			 team.getPlayerStats().add(PlayerStats);
		 }
	}

	public static List<LeagueTeam> PointsTableAsStanding(List<LeagueTeam> points_table, Match match) throws IOException {
		
		if(match.getHomeTeamScore() > match.getAwayTeamScore()) {
			for(LeagueTeam table : points_table) {
				if(table.getTeamName().equalsIgnoreCase(match.getHomeTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setWon(table.getWon()+1);
					table.setGoal_For(table.getGoal_For() + match.getHomeTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getAwayTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 4);
				}
				if(table.getTeamName().equalsIgnoreCase(match.getAwayTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setLost(table.getLost()+1);
					table.setGoal_For(table.getGoal_For() + match.getAwayTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getHomeTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					if((match.getHomeTeamScore() - match.getAwayTeamScore()) <= 7) {
						table.setPoints(table.getPoints() + 1);
					}
				}
			}
		}else if(match.getHomeTeamScore() < match.getAwayTeamScore()) {
			for(LeagueTeam table : points_table) {
				if(table.getTeamName().equalsIgnoreCase(match.getAwayTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setWon(table.getWon()+1);
					table.setGoal_For(table.getGoal_For() + match.getAwayTeamScore());
					table.setGoal_Against(table.getGoal_Against()+ match.getHomeTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 4);
				}
				if(table.getTeamName().equalsIgnoreCase(match.getHomeTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setLost(table.getLost()+1);
					table.setGoal_For(table.getGoal_For() + match.getHomeTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getAwayTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					if((match.getAwayTeamScore() - match.getHomeTeamScore()) <= 7) {
						table.setPoints(table.getPoints() + 1);
					}
				}
			}
		}else if(match.getHomeTeamScore() == match.getAwayTeamScore()) {
			for(LeagueTeam table : points_table) {
				if(table.getTeamName().equalsIgnoreCase(match.getAwayTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setDrawn(table.getDrawn() + 1);
					table.setGoal_For(table.getGoal_For() + match.getAwayTeamScore());
					table.setGoal_Against(table.getGoal_Against()+ match.getHomeTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 2);
				}
				if(table.getTeamName().equalsIgnoreCase(match.getHomeTeam().getTeamBadge())) {
					table.setPlayed(table.getPlayed()+1);
					table.setDrawn(table.getDrawn() + 1);
					table.setGoal_For(table.getGoal_For() + match.getHomeTeamScore());
					table.setGoal_Against(table.getGoal_Against() + match.getAwayTeamScore());
					table.setGD(table.getGoal_For() - table.getGoal_Against());
					table.setPoints(table.getPoints() + 2);
				}
			}
		}
		Collections.sort(points_table,new KabaddiFunctions.PointsComparator());
		
		return points_table;
	}
	
	public static int mapNumber(int n) {
        int groupIndex = (n - 1) / 6;
        int base = groupIndex * 3;
        int positionInGroup = (n - 1) % 6;
        int offset = positionInGroup % 3;

        return base + offset + 1;
    }
	
	public static String checkWhichDay(String dateStr, String firstDate) {
        String whichDay = "";
        try {
            // Parse all dates into Date objects and sort them
            Set<Date> dateSet = new TreeSet<>();
            dateSet.add(new SimpleDateFormat("dd-MM-yyyy").parse(firstDate));

            // Get the earliest date
            Date startDate = ((TreeSet<Date>) dateSet).first();
            Date matchDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);

            // Calculate difference in days
            long diffInMillies = matchDate.getTime() - startDate.getTime();
            long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);

            whichDay = "DAY " + (diffInDays + 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return whichDay;
    }
	
	public static class PointsComparator implements Comparator<LeagueTeam> {
	    @Override
	    public int compare(LeagueTeam pt1, LeagueTeam pt2) {
	    	if(pt2.getPoints() == pt1.getPoints()) {
	    		return Integer.compare(pt2.getGD(), pt1.getGD());
	    	}else {
	    		return Integer.compare(pt2.getPoints(), pt1.getPoints());
	    	}
	    }
	}
	
	public static List<Api_pre_match> getPreMatchDatafromXML(String file_path,String file_name) 
			throws SAXException, IOException, ParserConfigurationException, FactoryConfigurationError{
		
		List<Api_pre_match> api_pre_match = new ArrayList<Api_pre_match>();
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(file_path + file_name));
        doc.getDocumentElement().normalize();
        NodeList childNodes = doc.getDocumentElement().getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++) {
        	if(childNodes.item(i).getNodeType() == Node.ELEMENT_NODE && childNodes.item(i).getNodeName().equals("team-players-statistics")) {
        		
        		api_pre_match.add(new Api_pre_match(new ArrayList<TeamPlayerStats>()));
        		
        		for(int j=0;j<childNodes.item(i).getChildNodes().getLength();j++) {
        			if(childNodes.item(i).getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE 
            				&& childNodes.item(i).getChildNodes().item(j).getNodeName().equalsIgnoreCase("team")) {
        				
        				api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().add(new TeamPlayerStats(childNodes.item(i).getChildNodes().item(j).getAttributes().
	        					getNamedItem("team-name").getNodeValue(), Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getAttributes().getNamedItem("team-id")
	    	        			.getNodeValue()), 0, 0, 0, 0, new ArrayList<Points>(),new ArrayList<Raids>(),new ArrayList<Tackles>(),new ArrayList<Do_Or_Die>(),
	        					new ArrayList<PlayerStats>()));
        				
        				for(int k = 0; k < childNodes.item(i).getChildNodes().item(j).getChildNodes().getLength();k++) {
        					if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("matches")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).
        							setMatches(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()));
        						
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("won")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).
        							setWon(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()));
        						
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("lost")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).
        							setLost(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()));
        						
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("tied")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).
        							setTied(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getFirstChild().getNodeValue()));
        						
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("points")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).
        						getPoints().add(new Points(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().
	        							getNamedItem("total-points").getNodeValue()), new ArrayList<TacklePoints>(), new ArrayList<RaidPoints>()));
        						
        						for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength();l++) {
        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("all-out-points")) {

        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1)
        									.getPoints().size()-1).setAll_out_points(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
        									.getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("extra-points")) {
        					
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1)
    									.getPoints().size()-1).setExtra_points(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
    									.getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("tackle-points")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
        									getTackle_points().add(new TacklePoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).
        									getAttributes().getNamedItem("total-tackle-points").getNodeValue())));
        								
        								for(int m = 0; m < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().getLength();m++) {
        									if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
        											childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().
        											equalsIgnoreCase("capture-points")) {
        									
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getTackle_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getTackle_points().size()-1).setCapturePoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
	        									.getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
        										
        									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
        											childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().
        											equalsIgnoreCase("tackle-bonus-points")) {
        										
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getTackle_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getTackle_points().size()-1).setTackleBounsPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
	        									.getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
        									}
        								}
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("raid-points")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
    									getRaid_points().add(new RaidPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).
    									getAttributes().getNamedItem("total-raid-points").getNodeValue())));
        								
	    								for(int m = 0; m < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().getLength();m++) {
	    									if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
	    											childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().
	    											equalsIgnoreCase("touch-points")) {
	    										
	    										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getRaid_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getRaid_points().size()-1).setTouchPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
	        									.getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
	    										
	    									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
	    											childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().
	    											equalsIgnoreCase("raid-bonus-points")) {
	    										
	    										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getRaid_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().
	        									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPoints().size()-1).
	        									getRaid_points().size()-1).setRaidBounsPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k)
	        									.getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
	    									}
	    								}	
        							}
        						}
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("raids")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().add(new Raids(
        						Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("total-raids").getNodeValue()),
        						Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("super-raids").getNodeValue())));
        						
        						for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength();l++) {
        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("successful-raids")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().size()-1)
    									.setSuccessfulRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("unsuccessful-raids")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().size()-1)
    									.setUnsuccessfulRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("empty-raids")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getRaids().size()-1)
    									.setEmptyRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        								
        							}
        						}
        						
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("tackles")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getTackles().add(new Tackles(
        						Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("total-tackles").getNodeValue()),
        						Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("super-tackles").getNodeValue())));
        						
        						for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength();l++) {
        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("successful-tackles")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getTackles().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getTackles().size()-1)
    									.setSuccessfulTackles(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("unsuccessful-tackles")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getTackles().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getTackles().size()-1)
    									.setUnsuccessfulTackles(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}
        						}
        						
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("do-or-die")) {
        						
        						api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
        						add(new Do_Or_Die(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getAttributes().getNamedItem("total-raids")
        						.getNodeValue())));
        						
        						for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength();l++) {
        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("successfull-raids")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().size()-1)
    									.setSuccessfullRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("failed-raids")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().size()-1)
    									.setFailedRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("super-raids")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().size()-1)
    									.setSuperRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("raid-points")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().size()-1)
    									.setRaidPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("touch-points")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().size()-1)
    									.setTouchPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("bonus-points")) {
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().
    									get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getDo_or_die().size()-1)
    									.setBonusPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getFirstChild().getNodeValue()));
        								
        							}
        						}
        					}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE &&
        							childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName().equalsIgnoreCase("players")) {
        						for(int l = 0; l < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().getLength();l++) {
        							//System.out.println(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName());
        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeType() == Node.ELEMENT_NODE &&
        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getNodeName().equalsIgnoreCase("player")){
        								
        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
    	        						add(new PlayerStats(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getAttributes().getNamedItem("player-name").getNodeValue(), 
    	        							Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getAttributes().getNamedItem("player-id").getNodeValue()),  
    	        							new ArrayList<Points>(),new ArrayList<Raids>(),new ArrayList<Tackles>(),new ArrayList<Do_Or_Die>()));
        								
        								for(int m = 0; m < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().getLength();m++) {
        									if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("high_five")) {
        										
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        										get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        										setHigh_five(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
        										
        									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("super_ten")) {
        										
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        										get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        										setSuper_ten(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
        										
        									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("matches")) {
        										
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        										get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        										setMatches(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
        										
        									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("matches_raided")) {
        										
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        										get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        										setMatches_raided(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getFirstChild().getNodeValue()));
        										
        									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("points")) {
        										
        										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
    	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
    	        								getPoints().add(new Points(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).
    	        								getAttributes().getNamedItem("total-points").getNodeValue()), new ArrayList<TacklePoints>(), new ArrayList<RaidPoints>()));
        										
        										for(int n = 0; n < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().getLength();n++) {
        											if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("raid-points")) {
        												
        												api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
            	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
            	        								getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
            	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
            	        								getPoints().size()-1).getRaid_points().add(new RaidPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).
            	        								getChildNodes().item(m).getChildNodes().item(n).getAttributes().getNamedItem("total-raid-points").getNodeValue())));
        												
        												for(int o = 0; o < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getChildNodes().getLength();o++) {
        													if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getChildNodes().item(o).
        														getNodeType() == Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).
        														getChildNodes().item(o).getNodeName().equalsIgnoreCase("touch-points")) {
        														
        														api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().size()-1).getRaid_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().size()-1).getRaid_points().size()-1).setTouchPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).
                												getChildNodes().item(n).getChildNodes().item(o).getAttributes().getNamedItem("total-touch-points").getNodeValue()));
        			    										
        			    									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getChildNodes().item(o).
            														getNodeType() == Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).
            														getChildNodes().item(o).getNodeName().equalsIgnoreCase("raid-bonus-points")) {
        			    										
        			    										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().size()-1).getRaid_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().size()-1).getRaid_points().size()-1).setRaidBounsPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).
        														getChildNodes().item(n).getChildNodes().item(o).getFirstChild().getNodeValue()));
        			    										
        			    									}
        												}
        												
        											}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("tackle-points")) {
        												
        												api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
            	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
            	        								getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
            	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
            	        								getPoints().size()-1).getTackle_points().add(new TacklePoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).
            	        								getChildNodes().item(m).getChildNodes().item(n).getAttributes().getNamedItem("total-tackle-points").getNodeValue())));
        												
        												for(int o = 0; o < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getChildNodes().getLength();o++) {
        													if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getChildNodes().item(o).
        														getNodeType() == Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).
        														getChildNodes().item(o).getNodeName().equalsIgnoreCase("capture-points")) {
        														
        														api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().size()-1).getTackle_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().size()-1).getTackle_points().size()-1).setCapturePoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).
                												getChildNodes().item(n).getChildNodes().item(o).getAttributes().getNamedItem("total-capture-points").getNodeValue()));
        			    										
        			    									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getChildNodes().item(o).
            														getNodeType() == Node.ELEMENT_NODE && childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).
            														getChildNodes().item(o).getNodeName().equalsIgnoreCase("tackle-bonus-points")) {
        			    										
        			    										api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        														get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        														getPoints().size()-1).getTackle_points().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
                												get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
                												getPoints().size()-1).getTackle_points().size()-1).setTackleBounsPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).
        														getChildNodes().item(n).getChildNodes().item(o).getFirstChild().getNodeValue()));
        			    										
        			    									}
        												}
        											}
        										}
        										
        									}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("raids")) {
    	        								
    	        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
    	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
    	        								getRaids().add(new Raids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).
    	        								getAttributes().getNamedItem("total-raids").getNodeValue()),Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().
    	        								item(l).getChildNodes().item(m).getAttributes().getNamedItem("super-raids").getNodeValue())));
    	        		        						
        		        						for(int n = 0; n < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().getLength();n++) {
        		        							
        		        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("successful-raids")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getRaids().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getRaids().size() -1).setSuccessfulRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("unsuccessful-raids")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getRaids().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getRaids().size() -1).setUnsuccessfulRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("empty-raids")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getRaids().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getRaids().size() -1).setEmptyRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        							}
        		        						}
    	        								
    	        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("tackles")) {
    	        								
    	        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
    	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
    	        								getTackles().add(new Tackles(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().
    	        								item(m).getAttributes().getNamedItem("total-tackles").getNodeValue()),Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).
    	        								getChildNodes().item(l).getChildNodes().item(m).getAttributes().getNamedItem("super-tackles").getNodeValue())));
    	        		        						
        		        						for(int n = 0; n < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().getLength();n++) {
        		        							
        		        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("successful-tackles")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getTackles().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getTackles().size() -1).setSuccessfulTackles(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("unsuccessful-tackles")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getTackles().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getTackles().size() -1).setUnsuccessfulTackles(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}
        		        						}
    	        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeType() == Node.ELEMENT_NODE &&
    	        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getNodeName().equalsIgnoreCase("do-or-die")) {
    	        								
    	        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
    	        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
    	        								getDo_or_die().add(new Do_Or_Die(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().
    	        								item(m).getAttributes().getNamedItem("total-raids").getNodeValue())));
    	        		        						
        		        						for(int n = 0; n < childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().getLength();n++) {
        		        							
        		        							if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("successful-raids")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().size() -1).setSuccessfullRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("failed-raids")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().size() -1).setFailedRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("super-raids")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().size() -1).setSuperRaids(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("raid-points")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().size() -1).setRaidPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("touch-points")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().size() -1).setTouchPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        								
        		        							}else if(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeType() == Node.ELEMENT_NODE &&
        		        									childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes().item(m).getChildNodes().item(n).getNodeName().equalsIgnoreCase("bonus-points")) {
        		        								
        		        								api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().
        		        								get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().get(api_pre_match.get(api_pre_match.size()-1).getTeamPlayerStats().size()-1).getPlayerStats().size()-1).
        		        								getDo_or_die().size() -1).setBonusPoints(Integer.valueOf(childNodes.item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes().item(l).getChildNodes()
        		        								.item(m).getChildNodes().item(n).getFirstChild().getNodeValue()));
        		        							}
        		        						}
    	        							}
        								}
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        }
        
		return api_pre_match;
	}
	
	public static void DoadWriteCommandToSelectedViz(int SelectedViz, String SendTextIn, List<PrintWriter> print_writers) 
	{
		if(SelectedViz > 0 && SelectedViz <= print_writers.size()) {
			print_writers.get(SelectedViz-1).println(SendTextIn);
		}
	}	
	public static void DoadWriteCommandToAllViz(String SendTextIn, List<PrintWriter> print_writers) 
	{
		for(int i = 0; i < print_writers.size(); i++) {
			print_writers.get(i).println(SendTextIn);
		}
	}
	
	public static String Plural(int count){
		if (count == 1){
			return "";
		} else{
			return "S";
		}
	}
	
	public static String ordinal(int i) {
	    int mod100 = i % 100;
	    int mod10 = i % 10;
	    if(mod10 == 1 && mod100 != 11) {
	        return i + "st";
	    } else if(mod10 == 2 && mod100 != 12) {
	        return i + "nd";
	    } else if(mod10 == 3 && mod100 != 13) {
	        return i + "rd";
	    } else {
	        return i + "th";
	    }
	}
	
	public static String convertSecondsToMinSec(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
	
	@SuppressWarnings("resource")
	public static List<PrintWriter> processPrintWriter(Configurations config) throws UnknownHostException, IOException
	{
		List<PrintWriter> print_writer = new ArrayList<PrintWriter>();
		
		if(config.getIpAddress() != null && !config.getIpAddress().isEmpty()) {
			print_writer.add(new PrintWriter(new Socket(config.getIpAddress(), 
					config.getPortNumber()).getOutputStream(), true));
		}
		
		if(config.getSecondaryipAddress() != null && !config.getSecondaryipAddress().isEmpty()) {
			print_writer.add(new PrintWriter(new Socket(config.getSecondaryipAddress(), 
					config.getSecondaryportNumber()).getOutputStream(), true));
		}
	
		return print_writer;
	}
	
	public static List<Fixture> processAllFixtures(KabaddiService kabaddiService) {
		List<Fixture> fixtures = kabaddiService.getFixtures();
		for(Team tm : kabaddiService.getTeams()) {
			for(Fixture fix : fixtures) {
				if(fix.getHometeamid() == tm.getTeamId()) {
					fix.setHome_Team(tm);
				}
				if(fix.getAwayteamid() == tm.getTeamId()) {
					fix.setAway_Team(tm);
				}
			}
		}
		return fixtures;
	}
	
	public static String twoDigitString(long number) {
	    if (number == 0) {
	        return "00";
	    }
	    if (number / 10 == 0) {
	        return "0" + number;
	    }
	    return String.valueOf(number);
	}
	
	public static String replace(float number) {
	    return String.valueOf(number).replace(".0", "");
	}
	
	public static Player populatePlayer(KabaddiService footballService, Player player, Match match)
	{
		Player this_plyr = new Player();
		this_plyr = footballService.getPlayer(KabaddiUtil.PLAYER, String.valueOf(player.getPlayerId()));
		if(this_plyr != null) {
			this_plyr.setPlayerPosition(player.getPlayerPosition()); this_plyr.setCaptainGoalKeeper(player.getCaptainGoalKeeper());
		}
		return this_plyr;
	}
	
	/*public static Match populateMatchVariables(KabaddiService footballService, Match match) {
	    List<Player> players;

	    players = match.getHomeSquad().stream()
	        .map(plyr -> populatePlayer(footballService, plyr, match))
	        .collect(Collectors.toList());
	    match.setHomeSquad(players);

	    players = match.getHomeSubstitutes().stream()
	        .map(plyr -> populatePlayer(footballService, plyr, match))
	        .collect(Collectors.toList());
	    match.setHomeSubstitutes(players);

	    if (match.getHomeOtherSquad() != null) {
	        players = match.getHomeOtherSquad().stream()
	            .map(plyr -> populatePlayer(footballService, plyr, match))
	            .collect(Collectors.toList());
	        match.setHomeOtherSquad(players);
	    }

	    players = match.getAwaySquad().stream()
	        .map(plyr -> populatePlayer(footballService, plyr, match))
	        .collect(Collectors.toList());
	    match.setAwaySquad(players);

	    players = match.getAwaySubstitutes().stream()
	        .map(plyr -> populatePlayer(footballService, plyr, match))
	        .collect(Collectors.toList());
	    match.setAwaySubstitutes(players);

	    if (match.getAwayOtherSquad() != null) {
	        players = match.getAwayOtherSquad().stream()
	            .map(plyr -> populatePlayer(footballService, plyr, match))
	            .collect(Collectors.toList());
	        match.setAwayOtherSquad(players);
	    }

	    try {
	        if (match.getHomeTeamId() > 0) {
	            match.setHomeTeam(footballService.getTeam(KabaddiUtil.TEAM, String.valueOf(match.getHomeTeamId())));
	        }
	        if (match.getAwayTeamId() > 0) {
	            match.setAwayTeam(footballService.getTeam(KabaddiUtil.TEAM, String.valueOf(match.getAwayTeamId())));
	        }
	        if (match.getGroundId() > 0) {
	            match.setGround(footballService.getGround(match.getGroundId()));
	            match.setVenueName(match.getGround().getFullname());
	        }
	    } catch (Exception e) {
	        // Log the error and handle it appropriately
	        e.printStackTrace();
	    }

	    if (match.getMatchStats() != null) {
	        for (MatchStats ms : match.getMatchStats()) {
	            try {
	                ms.setPlayer(footballService.getPlayer(KabaddiUtil.PLAYER, String.valueOf(ms.getPlayerId())));
	            } catch (Exception e) {
	                // Log the error and handle it appropriately
	                e.printStackTrace();
	            }
	        }
	    }

	    return match;
	}*/

	public static Match populateMatchVariables(KabaddiService footballService,Match match) 
	{
		List<Player> players = new ArrayList<Player>();
		
		for(Player plyr:match.getHomeSquad()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setHomeSquad(players);

		players = new ArrayList<Player>();
		for(Player plyr:match.getHomeSubstitutes()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setHomeSubstitutes(players);
		
		players = new ArrayList<Player>();
		if(match.getHomeOtherSquad() != null) {
			for(Player plyr:match.getHomeOtherSquad()) {
				players.add(populatePlayer(footballService, plyr, match));
			}
		}
		match.setHomeOtherSquad(players);
		
		players = new ArrayList<Player>();
		for(Player plyr:match.getAwaySquad()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setAwaySquad(players);

		players = new ArrayList<Player>();
		for(Player plyr:match.getAwaySubstitutes()) {
			players.add(populatePlayer(footballService, plyr, match));
		}
		match.setAwaySubstitutes(players);
		
		players = new ArrayList<Player>();
		if(match.getAwayOtherSquad() != null) {
			for(Player plyr:match.getAwayOtherSquad()) {
				players.add(populatePlayer(footballService, plyr, match));
			}
		}
		match.setAwayOtherSquad(players);
		
		if(match.getHomeTeamId() > 0)
			match.setHomeTeam(footballService.getTeam(KabaddiUtil.TEAM, String.valueOf(match.getHomeTeamId())));
		if(match.getAwayTeamId() > 0)
			match.setAwayTeam(footballService.getTeam(KabaddiUtil.TEAM, String.valueOf(match.getAwayTeamId())));
		if(match.getGroundId() > 0) {
			match.setGround(footballService.getGround(match.getGroundId()));
			match.setVenueName(match.getGround().getFullname());
		}

		if(match.getMatchStats() != null) {
			for(MatchStats ms : match.getMatchStats()) {
				ms.setPlayer(footballService.getPlayer(KabaddiUtil.PLAYER, String.valueOf(ms.getPlayerId())));
			}
		}
		
		return match;
	}
	
	public static String getOnlineCurrentDate() throws IOException
	{
		HttpURLConnection httpCon = (HttpURLConnection) new URL("https://mail.google.com/").openConnection();
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(httpCon.getDate()));
	}	
	
	public static List<Player> getPlayersFromDB(KabaddiService footballService, String whichTeamToProcess, Match match)
	{
		List<Player> players = new ArrayList<Player>(),whichTeamToCheck = new ArrayList<Player>();
		boolean player_found = false; 
		int whichTeamId = 0; 
		
		switch (whichTeamToProcess) {
		case KabaddiUtil.HOME:
			whichTeamId = match.getHomeTeamId();
			whichTeamToCheck = match.getHomeSquad();
			break;
		case KabaddiUtil.AWAY:
			whichTeamId = match.getAwayTeamId();
			whichTeamToCheck = match.getAwaySquad();
			break;
		}
		for(Player plyr : footballService.getPlayers(KabaddiUtil.TEAM,String.valueOf(whichTeamId))) {
			player_found = false;
			for(Player subPlyr : whichTeamToCheck) {
				if (subPlyr.getPlayerId() == plyr.getPlayerId()) {
					player_found = true;
				}
			}
			if(player_found == false) {
				players.add(plyr);
			}
		}
		return players;
	}
	
	public static List<PlayerPreMatchData> getPlayerPreMatchData(Match match, KabaddiService kabaddiService){
		List<PlayerPreMatchData> playerPreMatchData = new ArrayList<PlayerPreMatchData>();
		int teamId = 0;
		if(match != null && match.getApi_PreMatch().get(0).getTeamPlayerStats() != null) {
			for(int i=0; i<match.getApi_PreMatch().get(0).getTeamPlayerStats().size(); i++) {
				teamId = match.getApi_PreMatch().get(0).getTeamPlayerStats().get(i).getTeamId();
				if(match.getApi_PreMatch().get(0).getTeamPlayerStats().get(i).getPlayerStats() != null) {
					for(PlayerStats ps: match.getApi_PreMatch().get(0).getTeamPlayerStats().get(i).getPlayerStats()) {
						
						Player player = kabaddiService.getAllPlayer().stream().filter(plyr -> Integer.valueOf(plyr.getPlayerAPIId()) == ps.getPlayerId()).findAny().orElse(null);
						
						playerPreMatchData.add(new PlayerPreMatchData(teamId ,ps.getPlayerId(), player, ps.getHigh_five(), ps.getSuper_ten(), ps.getMatches(), ps.getPoints().get(0).getTotalPoints(),
								ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints(), ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints(),
								ps.getRaids().get(0).getTotalRaids(), ps.getRaids().get(0).getSuperRaids(), ps.getRaids().get(0).getSuccessfulRaids(), ps.getRaids().get(0).getUnsuccessfulRaids(),
								ps.getTackles().get(0).getTotalTackles(), ps.getTackles().get(0).getSuperTackles(), ps.getTackles().get(0).getSuccessfulTackles(), ps.getTackles().get(0).getUnsuccessfulTackles()));
					}
				}
			}
		}
		
		return playerPreMatchData;
	}
	
	public static List<PlayerPreMatchData> getPastAndCurrentMatchData(List<PlayerPreMatchData> preMatchData, Match match, KabaddiService kabaddiService){
		
		int playerIndex = -1;
		int superTen = 0, highFive = 0;
		
		List<PlayerPreMatchData> past_tournament_data_clone = preMatchData.stream().map(data -> {
			try {
				return data.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return data;
		}).collect(Collectors.toList());
		
		if(past_tournament_data_clone != null && match != null) {
			if(match.getApi_Match().getHomeTeamStats() != null && match.getApi_Match().getHomeTeamStats().getPlayerStats() != null) {
				for(PlayerStats ps : match.getApi_Match().getHomeTeamStats().getPlayerStats()) {
					playerIndex = -1;
					for(int i=0; i<past_tournament_data_clone.size(); i++) {
						if(ps.getPlayerId() == past_tournament_data_clone.get(i).getPlayerId()) {
							playerIndex = i;
							break;
						}
					}
					if(playerIndex>=0) {
						superTen = 0;highFive = 0;
						
						Player player = kabaddiService.getAllPlayer().stream().filter(plyr -> Integer.valueOf(plyr.getPlayerAPIId()) == ps.getPlayerId()).findAny().orElse(null);
						if(ps.getPlayerId() == Integer.parseInt(player.getPlayerAPIId())) {
							if(ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints()>=10) {
								superTen++;
							}
							if(ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints()>=5) {
								highFive++;
							}
						}
						past_tournament_data_clone.get(playerIndex).setPlayer(player);
						past_tournament_data_clone.get(playerIndex).setHighFive((past_tournament_data_clone.get(playerIndex).getHighFive()+highFive));
						past_tournament_data_clone.get(playerIndex).setSuperTen((past_tournament_data_clone.get(playerIndex).getSuperTen()+superTen));
						past_tournament_data_clone.get(playerIndex).setMatches((past_tournament_data_clone.get(playerIndex).getMatches()));
						
						past_tournament_data_clone.get(playerIndex).setTotalPoints((past_tournament_data_clone.get(playerIndex).getTotalPoints()+ps.getPoints().get(0).getTotalPoints()));
						past_tournament_data_clone.get(playerIndex).setTotalRaidPoints((past_tournament_data_clone.get(playerIndex).getTotalRaidPoints()+ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints()));
						past_tournament_data_clone.get(playerIndex).setTotalTacklePoints((past_tournament_data_clone.get(playerIndex).getTotalTacklePoints()+ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints()));
						
						past_tournament_data_clone.get(playerIndex).setTotalRaids((past_tournament_data_clone.get(playerIndex).getTotalRaids()+ps.getRaids().get(0).getTotalRaids()));
						past_tournament_data_clone.get(playerIndex).setSuperRaids((past_tournament_data_clone.get(playerIndex).getSuperRaids()+ps.getRaids().get(0).getSuperRaids()));
						past_tournament_data_clone.get(playerIndex).setSuccessfullRaids((past_tournament_data_clone.get(playerIndex).getSuccessfullRaids()+ps.getRaids().get(0).getSuccessfulRaids()));
						past_tournament_data_clone.get(playerIndex).setUnsuccessfullRaids((past_tournament_data_clone.get(playerIndex).getUnsuccessfullRaids()+ps.getRaids().get(0).getUnsuccessfulRaids()));
						
						past_tournament_data_clone.get(playerIndex).setTotalTackles((past_tournament_data_clone.get(playerIndex).getTotalTackles()+ps.getTackles().get(0).getTotalTackles()));
						past_tournament_data_clone.get(playerIndex).setSuperTackle((past_tournament_data_clone.get(playerIndex).getSuperTackle()+ps.getTackles().get(0).getSuperTackles()));
						past_tournament_data_clone.get(playerIndex).setSuccessfullTackles((past_tournament_data_clone.get(playerIndex).getSuccessfullTackles()+ps.getTackles().get(0).getSuccessfulTackles()));
						past_tournament_data_clone.get(playerIndex).setUnsuccessfullTackles((past_tournament_data_clone.get(playerIndex).getUnsuccessfullTackles()+ps.getTackles().get(0).getUnsuccessfulTackles()));
						
					}else {
						superTen = 0;highFive = 0;
						
						Player player = kabaddiService.getAllPlayer().stream().filter(plyr->Integer.valueOf(plyr.getPlayerAPIId()) == ps.getPlayerId()).findAny().orElse(null);
						if(player != null) {
							if(ps.getPlayerId() == Integer.parseInt(player.getPlayerAPIId())) {
								if(ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints()>=10) {
									superTen++;
								}
								if(ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints()>=5) {
									highFive++;
								}
							}
							past_tournament_data_clone.add(new PlayerPreMatchData(match.getHomeTeam().getTeamId(),ps.getPlayerId(), player, highFive, superTen, (ps.getMatches()), ps.getPoints().get(0).getTotalPoints(),
									ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints(), ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints(),
									ps.getRaids().get(0).getTotalRaids(), ps.getRaids().get(0).getSuperRaids(), ps.getRaids().get(0).getSuccessfulRaids(), ps.getRaids().get(0).getUnsuccessfulRaids(),
									ps.getTackles().get(0).getTotalTackles(), ps.getTackles().get(0).getSuperTackles(), ps.getTackles().get(0).getSuccessfulTackles(), ps.getTackles().get(0).getUnsuccessfulTackles()));
						}
					}
				}
				
				if(match.getApi_Match().getAwayTeamStats() != null && match.getApi_Match().getAwayTeamStats().getPlayerStats() != null) {
					for(PlayerStats ps : match.getApi_Match().getAwayTeamStats().getPlayerStats()) {
						playerIndex = -1;
						for(int i=0; i<past_tournament_data_clone.size(); i++) {
							if(ps.getPlayerId() == past_tournament_data_clone.get(i).getPlayerId()) {
								playerIndex = i;
								break;
							}
						}
						if(playerIndex>=0) {
							superTen = 0;highFive = 0;
							Player player = kabaddiService.getAllPlayer().stream().filter(plyr -> Integer.valueOf(plyr.getPlayerAPIId()) == ps.getPlayerId()).findAny().orElse(null);
							if(ps.getPlayerId() == Integer.parseInt(player.getPlayerAPIId())) {
								if(ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints()>=10) {
									superTen++;
								}
								if(ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints()>=5) {
									highFive++;
								}
							}
							past_tournament_data_clone.get(playerIndex).setPlayer(player);
							past_tournament_data_clone.get(playerIndex).setHighFive((past_tournament_data_clone.get(playerIndex).getHighFive()+highFive));
							past_tournament_data_clone.get(playerIndex).setSuperTen((past_tournament_data_clone.get(playerIndex).getSuperTen()+superTen));
							past_tournament_data_clone.get(playerIndex).setMatches((past_tournament_data_clone.get(playerIndex).getMatches()));

							past_tournament_data_clone.get(playerIndex).setTotalPoints((past_tournament_data_clone.get(playerIndex).getTotalPoints()+ps.getPoints().get(0).getTotalPoints()));
							past_tournament_data_clone.get(playerIndex).setTotalRaidPoints((past_tournament_data_clone.get(playerIndex).getTotalRaidPoints()+ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints()));
							past_tournament_data_clone.get(playerIndex).setTotalTacklePoints((past_tournament_data_clone.get(playerIndex).getTotalTacklePoints()+ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints()));
							
							past_tournament_data_clone.get(playerIndex).setTotalRaids((past_tournament_data_clone.get(playerIndex).getTotalRaids()+ps.getRaids().get(0).getTotalRaids()));
							past_tournament_data_clone.get(playerIndex).setSuperRaids((past_tournament_data_clone.get(playerIndex).getSuperRaids()+ps.getRaids().get(0).getSuperRaids()));
							past_tournament_data_clone.get(playerIndex).setSuccessfullRaids((past_tournament_data_clone.get(playerIndex).getSuccessfullRaids()+ps.getRaids().get(0).getSuccessfulRaids()));
							past_tournament_data_clone.get(playerIndex).setUnsuccessfullRaids((past_tournament_data_clone.get(playerIndex).getUnsuccessfullRaids()+ps.getRaids().get(0).getUnsuccessfulRaids()));
							
							past_tournament_data_clone.get(playerIndex).setTotalTackles((past_tournament_data_clone.get(playerIndex).getTotalTackles()+ps.getTackles().get(0).getTotalTackles()));
							past_tournament_data_clone.get(playerIndex).setSuperTackle((past_tournament_data_clone.get(playerIndex).getSuperTackle()+ps.getTackles().get(0).getSuperTackles()));
							past_tournament_data_clone.get(playerIndex).setSuccessfullTackles((past_tournament_data_clone.get(playerIndex).getSuccessfullTackles()+ps.getTackles().get(0).getSuccessfulTackles()));
							past_tournament_data_clone.get(playerIndex).setUnsuccessfullTackles((past_tournament_data_clone.get(playerIndex).getUnsuccessfullTackles()+ps.getTackles().get(0).getUnsuccessfulTackles()));
							
						}else {
							superTen = 0;highFive = 0;
							Player player = kabaddiService.getAllPlayer().stream().filter(plyr->Integer.valueOf(plyr.getPlayerAPIId()) == ps.getPlayerId()).findAny().orElse(null);
							if(player != null) {
								if(ps.getPlayerId() == Integer.parseInt(player.getPlayerAPIId())) {
									if(ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints()>=10) {
										superTen++;
									}
									if(ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints()>=5) {
										highFive++;
									}
								}
								past_tournament_data_clone.add(new PlayerPreMatchData(match.getAwayTeam().getTeamId(),ps.getPlayerId(), player, highFive, superTen, (ps.getMatches()), ps.getPoints().get(0).getTotalPoints(),
										ps.getPoints().get(0).getRaid_points().get(0).getTotalRaidPoints(), ps.getPoints().get(0).getTackle_points().get(0).getTotalTacklePoints(),
										ps.getRaids().get(0).getTotalRaids(), ps.getRaids().get(0).getSuperRaids(), ps.getRaids().get(0).getSuccessfulRaids(), ps.getRaids().get(0).getUnsuccessfulRaids(),
										ps.getTackles().get(0).getTotalTackles(), ps.getTackles().get(0).getSuperTackles(), ps.getTackles().get(0).getSuccessfulTackles(), ps.getTackles().get(0).getUnsuccessfulTackles()));
							}
						}
					}
				}
			}
		}
		return past_tournament_data_clone;
	}

	public static class raidPointComparator implements Comparator<PlayerPreMatchData> {
	    @Override
	    public int compare(PlayerPreMatchData data1, PlayerPreMatchData data2) {
	    	if(data1.getTotalRaidPoints() == data2.getTotalRaidPoints()) {
	    		return Integer.compare(data1.getTotalRaids(), data2.getTotalRaids());
	    	}else {
	    		return Integer.compare(data2.getTotalRaidPoints(), data1.getTotalRaidPoints());
	    	}
	    }
	}
	public static class tacklePointComparator implements Comparator<PlayerPreMatchData> {
	    @Override
	    public int compare(PlayerPreMatchData data1, PlayerPreMatchData data2) {
	    	if(data1.getTotalTacklePoints() == data2.getTotalTacklePoints()) {
	    		return Integer.compare(data1.getTotalTackles(), data2.getTotalTackles());
	    	}else {
	    		return Integer.compare(data2.getTotalTacklePoints(), data1.getTotalTacklePoints());
	    	}
	    }
	}
	public static class TotalPointComparator implements Comparator<PlayerPreMatchData> {
	    @Override
	    public int compare(PlayerPreMatchData data1, PlayerPreMatchData data2) {
	    	if(data1.getTotalPoints() == data2.getTotalPoints()) {
	    		if(data1.getTotalRaidPoints() == data2.getTotalRaidPoints()) {
	    			return Integer.compare(data2.getTotalTacklePoints(), data1.getTotalTacklePoints());
	    		}else {
	    			return Integer.compare(data2.getTotalRaidPoints(), data1.getTotalRaidPoints());
	    		}
	    	}else {
	    		return Integer.compare(data2.getTotalPoints(), data1.getTotalPoints());
	    	}
	    }
	}
	public static List<PlayerComparison> processAllPlayerStatsComparion(KabaddiService KabaddiService) {
		
		List<PlayerComparison> playerstats = KabaddiService.getPlayerComparisons();
	
		for(Player plyr : KabaddiService.getAllPlayer()) {
			for(PlayerComparison ps : playerstats) {
				if(ps.getPlayerId() == plyr.getPlayerId()) {
					ps.setPlayer(plyr);
					ps.setTeam(KabaddiService.getTeams().get(plyr.getTeamId()-1));
				}if(ps.getPlayerId1() == plyr.getPlayerId()) {
					ps.setPlayer1(plyr);
					ps.setTeam1(KabaddiService.getTeams().get(plyr.getTeamId()-1));
				}
			}
		}
		return playerstats;
	}
}
