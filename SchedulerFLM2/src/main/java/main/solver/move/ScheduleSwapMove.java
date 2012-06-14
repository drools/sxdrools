package main.solver.move;

import java.util.Arrays;
import java.util.Collection;

import main.domain.Classroom;
import main.domain.Day;
import main.domain.Schedule;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.core.move.Move;
import org.drools.planner.core.score.director.ScoreDirector;

public class ScheduleSwapMove implements Move {
	private Schedule leftSchedule;
	private Schedule rightSchedule;

	public ScheduleSwapMove(Schedule leftSchedule, Schedule rightSchedule) {
		this.leftSchedule = leftSchedule;
		this.rightSchedule = rightSchedule;
	}

	public boolean isMoveDoable(ScoreDirector scoreDirector) {
		return !(leftSchedule.getClassroom().equals(
				rightSchedule.getClassroom()) && leftSchedule.getDay().equals(
				rightSchedule.getDay()));
	}

	public Move createUndoMove(ScoreDirector scoreDirector) {
		return new ScheduleSwapMove(rightSchedule, leftSchedule);
	}

	public void doMove(ScoreDirector scoreDirector) {
		// System.out.println(this.toString());
		Day oldLeftDay = leftSchedule.getDay();
		Day oldRightDay = rightSchedule.getDay();
		Classroom oldLeftClassroom = leftSchedule.getClassroom();
		Classroom oldRightClassroom = rightSchedule.getClassroom();
		if (oldLeftDay.equals(oldRightDay)) {
			ScheduleMoveHelper.moveClassroom(scoreDirector, leftSchedule,
					oldRightClassroom);
			ScheduleMoveHelper.moveClassroom(scoreDirector, rightSchedule,
					oldLeftClassroom);
		} else if (oldLeftClassroom.equals(oldRightClassroom)) {
			ScheduleMoveHelper
					.moveDay(scoreDirector, leftSchedule, oldRightDay);
			ScheduleMoveHelper
					.moveDay(scoreDirector, rightSchedule, oldLeftDay);
		} else {
			ScheduleMoveHelper.moveSchedule(scoreDirector, leftSchedule,
					oldRightDay, oldRightClassroom);
			ScheduleMoveHelper.moveSchedule(scoreDirector, rightSchedule,
					oldLeftDay, oldLeftClassroom);
		}
	}

	public Collection<? extends Object> getPlanningEntities() {
		return Arrays.asList(leftSchedule, rightSchedule);
	}

	public Collection<? extends Object> getPlanningValues() {
		return Arrays.<Object> asList(leftSchedule.getDay(),
				leftSchedule.getClassroom(), rightSchedule.getDay(),
				rightSchedule.getClassroom());
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof ScheduleSwapMove) {
			ScheduleSwapMove other = (ScheduleSwapMove) o;
			return new EqualsBuilder().append(leftSchedule, other.leftSchedule)
					.append(rightSchedule, other.rightSchedule).isEquals();
		} else {
			return false;
		}
	}

	public int hashCode() {
		return new HashCodeBuilder().append(leftSchedule).append(rightSchedule)
				.toHashCode();
	}

	public String toString() {
		return leftSchedule + " <=> " + rightSchedule;
	}

}
