package main.domain.solver;

import main.domain.Course;
import main.domain.CourseTotalSize;
import main.domain.PlannerSolution;
import main.domain.Schedule;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.drools.planner.api.domain.entity.PlanningEntityDifficultyWeightFactory;
import org.drools.planner.core.solution.Solution;

public class ScheduleDifficultyWeightFactory implements PlanningEntityDifficultyWeightFactory {
	public Comparable createDifficultyWeight(Solution solution, Object planningEntity) {
        PlannerSolution psol = (PlannerSolution) solution;
        Schedule schedule = (Schedule) planningEntity;
                
        int hasFixedRoom;
        int totalSize = 0;
        
        if (schedule.getCourse().getFixedRoomList().size() != 0) {
        	hasFixedRoom = 1;
        } else {
        	hasFixedRoom = 0;
        }
        
        if (schedule.getCourse().geteSize() == 0) {
        	for (CourseTotalSize totalSizeEntity : psol.getCourseTotalSizeList()) {
        		if (totalSizeEntity.getCourse().equals(schedule.getCourse())) {
        			totalSize = totalSizeEntity.getTotalSize();
        			break;
        		}
        	}
        } else {
        	totalSize = 0;
        }
              
        return new ScheduleDifficultyWeight(schedule, hasFixedRoom, totalSize);
    }
	
	public static class ScheduleDifficultyWeight implements Comparable<ScheduleDifficultyWeight> {

        private final Schedule schedule;
        private final int hasFixedRoom;
        private final int totalSize;

        public ScheduleDifficultyWeight(Schedule schedule, int hasFixedRoom, int totalSize) {
            this.schedule = schedule;
            this.hasFixedRoom = hasFixedRoom;
            this.totalSize = totalSize;
        }

        public int compareTo(ScheduleDifficultyWeight other) {
        	Course course = schedule.getCourse();
        	Course otherCourse = other.schedule.getCourse();
        	
            return new CompareToBuilder()
                    .append(course.geteSize(), otherCourse.geteSize())
                    .append(hasFixedRoom, other.hasFixedRoom)
                    .append(totalSize, other.totalSize)
                    .append(schedule.getScheduleID(), other.schedule.getScheduleID())
                    .toComparison();
        }

    }
}
