package main.domain.solver;

import main.domain.Classroom;
//import main.domain.PlannerSolution;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.drools.planner.api.domain.variable.PlanningValueStrengthWeightFactory;
import org.drools.planner.core.solution.Solution;

public class ClassroomStrengthWeightFactory implements PlanningValueStrengthWeightFactory {
	
	public Comparable createStrengthWeight(Solution solution, Object planningValue) {
		//PlannerSolution psol = (PlannerSolution) solution;
        Classroom room = (Classroom) planningValue;
        return new ClassroomStrengthWeight(room);
    }

    public static class ClassroomStrengthWeight implements Comparable<ClassroomStrengthWeight> {

        private final Classroom room;

        public ClassroomStrengthWeight(Classroom room) {
            this.room = room;
        }

        public int compareTo(ClassroomStrengthWeight other) {
            return new CompareToBuilder()
                    .append(room.getCapacity(), other.room.getCapacity())
                    .append(room.getID(), other.room.getID())
                    .toComparison();
        }

    }
}
