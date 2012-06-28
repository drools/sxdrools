package main.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import main.domain.Schedule;
import main.tools.ScoreDetail;

import org.drools.ClassObjectFilter;
import org.drools.WorkingMemory;
import org.drools.planner.core.score.constraint.ConstraintOccurrence;
import org.drools.planner.core.score.director.ScoreDirector;
import org.drools.planner.core.score.director.drools.DroolsScoreDirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportData {

	static final Logger logger = LoggerFactory.getLogger(ExportData.class);
	
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
	// エクセルシートへのエクスポート
	public boolean exportToXLS(String filename) {

		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					filename));
			WritableSheet sheet = workbook.createSheet("Schedule", 0);

			int i = 0;

			// 各データの書き込み
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

			// ルールの書き込み
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
			logger.error("Input " + filename + " cause IOException!");
			return false;
		} catch (WriteException e) {
			logger.error("Input " + filename + " cause WriteException!");
			return false;
		}

	}

	// エクセルシートへのルールログのエクスポート
	public boolean exportLogXLS(String filename) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(
					filename));

			// 制約エラーの書き込み
			WritableSheet sheet = workbook.createSheet("BrokenRule", 1);

			int i = 0;

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
			logger.error("Input " + filename + " cause IOException!");
			return false;
		} catch (WriteException e) {
			logger.error("Input " + filename + " cause WriteException!");
			return false;
		}
	}

	// テキストファイルへのルールログの書き込み
	public boolean exportLogText(String filename) {
		try {
			File f = new File(filename);
			FileWriter out;

			if (!f.exists()) {
				out = new FileWriter(filename);
			} else {
				out = new FileWriter(filename, true);
			}

			for (RuleLogger brokenRule : ruleLog) {
				out.write(brokenRule.getRuleID() + "\t"
						+ brokenRule.getMessage() + "\n");
			}

			out.close();

			return true;
		} catch (Exception e) {// Catch exception if any
			logger.error("Input " + filename + " cause Exception!");
			return false;
		}

	}

	// スコア詳細リストの作成
	private List<ScoreDetail> getScoreDetailList() {
		if (!(scoreDirector instanceof DroolsScoreDirector)) {
			return null;
		}
		Map<String, ScoreDetail> scoreDetailMap = new HashMap<String, ScoreDetail>();
		WorkingMemory workingMemory = ((DroolsScoreDirector) scoreDirector)
				.getWorkingMemory();
		if (workingMemory == null) {
			return Collections.emptyList();
		}
		Iterator<ConstraintOccurrence> it = (Iterator<ConstraintOccurrence>) workingMemory
				.iterateObjects(new ClassObjectFilter(
						ConstraintOccurrence.class));
		while (it.hasNext()) {
			ConstraintOccurrence constraintOccurrence = it.next();
			ScoreDetail scoreDetail = scoreDetailMap.get(constraintOccurrence
					.getRuleId());
			if (scoreDetail == null) {
				scoreDetail = new ScoreDetail(constraintOccurrence.getRuleId(),
						constraintOccurrence.getConstraintType());
				scoreDetailMap.put(constraintOccurrence.getRuleId(),
						scoreDetail);
			}
			scoreDetail.addConstraintOccurrence(constraintOccurrence);
		}
		List<ScoreDetail> scoreDetailList = new ArrayList<ScoreDetail>(
				scoreDetailMap.values());
		Collections.sort(scoreDetailList);
		return scoreDetailList;
	}

	// ゲッター・セッター
	public ArrayList<RuleLogger> getRuleLog() {
		return ruleLog;
	}

	public void setRuleLog(ArrayList<RuleLogger> ruleLog) {
		this.ruleLog = ruleLog;
	}
}
