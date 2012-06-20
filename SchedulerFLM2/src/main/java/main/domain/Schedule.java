package main.domain;

import main.domain.solver.ClassroomStrengthWeightFactory;
import main.domain.solver.DayStrengthWeightFactory;
import main.domain.solver.ScheduleDifficultyWeightFactory;

import org.drools.planner.api.domain.entity.PlanningEntity;
import org.drools.planner.api.domain.variable.PlanningVariable;
import org.drools.planner.api.domain.variable.ValueRange;
import org.drools.planner.api.domain.variable.ValueRangeType;

@PlanningEntity(difficultyWeightFactoryClass = ScheduleDifficultyWeightFactory.class)
public class Schedule {

	// �ϐ��̒�`
	private Course course;
	private int scheduleID;

	// Planning variable
	private Classroom classroom;
	private Day day;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public Schedule() {
	}

	// ��������i�R�[�X,����,�c�Ɠ��j
	public Schedule(Course course, Classroom classroom, Day day) {
		this.scheduleID = course.getCourseID();
		this.course = course;
		this.classroom = classroom;
		this.day = day;
	}
	
	public Schedule(int scheduleID, Course course, Classroom classroom, Day day) {
		this.scheduleID = scheduleID;
		this.course = course;
		this.classroom = classroom;
		this.day = day;
	}

	// �Q�b�^�[�E�Z�b�^�[
	// �X�P�W���[��ID
	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	// �R�[�X
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@PlanningVariable(strengthWeightFactoryClass = ClassroomStrengthWeightFactory.class)
	@ValueRange(type = ValueRangeType.FROM_SOLUTION_PROPERTY, solutionProperty = "classroomList")
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@PlanningVariable(strengthWeightFactoryClass = DayStrengthWeightFactory.class)
	@ValueRange(type = ValueRangeType.FROM_SOLUTION_PROPERTY, solutionProperty = "dayList")
	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	// ***********************************************************************************
	// Complex methods
	// ***********************************************************************************
	// �X�P�W���[���̃R�s�[
	public Schedule clone() {
		Schedule c = new Schedule();
		c.course = course.clone();
		c.classroom = classroom.clone();
		c.day = day.clone();
		c.scheduleID = scheduleID;
		return c;
	}

	// �X�P�W���[�����̕\��
	@Override
	public String toString() {
		return course.toString() + "@" + classroom.toString() + " on "
				+ day.toString();
	}

	// �R�[�X�J�Ó����̏d��
	public boolean conflictDayCheck(Schedule c) {
		if ((c == null) || (day == null) || (classroom == null)) {
			return false;
		} else {
			return ((c.getDay().getDayID() <= (this.day.getDayID()
					+ this.course.getLength() - 1)) && (c.getDay().getDayID() >= this.day
					.getDayID()))
					|| ((this.day.getDayID() <= (c.getDay().getDayID()
							+ c.getCourse().getLength() - 1)) && (this.day
							.getDayID() >= c.getDay().getDayID()));
		}
	}

	// �R�[�X�J�Ó����̏d���i�u���b�N�j
	public boolean conflictDayCheck(BlockedClassroom blockedClassroom) {
		if (blockedClassroom == null) {
			return false;
		} else {
			return ((blockedClassroom.getDay().getDayID() <= (this.day
					.getDayID() + this.course.getLength() - 1)) && (blockedClassroom
					.getDay().getDayID() >= this.day.getDayID()))
					|| ((this.day.getDayID() <= (blockedClassroom.getDay()
							.getDayID() + blockedClassroom.getLength() - 1)) && (this.day
							.getDayID() >= blockedClassroom.getDay().getDayID()));
		}
	}

	// �c�Ɠ����ł̊J��
	public boolean finishInWeek() {
		if (day == null) {
			return false;
		} else {
			// would planning course finish in week? (FRI = 4)
			if ((int) day.getDayweek1().getDayweek() <= 5 - course.getLength())
				return true;
			else
				return false;
		}
	}

	// PC�̎�ރ`�F�b�N
	public boolean checkPCRequirement() {
		if (course.getFixedRoomList().size() != 0) {
			return true;
		}
		return course.getSupportedPCList().contains(classroom.getPcType());
	}

	// �w�苳���̃`�F�b�N
	public boolean checkFixedRoomRequirement() {
		if (course.getFixedRoomList() == null) {
			return true;
		}
		/*if ((course.getFixedRoomList().size() == 1)
				&& (course.getFixedRoomList().get(0).equals(""))) {
			return true;
		}*/
		if (course.getFixedRoomList().size() == 0){
			return true;
		}
		return course.getFixedRoomList().contains(classroom.getID());
	}

}
