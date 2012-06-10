package main.domain;

public class Day {
	private int dayID;
	private DayWeek dayWeek;
	private Week week;
	private String ID;
	
	public Day() {}
	
	public Day(int dayID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayWeek=dayweek;
		this.week=week;
	}
	
	public Day(int dayID, String ID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayWeek=dayweek;
		this.week=week;
		this.ID=ID;
	}
	
	public DayWeek getDayweek() {
		return dayWeek;
	}
	public void setDayweek(DayWeek dayweek) {
		this.dayWeek = dayweek;
	}
	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	
	public Day clone() {
		Day c = new Day();
		c.dayID=dayID;
		c.dayWeek=dayWeek;
		c.week=week;
		c.ID=ID;
		return c;
	}

	public int getDayID() {
		return dayID;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}
	
	public boolean equals(Day c) {
		return (c.getDayID()==dayID);
	}
	
	@Override
	public String toString() {
		return "Day " + ID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
}
