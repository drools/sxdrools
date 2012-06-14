package main.domain;

import java.io.Serializable;

public class CourseTotalSize implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2769799412402018285L;

	private Course course;
	private int totalSize;
	private int countSize;

	public CourseTotalSize() {
	}

	public CourseTotalSize(Course course, String totalSize) {
		this.course = course;
		this.totalSize = Integer.parseInt(totalSize);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

}
