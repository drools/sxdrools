package main.data;

public class RuleLogger {

	// �����o�ϐ��̒�`
	// ���b�Z�[�W
	private String message;
	// ruleID
	private String ruleID;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public RuleLogger() {
	}

	// �R���X�g���N�^�̐ݒ�
	// ��������
	public RuleLogger(String ruleID, String message) {
		this.setRuleID(ruleID);
		this.message = message;
	}

	// �Q�b�^�[�E�Z�b�^�[
	// ���b�Z�[�W
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// ���[��ID
	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	// ���[�����O�̕\��
	public String toString() {
		return message;
	}

}
