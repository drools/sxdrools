package main.domain.solver;

import java.util.ArrayList;

import main.domain.BlockedClassroom;
import main.domain.Classroom;
import main.domain.PlannerSolution;
import main.domain.Schedule;
//import main.domain.PlannerSolution;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.drools.planner.api.domain.variable.PlanningValueStrengthWeightFactory;
import org.drools.planner.core.solution.Solution;

public class ClassroomStrengthWeightFactory implements PlanningValueStrengthWeightFactory {
	
	public Comparable createStrengthWeight(Solution solution, Object planningValue) {
		PlannerSolution psol = (PlannerSolution) solution;
        Classroom room = (Classroom) planningValue;
        
        ArrayList<BlockedClassroom> blockedClassroomList = 
        		(ArrayList<BlockedClassroom>) psol.getBlockedClassroomList();
        
        ArrayList<Schedule> scheduleList = (ArrayList<Schedule>) psol.getScheduleList();
        
        int numberOfBlockedDay=0;
        
        for (Schedule schedule : scheduleList) {
        	if (schedule.getCourse().getFixedRoomList().contains(room.getID())) {
        		numberOfBlockedDay+=schedule.getCourse().getLength();
        	}
        }
        
        for (BlockedClassroom blockedClassroom : blockedClassroomList) {
        	if (blockedClassroom.getClassroom().equals(room)) {
        		numberOfBlockedDay+=blockedClassroom.getLength();
        	}
        }
        
        return new ClassroomStrengthWeight(room,numberOfBlockedDay);
    }

    public static class ClassroomStrengthWeight implements Comparable<ClassroomStrengthWeight> {

        private final Classroom room;
        private final int numberOfBlockedDay;

        public ClassroomStrengthWeight(Classroom room, int numberOfBlockedDay) {
            this.room = room;
            this.numberOfBlockedDay = numberOfBlockedDay;
        }

        public int compareTo(ClassroomStrengthWeight other) {
        	int num = new CompareToBuilder()
	    		.append(other.numberOfBlockedDay, numberOfBlockedDay) //descending
	            .append(room.getCapacity(), other.room.getCapacity())
	            .append(room.getClassroomID(), other.room.getClassroomID())
	            .toComparison();
        	return num;
            /*return new CompareToBuilder()
            		.append(other.numberOfBlockedDay, numberOfBlockedDay) //descending
                    .append(room.getCapacity(), other.room.getCapacity())
                    .append(room.getClassroomID(), other.room.getClassroomID())
                    .toComparison();*/
        }

    }
}
