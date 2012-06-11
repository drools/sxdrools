package main.app;

import org.drools.planner.config.XmlSolverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import org.drools.planner.core.Solver;
import org.drools.planner.core.score.director.ScoreDirector;


import main.domain.*;
import main.data.*;

public class FLMPlannerHelloWorld {

	public static final String SOLVER_CONFIG = "/FLMPlannerSolverConfig.xml";
	
	public static void testMethod() {
		Course c1 = new Course(1,13,1,true);
		ArrayList<String> sPC = new ArrayList<String>();
		sPC.add("PRIMERGY");
		sPC.add("D750/A");
		c1.setSupportedPCList(sPC);
		Classroom r1 = new Classroom(3,15,true) ;
		r1.setPcType("D750/A");
		Schedule s1 = new Schedule(c1,r1,null);
		
		System.out.println(s1.checkPCRequirement());
	}
	
	public static void runData(String inFile, String outFile) {
		
		//testMethod();
		
		
		
		ImportData importer = new ImportData();
		//importer.initialtest();
		importer.importFromXLS(inFile);
		DataStorage storage = importer.getStorage();
		
		long startTimeCounter = System.currentTimeMillis();
		
		XmlSolverFactory solverFactory = new XmlSolverFactory();
        solverFactory.configure(SOLVER_CONFIG);
        Solver solver = solverFactory.buildSolver();
        
        PlannerSolution initialSolution = new PlannerSolution(storage.scheduleList,storage.classroomList,storage.dayList);
        solver.setPlanningProblem(initialSolution);
        solver.solve();
        
        ScoreDirector scoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();

        long elapsedTimeMillis = System.currentTimeMillis()-startTimeCounter;
        
        PlannerSolution solvedSolution = (PlannerSolution) solver.getBestSolution();
        
        System.out.println(solvedSolution.getScore());
        System.out.println("Elapsed time: " + (int) (elapsedTimeMillis/(60*1000F)) + "min "+ elapsedTimeMillis/1000F + "sec");
        
        scoreDirector.setWorkingSolution(solvedSolution);
        scoreDirector.calculateScore();
        
        List<Schedule> listSch = solvedSolution.getScheduleList();
        //listSch.get(0).setDay(storage.dayList.get(0));
        //listSch.get(0).setClassroom(storage.classroomList.get(0));
        listSch.get(6).setDay(storage.dayList.get(2));
        listSch.get(6).setClassroom(storage.classroomList.get(1));
        //solvedSolution.setScheduleList(listSch);
        solver.setPlanningProblem(solvedSolution);
        solvedSolution.setScheduleList(initialSolution.getScheduleList());
        System.out.println(solvedSolution.getScore());
        
        scoreDirector.setWorkingSolution(solvedSolution);
        scoreDirector.calculateScore();
        
		
		
        ExportData exporter = new ExportData(solvedSolution.getScheduleList());
        
        //exporter.showInitialTestResult();
        
        //System.out.println("Export to XLS: " + exporter.exportToXLS(outFile));
        System.out.println("Export to XLS: " + exporter.exportToXLS_debug(outFile));
        
        
	}

	
	
	public static void main(String[] args)
	{
		runData(args[0],args[1]);
	}

}
