//パッケージの作成
package main.domain;

public enum Week {
	
	FIRST(0), MIDDLE(1), LAST(2);
	
	//メンバ変数の定義
	private final int week;
	
	//コンストラクタの設定
	//引数あり
	Week(int week) {
		this.week=week;
	}

	//ゲッター・セッター
	public int getWeek() {
		return week;
	}
	
	//上旬・中旬・下旬の設定
	static Week parseWeek(String week) {
		if (week == "FIRST") {
			return FIRST;
		} else if (week == "MIDDLE") {
			return MIDDLE;
		} else if (week == "LAST") {
			return LAST;	
		} else {
			return null;
		}
	}
	//上旬・中旬・下旬の設定
	static public Week parseWeek(int week) {
		switch (week) {
			case 0: return FIRST; 
			case 1: return MIDDLE;
			case 2: return LAST;
			default: return null;
		}
	}
	
}
