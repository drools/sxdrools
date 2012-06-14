package main.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import main.domain.Schedule;

public class ExportData {

	// �����o�ϐ��̒�`
	// �X�P�W���[��
	private List<Schedule> scheduleList;

	// �R���X�g���N�^�̐ݒ�
	// �����Ȃ�
	public ExportData() {
	}

	// ��������
	public ExportData(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	// ���\�b�h
	// �����e�X�g�̌��ʕ\��
	public void showInitialTestResult() {
		System.out.println(" ======Result====== ");
		for (Schedule schedule : scheduleList) {
			System.out.println(schedule.toString()
					+ " ||||| "
					+ "Size "
					+ (schedule.getCourse().geteSize() <= schedule
							.getClassroom().getCapacity())
					+ " PC "
					+ (schedule.getCourse().getPC() == schedule.getClassroom()
							.getPC()));
		}
	}

	// �G�N�Z���V�[�g�ւ̃G�N�X�|�[�g�i�e�X�g�p�j
	// export with length for TEST Convenience
	public boolean exportToXLS_debug(String filename) {

		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					filename));
			WritableSheet sheet = workbook.createSheet("Schedule", 0);

			int i = 0;

			sheet.addCell(new Label(0, i, "Course"));
			sheet.addCell(new Label(1, i, "Day"));
			sheet.addCell(new Label(2, i, "Length"));
			sheet.addCell(new Label(3, i, "Classroom"));
			i++;

			for (Schedule schedule : scheduleList) {
				sheet.addCell(new Label(0, i, schedule.getCourse().getID()));
				sheet.addCell(new Label(1, i, schedule.getDay().getID()));
				sheet.addCell(new Label(2, i, String.valueOf(schedule
						.getCourse().getLength())));
				sheet.addCell(new Label(3, i, schedule.getClassroom().getID()));
				i++;
			}

			workbook.write();
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (WriteException e) {
			e.printStackTrace();
			return false;
		}

	}

	// �G�N�Z���V�[�g�̃G�N�X�|�[�g�i���j
	public boolean exportToXLS(String filename) {

		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					filename));
			WritableSheet sheet = workbook.createSheet("Schedule", 0);

			int i = 0;

			sheet.addCell(new Label(0, i, "Course"));
			sheet.addCell(new Label(1, i, "Day"));
			sheet.addCell(new Label(2, i, "Classroom"));
			i++;

			for (Schedule schedule : scheduleList) {
				sheet.addCell(new Label(0, i, schedule.getCourse().getID()));
				sheet.addCell(new Label(1, i, schedule.getDay().getID()));
				sheet.addCell(new Label(2, i, schedule.getClassroom().getID()));
				i++;
			}

			workbook.write();
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (WriteException e) {
			e.printStackTrace();
			return false;
		}

	}

}
