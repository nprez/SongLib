//Nick Prezioso, Tim Gassaway

package SongLib.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import SongLib.view.SongLibController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SongLib extends Application{
	
	public static File f;
	public SongLibController slc;
	public static Stage primaryStage;
	
	@Override
	public void start(Stage pStage) throws Exception {
		primaryStage=pStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SongLib/view/SongLib.fxml"));
		slc = new SongLibController();
		List<String> songstrs = Files.readAllLines(f.toPath());
		int numSongs = songstrs.size()/4;
		Song[] songs = new Song[numSongs];
		for(int i=0,s=0; i<songstrs.size(); i+=4,s++){
			songs[s] = new Song(songstrs.get(i), songstrs.get(i+1), songstrs.get(i+2), songstrs.get(i+3));
		}
		loader.setController(slc);
		AnchorPane root = (AnchorPane)loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SongLib");
		primaryStage.setResizable(false);
		
		slc.load(songs);
		
		primaryStage.show();
	}
	
	@Override
	public void stop() throws IOException{
		ObservableList<Song> savelist = slc.listview.getItems();
		String saveinfo = "";
		for(int i=0; i<savelist.size(); i++){
			Song s = savelist.get(i);
			saveinfo+=s.name+"\n"+s.artist+"\n"+s.album+"\n"+s.year+"\n";
		}
		FileWriter fw = new FileWriter(f, false);
		f.delete();
		fw.write(saveinfo);
		fw.close();
	}
	
	public static void main(String[] args) throws IOException {
		f = new File("songs.txt");
		f.createNewFile();
		launch(args);
	}
}
