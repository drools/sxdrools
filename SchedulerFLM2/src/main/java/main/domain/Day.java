package main.domain;

import java.io.Serializable;

public class Day implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2317568149056438509L;

	// ƒƒ“ƒo•Ï”‚Ì’è‹`
	private int dayID;
	private DayWeek dayweek;
	// dayWeek1‚ÍAˆêTŠÔ“à‚Éj“ú‚ªŠÜ‚Ü‚ê‚éê‡‚Ég—p‚·‚éB
	private DayWeek dayweek1;
	private Week week;
	private String ID;

	// ƒRƒ“ƒXƒgƒ‰ƒNƒ^‚Ìİ’è
	// ˆø”‚È‚µ
	public Day() {
	}

	// ˆø”‚ ‚èi‰c‹Æ“úID,—j“ú,Tj
	public Day(int dayID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek = dayweek;
		this.week = week;
	}

	// ˆø”‚ ‚èi+IDj
	public Day(int dayID, String ID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek = dayweek;
		this.week = week;
		this.ID = ID;
	}

	// ˆø”‚ ‚èi+j“ú‚Ì—j“új
	public Day(int dayID, String ID, String dayweek, String dayweekHoliday,
			String week) {
		this.dayID = dayID;
		this.dayweek = DayWeek.parseDayWeek(Integer.parseInt(dayweek));
		if (!dayweekHoliday.equals("")) {
			// T“à‚Éj“ú‚ª‘¶İ‚·‚éê‡Aj“ú‘O“ú‚ğ‹à—j“ú‚ÉŒ©—§‚Ä‚ÄU‚ç‚ê‚½—j“ú‚ª—p‚¢‚ç‚ê‚éB
			this.dayweek1 = DayWeek.parseDayWeek(Integer
					.parseInt(dayweekHoliday));
		} else {
			// T“à‚Éj“ú‚ª‘¶İ‚·‚éê‡A‚»‚Ì‚Ü‚Ü‚Ì—j“ú‚ª—p‚¢‚ç‚ê‚éB
			this.dayweek1 = this.dayweek;
		}
		this.week = Week.parseWeek(Integer.parseInt(week));
		this.ID = ID;
	}

	// ƒQƒbƒ^[EƒZƒbƒ^[
	// —j“ú
	public DayWeek getDayweek() {
		return dayweek;
	}

	public void setDayweek(DayWeek dayweek) {
		this.dayweek = dayweek;
	}

	// T
	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	// ‰c‹Æ“úID
	public int getDayID() {
		return dayID;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}

	// ID
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	// j“ú‚Ì—j“ú
	public DayWeek getDayweek1() {
		return dayweek1;
	}

	public void setDayweek1(DayWeek dayWeek1) {
		this.dayweek1 = dayWeek1;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// ‰c‹Æ“ú‚ÌƒRƒs[
	public Day clone() {
		Day c = new Day();
		c.dayID = dayID;
		c.dayweek = dayweek;
		c.dayweek1 = dayweek1;
		c.week = week;
		c.ID = ID;
		return c;
	}

	// ‰c‹Æ“ú‚Ìˆê’v
	public boolean equals(Day c) {
		return (c.getDayID() == dayID);
	}

	// ‰c‹Æ“úî•ñ‚Ì•\¦
	@Override
	public String toString() {
		return "(Day " + ID + ")";
	}

}
