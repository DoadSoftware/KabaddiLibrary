package com.kabaddi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kabaddi.dao.KabaddiDao;
import com.kabaddi.model.Bugs;
import com.kabaddi.model.ExtraData;
import com.kabaddi.model.Fixture;
import com.kabaddi.model.Formation;
import com.kabaddi.model.Ground;
import com.kabaddi.model.HeadToHead;
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
import com.kabaddi.service.KabaddiService;

@Service("footballService")
@Transactional
public class KabaddiServiceImpl implements KabaddiService {

 @Autowired
 private KabaddiDao footballDao;
 
@Override
public Player getPlayer(String whatToProcess, String valueToProcess) {
	return footballDao.getPlayer(whatToProcess, valueToProcess);
}

@Override
public Team getTeam(String whatToProcess, String valueToProcess) {
	return footballDao.getTeam(whatToProcess, valueToProcess);
}

@Override
public List<Team> getTeams() {
	return footballDao.getTeams();
}

@Override
public List<NameSuper> getNameSupers() {
	return footballDao.getNameSupers();
}

@Override
public List<Player> getPlayers(String whatToProcess, String valueToProcess) {
	return footballDao.getPlayers(whatToProcess, valueToProcess);
}

@Override
public List<Ground> getGrounds() {
	return footballDao.getGrounds();
}

@Override
public List<Playoff> getPlayoffs() {
	return footballDao.getPlayoffs();
}

@Override
public List<VariousText> getVariousTexts() {
	return footballDao.getVariousTexts();
}

@Override
public Ground getGround(int ground_id) {
	return footballDao.getGround(ground_id);
}

@Override
public List<Bugs> getBugs() {
	return footballDao.getBugs();
}

@Override
public List<Statistics> getAllStats() {
	return footballDao.getAllStats();
}

@Override
public List<Player> getAllPlayer() {
	return footballDao.getAllPlayer();
}

@Override
public List<Fixture> getFixtures() {
	return footballDao.getFixtures();
}

@Override
public List<Formation> getFormations() {
	return footballDao.getFormations();
}

@Override
public List<TeamColor> getTeamColors() {
	return footballDao.getTeamColors();
}

@Override
public List<Staff> getStaffs() {
	return footballDao.getStaff();
}

@Override
public List<Officials> getOfficials() {
	return footballDao.getOfficials();
}

@Override
public List<ExtraData> getExtraData() {
	return footballDao.getExtraData();
}

@Override
public List<LeaderBoard> getLeaderBoard() {
	return footballDao.getLeaderBoard();
}

@Override
public List<TeamStat> getTeamStats() {
	return footballDao.getTeamStats();
}

@Override
public List<HeadToHead> getHeadToHeadStats() {
	return footballDao.getHeadToHeadStats();
}

@Override
public List<PlayerStat> getPlayerStats() {
	return footballDao.getPlayerStats();
}

}