package main.domain;

public enum Week {
	FIRST(0), MIDDLE(1), LAST(2);
	
	private final int week;
	
	Week(int week) {
		this.week=week;
	}

	public int getWeek() {
		return week;
	}
	
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
	
	static public Week parseWeek(int week) {
		switch (week) {
			case 0: return FIRST; 
			case 1: return MIDDLE;
			case 2: return LAST;
			default: return null;
		}
	}
	
}
