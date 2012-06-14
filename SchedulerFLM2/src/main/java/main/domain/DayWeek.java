package main.domain;

public enum DayWeek {
	MON(0), TUE(1), WED(2), THU(3), FRI(4);

	// �����o�ϐ��̒�`
	private final int dayweek;

	// �R���X�g���N�^�̐ݒ�
	// ��������
	DayWeek(int dayweek) {
		this.dayweek = dayweek;
	}

	// �Q�b�^�[�E�Z�b�^�[
	public int getDayweek() {
		return dayweek;
	}

	// ??
	static DayWeek parseDayWeek(String dayweek) {
		if (dayweek == "MON") {
			return MON;
		} else if (dayweek == "TUE") {
			return TUE;
		} else if (dayweek == "WED") {
			return WED;
		} else if (dayweek == "THU") {
			return THU;
		} else if (dayweek == "FRI") {
			return FRI;
		} else {
			return null;
		}

	}

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
