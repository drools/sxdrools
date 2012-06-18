package main.data;

import java.io.File;
import java.io.IOException;
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
import main.domain.DayWeek;
import main.domain.Schedule;
import main.domain.Week;

public class ImportData {
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
				|| (!sheet.getCell(3, 0).getContents().equals("PC Type"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				storage.classroomList.add(new Classroom(i - 1, sheet.getCell(0,
						i).getContents(), sheet.getCell(1, i).getContents(),
						sheet.getCell(2, i).getContents(), sheet.getCell(3, i)
								.getContents()));
			} catch (Exception e) {
				System.out.println("Error in ClassroomMaster row " + i);
			}
		}

		return true;
	}

	// コース開催条件マスタのインポート
	private boolean importCourseMaster(Sheet sheet) {
		if (!(sheet.getName().equals("CourseMaster"))
				|| (!sheet.getCell(0, 0).getContents().equals("ID"))
				|| (!sheet.getCell(1, 0).getContents().equals("Expected Size"))
				|| (!sheet.getCell(2, 0).getContents().equals("Minimum Size"))
				|| (!sheet.getCell(3, 0).getContents().equals("Length"))
				|| (!sheet.getCell(4, 0).getContents().equals("PC"))
				|| (!sheet.getCell(5, 0).getContents().equals("Fixed Room"))
				|| (!sheet.getCell(6, 0).getContents().equals("Required PC"))) {
			return false;
		}

		for (int i = 1; i < sheet.getRows(); i++) {
			try {
				storage.courseList.add(new Course(i - 1, sheet.getCell(0, i)
						.getContents(), sheet.getCell(1, i).getContents(),
						sheet.getCell(2, i).getContents(), sheet.getCell(3, i)
								.getContents(), sheet.getCell(4, i)
								.getContents(), sheet.getCell(5, i)
								.getContents(), sheet.getCell(6, i)
								.getContents()));
			} catch (Exception e) {
				System.out.println("Error in CourseMaster row " + i);
				e.printStackTrace();
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

				storage.blockedClassroomList.add(new BlockedClassroom(storage
						.getClassroom(sheet.getCell(0, i).getContents()),
						storage.getDay(sheet.getCell(1, i).getContents()),
						sheet.getCell(2, i).getContents()));
			} catch (Exception e) {
				System.out.println("Error in BlockedClassroom row " + i);
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
				System.out.println("Error in CourseTotalSize row " + i);
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
				System.out.println("Error in DayMaster row " + i);
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
			ws.setLocale(new Locale("er", "ER"));
			Workbook workbook = Workbook.getWorkbook(f1, ws);

			System.out.println("Import Course = "
					+ importCourseMaster(workbook.getSheet(0)));
			System.out.println("Import Classroom = "
					+ importClassroomMaster(workbook.getSheet(1)));
			System.out.println("Import Day = "
					+ importDayMaster(workbook.getSheet(2)));
			System.out.println("Import BlockedClassroom = "
					+ importBlockedClassroomMaster(workbook.getSheet(3)));
			System.out.println("Import TotalCourseSize = "
					+ importCourseTotalSize(workbook.getSheet(4)));

			// Initialize Schedule Data

			
			for (Course course : storage.courseList) {
				storage.scheduleList.add(new Schedule(course,
						storage.classroomList.get(0),storage.dayList.get(0)));
			}
			
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (BiffException e) {
			e.printStackTrace();
		}

	}

	// 初期テスト用データ
	public void initialtest() {
		storage = new DataStorage();

		storage.classroomList.add(new Classroom(1, 18, false));
		storage.classroomList.add(new Classroom(2, 20, true));
		storage.classroomList.add(new Classroom(3, 15, true));
		storage.classroomList.add(new Classroom(4, 13, false));

		storage.courseList.add(new Course(1, 13, 1, true));
		storage.courseList.add(new Course(2, 10, 2, true));
		storage.courseList.add(new Course(3, 13, 3, false));
		storage.courseList.add(new Course(4, 10, 1, false));
		storage.courseList.add(new Course(5, 11, 2, true));
		storage.courseList.add(new Course(6, 15, 1, true));
		storage.courseList.add(new Course(7, 12, 2, false));
		storage.courseList.add(new Course(8, 18, 1, false));

		storage.dayList.add(new Day(1, DayWeek.MON, Week.FIRST));
		storage.dayList.add(new Day(2, DayWeek.TUE, Week.FIRST));
		storage.dayList.add(new Day(3, DayWeek.WED, Week.FIRST));
		storage.dayList.add(new Day(4, DayWeek.THU, Week.FIRST));
		storage.dayList.add(new Day(5, DayWeek.FRI, Week.FIRST));

		for (Course course : storage.courseList) {
			// storage.scheduleList.add(new Schedule
			// (course,storage.classroomList.get(2),storage.dayList.get(0)));
			storage.scheduleList.add(new Schedule(course, null, null));
		}

		storage.scheduleList.get(0).setDay(storage.dayList.get(2));
		System.out.println(storage.scheduleList.get(0).finishInWeek());
		}

	// ***********************************************************************************
	// アウトプットスケジュール確認用
	// ***********************************************************************************
	
	// エクセルシートのインポート
	public void importFromOutputXLS(String filename) {

		try {
			File f1 = new File(filename);
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("er", "ER"));
			Workbook workbook = Workbook.getWorkbook(f1, ws);

			storage.scheduleList.clear();
			
			System.out.println("Import Schedule = "
					+ importScheduleOutput(workbook.getSheet(0)));

			workbook.close();

			/*
			 * storage.scheduleList.get(0).setDay(storage.dayList.get(2));
			 * System.out.println(storage.scheduleList.get(0).finishInWeek());
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (BiffException e) {
			e.printStackTrace();
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
					/*
					 * storage.dayList.add(new Day(i-1, sheet.getCell(0,
					 * i).getContents(),
					 * DayWeek.parseDayWeek(Integer.parseInt(sheet.getCell(1,
					 * i).getContents())),
					 * Week.parseWeek(Integer.parseInt(sheet.getCell(2,
					 * i).getContents()))));
					 */
					storage.scheduleList.add(new Schedule(
							storage.getCourse(sheet.getCell(0, i).getContents()),
							storage.getClassroom(sheet.getCell(3, i).getContents()), 
							storage.getDay(sheet.getCell(1, i).getContents())));
					
				} catch (Exception e) {
					System.out.println("Error in ScheduleOutput row " + i);
				}
			}

			return true;
	}
	
		
}
