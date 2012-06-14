package main.domain;

import java.io.Serializable;

public class CourseTotalSize implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2769799412402018285L;

	// �����o�ϐ��̒�`
	private Course course;
	private int totalSize;
	private int countSize;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public CourseTotalSize() {
	}

	// ��������
	public CourseTotalSize(Course course, String totalSize) {
		this.course = course;
		this.totalSize = Integer.parseInt(totalSize);
	}

	// �Q�b�^�[�E�Z�b�^�[
	// �R�[�X
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	// �R�[�X�����
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	// �����v���̑����
	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

}
