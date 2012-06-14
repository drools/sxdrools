package main.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.api.domain.solution.PlanningEntityCollectionProperty;
import org.drools.planner.core.score.buildin.hardandsoft.HardAndSoftScore;
import org.drools.planner.core.solution.Solution;

public class PlannerSolution implements Solution<HardAndSoftScore> {

	// problem fact
	private List<Classroom> classroomList;
	private List<Day> dayList;
	private List<BlockedClassroom> blockedClassroomList;
	private List<CourseTotalSize> courseTotalSizeList;
	// private List<DayWeek> dayWeekList;
	// private List<Week> weekList;
	// private List<Course> courseList;

	// planning entity
	private List<Schedule> scheduleList;

	private HardAndSoftScore score;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public PlannerSolution() {
	}

	/*
	 * public PlannerSolution(List<Schedule> scheduleList, List<Course>
	 * courseList, List<Classroom> classroomList, List<Day> dayList) {
	 * this.classroomList=classroomList; this.scheduleList=scheduleList;
	 * this.courseList=courseList; }
	 */
	// ��������
	public PlannerSolution(List<Schedule> scheduleList,
			List<Classroom> classroomList, List<Day> dayList,
			List<BlockedClassroom> blockedClassroomList,
			List<CourseTotalSize> courseTotalSizeList) {

		this.classroomList = classroomList;
		this.scheduleList = scheduleList;
		this.dayList = dayList;
		this.blockedClassroomList = blockedClassroomList;
		this.courseTotalSizeList = courseTotalSizeList;
	}

	// �Z�b�^�[�E�Q�b�^�[
	// �X�R�A
	public HardAndSoftScore getScore() {
		return score;
	}

	public void setScore(HardAndSoftScore score) {
		this.score = score;
	}

	// �������X�g
	public List<Classroom> getClassroomList() {
		return classroomList;
	}

	public void setClassroomList(List<Classroom> classroomList) {
		this.classroomList = classroomList;
	}

	// �c�Ɠ����X�g
	public List<Day> getDayList() {
		return dayList;
	}

	public void setDayList(List<Day> dayList) {
		this.dayList = dayList;
	}

	// �u���b�N�������X�g
	public List<BlockedClassroom> getBlockedClassroomList() {
		return blockedClassroomList;
	}

	public void setBlockedClassroomList(
			List<BlockedClassroom> blockedClassroomList) {
		this.blockedClassroomList = blockedClassroomList;
	}

	// �R�[�X��������X�g
	public List<CourseTotalSize> getCourseTotalSizeList() {
		return courseTotalSizeList;
	}

	public void setCourseTotalSizeList(List<CourseTotalSize> courseTotalSizeList) {
		this.courseTotalSizeList = courseTotalSizeList;
	}

	/*
	 * public List<DayWeek> getDayWeekList() { return dayWeekList; }
	 * 
	 * public void setDayWeekList(List<DayWeek> dayWeekList) { this.dayWeekList
	 * = dayWeekList; }
	 * 
	 * public List<Week> getWeekList() { return weekList; }
	 * 
	 * public void setWeekList(List<Week> weekList) { this.weekList = weekList;
	 * }
	 */

	/*
	 * public List<Course> getCourseList() { return courseList; }
	 * 
	 * public void setCourseList(List<Course> courseList) { this.courseList =
	 * courseList; }
	 */

	// �X�P�W���[�����X�g
	@PlanningEntityCollectionProperty
	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	// Complex methods
	// �@problemFacts�̏W��
	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		facts.addAll(classroomList);
		facts.addAll(dayList);
		facts.addAll(blockedClassroomList);
		facts.addAll(courseTotalSizeList);
		// facts.addAll(courseList);
		// facts.addAll(dayWeekList);
		// facts.addAll(weekList);

		// Do not add the planning entity's (scheduleList) because that will be
		// done automatically
		return facts;
	}

	// �v�挋�ʂ̃R�s�[
	public PlannerSolution cloneSolution() {
		PlannerSolution clone = new PlannerSolution();

		// clone.courseList = courseList;
		clone.dayList = dayList;
		clone.classroomList = classroomList;
		clone.courseTotalSizeList = courseTotalSizeList;
		clone.blockedClassroomList = blockedClassroomList;
		// clone.dayWeekList = dayWeekList;
		// clone.weekList = weekList;

		List<Schedule> clonedscheduleList = new ArrayList<Schedule>(
				scheduleList.size());
		for (Schedule schedule : scheduleList) {
			Schedule clonedSchedule = schedule.clone();
			clonedscheduleList.add(clonedSchedule);
		}
		clone.scheduleList = clonedscheduleList;
		clone.score = score;
		return clone;
	}

	// hashCode
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		for (Schedule schedule : scheduleList) {
			// Notice: we don't use hashCode()
			hashCodeBuilder.append(schedule.hashCode());
		}
		return hashCodeBuilder.toHashCode();
	}

}
