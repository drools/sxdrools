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

	// メンバ変数の定義
	// スケジュール
	private List<Schedule> scheduleList;

	// コンストラクタの設定
	// 引数なし
	public ExportData() {
	}

	// 引数あり
	public ExportData(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	// メソッド
	// 初期テストの結果表示
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

	// エクセルシートへのエクスポート（テスト用）
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

	// エクセルシートのエクスポート（仮）
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
