package main.domain;

import java.io.Serializable;

public class Classroom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5750305416548495970L;

	// メンバ変数の定義
	private int capacity;
	private Boolean PC;
	private int classroomID;
	private String ID;
	private String pcType;

	// コンストラクタの設定
	// 引数なし
	public Classroom() {
	}

	// 引数あり（教室ID, 教室定員, PCの有無）
	public Classroom(int classroomID, int capacity, Boolean PC) {
		this.capacity = capacity;
		this.PC = PC;
		this.classroomID = classroomID;
	}

	// 引数あり（+String　ID）
	public Classroom(int classroomID, String ID, int capacity, Boolean PC) {
		this.capacity = capacity;
		this.PC = PC;
		this.classroomID = classroomID;
		this.ID = ID;
	}

	// 引数あり（+PCの種類）
	public Classroom(int classroomID, String ID, String capacity, String PC,
			String pcType) {
		this.capacity = Integer.parseInt(capacity);
		this.PC = Boolean.parseBoolean(PC);
		this.classroomID = classroomID;
		this.ID = ID;
		this.pcType = pcType;
	}

	// ゲッター・セッター
	// 教室定員
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

	// 教室ID
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

	// PCの種類
	public String getPcType() {
		return pcType;
	}

	public void setPcType(String pcType) {
		this.pcType = pcType;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// 教室のコピー
	public Classroom clone() {
		Classroom c = new Classroom();
		c.capacity = capacity;
		c.PC = PC;
		c.classroomID = classroomID;
		c.ID = ID;
		c.pcType = pcType;
		return c;
	}

	// 教室の一致
	public boolean equals(Classroom c) {
		return (c.getClassroomID() == classroomID);
	}

	// 教室情報の表示
	@Override
	public String toString() {
		return "RoomID " + ID + " limit " + capacity + " PC " + PC;
	}

}
