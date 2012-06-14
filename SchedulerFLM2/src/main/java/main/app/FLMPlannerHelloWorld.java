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

public class FLMPlannerHelloWorld {

	public static final String SOLVER_CONFIG = "/FLMPlannerSolverConfig.xml";

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

		// ----- Import data ---------
		ImportData importer = new ImportData();
		// importer.initialtest();
		importer.importFromXLS(inFile);
		DataStorage storage = importer.getStorage();
		// ----- Finish import data ---------

		// ----- Initialize solver ---------
		long startTimeCounter = System.currentTimeMillis();

		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(SOLVER_CONFIG);
		Solver solver = solverFactory.buildSolver();
		// ----- Finish initializing solver ---------

		// ----- Set initial solution
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);
		solver.setPlanningProblem(initialSolution);
		// --------------------------

		// ----- Start solving
		solver.solve();
		// --------------------------

		// ----- Get solution
		PlannerSolution solvedSolution = (PlannerSolution) solver
				.getBestSolution();
		// --------------------------

		// ----- Get planning score -----
		System.out.println(solvedSolution.getScore());
		// --------------------------

		// ----- Get calculation time -----
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeCounter;
		System.out.println("Elapsed time: "
				+ (int) (elapsedTimeMillis / (60 * 1000F)) + "min "
				+ elapsedTimeMillis / 1000F + "sec");
		// --------------------------

		/*
		 * List<Schedule> listSch = solvedSolution.getScheduleList();
		 * //listSch.get(0).setDay(storage.dayList.get(0));
		 * //listSch.get(0).setClassroom(storage.classroomList.get(0));
		 * listSch.get(6).setDay(storage.dayList.get(2));
		 * listSch.get(6).setClassroom(storage.classroomList.get(1));
		 * solvedSolution.setScheduleList(listSch);
		 * 
		 * ScoreDirector scoreDirector =
		 * solver.getScoreDirectorFactory().buildScoreDirector();
		 * scoreDirector.setWorkingSolution(solvedSolution);
		 * scoreDirector.calculateScore();
		 * System.out.println(solvedSolution.getScore());
		 */

		// ----- Export result
		ExportData exporter = new ExportData(solvedSolution.getScheduleList());

		// exporter.showInitialTestResult();
		// System.out.println("Export to XLS: " +
		// exporter.exportToXLS(outFile));
		System.out.println("Export to XLS: "
				+ exporter.exportToXLS_debug(outFile));
		// --------------------------

	}

	public static void main(String[] args) {
		runData(args[0], args[1]);
	}

}
