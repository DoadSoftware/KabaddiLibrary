package com.kabaddi.service;

import java.util.List;

import com.kabaddi.model.PlayerComparison;
import com.kabaddi.model.Bugs;
import com.kabaddi.model.ExtraData;
import com.kabaddi.model.Fixture;
import com.kabaddi.model.Formation;
import com.kabaddi.model.Ground;
import com.kabaddi.model.HeadToHead;
import com.kabaddi.model.InfobarStats;
import com.kabaddi.model.LeaderBoard;
import com.kabaddi.model.NameSuper;
import com.kabaddi.model.Officials;
import com.kabaddi.model.Player;
import com.kabaddi.model.PlayerStat;
import com.kabaddi.model.Playoff;
import com.kabaddi.model.Staff;
import com.kabaddi.model.Statistics;
import com.kabaddi.model.Team;
import com.kabaddi.model.TeamColor;
import com.kabaddi.model.TeamStat;
import com.kabaddi.model.VariousText;

public interface KabaddiService {
  Player getPlayer(String whatToProcess, String valueToProcess);
  Team getTeam(String whatToProcess, String valueToProcess);
  Ground getGround(int ground_id);
  List<Player> getPlayers(String whatToProcess, String valueToProcess);
  List<Team> getTeams();
  List<NameSuper> getNameSupers();
  List<Ground> getGrounds();
  List<Playoff> getPlayoffs();
  List<VariousText> getVariousTexts();
  List<Statistics> getAllStats();
  List<Player> getAllPlayer();
  List<Bugs> getBugs();
  List<Fixture> getFixtures();
  List<Formation> getFormations();
  List<TeamColor> getTeamColors();
  List<Staff> getStaffs();
  List<Officials> getOfficials();
  List<ExtraData> getExtraData();
  List<LeaderBoard> getLeaderBoard();
  List<TeamStat> getTeamStats();
  List<HeadToHead> getHeadToHeadStats();
  List<PlayerStat> getPlayerStats();
  List<InfobarStats> getInfobarStats();
  List<PlayerComparison> getPlayerComparisons();

}