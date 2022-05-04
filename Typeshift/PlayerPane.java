import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayerPane extends BorderPane {
	
	protected BackgroundImage bgImage = new BackgroundImage(new Image("/background.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	protected Background bg = new Background(bgImage);
	
	protected Text tPrompt = new Text("ENTER YOUR NAME");
	
	protected TextField tfName = new TextField();
	
	protected Button btSubmitName = new Button("Submit Name");
	
	private HBox hbPrompt = new HBox(50);
	private HBox hbName = new HBox(50);
	
	public PlayerPane() {
		DrawPP();
	}
	
	private void DrawPP() {
		
		tPrompt.setFont(Font.font("VERDANA", 40));
		tPrompt.setFill(Color.RED);
		
		hbPrompt.getChildren().addAll(tPrompt);
		hbName.getChildren().addAll(tfName, btSubmitName);
		
		setTop(hbPrompt);
		setCenter(hbName);
		setBackground(bg);
		
		hbPrompt.setAlignment(Pos.TOP_CENTER);
		hbName.setAlignment(Pos.CENTER);
		
		setMargin(hbPrompt, new Insets(30));
		setMargin(hbName, new Insets(30));
		
	}
	
	public static void main(String[] args) {
		
	}
}