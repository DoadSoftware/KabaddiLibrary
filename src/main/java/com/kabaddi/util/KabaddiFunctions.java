package com.kabaddi.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.kabaddi.model.Api_pre_match;
import com.kabaddi.model.Configurations;
import com.kabaddi.model.Do_Or_Die;
import com.kabaddi.model.Fixture;
import com.kabaddi.model.Match;
import com.kabaddi.model.MatchStats;
import com.kabaddi.model.Player;
import com.kabaddi.model.PlayerStats;
import com.kabaddi.model.Points;
import com.kabaddi.model.RaidPoints;
import com.kabaddi.model.Raids;
import com.kabaddi.model.TacklePoints;
import com.kabaddi.model.Tackles;
import com.kabaddi.model.Team;
import com.kabaddi.model.TeamPlayerStats;
import com.kabaddi.service.KabaddiService;

public class KabaddiFunctions {
	
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
	
	public static List<Fixture> processAllFixtures(KabaddiService footballService) {
		List<Fixture> fixtures = footballService.getFixtures();
		for(Team tm : footballService.getTeams()) {
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
}
