package main.solver.move;

import java.util.Collection;
import java.util.Collections;

import main.domain.Classroom;
import main.domain.Schedule;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.core.move.Move;
import org.drools.planner.core.score.director.ScoreDirector;

public class ClassroomChangeMove implements Move {

	// メンバ変数の定義
	private Schedule schedule;
	private Classroom toClassroom;

	// Move
	public ClassroomChangeMove(Schedule schedule, Classroom toClassroom) {
		this.schedule = schedule;
		this.toClassroom = toClassroom;
	}

	public boolean isMoveDoable(ScoreDirector scoreDirector) {
		return !(schedule.getClassroom().equals(toClassroom));
	}

	public Move createUndoMove(ScoreDirector scoreDirector) {
		return new ClassroomChangeMove(schedule, schedule.getClassroom());
	}

	public void doMove(ScoreDirector scoreDirector) {
		ScheduleMoveHelper.moveClassroom(scoreDirector, schedule, toClassroom);
	}

	public Collection<? extends Object> getPlanningEntities() {
		return Collections.singletonList(schedule);
	}

	public Collection<? extends Object> getPlanningValues() {
		return Collections.singletonList(toClassroom);
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof ClassroomChangeMove) {
			ClassroomChangeMove other = (ClassroomChangeMove) o;
			return new EqualsBuilder().append(schedule, other.schedule)
					.append(toClassroom, other.toClassroom).isEquals();
		} else {
			return false;
		}
	}

	// hashCode
	public int hashCode() {
		return new HashCodeBuilder().append(schedule).append(toClassroom)
				.toHashCode();
	}

	// Move情報の表示
	public String toString() {
		return schedule + " => " + toClassroom;
	}
}
