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
import org.drools.planner.core.solver.DefaultSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FLMPlannerHelloWorld {

	// エンジン用ロガーの作成
	static final Logger logger = LoggerFactory
			.getLogger(FLMPlannerHelloWorld.class);

	// データ倉庫の作成
	public static DataStorage storage;

	// ソルバーの作成
	public static final String SOLVER_CONFIG = "/FLMPlannerSolverConfig.xml";
	public static final String TEST_CONFIG = "/FLMPlannerRuleCheck.xml";

	// 計算の実行
	public static void runData(String inFile) {

		// データのインポート
		ImportData importer = new ImportData();
		importer.importFromXLS(inFile);
		storage = importer.getStorage();

		// ソルバーの初期化
		long startTimeCounter = System.currentTimeMillis();
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(SOLVER_CONFIG);
		DefaultSolver solver = (DefaultSolver) solverFactory.buildSolver();

		// 初期解決値の設定
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);
		solver.setPlanningProblem(initialSolution);

		// 開催日程計画開始
		solver.solve();

		// 開催日程計画結果の取得
		PlannerSolution solvedSolution = (PlannerSolution) solver
				.getBestSolution();

		// 最終結果のスコアの表示
		logger.info(solvedSolution.getScore().toString());

		// 計算時間の表示
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeCounter;
		System.out.println("Elapsed time: "
				+ (int) (elapsedTimeMillis / (60 * 1000F)) + "min "
				+ elapsedTimeMillis / 1000F + "sec");

		// スケジュールリストへの格納
		storage.scheduleList = solvedSolution.getScheduleList();
	}

	// 計算結果のチェック
	public static void checkOutput(String exportMessage) {
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(TEST_CONFIG);
		Solver solver = solverFactory.buildSolver();

		// 初期解決値の設定
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);

		// スコアディレクターの作成
		DroolsScoreDirector scoreDirector = (DroolsScoreDirector) solver
				.getScoreDirectorFactory().buildScoreDirector();
		scoreDirector.setWorkingSolution(initialSolution);

		// ルールのロガーの作成・書き込み
		ArrayList<RuleLogger> ruleLogList = new ArrayList<RuleLogger>();
		ruleLogList.add(new RuleLogger(exportMessage, ""));
		scoreDirector.getWorkingMemory().setGlobal("ruleLog", ruleLogList);
		scoreDirector.calculateScore();
		storage.ruleLog = (ArrayList<RuleLogger>) scoreDirector.getWorkingMemory().getGlobal("ruleLog");

		// 最終結果のスコアの表示
		logger.info(initialSolution.getScore().toString());
	}

	// データのエクスポート
	public static void exportResult(String outFile, String logFile) {
		ExportData exporter = new ExportData(storage.scheduleList,
				storage.ruleLog);
		logger.info("Export to XLS: " + exporter.exportToXLS(outFile));
		if (!logFile.equals("")) {
			logger.info("Export Log = " + exporter.exportLogText(logFile));
		}
	}

	public static void main(String[] args) {
		runData(args[0]);
		checkOutput("Planning month: "
				+ args[0].replace(".xls", "").replace("DroolsMaster", ""));
		if (args.length == 2) {
			exportResult(args[1], "");
		} else {
			exportResult(args[1], args[2]);
		}
	}

}
