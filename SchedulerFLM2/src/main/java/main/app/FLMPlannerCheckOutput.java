package main.app;

import java.util.ArrayList;

import main.data.DataStorage;
import main.data.ExportData;
import main.data.ImportData;
import main.data.RuleLogger;
import main.domain.PlannerSolution;

import org.drools.planner.config.XmlSolverFactory;
import org.drools.planner.core.Solver;
import org.drools.planner.core.score.director.drools.DroolsScoreDirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FLMPlannerCheckOutput {

	// エンジン用ロガーの作成
	static final Logger logger = LoggerFactory
			.getLogger(FLMPlannerCheckOutput.class);

	// ソルバーの設置
	public static final String SOLVER_CONFIG = "/FLMPlannerRuleCheck.xml";

	// データのチェックの開始
	public static void checkData(String inFile, String outFile, String logFile) {

		// データのインポート
		ImportData importer = new ImportData();
		importer.importFromXLS(inFile);
		importer.importFromOutputXLS(outFile);
		DataStorage storage = importer.getStorage();

		// ソルバーの初期化
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(SOLVER_CONFIG);
		Solver solver = solverFactory.buildSolver();

		// 初期解決値の設定
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);

		// スコアディレクターの作成
		DroolsScoreDirector scoreDirector = (DroolsScoreDirector) solver
				.getScoreDirectorFactory().buildScoreDirector();
		scoreDirector.setWorkingSolution(initialSolution);

		// ルールロガーの作成・書き込み
		scoreDirector.getWorkingMemory().setGlobal("ruleLog",
				new ArrayList<RuleLogger>());
		scoreDirector.calculateScore();
		storage.ruleLog = (ArrayList<RuleLogger>) scoreDirector
				.getWorkingMemory().getGlobal("ruleLog");

		ExportData exporter = new ExportData(storage.scheduleList,
				storage.ruleLog);
		if (!logFile.equals("")) {
			logger.info("Export Log = " + exporter.exportLogText(logFile));
		}

		// 最終結果のスコアの表示
		logger.info(initialSolution.getScore().toString());
	}

	public static void main(String[] args) {
		if (args.length > 2) {
			checkData(args[0], args[1], args[2]);
		} else {
			checkData(args[0], args[1], "");
		}
	}

}
