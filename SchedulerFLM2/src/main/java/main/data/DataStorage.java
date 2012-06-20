package main.data;

import java.util.ArrayList;
import java.util.List;

import main.domain.BlockedClassroom;
import main.domain.Classroom;
import main.domain.Course;
import main.domain.CourseTotalSize;
import main.domain.Day;
import main.domain.Schedule;

public class DataStorage {

	// メンバ変数の定義
	// 教室
	public final List<Classroom> classroomList;
	// 営業日
	public final List<Day> dayList;
	// コース
	public final List<Course> courseList;
	// ブロック教室
	public final List<BlockedClassroom> blockedClassroomList;
	// スケジュール
	public List<Schedule> scheduleList;
	// コース総定員（コースの定員が教室定員によるもの）
	public final List<CourseTotalSize> courseTotalSizeList;

	public ArrayList<RuleLogger> ruleLog;

	// データ倉庫の作成
	public DataStorage() {
		classroomList = new ArrayList<Classroom>();
		dayList = new ArrayList<Day>();
		courseList = new ArrayList<Course>();

		scheduleList = new ArrayList<Schedule>();
		blockedClassroomList = new ArrayList<BlockedClassroom>();
		courseTotalSizeList = new ArrayList<CourseTotalSize>();

		ruleLog = new ArrayList<RuleLogger>();
	}

	// ゲッター
	// 営業日
	public Day getDay(String day) {
		for (Day d : dayList) {
			if (d.getID().equals(day)) {
				return d;
			}
		}
		return null;
	}

	// 教室
	public Classroom getClassroom(String classroom) {
		for (Classroom c : classroomList) {
			if (c.getID().equals(classroom)) {
				return c;
			}
		}
		return null;
	}

	public Course getCourse(String course) {
		for (Course c : courseList) {
			if (c.getID().equals(course)) {
				return c;
			}
		}
		return null;
	}

}
