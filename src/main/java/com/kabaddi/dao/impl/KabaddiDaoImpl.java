package com.kabaddi.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
import com.kabaddi.util.KabaddiUtil;

@Transactional
@Repository("footballDao")
@SuppressWarnings("unchecked")
public class KabaddiDaoImpl implements KabaddiDao {

 @Autowired
 private SessionFactory sessionFactory;
 
@Override
public Player getPlayer(String whatToProcess, String valueToProcess) {
	switch (whatToProcess) {
	case KabaddiUtil.PLAYER:
		return (Player) sessionFactory.getCurrentSession().createQuery("from Player WHERE PlayerId=" + valueToProcess).uniqueResult();  
	default:
		return null;  
	}
}

@Override
public Team getTeam(String whatToProcess, String valueToProcess) {
	switch (whatToProcess) {
	case KabaddiUtil.TEAM:
		return (Team) sessionFactory.getCurrentSession().createQuery("from Team WHERE TeamId=" + valueToProcess).uniqueResult();  
	default:
		return null;  
	}
}

@Override
public List<NameSuper> getNameSupers() {
	return sessionFactory.getCurrentSession().createQuery("from NameSuper").list();
}

@Override
public List<Team> getTeams() {
	return sessionFactory.getCurrentSession().createQuery("from Team").list();
}

@Override
public List<Player> getPlayers(String whatToProcess, String valueToProcess) {
	switch (whatToProcess) {
	case KabaddiUtil.TEAM:
		return sessionFactory.getCurrentSession().createQuery("from Player WHERE TeamId=" + valueToProcess).list();  
	default:
		return null;  
	}
}

@Override
public List<Bugs> getBugs() {
	return sessionFactory.getCurrentSession().createQuery("from Bugs").list();
}

@Override
public List<Ground> getGrounds() {
	return sessionFactory.getCurrentSession().createQuery("from Ground").list();  
}

@Override
public List<Playoff> getPlayoffs() {
	return sessionFactory.getCurrentSession().createQuery("from Playoff").list();  
}

@Override
public List<VariousText> getVariousTexts() {
	return sessionFactory.getCurrentSession().createQuery("from VariousText").list();  
}

@Override
public Ground getGround(int ground_id) {
	return (Ground) sessionFactory.getCurrentSession().createQuery("from Ground WHERE GroundId=" + ground_id).uniqueResult();  
}

@Override
public List<Statistics> getAllStats() {
	return sessionFactory.getCurrentSession().createQuery("from Statistics").list();
}

@Override
public List<Player> getAllPlayer() {
	return sessionFactory.getCurrentSession().createQuery("from Player").list();
}

@Override
public List<Fixture> getFixtures() {
	return sessionFactory.getCurrentSession().createQuery("from Fixture").list();
}

@Override
public List<Formation> getFormations() {
	return sessionFactory.getCurrentSession().createQuery("from Formation").list();
}

@Override
public List<TeamColor> getTeamColors() {
	return sessionFactory.getCurrentSession().createQuery("from TeamColor").list();
}

@Override
public List<Staff> getStaff() {
	return sessionFactory.getCurrentSession().createQuery("from Staff").list();
}

@Override
public List<Officials> getOfficials() {
	return sessionFactory.getCurrentSession().createQuery("from Officials").list();
}

@Override
public List<ExtraData> getExtraData() {
	return sessionFactory.getCurrentSession().createQuery("from ExtraData").list();
}

@Override
public List<LeaderBoard> getLeaderBoard() {
	return sessionFactory.getCurrentSession().createQuery("from LeaderBoard").list();
}

@Override
public List<TeamStat> getTeamStats() {
	return sessionFactory.getCurrentSession().createQuery("from TeamStat").list();
}

@Override
public List<HeadToHead> getHeadToHeadStats() {
	return sessionFactory.getCurrentSession().createQuery("from HeadToHead").list();
}

@Override
public List<PlayerStat> getPlayerStats() {
	return sessionFactory.getCurrentSession().createQuery("from PlayerStat").list();
}

}