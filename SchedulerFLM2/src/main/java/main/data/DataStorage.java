//�p�b�P�[�W�̍쐬
package main.data;
//�p�b�P�[�W�̃C���|�[�g
import main.domain.BlockedClassroom;
import main.domain.Classroom;
import main.domain.CourseTotalSize;
import main.domain.Day;
import main.domain.Course;
import main.domain.Schedule;

import java.util.List;
import java.util.ArrayList;

public class DataStorage {
	
	//�����o�ϐ��̒�`
	//����
	public final List<Classroom> classroomList;
	//�c�Ɠ�
	public final List<Day> dayList;
	//�R�[�X
	public final List<Course> courseList;
	//�u���b�N����
	public final List<BlockedClassroom> blockedClassroomList;
	//�X�P�W���[��
	public final List<Schedule> scheduleList;
	
	public final List<CourseTotalSize> courseTotalSizeList;
	
	//�f�[�^�q�ɂ̍쐬
	public DataStorage() {
		classroomList = new ArrayList<Classroom>();
		dayList = new ArrayList<Day>();
		courseList = new ArrayList<Course>();
		
		scheduleList = new ArrayList<Schedule>();
		blockedClassroomList = new ArrayList<BlockedClassroom>();
		courseTotalSizeList = new ArrayList<CourseTotalSize>();
	}
	//�Q�b�^�[�E�Z�b�^�[
	//�c�Ɠ�
	public Day getDay(String day) {
		for (Day d : dayList) {
			if (d.getID().equals(day)) {
				return d;
			}
		}
		return null;
	}
	//����
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
