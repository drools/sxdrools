package main.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import main.domain.BlockedClassroom;
import main.domain.Classroom;
import main.domain.Course;
import main.domain.CourseTotalSize;
import main.domain.Day;
import main.domain.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ***********************************************************************************
// 開催日程計画作成用
// ***********************************************************************************

public class ImportData {

	// ロガーの作成
	static final Logger logger = LoggerFactory.getLogger(ImportData.class);

	// メンバ変数の定義
	private DataStorage storage;

	// コンストラクタの設定
	// 引数なし
	public ImportData() {
		storage = new DataStorage();
	}

	// 引数あり
	public ImportData(DataStorage storage) {
		this.storage = storage;
	}

	// セッター・ゲッター
	// データ倉庫
	public void setStorage(DataStorage storage) {
		this.storage = storage;
	}

	public DataStorage getStorage() {
		return storage;
	}

	// メソッド
	// 教室マスタのインポート
	private boolean importClassroomMaster(Sheet sheet) {
		if ((!sheet.getName().equals("ClassroomMaster"))
				|| (!sheet.getCell(0, 0).getContents().equals("ID"))
				|| (!sheet.getCell(1, 0).getContents().equals("Capacity"))
				|| (!sheet.getCell(2, 0).getContents().equals("PC"))
				|| (!sheet.getCell(3, 0).getContents().equals("PC Type"))
				|| (!sheet.getCell(4, 0).getContents().equals("Group Capacity"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				storage.classroomList.add(new Classroom(i - 1, sheet.getCell(0,
						i).getContents(), sheet.getCell(1, i).getContents(),
						sheet.getCell(2, i).getContents(), sheet.getCell(3, i)
								.getContents(), sheet.getCell(4, i)
								.getContents()));
			} catch (Exception e) {
				logger.error("Error in ClassroomMaster row " + i);
			}
		}

		return true;
	}

	// コース開催条件マスタのインポート
	private boolean importCourseMaster(Sheet sheet) {
		if (!(sheet.getName().equals("CourseMaster"))
				|| (!sheet.getCell(0, 0).getContents().equals("ID"))
				|| (!sheet.getCell(1, 0).getContents().equals("Expected Size"))
				|| (!sheet.getCell(2, 0).getContents().equals("Group"))
				|| (!sheet.getCell(3, 0).getContents().equals("Length"))
				|| (!sheet.getCell(4, 0).getContents().equals("PC"))
				|| (!sheet.getCell(5, 0).getContents().equals("Fixed Room"))
				|| (!sheet.getCell(6, 0).getContents().equals("Required PC"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				Course c = new Course(i - 1, sheet.getCell(0, i).getContents(),
						sheet.getCell(1, i).getContents(), sheet.getCell(2, i)
								.getContents(), sheet.getCell(3, i)
								.getContents(), sheet.getCell(4, i)
								.getContents(), sheet.getCell(5, i)
								.getContents(), sheet.getCell(6, i)
								.getContents());

				ArrayList<String> newFixedList = new ArrayList<String>();
				for (String s : c.getFixedRoomList()) {
					if (storage.getClassroom(s) != null) {
						newFixedList.add(s);
					}
				}
				c.setFixedRoomList(newFixedList);

				storage.courseList.add(c);
			} catch (Exception e) {
				logger.error("Error in CourseMaster row " + i);
				// e.printStackTrace();
			}
		}

		return true;
	}

	// ブロック教室マスタのインポート
	private boolean importBlockedClassroomMaster(Sheet sheet) {
		if (!(sheet.getName().equals("BlockedClassRoomMaster"))
				|| (!sheet.getCell(0, 0).getContents().equals("Classroom"))
				|| (!sheet.getCell(1, 0).getContents().equals("Day"))
				|| (!sheet.getCell(2, 0).getContents().equals("Length"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				if ((storage.getClassroom(sheet.getCell(0, i).getContents()) != null)
						&& (storage.getDay(sheet.getCell(1, i).getContents()) != null)) {
					storage.blockedClassroomList.add(new BlockedClassroom(
							storage.getClassroom(sheet.getCell(0, i)
									.getContents()), storage.getDay(sheet
									.getCell(1, i).getContents()), sheet
									.getCell(2, i).getContents()));
				}
			} catch (Exception e) {
				logger.error("Error in BlockedClassroom row " + i);
			}
		}

		return true;
	}

	// コース総定員のインポート
	private boolean importCourseTotalSize(Sheet sheet) {
		if (!(sheet.getName().equals("TotalSize"))
				|| (!sheet.getCell(0, 0).getContents().equals("Course"))
				|| (!sheet.getCell(1, 0).getContents().equals("Total Size"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {

				storage.courseTotalSizeList.add(new CourseTotalSize(storage
						.getCourse(sheet.getCell(0, i).getContents()), sheet
						.getCell(1, i).getContents()));
			} catch (Exception e) {
				logger.error("Error in CourseTotalSize row " + i);
			}
		}

		return true;
	}

	// 営業日マスタのインポート
	private boolean importDayMaster(Sheet sheet) {
		if ((!sheet.getName().equals("DayMaster"))
				|| (!sheet.getCell(0, 0).getContents().equals("ID"))
				|| (!sheet.getCell(1, 0).getContents().equals("DayWeek"))
				|| (!sheet.getCell(2, 0).getContents().equals("DayWeekHoliday"))
				|| (!sheet.getCell(3, 0).getContents().equals("Week"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				storage.dayList.add(new Day(i - 1, sheet.getCell(0, i)
						.getContents(), sheet.getCell(1, i).getContents(),
						sheet.getCell(2, i).getContents(), sheet.getCell(3, i)
								.getContents()));
			} catch (Exception e) {
				logger.error("Error in DayMaster row " + i);
			}
		}

		return true;
	}

	// エクセルシートのインポート
	public void importFromXLS(String filename) {

		storage = new DataStorage();

		try {
			File f1 = new File(filename);
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(Locale.JAPANESE);
			Workbook workbook = Workbook.getWorkbook(f1, ws);

			logger.info("Import Classroom = "
					+ importClassroomMaster(workbook.getSheet(1)));
			logger.info("Import Day = " + importDayMaster(workbook.getSheet(2)));
			logger.info("Import BlockedClassroom = "
					+ importBlockedClassroomMaster(workbook.getSheet(3)));
			logger.info("Import Course = "
					+ importCourseMaster(workbook.getSheet(0)));
			logger.info("Import TotalCourseSize = "
					+ importCourseTotalSize(workbook.getSheet(4)));

			// 初期スケジュールデータ（ブロック教室）の書き込み

			for (Course course : storage.courseList) {
				if (course.getFixedRoomList().size() > 0) {
					storage.scheduleList.add(new Schedule(course, storage
							.getClassroom(course.getFixedRoomList().get(0)),
							storage.dayList.get(0)));
				} else {
					storage.scheduleList.add(new Schedule(course,
							storage.classroomList.get(0), storage.dayList
									.get(0)));
				}
			}

			workbook.close();

		} catch (IOException e) {
			logger.error("Input " + filename + " cause IOException!");
		}

		catch (BiffException e) {
			logger.error("Input " + filename + " cause BiffException!");
		}

	}

	// ***********************************************************************************
	// アウトプットスケジュール確認用
	// ***********************************************************************************

	// エクセルシートのインポート
	public void importFromOutputXLS(String filename) {

		try {
			File f1 = new File(filename);
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(Locale.JAPANESE);
			Workbook workbook = Workbook.getWorkbook(f1, ws);

			storage.scheduleList.clear();

			logger.info("Import Schedule = "
					+ importScheduleOutput(workbook.getSheet(0)));

			workbook.close();

		} catch (IOException e) {
			logger.error("Input " + filename + " cause IOException!");
		}

		catch (BiffException e) {
			logger.error("Input " + filename + " cause BiffException!");
		}

	}

	// スケジュールアウトプットのインポート
	private boolean importScheduleOutput(Sheet sheet) {
		if ((!sheet.getName().equals("Schedule"))
				|| (!sheet.getCell(0, 0).getContents().equals("Course"))
				|| (!sheet.getCell(1, 0).getContents().equals("Day"))
				|| (!sheet.getCell(3, 0).getContents().equals("Classroom"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				storage.scheduleList.add(new Schedule(i, storage
						.getCourse(sheet.getCell(0, i).getContents()), storage
						.getClassroom(sheet.getCell(3, i).getContents()),
						storage.getDay(sheet.getCell(1, i).getContents())));

			} catch (Exception e) {
				logger.error("Error in ScheduleOutput row " + i);
			}
		}

		return true;
	}

}
