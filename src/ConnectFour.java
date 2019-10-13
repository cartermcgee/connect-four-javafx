import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnectFour extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        //create a grid pane within a border pane
        BorderPane pane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(3);
        gridPane.setVgap(3);

        //create a grid of disks
        Grid grid = new Grid();
        grid.setAllOnClicked();

        //add disks to the pane
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 6; j++) {
                gridPane.add(grid.getGrid()[i][j].getCircle(), i, j);
            }
        }

        //make and show text with directions
        text = new Text("CONNECT FOUR TO WIN THE GAME!");
        text.setFill(Color.WHITE);
        text.setStroke(Color.BLACK);
        text.setFont(new Font(28));

        //add to main pane
        pane.setCenter(gridPane);
        pane.setBottom(text);

        //add scene and show stage
        Scene scene = new Scene(pane, 460, 430);
        scene.setFill(Color.BLUE);
        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Text text;

    public void setTextNode(Text text) {
        this.text = text;
    }
}
