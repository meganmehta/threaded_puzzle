import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.geometry.Pos;



public class JavaFXTemplate extends Application {
	Stage primaryStage;
	ArrayList<String> createdPuzzles;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Threaded Puzzle!");

		Scene scene = new Scene(new VBox(), 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();

		//Each node contains an Array that represents the whole board after one move.
		Thread t = new Thread(()-> {A_IDS_A_15solver ids = new A_IDS_A_15solver();});
		t.start();

		HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();

		primaryStage.setTitle("Baccarat Server");

		Text welcome = new Text("Welcome to the Puzzle Game!");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

		Button startBtn = new Button("Start Game!");
		startBtn.setStyle("-fx-background-color: yellow; ");

		BorderPane welcomePane = new BorderPane();
		welcomePane.setPadding(new Insets(70));
		welcomePane.setCenter(welcome);
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

		//create puzzles here!
		createdPuzzles = new ArrayList<String>();
		createdPuzzles.add("2 6 10 3 1 4 7 11 8 5 9 15 12 13 14 0");

		//creates game board - sets initial button values based on puzzle list
		GridPane gameBoard = new GridPane();
		int count = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				Button butt = new Button(Integer.toString(count));
				count++;
				butt.setStyle("-fx-background-color: yellow;-fx-font-family: 'verdana';");
				butt.setMinWidth(50);
				butt.setMinHeight(50);
				gameBoard.add(butt, j, i);
				if (butt.getText().equals("0")){
					butt.setText("");
				}
			}
		}

		//creates menu with additional options:
		//allow for a new puzzle tbe displayed,
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

		exitGame.setOnAction(event -> {
			Platform.exit();
		});

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(mainMenu);

		Button seeSolution = new Button("See Solution Displayed");

		BorderPane root = new BorderPane();
		gameBoard.setAlignment(Pos.CENTER);
		root.setCenter(gameBoard);
		seeSolution.setAlignment(Pos.CENTER);
		root.setBottom(seeSolution);

		Scene gameScene = new Scene(root, 850, 750);
		gameScene.getRoot().setStyle("-fx-background-color: blue;-fx-font-family: 'verdana';");

		return gameScene;
	}


}
