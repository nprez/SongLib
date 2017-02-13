//Nick Prezioso
//Tim Gassaway

package SongLib.app;

import SongLib.view.SongLibController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SongLib extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SongLib/view/SongLib.fxml"));
//		loader.setController("/SongLib/view/SongLibController.java");
		loader.setController(new SongLibController());
		AnchorPane root = (AnchorPane)loader.load();
		
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
