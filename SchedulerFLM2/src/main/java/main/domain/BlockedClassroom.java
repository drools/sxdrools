package main.domain;

import java.io.Serializable;

public class BlockedClassroom implements Serializable{
	private Classroom classroom;
	private Day day;
	
	public BlockedClassroom() {	}
	
	public BlockedClassroom(Classroom classroom, Day day) {
		this.classroom = classroom;
		this.day = day;
	}
	
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}

}
