//パッケージの作成
package main.domain;

//パッケージのインポート
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1887271211307841703L;

	// メンバ変数の定義
	private int courseID;
	private int eSize;
	private int length;
	private Boolean PC;
	private String ID;
	private ArrayList<String> supportedPCList;
	private ArrayList<String> fixedRoomList;
	private Boolean group;

	// コンストラクタの設定
	// 引数なし
	public Course() {
	}

	// 引数あり（コースID、コース定員、コース期間、PC）
	public Course(int courseID, int eSize, int length, Boolean PC) {
		this.courseID = courseID;
		this.eSize = eSize;
		this.length = length;
		this.PC = PC;
	}

	// 引数あり（+ID）
	public Course(int courseID, String ID, int eSize, int length, Boolean PC) {
		this.courseID = courseID;
		this.eSize = eSize;
		this.length = length;
		this.PC = PC;
		this.ID = ID;
	}

	// 引数あり（+グループ, 指定教室, サポートPC）
	public Course(int courseID, String ID, String eSize, String group,
			String length, String PC, String fixedRoom, String supportedPC) {
		this.courseID = courseID;
		this.eSize = Integer.parseInt(eSize);
		this.length = Integer.parseInt(length);
		this.group = Boolean.parseBoolean(group);

		if (PC.equals("")) {
			this.PC = (Boolean) null;
		} else {
			this.PC = Boolean.parseBoolean(PC);
		}
		this.ID = ID;
		if (!supportedPC.replace(" ", "").equals("")) {
			this.supportedPCList = new ArrayList<String>(
					Arrays.asList(supportedPC.split(",")));
		} else {
			this.supportedPCList = new ArrayList<String>();
		}
		this.fixedRoomList = new ArrayList<String>(Arrays.asList(fixedRoom
				.split(",")));
	}

	// ゲッター・セッター
	// コースID
	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	// コース定員
	public int geteSize() {
		return eSize;
	}

	public void seteSize(int eSize) {
		this.eSize = eSize;
	}

	// コース期間
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

	// 対応PCリスト
	public ArrayList<String> getSupportedPCList() {
		return supportedPCList;
	}

	public void setSupportedPCList(ArrayList<String> supportedPC) {
		this.supportedPCList = supportedPC;
	}

	// 指定教室リスト
	public ArrayList<String> getFixedRoomList() {
		return fixedRoomList;
	}

	public void setFixedRoomList(ArrayList<String> fixedRoom) {
		this.fixedRoomList = fixedRoom;
	}

	public Boolean getGroup() {
		return group;
	}

	public void setGroup(Boolean group) {
		this.group = group;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// コースのコピー
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

	// コースの一致
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

	// コース情報の表示
	@Override
	public String toString() {
		// return "CourseID " + ID + " size " + eSize + " PC " + PC;
		return "CourseID " + ID;
	}
}
