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

	// �����o�ϐ��̒�`
	// ����
	public final List<Classroom> classroomList;
	// �c�Ɠ�
	public final List<Day> dayList;
	// �R�[�X
	public final List<Course> courseList;
	// �u���b�N����
	public final List<BlockedClassroom> blockedClassroomList;
	// �X�P�W���[��
	public List<Schedule> scheduleList;
	// �R�[�X������i�R�[�X�̒������������ɂ����́j
	public final List<CourseTotalSize> courseTotalSizeList;

	public ArrayList<RuleLogger> ruleLog;

	// �f�[�^�q�ɂ̍쐬
	public DataStorage() {
		classroomList = new ArrayList<Classroom>();
		dayList = new ArrayList<Day>();
		courseList = new ArrayList<Course>();

		scheduleList = new ArrayList<Schedule>();
		blockedClassroomList = new ArrayList<BlockedClassroom>();
		courseTotalSizeList = new ArrayList<CourseTotalSize>();

		ruleLog = new ArrayList<RuleLogger>();
	}

	// �Q�b�^�[
	// �c�Ɠ�
	public Day getDay(String day) {
		for (Day d : dayList) {
			if (d.getID().equals(day)) {
				return d;
			}
		}
		return null;
	}

	// ����
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
