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

	// �G���W���p���K�[�̍쐬
	static final Logger logger = LoggerFactory
			.getLogger(FLMPlannerCheckOutput.class);

	// �\���o�[�̐ݒu
	public static final String SOLVER_CONFIG = "/FLMPlannerRuleCheck.xml";

	// �f�[�^�̃`�F�b�N�̊J�n
	public static void checkData(String inFile, String outFile, String logFile) {

		// �f�[�^�̃C���|�[�g
		ImportData importer = new ImportData();
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

		// �X�R�A�f�B���N�^�[�̍쐬
		DroolsScoreDirector scoreDirector = (DroolsScoreDirector) solver
				.getScoreDirectorFactory().buildScoreDirector();
		scoreDirector.setWorkingSolution(initialSolution);

		// ���[�����K�[�̍쐬�E��������
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

		// �ŏI���ʂ̃X�R�A�̕\��
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
