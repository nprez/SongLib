//Nick Prezioso
//Tim Gassaway

package SongLib.view;

import SongLib.app.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SongLibController {
	@FXML Button addsong;
	@FXML Button editsong;
	@FXML Button deletesong;
	@FXML TextField addname;
	@FXML TextField addartist;
	@FXML TextField addalbum;
	@FXML TextField addyear;
	@FXML TextField editname;
	@FXML TextField editartist;
	@FXML TextField editalbum;
	@FXML TextField edityear;
	@FXML ListView<Song> listview;
	
	public void load(Song[] songs){
		ObservableList<Song> songsOL = FXCollections.observableArrayList();
		songsOL.addAll(songs);
		listview.setItems(songsOL);
	}
	
	public void select(ActionEvent e){
		Song s = listview.getSelectionModel().getSelectedItem();
		System.out.println(s.name);
	}
	
	public void add(ActionEvent e){
		System.out.println("added");
	}
	
	public void edit(ActionEvent e){
		System.out.println("edited");
	}
	
	public void delete(ActionEvent e){
		System.out.println("deleted");
	}
	
	//.getText
	//.setText
}
