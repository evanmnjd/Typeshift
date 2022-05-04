import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class Typeshift extends Application {
	protected Enemy enemy = new Enemy();
	protected Enemy[] enemies = new Enemy[7];
	protected static Enemy randEnemy = new Enemy();
	protected static MenuPane mp = new MenuPane();
	protected static PlayerPane pp = new PlayerPane();
	protected static GameplayPane gp = new GameplayPane();
	protected static LevelFinishPane lfp = new LevelFinishPane();
	protected static DeathPane dp = new DeathPane();
	protected static HighScorePane hsp = new HighScorePane();
	protected static GameWinPane gwp = new GameWinPane();
	private Connection connection;
	protected DatabaseConnection dc = new DatabaseConnection(connection);
	protected static Timer timer = new Timer();
	
	public static String response;
	public static String randWord;
	public static int correctCount = 0;
	public static int levelCount = 0;
	public static int incorrectCount = 0;
	public static int count = 0;
	public static int level = 1;
	public static int score;
	public static int totalScore;
	public static int prevScore;
	public static String pName;
	public static String testName;
	public static boolean test;
	public static boolean end = false;
	public static boolean dead = false;
	public static String[] playerNames = new String[10];
	public static String[] playerScore = new String[10];
	public static String[] strings = new String[6];
	public static Image[] images = new Image[6];
	public static BackgroundImage[] bgImages = new BackgroundImage[6];
	public static Background[] bg = new Background[6];

	Scene scenePP = new Scene(pp, 1280, 852);
	Scene sceneMP = new Scene(mp, 1280, 852);
	Scene sceneGP = new Scene(gp, 1280, 852);
	Scene sceneLFP = new Scene(lfp, 1280, 852);
	static Scene sceneDP = new Scene(dp, 1280, 852);
	Scene sceneHSP = new Scene(hsp, 1280, 852);
	Scene sceneGWP = new Scene(gwp, 1280, 852);
	
	boolean check() {
		count++;
		if (response.equals(randWord) == true) {
			correctCount++;
			levelCount++;
			UpdateLevelScore(level, correctCount);
			try {
				GetWord();
			} catch (SQLException e) {
				System.out.println("Failed GetWord()");
			}
			clear();
			GetShowRandomEnemy(randEnemy);
			return true;
		} else {
			incorrectCount++;
			return false;
		}
	}
	public void clear() {
		gp.tfAnswer.setText("");
	}
	
	public static void pause() {
		end = true;
	}
	
	public static void resume() {
		end = false;
		incorrectCount = 0;
		count = 0;
		dead = false;
	}
	
	public void GetShowRandomEnemy(Enemy randEnemy) {			
		randEnemy = enemy.getRandomEnemy(enemies);
		gp.viewEnemy.setImage(randEnemy.enemyImage);
	}
	
	public void GetWord() throws SQLException {
		gp.tWord.setText(dc.getWord());
	}
	
	public void UpdateName() {
		pName = pp.tfName.getText();
		mp.playerName.setText(pName);
	}
	
	public static void UpdateTimer() {
		gp.tTime.setText("Time: " + GameTimer.countdown);
		}
	
	public void AddPlayer(String userName) throws SQLException {
		dc.addUser(userName);
		}
	
	public void AddWord() throws SQLException {
		String addWord = mp.tfAdd.getText();
		dc.addWord(addWord);
		mp.tfAdd.setText("");
	}
	
	public void CheckNameExists() throws SQLException {
		dc.CheckExists(pName);
		test = testName.equals(pName);
	}
	
	public void PopulateEnemies() {
		enemies = enemy.PopulateEnemies();
	}
	
	public void PopulateHighScores() throws SQLException {
		playerNames = dc.getNames();
		playerScore = dc.getScores();
		for (int i = 1; i <= 9; i++) {
			hsp.tName[i].setText(playerNames[i]);
			hsp.tScore[i].setText(playerScore[i]);
		}
	}
	
	public void CompareUpdateScore(int newScore, String name) throws SQLException {
		int oldScore = dc.GetScore(name);
		if (newScore > oldScore) {
			dc.UpdateHighScore(newScore, name);
		}
	}
	
	public void UpdateScore(int correct, int time) {
		totalScore = totalScore + levelCount + time;
		System.out.println(totalScore + " " + levelCount + " " + time);
		GameWinPane.score = totalScore;
	}
	
	public void UpdateLevelScore(int level, int correct) {
		gp.tLevel.setText("Level: " + level);
		gp.tCorrect.setText("Correct: " + correct);
		gwp.tScore.setText("Final Score: " + totalScore);
		dp.tScore.setText("Final Score: " + totalScore);
		dp.tLevel.setText("Level: " + level);
		lfp.tScore.setText("Score: " + totalScore);
	}
	
	public void PopulateBackgrounds() {
		for (int i = 1; i <= 5; i++) {
			strings[i] = "/room" + i + ".png";
			bg[i] = new Background(new BackgroundImage(new Image(strings[i]), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
		}
	}
	
	public void UpdateBackground() {
		gp.setBackground(bg[level]);
		lfp.setBackground(bg[level - 1]);
	}
	
	@Override
	public void start(Stage primaryStage) throws SQLException {
		
		dc.createConnection();
		
		mp.btPlay.setOnAction((ActionEvent event) -> {
			levelCount = 0;
			score = 0;
			level = 1;
			GameTimer.countdown = 20;
			gp.tTime.setText("Time: " + GameTimer.countdown);
			resume();
			timer.schedule(GameTimer.CreateTimerTask(), 1000, 1000);
			PopulateBackgrounds();
			UpdateBackground();
			UpdateLevelScore(level, correctCount);
			incorrectCount = 0;
			try {
				PopulateHighScores();
				PopulateEnemies();
				GetShowRandomEnemy(randEnemy);	
				GetWord();
			} catch (SQLException e) {
				System.out.println("Failed PopulateHighScores() / PopulateEnemies() / GetShowRandomEnemy(randEnemyt) / GetWord()");
			}
			primaryStage.setScene(sceneGP);
		});
		
		mp.btAdd.setOnAction((ActionEvent event) -> {
			try {
				AddWord();
			} catch (SQLException e) {
				System.out.println("Failed AddWord()");
			}
		});
		mp.tfAdd.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				try {
					AddWord();
				} catch (SQLException e) {
					System.out.println("Failed AddWord()");
				}
			}
		});
		
		gp.btSubmit.setOnAction((ActionEvent event) -> {
			response = gp.tfAnswer.getText();
			check();
			if (((incorrectCount % 5 == 0) && (incorrectCount != 0)) || dead == true) {
				UpdateScore(levelCount, 0);
				pause();
				UpdateLevelScore(level, correctCount);
				try {
					CompareUpdateScore(score, pName);
				} catch (SQLException e) {
					System.out.println("Failed CompareUpdateScore(correctCount, pName)");
				}
				primaryStage.setScene(sceneDP);			
				}
			if (count % 10 == 0) {
				UpdateScore(levelCount, GameTimer.countdown);
				pause();
				level++;
				UpdateLevelScore(level, correctCount);
				try {
					CompareUpdateScore(correctCount, pName);
				} catch (SQLException e) {
					System.out.println("Failed CompareUpdateScore(correctCount, pName)");
				}
				UpdateBackground();
				if (level >= 6) {
					primaryStage.setScene(sceneGWP);
				} else {
					primaryStage.setScene(sceneLFP);
				}
			}
		});
		
		gp.tfAnswer.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				response = gp.tfAnswer.getText();
				check();
				System.out.println(incorrectCount);
				if (((incorrectCount % 5 == 0) && (incorrectCount != 0)) || dead == true) {
					UpdateScore(levelCount, 0);
					pause();
					UpdateLevelScore(level, correctCount);
					try {
						CompareUpdateScore(correctCount, pName);
					} catch (SQLException e) {
						System.out.println("Failed CompareUpdateScore(correctCount, pName)");
					}
					primaryStage.setScene(sceneDP);				}
				if (count % 10 == 0) {
					UpdateScore(levelCount, GameTimer.countdown);
					pause();
					level++;
					UpdateLevelScore(level, correctCount);
					try {
						CompareUpdateScore(correctCount, pName);
					} catch (SQLException e) {
						System.out.println("Failed CompareUpdateScore(correctCount, pName)");
					}
					if (level >= 6) {
						primaryStage.setScene(sceneGWP);
					} else {
						UpdateBackground();
						primaryStage.setScene(sceneLFP);
					}
				}
			}
		});
		
		pp.btSubmitName.setOnAction((ActionEvent event) -> {
			UpdateName();
			try {
				CheckNameExists();
				if (test == false) {
					AddPlayer(pName);
				}
			} catch (SQLException e1) {
				System.out.println("Failed CheckNameExists()");
			}
			primaryStage.setScene(sceneMP);
		});
		
		pp.tfName.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				UpdateName();
				try {
					CheckNameExists();
					if (test == false) {
						AddPlayer(pName);
					}
				} catch (SQLException e1) {
					System.out.println("Failed CheckNameExists()");
				}
				primaryStage.setScene(sceneMP);
			}
		});
		
		lfp.btMenu.setOnAction((ActionEvent event) -> {
			primaryStage.setScene(sceneMP);
		});
		
		hsp.btMenu.setOnAction((ActionEvent event) -> {
			primaryStage.setScene(sceneMP);
		});
		
		gwp.btNew.setOnAction((ActionEvent event) -> {
			primaryStage.setScene(sceneMP);
		});
		
		lfp.btContinue.setOnAction((ActionEvent event) -> {
			incorrectCount = 0;
			levelCount = 0;
			GameTimer.countdown = 20;
			primaryStage.setScene(sceneGP);
			resume();
			timer.schedule(GameTimer.CreateTimerTask(), 1000, 1000);
		});
		
		dp.btReplay.setOnAction((ActionEvent event) -> {
			incorrectCount = 0;
			levelCount = 0;
			correctCount = 0;
			primaryStage.setScene(sceneMP);
			resume();
		});
		
		dp.btHighScores.setOnAction((ActionEvent event) -> {
			try {
				PopulateHighScores();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			primaryStage.setScene(sceneHSP);
		});
		
		mp.btHighScores.setOnAction((ActionEvent event) -> {
			try {
				PopulateHighScores();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			primaryStage.setScene(sceneHSP);
		});
		gwp.btHighScores.setOnAction((ActionEvent event) -> {
			try {
				PopulateHighScores();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			primaryStage.setScene(sceneHSP);
		});
		
		dp.btQuit.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
		
		mp.btQuit.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
		
		lfp.btQuit.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
		
		gwp.btQuit.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
		
		primaryStage.setTitle("Typeshift");
		primaryStage.setScene(scenePP);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}