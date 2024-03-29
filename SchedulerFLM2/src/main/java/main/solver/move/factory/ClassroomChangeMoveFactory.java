package main.solver.move.factory;

import java.util.ArrayList;
import java.util.List;

import main.domain.Classroom;
import main.domain.PlannerSolution;
import main.domain.Schedule;
import main.solver.move.ClassroomChangeMove;

import org.drools.planner.core.move.Move;
import org.drools.planner.core.move.factory.CachedMoveFactory;
import org.drools.planner.core.solution.Solution;

//MoveList（教室）の作成
public class ClassroomChangeMoveFactory extends CachedMoveFactory {
	public List<Move> createCachedMoveList(Solution solution) {
		PlannerSolution planner = (PlannerSolution) solution;
		List<Classroom> classroomList = planner.getClassroomList();
		List<Move> moveList = new ArrayList<Move>();
		for (Schedule schedule : planner.getScheduleList()) {
			for (Classroom classroom : classroomList) {
				moveList.add(new ClassroomChangeMove(schedule, classroom));
			}
		}
		return moveList;
	}
}
