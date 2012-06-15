package main.app;

import java.util.ArrayList;

import main.data.DataStorage;
import main.data.ExportData;
import main.data.ImportData;
import main.domain.Classroom;
import main.domain.Course;
import main.domain.PlannerSolution;
import main.domain.Schedule;

import org.drools.planner.config.XmlSolverFactory;
import org.drools.planner.core.Solver;
import org.drools.planner.core.score.director.ScoreDirector;

public class FLMPlannerHelloWorld {

	public static DataStorage storage;
	
	// ソルバーの設置
	public static final String SOLVER_CONFIG = "/FLMPlannerSolverConfig.xml";
	public static final String TEST_CONFIG = "/FLMPlannerRuleCheck.xml";

	public static void testMethod() {
		Course c1 = new Course(1, 13, 1, true);
		ArrayList<String> sPC = new ArrayList<String>();
		sPC.add("PRIMERGY");
		sPC.add("D750/A");
		c1.setSupportedPCList(sPC);
		Classroom r1 = new Classroom(3, 15, true);
		r1.setPcType("D750/A");
		Schedule s1 = new Schedule(c1, r1, null);

		System.out.println(s1.checkPCRequirement());
	}

	public static void runData(String inFile, String outFile) {

		// データのインポート
		ImportData importer = new ImportData();
		// importer.initialtest();
		importer.importFromXLS(inFile);
		storage = importer.getStorage();

		// ソルバーの初期化
		long startTimeCounter = System.currentTimeMillis();
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(SOLVER_CONFIG);
		Solver solver = solverFactory.buildSolver();

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
		System.out.println(solvedSolution.getScore());

		// 計算時間の表示
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeCounter;
		System.out.println("Elapsed time: "
				+ (int) (elapsedTimeMillis / (60 * 1000F)) + "min "
				+ elapsedTimeMillis / 1000F + "sec");

		// データのエクスポート
		ExportData exporter = new ExportData(solvedSolution.getScheduleList());

		// exporter.showInitialTestResult();
		// System.out.println("Export to XLS: " +
		// exporter.exportToXLS(outFile));
		System.out.println("Export to XLS: "
				+ exporter.exportToXLS_debug(outFile));
	}

	public static void checkOutput() {
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(TEST_CONFIG);
		Solver solver = solverFactory.buildSolver();

		// 初期解決値の設定
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);

		 ScoreDirector scoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();
		 scoreDirector.setWorkingSolution(initialSolution);
	     scoreDirector.calculateScore();
		
		// 最終結果のスコアの表示
	     System.out.println(initialSolution.getScore());
	}
	
	public static void main(String[] args) {
		runData(args[0], args[1]);
		checkOutput();
	}

}
