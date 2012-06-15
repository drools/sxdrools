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

public class FLMPlannerCheckOutput {

	// �\���o�[�̐ݒu
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

		// �f�[�^�̃C���|�[�g
		ImportData importer = new ImportData();
		// importer.initialtest();
		importer.importFromXLS(inFile);
		importer.importFromOutputXLS(outFile);
		DataStorage storage = importer.getStorage();

		// �\���o�[�̏�����
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(SOLVER_CONFIG);
		Solver solver = solverFactory.buildSolver();

		// ���������l�̐ݒ�
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);
		//solver.setPlanningProblem(initialSolution);

		 ScoreDirector scoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();
		 scoreDirector.setWorkingSolution(initialSolution);
	     scoreDirector.calculateScore();
		
		// �J�Ó����v��J�n
		//solver.solve();

		// �J�Ó����v�挋�ʂ̎擾
		//PlannerSolution solvedSolution = (PlannerSolution) solver
		//		.getBestSolution();

		// �ŏI���ʂ̃X�R�A�̕\��
		//System.out.println(solvedSolution.getScore());
	     System.out.println(initialSolution.getScore());
	}

	public static void main(String[] args) {
		checkData(args[0], args[1]);
	}

}