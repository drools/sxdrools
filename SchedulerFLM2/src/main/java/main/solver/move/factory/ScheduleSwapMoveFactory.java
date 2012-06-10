package main.solver.move.factory;

import main.domain.Schedule;
import main.domain.PlannerSolution;
import main.solver.move.ScheduleSwapMove;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.drools.planner.core.move.Move;
import org.drools.planner.core.move.factory.CachedMoveFactory;
import org.drools.planner.core.solution.Solution;

public class ScheduleSwapMoveFactory extends CachedMoveFactory {
    
	public List<Move> createCachedMoveList(Solution solution) {
        PlannerSolution planner = (PlannerSolution) solution;
        List<Schedule> lectureList = planner.getScheduleList();
        List<Move> moveList = new ArrayList<Move>();
        for (ListIterator<Schedule> leftIt = lectureList.listIterator(); leftIt.hasNext();) {
            Schedule leftSchedule = leftIt.next();
            for (ListIterator<Schedule> rightIt = lectureList.listIterator(leftIt.nextIndex()); rightIt.hasNext();) {
                Schedule rightSchedule = rightIt.next();
                if (!leftSchedule.getCourse().equals(rightSchedule.getCourse())) {
                    moveList.add(new ScheduleSwapMove(leftSchedule, rightSchedule));
                }
            }
        }
        return moveList;
    }
}
