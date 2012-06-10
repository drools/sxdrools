package main.domain;

import java.io.Serializable;

public class Classroom implements Serializable {
	private int capacity;
	private boolean PC;
	private int classroomID;
	private String ID;
	
	public Classroom() {}
	
	public Classroom(int classroomID, int capacity, boolean PC) {
		this.capacity=capacity;
		this.PC = PC;
		this.classroomID = classroomID;
	}
	
	public Classroom(int classroomID, String ID, int capacity, boolean PC) {
		this.capacity=capacity;
		this.PC = PC;
		this.classroomID = classroomID;
		this.ID=ID;
	}
	
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public boolean isPC() {
		return PC;
	}
	
	public void setPC(boolean pC) {
		PC = pC;
	}
	
	public int getClassroomID() {
		return classroomID;
	}
	
	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	//complex method
	public Classroom clone() {
		Classroom c = new Classroom();
		c.capacity=capacity;
		c.PC = PC;
		c.classroomID = classroomID;
		c.ID = ID;
		return c;
	}
	
	@Override
	public String toString() {
		return "RoomID " + ID + " limit " + capacity + " PC " + PC;
	}
	
	public boolean equals(Classroom c) {
		return (c.getClassroomID()==classroomID);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
}
