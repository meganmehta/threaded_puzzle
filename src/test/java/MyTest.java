import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class MyTest {

	private DB_Solver2 DBSolver;
	private Node primaryNode;


	//You are required to include at least 10 JUnit 5 test cases for your program. You should
	//test the AI code given to you

	@Test
	void test1() {
		int puzzle[] = new int[]{2,6,10,3,1,4,7,11,8,5,9,15,12,13,14,0};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertNotNull(DBSolver.findSolutionPath(), "solution path not working");
	}

	@Test
	void test2() {
		int puzzle[] = new int[]{11,1,13,15,7,2,8,0,14,5,12,6,10,9,4,3};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertEquals(false, DBSolver.goalTest(puzzle), "goalTest not working");
	}

	@Test
	void test3() {
		int puzzle[] = new int[]{15,0,6,10,8,7,11,5,13,3,1,9,2,4,12,14};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicOne");
		assertNotEquals(0, DBSolver.getH1(puzzle), "getH1 not working");
	}

	@Test
	void test4() {
		int puzzle[] = new int[]{9,1,6,5,7,11,12,13,2,3,15,8,4,0,14,10};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertNotEquals(0, DBSolver.getH2(puzzle), "getH2 not working");
	}

	@Test
	void test5() {
		int puzzle[] = new int[]{3,2,4,7,8,0,11,1,14,9,12,5,15,6,10,13};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		DBSolver.moveZero(puzzle, 5, 2);
		assertEquals(0, puzzle[2], "moveZero not working");
	}

	@Test
	void test6() {
		int puzzle[] = new int[]{11,9,6,12,14,10,13,8,5,1,3,4,15,7,0,2};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertEquals(14,DBSolver.findZero(puzzle), "findZero not working");
	}

	@Test
	void test7() {
		int puzzle[] = new int[]{4,15,6,0,10,14,7,13,12,2,5,3,1,11,8,9};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertEquals(false,DBSolver.moveRight(puzzle), "moveRight not working");
	}

	@Test
	void test8() {
		int puzzle[] = new int[]{4,15,6,0,10,14,7,13,12,2,5,3,1,11,8,9};
		int puzzle2[] = new int[]{4,15,6,0,10,14,7,13,12,2,5,3,1,11,8,9};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		//won't be equal since node signatures are different
		assertNotEquals(puzzle2,DBSolver.copyArray(puzzle), "copyArray not working");
	}

	@Test
	void test9() {
		int puzzle[] = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertEquals(true, DBSolver.goalTest(puzzle), "goalTest not working");
	}

	@Test
	void test10() {
		int puzzle[] = new int[]{4,15,6,0,10,14,7,13,12,2,5,3,1,11,8,9};
		primaryNode = new Node(puzzle);
		DBSolver = new DB_Solver2(primaryNode, "heuristicTwo");
		assertEquals(false,DBSolver.moveLeft(puzzle), "moveLEFT not working");
	}
}
