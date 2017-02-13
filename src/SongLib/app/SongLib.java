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
		SongLibController slc = new SongLibController();
		Song[] testarr = new Song[5];
		testarr[0] = new Song("allstar", "smashmouth");
		testarr[1] = new Song("ocean man", "spongebob");
		testarr[2] = new Song("scatman", "scatman john");
		testarr[3] = new Song("yy", "y");
		testarr[4] = new Song("zz", "z");
		loader.setController(slc);
		AnchorPane root = (AnchorPane)loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SongLib");
		primaryStage.setResizable(false);
		
		slc.load(testarr);
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
