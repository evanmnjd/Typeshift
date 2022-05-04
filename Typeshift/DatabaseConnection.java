import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.stage.Stage;

public class DatabaseConnection extends Application {
	
	protected GameplayPane gp = new GameplayPane();
	protected HighScorePane hsp = new HighScorePane();
	protected Connection connection;
	protected String sqlGetWord = "SELECT words FROM Words_List ORDER BY RAND() LIMIT 1;";
	protected static String[] playerName = new String[10];
	protected static String[] scores = new String[10];
		
	@Override
	public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
		createConnection();	
	}
	
	public DatabaseConnection(Connection connection) {
		super();
		this.connection = connection;
	}

	Connection createConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Typeshift_DB", "root", "");
		}
		catch (SQLException ex) {
			System.out.println("Database Error");
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Driver Error");
		}
		return connection;
	}
	
	public String getWord() throws SQLException {
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlGetWord);
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		Integer resultColumnCount = rsmd.getColumnCount();
		
		String resultsString = "";
		
		while (resultSet.next()) {
			for(int i = 1; i <= resultColumnCount; i++) {
				resultsString = resultSet.getString(i);
			}
			
		}
		
		String randWord = resultsString.trim();
		Typeshift.randWord = randWord;
		System.out.println("The new word is: " + randWord);
		return randWord;
	}
	
	void addWord(String addWord) throws SQLException {
		String sqlAddWord = "INSERT INTO Words_List VALUES ('" + addWord + "');";
		Statement statement = connection.createStatement();
		
		statement.executeUpdate(sqlAddWord);
	}
	
	void addUser(String addUser) throws SQLException {
		String sqlAddUser = "INSERT INTO High_Scores VALUES ('" + addUser + "', '0');";
		Statement statement = connection.createStatement();
		
		statement.executeUpdate(sqlAddUser);
		
		System.out.println(addUser + " added");
	}
	
	public int GetScore(String name) throws SQLException {
		String sqlGetScore = "SELECT score FROM High_Scores WHERE player_name = '" + name + "';";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlGetScore);
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		Integer resultColumnCount = rsmd.getColumnCount();
		
		int resultsInt = 0;
		
		while (resultSet.next()) {
			for(int i = 1; i <= resultColumnCount; i++) {
				resultsInt = resultSet.getInt(i);
			}
			
		}
		
		int score = resultsInt;
		Typeshift.prevScore = score;
		System.out.println("The previous high score is: " + resultsInt);
		return score;
	}
	
	public int GetCount() throws SQLException {
		String sqlGetCount = "SELECT COUNT(player_name) FROM High_Scores;";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlGetCount);
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		Integer resultColumnCount = rsmd.getColumnCount();
		
		int rowCount = 0;
		
		while (resultSet.next()) {
			for(int i = 1; i <= resultColumnCount; i++) {
				rowCount = resultSet.getInt(i);
			}
			
		}
		return rowCount;
	}
	
	void UpdateHighScore(int score, String name) throws SQLException {
		String sqlUpdateScore = "UPDATE High_Scores SET score = " + score + " WHERE player_name = '" + name + "';";
		Statement statement = connection.createStatement();
		
		statement.executeUpdate(sqlUpdateScore);
	}
	
	public void CheckExists(String user) throws SQLException {
		String sqlCheckExists = "SELECT player_name FROM High_Scores WHERE player_name = '" + user + "';";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlCheckExists);
		
		ResultSetMetaData rsmd = resultSet.getMetaData();
		Integer resultColumnCount = rsmd.getColumnCount();
		
		String resultsString = "";
		
		while (resultSet.next()) {
			for(int i = 1; i <= resultColumnCount; i++) {
				resultsString = resultSet.getString(i);
			}
			
		}
		
		String matchingUser = resultsString.trim();
		Typeshift.testName = matchingUser;
		System.out.println(matchingUser);
		}
	
	public String[] getNames() throws SQLException {
		String[] playerName = new String[10];
		int count = GetCount();
		
		String sqlGetNames = "SELECT player_name FROM High_Scores ORDER BY score DESC LIMIT 10;";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSetNames = statement.executeQuery(sqlGetNames);
		
		while (resultSetNames.next()) {
			for(int i = 1; i <= count; i++) {
				playerName[i] = resultSetNames.getString("player_name");
				playerName[i].trim();
				resultSetNames.next();
			}
		}
		System.out.println(playerName[1] + " " + playerName[2] + " " + playerName[3] + " " + playerName[4]);
		return playerName;
	}
		
	public String[] getScores() throws SQLException {
		int[] score = new int[10];
		String[] scores = new String[10];
		
		int count = GetCount();
		
		String sqlGetScores = "SELECT score FROM High_Scores ORDER BY score DESC LIMIT 10;";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSetScores = statement.executeQuery(sqlGetScores);
				
		while (resultSetScores.next()) {
			for (int i = 1; i <= count; i++) {
				score[i] = resultSetScores.getInt("score");
				scores[i] = Integer.toString(score[i]);
				scores[i].trim();
				resultSetScores.next();
			}
		}
		
		System.out.println(scores[1] + " " + scores[2] + " " + scores[3] + " " + scores[4]);
		return scores;
	}
	
	public static void main(String[] args) {
		
	}
}
