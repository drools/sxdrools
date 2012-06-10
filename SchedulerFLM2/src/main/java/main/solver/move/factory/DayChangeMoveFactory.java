package main.solver.move.factory;

import main.domain.Schedule;
import main.domain.Day;
import main.domain.PlannerSolution;
import main.solver.move.DayChangeMove;


import java.util.ArrayList;
import java.util.List;

import org.drools.planner.core.move.Move;
import org.drools.planner.core.move.factory.CachedMoveFactory;
import org.drools.planner.core.solution.Solution;

public class DayChangeMoveFactory extends CachedMoveFactory{
	 public List<Move> createCachedMoveList(Solution solution) {
	        PlannerSolution planner = (PlannerSolution) solution;
	        List<Day> dayList = planner.getDayList();
	        List<Move> moveList = new ArrayList<Move>();
	        for (Schedule schedule : planner.getScheduleList()) {
	            for (Day day : dayList) {
	                moveList.add(new DayChangeMove(schedule, day));
	            }
	        }
	        return moveList;
	    }
}
