package main.domain;

public class Day {
	private int dayID;
	private DayWeek dayweek;
	//dayWeek1 is used only when there is holiday below this day in a week
	private DayWeek dayweek1;
	private Week week;
	private String ID;
	
	public Day() {}
	
	public Day(int dayID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek=dayweek;
		this.week=week;
	}
	
	public Day(int dayID, String ID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek=dayweek;
		this.week=week;
		this.ID=ID;
	}
	
	public Day(int dayID, String ID, String dayweek, String dayweekHoliday, String week) {
		this.dayID = dayID;
		this.dayweek=DayWeek.parseDayWeek(Integer.parseInt(dayweek));
		if (!dayweekHoliday.equals("")) {
			// if there is holiday in this week, feed dayweek1 with appropriate data
			this.dayweek1=DayWeek.parseDayWeek(Integer.parseInt(dayweekHoliday));
		}
		else {
			// if there is no holiday in this week, feed dayweek1 with normal dayweek
			this.dayweek1=this.dayweek;
		}
		this.week=Week.parseWeek(Integer.parseInt(week));
		this.ID=ID;
	}
	
	public DayWeek getDayweek() {
		return dayweek;
	}
	public void setDayweek(DayWeek dayweek) {
		this.dayweek = dayweek;
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
		c.dayweek=dayweek;
		c.dayweek1=dayweek1;
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

	public DayWeek getDayweek1() {
		return dayweek1;
	}

	public void setDayweek1(DayWeek dayWeek1) {
		this.dayweek1 = dayWeek1;
	}
	
}
