package main.domain;

import java.io.Serializable;

public class Day implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2317568149056438509L;

	// メンバ変数の定義
	private int dayID;
	private DayWeek dayweek;
	// dayWeek1は、一週間内に祝日が含まれる場合に使用する。
	private DayWeek dayweek1;
	private Week week;
	private String ID;

	// コンストラクタの設定
	// 引数なし
	public Day() {
	}

	// 引数あり（営業日ID,曜日,週）
	public Day(int dayID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek = dayweek;
		this.week = week;
	}

	// 引数あり（+ID）
	public Day(int dayID, String ID, DayWeek dayweek, Week week) {
		this.dayID = dayID;
		this.dayweek = dayweek;
		this.week = week;
		this.ID = ID;
	}

	// 引数あり（+祝日の曜日）
	public Day(int dayID, String ID, String dayweek, String dayweekHoliday,
			String week) {
		this.dayID = dayID;
		this.dayweek = DayWeek.parseDayWeek(Integer.parseInt(dayweek));
		if (!dayweekHoliday.equals("")) {
			// 週内に祝日が存在する場合、祝日前日を金曜日に見立てて振られた曜日が用いられる。
			this.dayweek1 = DayWeek.parseDayWeek(Integer
					.parseInt(dayweekHoliday));
		} else {
			// 週内に祝日が存在する場合、そのままの曜日が用いられる。
			this.dayweek1 = this.dayweek;
		}
		this.week = Week.parseWeek(Integer.parseInt(week));
		this.ID = ID;
	}

	// ゲッター・セッター
	// 曜日
	public DayWeek getDayweek() {
		return dayweek;
	}

	public void setDayweek(DayWeek dayweek) {
		this.dayweek = dayweek;
	}

	// 週
	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	// 営業日ID
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

	// 祝日の曜日
	public DayWeek getDayweek1() {
		return dayweek1;
	}

	public void setDayweek1(DayWeek dayWeek1) {
		this.dayweek1 = dayWeek1;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// 営業日のコピー
	public Day clone() {
		Day c = new Day();
		c.dayID = dayID;
		c.dayweek = dayweek;
		c.dayweek1 = dayweek1;
		c.week = week;
		c.ID = ID;
		return c;
	}

	// 営業日の一致
	public boolean equals(Day c) {
		return (c.getDayID() == dayID);
	}

	// 営業日情報の表示
	@Override
	public String toString() {
		return "(Day " + ID + ")";
	}

}
