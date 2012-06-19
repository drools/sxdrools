//�p�b�P�[�W�̍쐬
package main.domain;

//�p�b�P�[�W�̃C���|�[�g
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1887271211307841703L;

	// �����o�ϐ��̒�`
	private int courseID;
	private int eSize;
	private int length;
	private Boolean PC;
	private String ID;
	private ArrayList<String> supportedPCList;
	private ArrayList<String> fixedRoomList;
	// minEsize == 0 means course size is fixed
	private int minESize;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public Course() {
	}

	// ��������i�R�[�XID�A�R�[�X����A�R�[�X���ԁAPC�j
	public Course(int courseID, int eSize, int length, Boolean PC) {
		this.courseID = courseID;
		this.eSize = eSize;
		this.length = length;
		this.PC = PC;
	}

	// ��������i+ID�j
	public Course(int courseID, String ID, int eSize, int length, Boolean PC) {
		this.courseID = courseID;
		this.eSize = eSize;
		this.length = length;
		this.PC = PC;
		this.ID = ID;
	}

	// ��������i+�ŏ��\�l���j
	public Course(int courseID, String ID, String eSize, String minSize,
			String length, String PC, String fixedRoom, String supportedPC) {
		this.courseID = courseID;
		this.eSize = Integer.parseInt(eSize);
		this.length = Integer.parseInt(length);
		if (!minSize.equals("")) {
			this.minESize = Integer.parseInt(minSize);
		} else {
			// minEsize == 0 means course size is fixed
			this.minESize = 0;
		}

		if (PC.equals("")) {
			this.PC = (Boolean) null;
		} else {
			this.PC = Boolean.parseBoolean(PC);
		}
		this.ID = ID;
		if (!supportedPC.replace(" ", "").equals("")) {
			this.supportedPCList = new ArrayList<String>(Arrays.asList(supportedPC
					.split(",")));
		} else {
			this.supportedPCList = new ArrayList<String>();
		}
		/*if (!fixedRoom.replace(" ", "").equals("")) {
			this.fixedRoomList = new ArrayList<String>(Arrays.asList(fixedRoom
					.split(",")));
		} else {
			this.fixedRoomList = new ArrayList<String>();
		}*/
		this.fixedRoomList = new ArrayList<String>(Arrays.asList(fixedRoom.split(",")));
	}

	// �Q�b�^�[�E�Z�b�^�[
	// �R�[�XID
	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	// �R�[�X���
	public int geteSize() {
		return eSize;
	}

	public void seteSize(int eSize) {
		this.eSize = eSize;
	}

	// �R�[�X����
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	// PC
	public Boolean getPC() {
		return PC;
	}

	public void setPC(Boolean pC) {
		PC = pC;
	}

	// ID
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	// �Ή�PC���X�g
	public ArrayList<String> getSupportedPCList() {
		return supportedPCList;
	}

	public void setSupportedPCList(ArrayList<String> supportedPC) {
		this.supportedPCList = supportedPC;
	}

	// �w�苳�����X�g
	public ArrayList<String> getFixedRoomList() {
		return fixedRoomList;
	}

	public void setFixedRoomList(ArrayList<String> fixedRoom) {
		this.fixedRoomList = fixedRoom;
	}

	// �ŏ��\�l��
	public int getMinESize() {
		return minESize;
	}

	public void setMinESize(int minESize) {
		this.minESize = minESize;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// �R�[�X�̃R�s�[
	public Course clone() {
		Course c = new Course();
		c.courseID = courseID;
		c.eSize = eSize;
		c.length = length;
		c.PC = PC;
		c.ID = ID;
		c.supportedPCList = new ArrayList<String>();
		for (String s : supportedPCList) {
			c.supportedPCList.add(s);
		}
		c.fixedRoomList = new ArrayList<String>();
		for (String s1 : fixedRoomList) {
			c.fixedRoomList.add(s1);
		}
		return c;
	}

	// �R�[�X�̈�v
	public boolean equals(Course c) {
		if (c == null) {
			return false;
		}
		if (c.getID().equals(ID)) {
			return true;
		} else {
			return false;
		}
	}
	//�R�[�X���̕\��
	@Override
	public String toString() {
		//return "CourseID " + ID + " size " + eSize + " PC " + PC;
		return "CourseID " + ID;
	}
}
