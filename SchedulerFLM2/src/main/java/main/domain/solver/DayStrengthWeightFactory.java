package main.domain.solver;

import main.domain.Day;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.drools.planner.api.domain.variable.PlanningValueStrengthWeightFactory;
import org.drools.planner.core.solution.Solution;

public class DayStrengthWeightFactory implements PlanningValueStrengthWeightFactory {
	public Comparable createStrengthWeight(Solution solution, Object planningValue) {
		//PlannerSolution psol = (PlannerSolution) solution;
		Day day = (Day) planningValue;
        return new DayStrengthWeight(day);
    }

    public static class DayStrengthWeight implements Comparable<DayStrengthWeight> {

        private final Day day;

        public DayStrengthWeight(Day day) {
            this.day = day;
        }

        public int compareTo(DayStrengthWeight other) {
            return new CompareToBuilder()
                    .append(day.getID(), other.day.getID())
                    .toComparison();
        }

    }
}
