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

	// 変数の定義
	private Course course;
	private int scheduleID;

	// Planning variable
	private Classroom classroom;
	private Day day;

	// コンストラクタの設定
	// 引数なし
	public Schedule() {
	}

	// 引数あり（コース,教室,営業日）
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

	// ゲッター・セッター
	// スケジュールID
	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	// コース
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
	// スケジュールのコピー
	public Schedule clone() {
		Schedule c = new Schedule();
		c.course = course.clone();
		c.classroom = classroom.clone();
		c.day = day.clone();
		c.scheduleID = scheduleID;
		return c;
	}

	// スケジュール情報の表示
	@Override
	public String toString() {
		return course.toString() + "@" + classroom.toString() + " on "
				+ day.toString();
	}

	// コース開催日程の重複
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

	// コース開催日程の重複（ブロック）
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

	// 営業日内での開催
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

	// PCの種類チェック
	public boolean checkPCRequirement() {
		if (course.getFixedRoomList().size() != 0) {
			return true;
		}
		return course.getSupportedPCList().contains(classroom.getPcType());
	}

	// 指定教室のチェック
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
