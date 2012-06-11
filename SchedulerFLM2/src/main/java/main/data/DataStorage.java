package main.data;

import main.domain.BlockedClassroom;
import main.domain.Classroom;
import main.domain.Day;
import main.domain.Course;
import main.domain.Schedule;

import java.util.List;
import java.util.ArrayList;

public class DataStorage {
	public List<Classroom> classroomList;
	public List<Day> dayList;
	public List<Course> courseList;
	public List<BlockedClassroom> blockedClassroomList;
	
	public List<Schedule> scheduleList;
	
	public DataStorage() {
		classroomList = new ArrayList<Classroom>();
		dayList = new ArrayList<Day>();
		courseList = new ArrayList<Course>();
		
		scheduleList = new ArrayList<Schedule>();
		blockedClassroomList = new ArrayList<BlockedClassroom>();
	}
	
	public Day getDay(String day) {
		for (Day d : dayList) {
			if (d.getID().equals(day)) {
				return d;
			}
		}
		return null;
	}
	
	public Classroom getClassroom(String classroom) {
		for (Classroom c : classroomList) {
			if (c.getID().equals(classroom)) {
				return c;
			}
		}
		return null;
	}
	
}
