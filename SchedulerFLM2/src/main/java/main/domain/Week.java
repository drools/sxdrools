//�p�b�P�[�W�̍쐬
package main.domain;

public enum Week {
	
	FIRST(0), MIDDLE(1), LAST(2);
	
	//�����o�ϐ��̒�`
	private final int week;
	
	//�R���X�g���N�^�̐ݒ�
	//��������
	Week(int week) {
		this.week=week;
	}

	//�Q�b�^�[�E�Z�b�^�[
	public int getWeek() {
		return week;
	}
	
	//��{�E���{�E���{�̐ݒ�
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
	//��{�E���{�E���{�̐ݒ�
	static public Week parseWeek(int week) {
		switch (week) {
			case 0: return FIRST; 
			case 1: return MIDDLE;
			case 2: return LAST;
			default: return null;
		}
	}
	
}
