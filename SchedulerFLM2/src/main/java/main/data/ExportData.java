package main.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.drools.ClassObjectFilter;
import org.drools.WorkingMemory;
import org.drools.planner.core.score.constraint.ConstraintOccurrence;
import org.drools.planner.core.score.constraint.ConstraintType;
import org.drools.planner.core.score.director.ScoreDirector;
import org.drools.planner.core.score.director.drools.DroolsScoreDirector;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import main.app.ScoreDetail;
import main.domain.Schedule;

public class ExportData {

	// メンバ変数の定義
	// スケジュール
	private List<Schedule> scheduleList;
	private ScoreDirector scoreDirector;
	private ArrayList<RuleLogger> ruleLog;
	
	// コンストラクタの設定
	// 引数なし
	public ExportData() {
	}

	// 引数あり
	public ExportData(List<Schedule> scheduleList, ScoreDirector scoreDirector) {
		this.scheduleList = scheduleList;
		this.scoreDirector = scoreDirector;
	}
	
	public ExportData(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	public ExportData(List<Schedule> scheduleList, ArrayList<RuleLogger> ruleLog) {
		this.scheduleList = scheduleList;
		this.ruleLog = ruleLog;
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

			//write Rules list
			
			sheet = workbook.createSheet("BrokenRule", 1);

			i = 0;

			sheet.addCell(new Label(0, i, "Broken Rule Name"));
			sheet.addCell(new Label(1, i, "Broken Elements"));
			i++;

			for (RuleLogger brokenRule : ruleLog) {
				sheet.addCell(new Label(0, i, brokenRule.getRuleID()));
				sheet.addCell(new Label(1, i, brokenRule.getMessage()));
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
			
			//write Schedule List
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
			
			//write Rules list
			
			sheet = workbook.createSheet("BrokenRule", 1);

			i = 0;

			sheet.addCell(new Label(0, i, "Broken Rule Name"));
			sheet.addCell(new Label(1, i, "Broken Elements"));
			i++;

			for (RuleLogger brokenRule : ruleLog) {
				sheet.addCell(new Label(0, i, brokenRule.getRuleID()));
				sheet.addCell(new Label(1, i, brokenRule.getMessage()));
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

	private List<ScoreDetail> getScoreDetailList() {
	    if (!(scoreDirector instanceof DroolsScoreDirector)) {
	        return null;
	    }
	    Map<String, ScoreDetail> scoreDetailMap = new HashMap<String, ScoreDetail>();
	    WorkingMemory workingMemory = ((DroolsScoreDirector) scoreDirector).getWorkingMemory();
	    if (workingMemory == null) {
	        return Collections.emptyList();
	    }
	    Iterator<ConstraintOccurrence> it = (Iterator<ConstraintOccurrence>) workingMemory.iterateObjects(
	            new ClassObjectFilter(ConstraintOccurrence.class));
	    while (it.hasNext()) {
	        ConstraintOccurrence constraintOccurrence = it.next();
	        ScoreDetail scoreDetail = scoreDetailMap.get(constraintOccurrence.getRuleId());
	        if (scoreDetail == null) {
	            scoreDetail = new ScoreDetail(constraintOccurrence.getRuleId(), constraintOccurrence.getConstraintType());
	            scoreDetailMap.put(constraintOccurrence.getRuleId(), scoreDetail);
	        }
	        scoreDetail.addConstraintOccurrence(constraintOccurrence);
	    }
	    List<ScoreDetail> scoreDetailList = new ArrayList<ScoreDetail>(scoreDetailMap.values());
	    Collections.sort(scoreDetailList);
	    return scoreDetailList;
	}


	public ArrayList<RuleLogger> getRuleLog() {
		return ruleLog;
	}

	public void setRuleLog(ArrayList<RuleLogger> ruleLog) {
		this.ruleLog = ruleLog;
	}
}
