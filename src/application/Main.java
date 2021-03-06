package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {

		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("controller/GridScene.fxml"));
			Scene scene = new Scene(root,1080,750);
			scene.getStylesheets().add(getClass().getResource("controller/rootStyle.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
