package main.data;

import main.domain.*;
import java.io.File; 
import java.io.IOException;
import jxl.read.biff.*;

import java.util.Date; 
import jxl.*;

import java.util.Locale;



public class ImportData {
	private DataStorage storage;
	
	public ImportData() {
		storage = new DataStorage();
	}
	
	public ImportData(DataStorage storage) {
		this.storage = storage;
	}
	
	// setter - getter
	public void setStorage(DataStorage storage) {
		this.storage = storage;
	}
	
	public DataStorage getStorage() {
		return storage;
	}
	
	private boolean importClassroomMaster(Sheet sheet) {
		if ((!sheet.getName().equals("ClassroomMaster"))||
				(!sheet.getCell(0, 0).getContents().equals("ID"))||(!sheet.getCell(1, 0).getContents().equals("Capacity"))||
				(!sheet.getCell(2, 0).getContents().equals("PC"))) {
			return false;
		}
		
		for (int i=1;i<sheet.getRows();i++) {
			storage.classroomList.add(new Classroom(i-1,
					sheet.getCell(0, i).getContents(),Integer.parseInt(sheet.getCell(1, i).getContents()),
					Boolean.parseBoolean(sheet.getCell(2, i).getContents())));
    	}
		
		return true;
	}
	
	private boolean importCourseMaster(Sheet sheet) {
		if (!(sheet.getName().equals("CourseMaster"))||
				(!sheet.getCell(0, 0).getContents().equals("ID"))||(!sheet.getCell(1, 0).getContents().equals("Expected Size"))||
				(!sheet.getCell(2, 0).getContents().equals("Length"))||(!sheet.getCell(3, 0).getContents().equals("PC"))) {
			return false;
		}
		
		for (int i=1;i<sheet.getRows();i++) {
			storage.courseList.add(new Course(i-1,
					sheet.getCell(0, i).getContents(),Integer.parseInt(sheet.getCell(1, i).getContents()),
					Integer.parseInt(sheet.getCell(2, i).getContents()),Boolean.parseBoolean(sheet.getCell(3, i).getContents())));
    	}
		
		return true;
	}
	
	private boolean importDayMaster(Sheet sheet) {
		if ((!sheet.getName().equals("DayMaster"))||
				(!sheet.getCell(0, 0).getContents().equals("ID"))||(!sheet.getCell(1, 0).getContents().equals("DayWeek"))||
				(!sheet.getCell(2, 0).getContents().equals("Week"))) {
			return false;
		}
		
		for (int i=1;i<sheet.getRows();i++) {
			storage.dayList.add(new Day(i-1,
					sheet.getCell(0, i).getContents(),DayWeek.parseDayWeek(Integer.parseInt(sheet.getCell(1, i).getContents())),
					Week.parseWeek(Integer.parseInt(sheet.getCell(2, i).getContents()))));
    	}
		
		return true;
	}
	
	public void importFromXLS(String filename) {
		
		storage = new DataStorage();
		
		try
        {
        	File f1=new File(filename);
        	WorkbookSettings ws=new WorkbookSettings();
        	ws.setLocale(new Locale("er","ER"));
        	Workbook workbook=Workbook.getWorkbook(f1,ws);
        	      	
        	System.out.println("Import Course = " + importCourseMaster(workbook.getSheet(0)));
        	System.out.println("Import Classroom = " + importClassroomMaster(workbook.getSheet(1)));
        	System.out.println("Import Day = " + importDayMaster(workbook.getSheet(2)));
        	
        	// Initialize Schedule Data
        	
        	for (Course course : storage.courseList) {
    			storage.scheduleList.add(new Schedule (course,storage.classroomList.get(2),storage.dayList.get(0)));
    		}
    		
        	workbook.close();
        	
    		/*storage.scheduleList.get(0).setDay(storage.dayList.get(2));
    		System.out.println(storage.scheduleList.get(0).finishInWeek());*/
        	
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
       
        catch(BiffException e)
        {
        	e.printStackTrace();
        }

	}
	
	// initial test data
	public void initialtest() {
		storage = new DataStorage();
		
		storage.classroomList.add(new Classroom(1,18,false));
		storage.classroomList.add(new Classroom(2,20,true) );
		storage.classroomList.add(new Classroom(3,15,true) );
		storage.classroomList.add(new Classroom(4,13,false));
		
		storage.courseList.add(new Course(1,13,1,true));
		storage.courseList.add(new Course(2,10,2,true));
		storage.courseList.add(new Course(3,13,3,false));
		storage.courseList.add(new Course(4,10,1,false));
		storage.courseList.add(new Course(5,11,2,true));
		storage.courseList.add(new Course(6,15,1,true));
		storage.courseList.add(new Course(7,12,2,false));
		storage.courseList.add(new Course(8,18,1,false));
		
		storage.dayList.add(new Day(1,DayWeek.MON,Week.FIRST));
		storage.dayList.add(new Day(2,DayWeek.TUE,Week.FIRST));
		storage.dayList.add(new Day(3,DayWeek.WED,Week.FIRST));
		storage.dayList.add(new Day(4,DayWeek.THU,Week.FIRST));
		storage.dayList.add(new Day(5,DayWeek.FRI,Week.FIRST));
		
		for (Course course : storage.courseList) {
			//storage.scheduleList.add(new Schedule (course,storage.classroomList.get(2),storage.dayList.get(0)));
			storage.scheduleList.add(new Schedule (course,null,null));
		}
		
		storage.scheduleList.get(0).setDay(storage.dayList.get(2));
		System.out.println(storage.scheduleList.get(0).finishInWeek());
	}
	
	
}
