package main.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Course implements Serializable {
	private int courseID;
	private int eSize;
	private int length;
	private Boolean PC;
	private String ID;
	private ArrayList<String> supportedPCList;
	private ArrayList<String> fixedRoomList;
	//minEsize == 0 means course size is fixed
	private int minESize;
	
	public Course() { }
	
	public Course(int courseID, int eSize, int length, Boolean PC) {
		this.courseID=courseID;
		this.eSize=eSize;
		this.length=length;
		this.PC=PC;
	}
	
	public Course(int courseID, String ID, int eSize, int length, Boolean PC) {
		this.courseID=courseID;
		this.eSize=eSize;
		this.length=length;
		this.PC=PC;
		this.ID=ID;
	}
	
	public Course(int courseID, String ID, String eSize, String minSize, String length, 
			String PC, String fixedRoom, String supportedPC) {
		this.courseID=courseID;
		this.eSize=Integer.parseInt(eSize);
		this.length=Integer.parseInt(length);
		if (!minSize.equals("")) {
			this.minESize = Integer.parseInt(minSize);
		} else {
			//minEsize == 0 means course size is fixed
			this.minESize = 0;
		}
		
		if (PC.equals("")) {
			this.PC = (Boolean) null;
		} else {
			this.PC = Boolean.parseBoolean(PC);
		}
		this.ID=ID;
		this.supportedPCList = new ArrayList<String>(Arrays.asList(supportedPC.split(",")));
		this.fixedRoomList = new ArrayList<String>(Arrays.asList(fixedRoom.split(",")));
	}
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int geteSize() {
		return eSize;
	}
	public void seteSize(int eSize) {
		this.eSize = eSize;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Boolean getPC() {
		return PC;
	}
	public void setPC(Boolean pC) {
		PC = pC;
	}

	//complex method
	public Course clone() {
		Course c = new Course();
		c.courseID=courseID;
		c.eSize=eSize;
		c.length=length;
		c.PC=PC;
		c.ID=ID;
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
	
	public String toString() {
		return "CourseID " + ID + " size " + eSize + " PC " + PC;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<String> getSupportedPCList() {
		return supportedPCList;
	}

	public void setSupportedPCList(ArrayList<String> supportedPC) {
		this.supportedPCList = supportedPC;
	}

	public ArrayList<String> getFixedRoomList() {
		return fixedRoomList;
	}

	public void setFixedRoomList(ArrayList<String> fixedRoom) {
		this.fixedRoomList = fixedRoom;
	}

	public int getMinESize() {
		return minESize;
	}

	public void setMinESize(int minESize) {
		this.minESize = minESize;
	}

}
