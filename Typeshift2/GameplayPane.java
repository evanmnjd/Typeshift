import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameplayPane extends BorderPane {
	
	Enemy randEnemy = new Enemy();
	
	protected static int level = Typeshift.level;

	public Image currentEnemy = new Image("/test.jpeg");
	public ImageView viewEnemy = new ImageView(currentEnemy);
	
	protected static String[] strings = new String[6];
	protected static Image[] images = new Image[6];
	public static BackgroundImage[] bgImages = new BackgroundImage[6];
	public static Background[] bg = new Background[6];
	
	private VBox vbWord = new VBox(50);
	private HBox hbType = new HBox(50);
	private HBox hbEnemy = new HBox(50);
	private VBox vbLevelScore = new VBox(50);
		
	protected Text tWord = new Text();
	protected Text tLevel = new Text("Level: " + Typeshift.level);
	protected Text tCorrect = new Text("Correct: " + Typeshift.score);
	protected Text tTime = new Text("Time: 20");
	protected TextField tfAnswer = new TextField();
		
	protected Button btSubmit = new Button("Submit");
	
	public GameplayPane() {
		DrawGP();
	}
	
	private void DrawGP() {
		tWord.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tWord.setFill(Color.RED);
		tLevel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tLevel.setFill(Color.GREEN);
		tCorrect.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tCorrect.setFill(Color.YELLOW);
		tTime.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		tTime.setFill(Color.ORANGE);
		
		vbLevelScore.getChildren().addAll(tTime, tLevel, tCorrect);
		vbWord.getChildren().addAll(tWord);
		hbType.getChildren().addAll(tfAnswer, btSubmit);
		hbEnemy.getChildren().addAll(viewEnemy);
		
		setLeft(vbLevelScore);
		setTop(vbWord);
		setCenter(hbEnemy);
		setBottom(hbType);
		setBackground(bg[level]);
				
		vbLevelScore.setAlignment(Pos.TOP_LEFT);
		vbWord.setAlignment(Pos.CENTER);
		hbType.setAlignment(Pos.CENTER);
		hbEnemy.setAlignment(Pos.CENTER);
		setMargin(vbWord, new Insets(30));
		setMargin(hbType, new Insets(30));
		setMargin(hbEnemy, new Insets(30));
		setMargin(vbLevelScore, new Insets(30));
	}
		
	public static void main(String[] args) {
		
	}
}