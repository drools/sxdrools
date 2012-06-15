package main.domain;

import java.io.Serializable;

public class Classroom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5750305416548495970L;

	// �����o�ϐ��̒�`
	private int capacity;
	private Boolean PC;
	private int classroomID;
	private String ID;
	private String pcType;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public Classroom() {
	}

	// ��������i����ID, �������, PC�̗L���j
	public Classroom(int classroomID, int capacity, Boolean PC) {
		this.capacity = capacity;
		this.PC = PC;
		this.classroomID = classroomID;
	}

	// ��������i+String�@ID�j
	public Classroom(int classroomID, String ID, int capacity, Boolean PC) {
		this.capacity = capacity;
		this.PC = PC;
		this.classroomID = classroomID;
		this.ID = ID;
	}

	// ��������i+PC�̎�ށj
	public Classroom(int classroomID, String ID, String capacity, String PC,
			String pcType) {
		this.capacity = Integer.parseInt(capacity);
		this.PC = Boolean.parseBoolean(PC);
		this.classroomID = classroomID;
		this.ID = ID;
		this.pcType = pcType;
	}

	// �Q�b�^�[�E�Z�b�^�[
	// �������
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// PC
	public Boolean getPC() {
		return PC;
	}

	public void setPC(Boolean pC) {
		PC = pC;
	}

	// ����ID
	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	// ID
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	// PC�̎��
	public String getPcType() {
		return pcType;
	}

	public void setPcType(String pcType) {
		this.pcType = pcType;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// �����̃R�s�[
	public Classroom clone() {
		Classroom c = new Classroom();
		c.capacity = capacity;
		c.PC = PC;
		c.classroomID = classroomID;
		c.ID = ID;
		c.pcType = pcType;
		return c;
	}

	// �����̈�v
	public boolean equals(Classroom c) {
		return (c.getClassroomID() == classroomID);
	}

	// �������̕\��
	@Override
	public String toString() {
		return "RoomID " + ID + " limit " + capacity + " PC " + PC;
	}

}
