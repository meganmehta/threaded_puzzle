import javafx.animation.FadeTransition;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;


public class JavaFXTemplate extends Application {
	Stage primaryStage;
	ArrayList<String> createdPuzzles;
	int gameBoard[][] = new int[4][4];
	//int blackRow, blackCol;
	GamePiece blackPiece;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//convert string of puzzles to array list
	public ArrayList<Integer> stringToIntArrrayList(String puzzle){
		ArrayList<String> list = new ArrayList<>(Arrays.asList(puzzle.split(" ")));
		ArrayList<Integer> returnedList = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++){
			returnedList.add(Integer.valueOf(list.get(i)));
		}
		return returnedList;
	}

	public boolean validSwitch(GamePiece selected){
		if (selected.getRowNum() == blackPiece.getRowNum() || selected.getColumnNum() == blackPiece.getColumnNum()){
			if (selected.getRowNum() == blackPiece.getRowNum() && (selected.getColumnNum() == blackPiece.getColumnNum() - 1 || selected.getColumnNum() == blackPiece.getColumnNum() + 1)){
				return true;
			}
			if (selected.getColumnNum() == blackPiece.getColumnNum() && (selected.getRowNum() == blackPiece.getRowNum() - 1 || selected.getRowNum() == blackPiece.getRowNum() + 1)){
				return true;
			}
			return false;
		}
		else{
			return false;
		}

	}

	//contains all UI components
	@Override
	public void start(Stage primaryStage) throws Exception {
		//puzzles listed here - 10 unique puzzles to solve
		//all of these puzzles can be solved by the AI puzzle solver algorithm
		createdPuzzles = new ArrayList<String>();
		createdPuzzles.add("14 12 8 15 1 0 3 11 5 2 13 7 4 10 6 9");
		createdPuzzles.add("15 0 6 10 8 7 11 5 13 3 1 9 2 4 12 14");
		createdPuzzles.add("9 1 6 5 7 11 12 13 2 3 15 8 4 0 14 10");
		createdPuzzles.add("3 2 4 7 8 0 11 1 14 9 12 5 15 6 10 13");
		createdPuzzles.add("3 13 10 1 14 9 6 7 15 11 2 4 12 0 8 5");
		createdPuzzles.add("11 9 6 12 14 10 13 8 5 1 3 4 15 7 0 2");
		createdPuzzles.add("1 5 11 7 10 12 15 14 2 0 4 13 8 9 6 3");
		createdPuzzles.add("7 9 13 11 6 14 4 2 12 5 10 8 1 15 3 0");
		createdPuzzles.add("12 1 0 13 14 7 4 8 10 15 2 3 5 11 9 6");
		createdPuzzles.add("4 15 6 0 10 14 7 13 12 2 5 3 1 11 8 9");

		primaryStage.setTitle("Threaded Puzzle!");

		//create welcome transition
		Text intro = new Text("Welcome to the Puzzle Game!");
		intro.setX(100);
		intro.setY(250);
		intro.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

		/*FadeTransition welcome = new FadeTransition();
		welcome.setDuration(Duration.millis(5000));
		welcome.setFromValue(10);
		welcome.setToValue(0);
		welcome.setNode(intro);
		welcome.play();*/

		//Each node contains an Array that represents the whole board after one move.
		//Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
		//t.start();

		HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();
		Button startBtn = new Button("Start Game!");
		startBtn.setStyle("-fx-background-color: yellow; ");

		BorderPane welcomePane = new BorderPane();
		welcomePane.setPadding(new Insets(70));
		welcomePane.setCenter(intro);
		welcomePane.setBottom(startBtn);

		//use pause transition

		//add all scenes/screens in hashmap
		sceneMap.put("gameScreen", puzzleGameScene());

		//when startBtn is clicked, switch to gameScreen
		startBtn.setOnAction(e -> primaryStage.setScene(sceneMap.get("gameScreen")));

		Scene introScene = new Scene(welcomePane, 850, 750);

		//add any styling elements in this function
		introScene.getRoot().setStyle("-fx-background-color: blue;-fx-font-family: 'verdana';");

		primaryStage.setScene(introScene);
		primaryStage.show();
		this.primaryStage = primaryStage;
	}

	public Scene puzzleGameScene(){

		//choose a random puzzle from the createdPuzzles list
		int rnd = new Random().nextInt(createdPuzzles.size());
		ArrayList<Integer> chosenPuzzle = stringToIntArrrayList(createdPuzzles.get(rnd));
		//System.out.println(chosenPuzzle);

		//creates new gameBoard everytime a newGame button is pressed
		GridPane gameBoard = new GridPane();
		int count = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				GamePiece gp = new GamePiece();
				gp.setRowNum(i);
				gp.setColumnNum(j);
				gp.setButtonColor("yellow"); //default color
				gp.setText(Integer.toString(chosenPuzzle.get(count))); //assigns text based on puzzle generated
				this.gameBoard[i][j] = chosenPuzzle.get(count);
				System.out.println(this.gameBoard[i][j]);
				count++;
				gp.setStyle("-fx-background-color: yellow;-fx-font-family: 'verdana'; -fx-border-color: black;");
				gp.setMinWidth(50);
				gp.setMinHeight(50);
				gameBoard.add(gp, j, i);

				//create "empty space" to switch buttons
				if (gp.getText().equals("0")){
					gp.setText("0");
					gp.setStyle("-fx-background-color: black;");
					gp.setButtonColor("black");
					//blackRow = gp.getRowNum();
					//blackCol = gp.getColumnNum();
					blackPiece = gp;
				}

				//onClick actions
				gp.setOnAction(e -> {
					//check if black square is left, right, up, or down
					int tempRow = gp.getRowNum();
					int tempCol = gp.getColumnNum();
					String tempText = gp.getText();
					if (validSwitch(gp) == true){
						blackPiece.setText(tempText);
						blackPiece.setButtonColor("yellow");
						blackPiece.setStyle("-fx-background-color: yellow;-fx-font-family: 'verdana'; -fx-border-color: black;");
						gp.setButtonColor("black");
						gp.setText("0");
						gp.setStyle("-fx-background-color: black;");
						blackPiece = gp;

					}
				});
			}
		}
		//System.out.println(this.gameBoard);
		//creates menu with additional options:
		//allow for a new puzzle the displayed,
		// solve with AI H1,
		// with AI H2,
		// exit the program,
		// and see the solution displayed.

		Menu mainMenu = new Menu("Game Options");
		MenuItem newPuzzle = new MenuItem("New Puzzle");
		MenuItem a1h1 = new MenuItem("Solve with A1 H1");
		MenuItem a1h2 = new MenuItem("Solve with A1 H2");
		MenuItem exitGame = new MenuItem("Exit Game");

		mainMenu.getItems().add(newPuzzle);
		mainMenu.getItems().add(a1h1);
		mainMenu.getItems().add(a1h2);
		mainMenu.getItems().add(exitGame);

		//resets gameBoard with new game
		newPuzzle.setOnAction(event->{
			this.primaryStage.setScene(puzzleGameScene());
			this.primaryStage.show();
		});

		exitGame.setOnAction(event -> {
			Platform.exit();
		});

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(mainMenu);

		Button seeSolution = new Button("See Solution Displayed");

		BorderPane root = new BorderPane();
		gameBoard.setAlignment(Pos.CENTER);
		root.setCenter(gameBoard);
		root.setBottom(seeSolution);
		seeSolution.setAlignment(Pos.CENTER);
		root.setTop(menuBar);

		//used for welcome transition!!!
		//Group primary = new Group();
		//primary.getChildren().addAll(intro);

		Scene gameScene = new Scene(root, 850, 750);
		gameScene.getRoot().setStyle("-fx-background-color: blue;-fx-font-family: 'verdana';");

		return gameScene;

	}



}
