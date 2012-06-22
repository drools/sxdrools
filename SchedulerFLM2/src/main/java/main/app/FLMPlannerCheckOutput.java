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

public class FLMPlannerCheckOutput {

	// ソルバーの設置
	public static final String SOLVER_CONFIG = "/FLMPlannerRuleCheck.xml";

	public static void checkData(String inFile, String outFile, String logFile) {

		// データのインポート
		ImportData importer = new ImportData();
		// importer.initialtest();
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
		// solver.setPlanningProblem(initialSolution);

		DroolsScoreDirector scoreDirector = (DroolsScoreDirector) solver
				.getScoreDirectorFactory().buildScoreDirector();
		scoreDirector.setWorkingSolution(initialSolution);

		// Set Logger to System
		scoreDirector.getWorkingMemory().setGlobal("ruleLog",
				new ArrayList<RuleLogger>());

		scoreDirector.calculateScore();

		storage.ruleLog = (ArrayList<RuleLogger>) scoreDirector
				.getWorkingMemory().getGlobal("ruleLog");

		ExportData exporter = new ExportData(storage.scheduleList,
				storage.ruleLog);

		if (!logFile.equals("")) {
			System.out.println("Export Log = "
					+ exporter.exportLogText(logFile));
		}

		System.out.println(initialSolution.getScore());
	}

	public static void main(String[] args) {
		if (args.length>2) {
			checkData(args[0], args[1], args[2]);
		} else {
			checkData(args[0], args[1], "");
		}
	}

}
