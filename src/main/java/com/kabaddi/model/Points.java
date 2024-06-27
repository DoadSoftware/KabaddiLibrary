package com.kabaddi.model;

import java.util.List;

public class Points {
	
	int totalPoints;
	List<TacklePoints> tackle_points;
	List<RaidPoints> raid_points;
	int all_out_points;
	int extra_points;
	
	public Points() {
		super();
	}

	public Points(int totalPoints, List<TacklePoints> tackle_points, List<RaidPoints> raid_points) {
		super();
		this.totalPoints = totalPoints;
		this.tackle_points = tackle_points;
		this.raid_points = raid_points;
	}

	public int getTotalPoints() {
		return totalPoints;
	}
	
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public List<TacklePoints> getTackle_points() {
		return tackle_points;
	}

	public void setTackle_points(List<TacklePoints> tackle_points) {
		this.tackle_points = tackle_points;
	}

	public List<RaidPoints> getRaid_points() {
		return raid_points;
	}

	public void setRaid_points(List<RaidPoints> raid_points) {
		this.raid_points = raid_points;
	}

	public int getAll_out_points() {
		return all_out_points;
	}

	public void setAll_out_points(int all_out_points) {
		this.all_out_points = all_out_points;
	}

	public int getExtra_points() {
		return extra_points;
	}

	public void setExtra_points(int extra_points) {
		this.extra_points = extra_points;
	}

	@Override
	public String toString() {
		return "Points [totalPoints=" + totalPoints + ", tackle_points=" + tackle_points + ", raid_points="
				+ raid_points + ", all_out_points=" + all_out_points + ", extra_points=" + extra_points + "]";
	}
	
}