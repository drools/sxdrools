package main.solver.move;

import java.util.Collection;
import java.util.Collections;

import main.domain.Day;
import main.domain.Schedule;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.core.move.Move;
import org.drools.planner.core.score.director.ScoreDirector;

public class DayChangeMove implements Move {

	// �����o�ϐ��̒�`
	private Schedule schedule;
	private Day toDay;

	// Move
	public DayChangeMove(Schedule schedule, Day toDay) {
		this.schedule = schedule;
		this.toDay = toDay;
	}

	public boolean isMoveDoable(ScoreDirector scoreDirector) {
		return !schedule.getDay().equals(toDay);
	}

	public Move createUndoMove(ScoreDirector scoreDirector) {
		return new DayChangeMove(schedule, schedule.getDay());
	}

	public void doMove(ScoreDirector scoreDirector) {
		ScheduleMoveHelper.moveDay(scoreDirector, schedule, toDay);
	}

	public Collection<? extends Object> getPlanningEntities() {
		return Collections.singletonList(schedule);
	}

	public Collection<? extends Object> getPlanningValues() {
		return Collections.singletonList(toDay);
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof DayChangeMove) {
			DayChangeMove other = (DayChangeMove) o;
			return new EqualsBuilder().append(schedule, other.schedule)
					.append(toDay, other.toDay).isEquals();
		} else {
			return false;
		}
	}

	// hashCode
	public int hashCode() {
		return new HashCodeBuilder().append(schedule).append(toDay)
				.toHashCode();
	}

	// Move���̕\��
	public String toString() {
		return schedule + " => " + toDay;
	}

}
