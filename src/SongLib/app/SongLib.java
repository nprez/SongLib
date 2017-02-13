//Nick Prezioso
//Tim Gassaway

package SongLib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SongLib extends Application{
	private SongLib(){}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/SongLib/view/SongLib.fxml"));
		
		GridPane root = (GridPane)loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SongLib");
		primaryStage.setResizable(false);  
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
