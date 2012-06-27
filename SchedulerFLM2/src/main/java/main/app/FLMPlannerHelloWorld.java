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

	// �G���W���p���K�[�̍쐬
	static final Logger logger = LoggerFactory
			.getLogger(FLMPlannerHelloWorld.class);

	// �f�[�^�q�ɂ̍쐬
	public static DataStorage storage;

	// �\���o�[�̍쐬
	public static final String SOLVER_CONFIG = "/FLMPlannerSolverConfig.xml";
	public static final String TEST_CONFIG = "/FLMPlannerRuleCheck.xml";

	// �v�Z�̎��s
	public static void runData(String inFile) {

		// �f�[�^�̃C���|�[�g
		ImportData importer = new ImportData();
		importer.importFromXLS(inFile);
		storage = importer.getStorage();

		// �\���o�[�̏�����
		long startTimeCounter = System.currentTimeMillis();
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(SOLVER_CONFIG);
		DefaultSolver solver = (DefaultSolver) solverFactory.buildSolver();

		// ���������l�̐ݒ�
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);
		solver.setPlanningProblem(initialSolution);

		// �J�Ó����v��J�n
		solver.solve();

		// �J�Ó����v�挋�ʂ̎擾
		PlannerSolution solvedSolution = (PlannerSolution) solver
				.getBestSolution();

		// �ŏI���ʂ̃X�R�A�̕\��
		logger.info(solvedSolution.getScore().toString());

		// �v�Z���Ԃ̕\��
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeCounter;
		System.out.println("Elapsed time: "
				+ (int) (elapsedTimeMillis / (60 * 1000F)) + "min "
				+ elapsedTimeMillis / 1000F + "sec");

		// �X�P�W���[�����X�g�ւ̊i�[
		storage.scheduleList = solvedSolution.getScheduleList();
	}

	// �v�Z���ʂ̃`�F�b�N
	public static void checkOutput(String exportMessage) {
		XmlSolverFactory solverFactory = new XmlSolverFactory();
		solverFactory.configure(TEST_CONFIG);
		Solver solver = solverFactory.buildSolver();

		// ���������l�̐ݒ�
		PlannerSolution initialSolution = new PlannerSolution(
				storage.scheduleList, storage.classroomList, storage.dayList,
				storage.blockedClassroomList, storage.courseTotalSizeList);

		// �X�R�A�f�B���N�^�[�̍쐬
		DroolsScoreDirector scoreDirector = (DroolsScoreDirector) solver
				.getScoreDirectorFactory().buildScoreDirector();
		scoreDirector.setWorkingSolution(initialSolution);

		// ���[���̃��K�[�̍쐬�E��������
		ArrayList<RuleLogger> ruleLogList = new ArrayList<RuleLogger>();
		ruleLogList.add(new RuleLogger(exportMessage, ""));
		scoreDirector.getWorkingMemory().setGlobal("ruleLog", ruleLogList);
		scoreDirector.calculateScore();
		storage.ruleLog = (ArrayList<RuleLogger>) scoreDirector.getWorkingMemory().getGlobal("ruleLog");

		// �ŏI���ʂ̃X�R�A�̕\��
		logger.info(initialSolution.getScore().toString());
	}

	// �f�[�^�̃G�N�X�|�[�g
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
