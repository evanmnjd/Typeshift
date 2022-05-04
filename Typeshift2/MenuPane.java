import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import javafx.scene.text.Text;

public class MenuPane extends BorderPane {
	
	protected BackgroundImage bgImage = new BackgroundImage(new Image("/background.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	protected Background bg = new Background(bgImage);
	
	protected Text gameTitle = new Text("TYPESHIFT");
	protected Text playerName = new Text(Typeshift.pName);
	
	protected TextField tfAdd = new TextField("");
	
	protected Button btPlay = new Button("Play");
	protected Button btHighScores = new Button("High Scores");
	protected Button btAdd = new Button("Add Words");
	protected Button btQuit = new Button("Quit");
	
	private VBox vbTitle = new VBox(50);
	private VBox vbMenuButtons = new VBox(50);
	
	protected ToggleGroup tgDifficulty = new ToggleGroup();
	
	public MenuPane() {
		DrawMP();
	}
	
	private void DrawMP() {
		gameTitle.setFont(Font.font("VERDANA", 40));
		gameTitle.setFill(Color.RED);
		playerName.setFont(Font.font("VERDANA", 40));
		playerName.setFill(Color.WHITE);
		
		tfAdd.setPrefWidth(100);
		tfAdd.setMaxWidth(100);
		
		vbMenuButtons.setAlignment(Pos.BOTTOM_CENTER);
		vbTitle.setAlignment(Pos.TOP_CENTER);
		
		setMargin(vbMenuButtons, new Insets(30));
		setMargin(vbTitle, new Insets(30));
		
		setTop(vbTitle);
		setBottom(vbMenuButtons);
		setBackground(bg);
		
		vbTitle.getChildren().addAll(gameTitle, playerName);
		vbMenuButtons.getChildren().addAll(btPlay, btHighScores, btAdd, tfAdd, btQuit);
	}
	
	public static void main(String[] args) {
		
	}
}