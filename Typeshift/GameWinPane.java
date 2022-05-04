import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameWinPane extends BorderPane {
	public static int score;
	
	protected BackgroundImage bgImage = new BackgroundImage(new Image("/background.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	protected Background bg = new Background(bgImage);
	
	protected Text tWin = new Text("CONGRATULATIONS - YOU ESCAPED!");
	protected Text tScore = new Text("Final Score: " + score);
	
	protected Button btNew = new Button("New Game");
	protected Button btHighScores = new Button("High Scores");
	protected Button btQuit = new Button("Quit");
	
	private VBox vbWin = new VBox(50);
	private VBox vbButtons = new VBox(50);
	
	public GameWinPane() {
		DrawGWP();
	}
	
	public void DrawGWP() {
		tWin.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tWin.setFill(Color.PURPLE);
		tScore.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
		tScore.setFill(Color.YELLOW);
		
		vbWin.getChildren().addAll(tWin, tScore);
		vbButtons.getChildren().addAll(btNew, btHighScores, btQuit);
		
		setTop(vbWin);
		setBottom(vbButtons);
		setBackground(bg);
		
		vbWin.setAlignment(Pos.TOP_CENTER);
		vbButtons.setAlignment(Pos.CENTER);
		
		setMargin(vbWin, new Insets(30));
		setMargin(vbButtons, new Insets(30));
		
	}
	
	public static void main(String[] args) {
		
	}
}