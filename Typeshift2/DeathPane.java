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

public class DeathPane extends BorderPane {
	
	protected BackgroundImage bgImage = new BackgroundImage(new Image("/background.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	protected Background bg = new Background(bgImage);
	
	protected Text tDeath = new Text("YOU DIED");
	protected Text tLevel = new Text("Level: ");
	protected Text tScore = new Text("Final Score: ");
	
	private VBox vbDeath = new VBox();
	private VBox vbButtons = new VBox(10);
	
	protected Button btReplay = new Button("Replay");
	protected Button btHighScores = new Button("High Scores");
	protected Button btQuit = new Button("Quit");
	
	public DeathPane() {
		DrawDP();
	}
	
	private void DrawDP() {
		
		tLevel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tLevel.setFill(Color.GREEN);
		tScore.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tScore.setFill(Color.YELLOW);
		tDeath.setFont(Font.font("VERDANA", 60));
		tDeath.setFill(Color.RED);
		
		vbDeath.getChildren().addAll(tDeath, tLevel, tScore);
		vbButtons.getChildren().addAll(btReplay, btHighScores, btQuit);
		vbDeath.setAlignment(Pos.CENTER);
		vbButtons.setAlignment(Pos.CENTER);
		
		setMargin(vbDeath, new Insets(30));
		setMargin(vbButtons, new Insets(30));
		
		setBackground(bg);
		setTop(vbDeath);
		setBottom(vbButtons);
	}
	
	public static void main(String[] args) {
		
	}
}