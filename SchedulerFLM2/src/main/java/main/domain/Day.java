package main.domain;

import java.io.Serializable;

public class Day implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2317568149056438509L;

	// �����o�ϐ��̒�`
	private int dayID;
	private DayWeek dayweek;
	// dayWeek1�́A��T�ԓ��ɏj�����܂܂��ꍇ�Ɏg�p����B
	private DayWeek dayweek1;
	private Week week;
	private String ID;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public Day() {
	}

	// ��������i�c�Ɠ�ID,�j��,�T�j
	public Day(int dayID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek = dayweek;
		this.week = week;
	}

	// ��������i+ID�j
	public Day(int dayID, String ID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek = dayweek;
		this.week = week;
		this.ID = ID;
	}

	// ��������i+�j���̗j���j
	public Day(int dayID, String ID, String dayweek, String dayweekHoliday,
			String week) {
		this.dayID = dayID;
		this.dayweek = DayWeek.parseDayWeek(Integer.parseInt(dayweek));
		if (!dayweekHoliday.equals("")) {
			// if there is holiday in this week, feed dayweek1 with appropriate
			// data
			this.dayweek1 = DayWeek.parseDayWeek(Integer
					.parseInt(dayweekHoliday));
		} else {
			// if there is no holiday in this week, feed dayweek1 with normal
			// dayweek
			this.dayweek1 = this.dayweek;
		}
		this.week = Week.parseWeek(Integer.parseInt(week));
		this.ID = ID;
	}

	// �Q�b�^�[�E�Z�b�^�[
	// �j��
	public DayWeek getDayweek() {
		return dayweek;
	}

	public void setDayweek(DayWeek dayweek) {
		this.dayweek = dayweek;
	}

	// �T
	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	// �c�Ɠ�ID
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

	// �j���̗j��
	public DayWeek getDayweek1() {
		return dayweek1;
	}

	public void setDayweek1(DayWeek dayWeek1) {
		this.dayweek1 = dayWeek1;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// �c�Ɠ��̃R�s�[
	public Day clone() {
		Day c = new Day();
		c.dayID = dayID;
		c.dayweek = dayweek;
		c.dayweek1 = dayweek1;
		c.week = week;
		c.ID = ID;
		return c;
	}

	// �c�Ɠ��̈�v
	public boolean equals(Day c) {
		return (c.getDayID() == dayID);
	}

	// �c�Ɠ����̕\��
	@Override
	public String toString() {
		return "(Day " + ID + ")";
	}

}
