//Nick Prezioso
//Tim Gassaway

package SongLib.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import SongLib.view.SongLibController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SongLib extends Application{
	
	public static File f;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SongLib/view/SongLib.fxml"));
//		loader.setController("/SongLib/view/SongLibController.java");
		SongLibController slc = new SongLibController();
		List<String> songstrs = Files.readAllLines(f.toPath());
		int numSongs = songstrs.size()/4;
		Song[] songs = new Song[numSongs];
		for(int i=0,s=0; i<songstrs.size(); i+=4,s++){
			songs[s] = new Song(songstrs.get(i), songstrs.get(i+1), songstrs.get(i+2), songstrs.get(i+3));
		}
		/*Song[] testarr = new Song[5];
		testarr[0] = new Song("allstar", "smashmouth");
		testarr[1] = new Song("ocean man", "spongebob");
		testarr[2] = new Song("scatman", "scatman john");
		testarr[3] = new Song("yy", "y");
		testarr[4] = new Song("zz", "z");*/
		loader.setController(slc);
		AnchorPane root = (AnchorPane)loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SongLib");
		primaryStage.setResizable(false);
		
		//slc.load(testarr);
		slc.load(songs);
		
		primaryStage.show();
	}
	
	@Override
	public void stop(){
		System.out.println("closed");
	}
	
	public static void main(String[] args) {
		f = new File("songs.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			
		}
		launch(args);
	}
}
