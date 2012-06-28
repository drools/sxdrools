package main.domain;

public enum DayWeek {
	MON(0), TUE(1), WED(2), THU(3), FRI(4);

	// メンバ変数の定義
	private final int dayweek;

	// コンストラクタの設定
	// 引数あり
	DayWeek(int dayweek) {
		this.dayweek = dayweek;
	}

	// ゲッター・セッター
	public int getDayweek() {
		return dayweek;
	}
	
	//数字を曜日に変換する
	static public DayWeek parseDayWeek(int dayweek) {
		switch (dayweek) {
		case 0:
			return MON;
		case 1:
			return TUE;
		case 2:
			return WED;
		case 3:
			return THU;
		case 4:
			return FRI;
		default:
			return null;
		}

	}

}
