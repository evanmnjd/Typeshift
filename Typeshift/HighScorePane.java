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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HighScorePane extends BorderPane {
	
	protected BackgroundImage bgImage = new BackgroundImage(new Image("/background.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	protected Background bg = new Background(bgImage);
	
	protected Text tBanner = new Text("High Scores");
	protected Text tNameCol = new Text("Name");
	protected Text tScoreCol = new Text("Score");

	protected Text[] tName = new Text[10];
	protected Text[] tScore = new Text[10];
	
	protected Button btMenu = new Button("Main Menu");
	
	private GridPane gpScores = new GridPane();
	private VBox vbButtons = new VBox(50);
	private VBox vbBanner = new VBox(50);
	
	public HighScorePane() {
		DrawHSP();
	}
	
	private void DrawHSP() {
		tBanner.setFont(Font.font("VERDANA", FontWeight.BOLD, 50));
		tBanner.setFill(Color.YELLOW);
		tNameCol.setFont(Font.font("CAMBRIA", FontWeight.BOLD, 45));
		tNameCol.setFill(Color.WHITE);
		tScoreCol.setFont(Font.font("CAMBRIA", FontWeight.BOLD, 45));
		tScoreCol.setFill(Color.WHITE);
		
		for (int i = 1; i <= 9; i++) {
			tName[i] = new Text(Integer.toString(i));
			tScore[i] = new Text(Integer.toString(i));
			
			tName[i].setFont(Font.font("CAMBRIA", FontWeight.BOLD, 30));
			tName[i].setFill(Color.ORANGE);
			tScore[i].setFont(Font.font("CAMBRIA", FontWeight.BOLD, 30));
			tScore[i].setFill(Color.LIME);
			
			gpScores.add(tName[i], 0, i);
			gpScores.add(tScore[i], 1, i);
		}
		
		gpScores.add(tNameCol, 0, 0);
		gpScores.add(tScoreCol, 1, 0);
		
		gpScores.setPrefSize(300, 300);
		gpScores.setVgap(10);
		gpScores.setHgap(70);
		
		vbBanner.getChildren().addAll(tBanner);
		vbButtons.getChildren().addAll(btMenu);
		
		vbBanner.setAlignment(Pos.TOP_CENTER);
		gpScores.setAlignment(Pos.CENTER);
		vbButtons.setAlignment(Pos.BOTTOM_CENTER);
		
		setMargin(vbBanner, new Insets(30));
		setMargin(vbButtons, new Insets(30));
		
		setTop(vbBanner);
		setCenter(gpScores);
		setBottom(vbButtons);
		setBackground(bg);
	}
	
	public static void main(String[] args) {
		
	}
}