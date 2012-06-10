package main.domain;

import java.io.Serializable;

public class Course implements Serializable {
	private int courseID;
	private int eSize;
	private int length;
	private boolean PC;
	private String ID;
	
	public Course() { }
	
	public Course(int courseID, int eSize, int length, boolean PC) {
		this.courseID=courseID;
		this.eSize=eSize;
		this.length=length;
		this.PC=PC;
	}
	
	public Course(int courseID, String ID, int eSize, int length, boolean PC) {
		this.courseID=courseID;
		this.eSize=eSize;
		this.length=length;
		this.PC=PC;
		this.ID=ID;
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
	public boolean isPC() {
		return PC;
	}
	public void setPC(boolean pC) {
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
}
