//�p�b�P�[�W�̍쐬
package main.domain;
//�p�b�P�[�W�̃C���|�[�g
import java.io.Serializable;

public class BlockedClassroom implements Serializable{
	//�����o�ϐ��̒�`
	private Classroom classroom;
	private Day day;
	private int length;
	
	//�R���X�g���N�^�̐ݒ�
	//�����Ȃ�
	public BlockedClassroom() {	}
	//��������
	public BlockedClassroom(Classroom classroom, Day day, String length) {
		this.classroom = classroom;
		this.day = day;
		this.length = Integer.parseInt(length);
	}
	
	//�Q�b�^�[�E�Z�b�^�[
	//����
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	//�c�Ɠ�
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	//�R�[�X����
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

}
