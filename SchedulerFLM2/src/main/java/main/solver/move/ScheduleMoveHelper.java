package main.solver.move;

import main.domain.Classroom;
import main.domain.Day;
import main.domain.Schedule;

import org.drools.planner.core.score.director.ScoreDirector;

public class ScheduleMoveHelper {
	ScheduleMoveHelper() {
	}

	public static void moveClassroom(ScoreDirector scoreDirector,
			Schedule schedule, Classroom classroom) {
		scoreDirector.beforeVariableChanged(schedule, "classroom");
		schedule.setClassroom(classroom);
		scoreDirector.afterVariableChanged(schedule, "classroom");
	}

	public static void moveDay(ScoreDirector scoreDirector, Schedule schedule,
			Day day) {
		scoreDirector.beforeVariableChanged(schedule, "day");
		schedule.setDay(day);
		scoreDirector.afterVariableChanged(schedule, "day");
	}

	public static void moveSchedule(ScoreDirector scoreDirector,
			Schedule schedule, Day day, Classroom classroom) {
		scoreDirector.beforeAllVariablesChanged(schedule);
		schedule.setClassroom(classroom);
		schedule.setDay(day);
		scoreDirector.afterAllVariablesChanged(schedule);
	}

}
