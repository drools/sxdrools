package main.domain;

import org.drools.planner.api.domain.entity.PlanningEntity;
import org.drools.planner.api.domain.variable.PlanningVariable;
import org.drools.planner.api.domain.variable.ValueRange;
import org.drools.planner.api.domain.variable.ValueRangeType;

@PlanningEntity
public class Schedule {
	private Course course;
	
	private int scheduleID;
	
	//Planning variable
	private Classroom classroom;
	private Day day;
	
	public Schedule() {}
	
	public Schedule(Course course, Classroom classroom, Day day) {
		this.scheduleID=course.getCourseID();
		this.course=course;
		this.classroom=classroom;
		this.day=day;
	}
	
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@PlanningVariable
	@ValueRange(type = ValueRangeType.FROM_SOLUTION_PROPERTY, solutionProperty = "classroomList")
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	@PlanningVariable
	@ValueRange(type = ValueRangeType.FROM_SOLUTION_PROPERTY, solutionProperty = "dayList")
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	
	// complex class
  
    public Schedule clone() {
    	Schedule c = new Schedule();
		c.course=course.clone();
		c.classroom=classroom.clone();
		c.day=day.clone();
		c.scheduleID=scheduleID;
		return c;
    }
    
    //Schedule day conflict check
    public boolean conflictDayCheck(Schedule c) {
    	if ((c==null)||(day ==null)||(classroom ==null)) {
    		return false;
    	} else {
    		return ((c.getDay().getDayID() <= (this.day.getDayID() + this.course.getLength() - 1)) &&
    				(c.getDay().getDayID()>=this.day.getDayID()))	||
    				((this.day.getDayID() <= (c.getDay().getDayID() + c.getCourse().getLength() - 1)) &&
    	    		(this.day.getDayID()>=c.getDay().getDayID()));
    	}
    }
    
    //finish in week check
    public boolean finishInWeek() {
    	if (day ==null) {
    		return false;
    	} else {
    		// would planning course finish in week? (FRI = 4) 
    		return ((int) day.getDayweek1().getDayweek() <= 5-course.getLength());
    	}
    }
    
    //PC requirement check
    public boolean checkPCRequirement() {
    	return course.getSupportedPCList().contains(classroom.getPcType());
    }
    
    //PC check room condition
    public boolean checkFixedRoomRequirement() {
    	if (course.getFixedRoomList() == null) {
    		return true;
    	}
    	return course.getFixedRoomList().contains(classroom.getID());
    }
    
    @Override
    public String toString() {
    	return course.toString() + "@" + classroom.toString() + " on " + day.toString();
    }
    
}
