package main.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.data.DataStorage;
import main.data.ExportData;
import main.data.ImportData;
import main.domain.Classroom;
import main.domain.Course;
import main.domain.PlannerSolution;
import main.domain.Schedule;

import org.drools.ClassObjectFilter;
import org.drools.WorkingMemory;
import org.drools.planner.config.XmlSolverFactory;
import org.drools.planner.core.Solver;
import org.drools.planner.core.score.constraint.ConstraintOccurrence;
import org.drools.planner.core.score.constraint.ConstraintType;
import org.drools.planner.core.score.director.ScoreDirector;
import org.drools.planner.core.score.director.drools.DroolsScoreDirector;

public class FLMPlannerCheckOutput {

	// ソルバーの設置
	public static final String SOLVER_CONFIG = "/FLMPlannerRuleCheck.xml";

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

	public static void checkData(String inFile, String outFile) {

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
		//solver.setPlanningProblem(initialSolution);
		
		 ScoreDirector scoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();
		 scoreDirector.setWorkingSolution(initialSolution);
		 
	     scoreDirector.calculateScore();
	     
	     ExportData exporter = new ExportData(storage.scheduleList, scoreDirector);
	     
	     exporter.exportToXLS("OutputTest.xls");
	     
	     /*for (ScoreDetail score : getScoreDetailList(scoreDirector)) {
	    	 if (score.getConstraintType() == ConstraintType.NEGATIVE_HARD) {
	    		 System.out.println(score.getRuleId());
	    		 for (ConstraintOccurrence con : score.getConstraintOccurrenceSet()) {
	    			 con.toString();
	    		 }
	    	 }
	     }*/
	     //System.out.println("total rule size = " + getScoreDetailList(scoreDirector).size());
		
		// 開催日程計画開始
		//solver.solve();

		// 開催日程計画結果の取得
		//PlannerSolution solvedSolution = (PlannerSolution) solver
		//		.getBestSolution();

		// 最終結果のスコアの表示
		//System.out.println(solvedSolution.getScore());
	     System.out.println(initialSolution.getScore());
	}

	
	
	public static void main(String[] args) {
		checkData(args[0], args[1]);
	}

}
