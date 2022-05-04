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

public class LevelFinishPane extends BorderPane {
	
	protected BackgroundImage bgImage = new BackgroundImage(new Image("/room1.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	protected Background bg = new Background(bgImage);
	
	protected Text tMessage = new Text("LEVEL COMPLETE");
	protected Text tScore = new Text(Integer.toString(Typeshift.score));
	
	protected Button btContinue = new Button("Continue");
	protected Button btQuit = new Button("Quit");
	protected Button btMenu = new Button("Main Menu");
	
	private VBox vbScore = new VBox(50);
	private VBox vbButtons = new VBox(50);
	
	public LevelFinishPane() {
		DrawLFP();
	}
	
	private void DrawLFP() {
		tMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tMessage.setFill(Color.BLUE);
		tScore.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
		tScore.setFill(Color.YELLOW);
		
		vbScore.getChildren().addAll(tMessage, tScore);
		vbButtons.getChildren().addAll(btContinue, btMenu, btQuit);
		
		setTop(vbScore);
		setBottom(vbButtons);
		setBackground(bg);
		
		vbScore.setAlignment(Pos.CENTER);
		vbButtons.setAlignment(Pos.CENTER);
		
		setMargin(vbScore, new Insets(30));
		setMargin(vbButtons, new Insets(30));
		
	}
	
	public static void main(String[] args) {
		
	}
}