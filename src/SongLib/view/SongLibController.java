//Nick Prezioso
//Tim Gassaway

package SongLib.view;

import SongLib.app.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
		listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
		    @Override
		    public void changed(ObservableValue<? extends Song> observable,
		            Song oldValue, Song newValue) {
		    	if(newValue == null){return;}
		    	editname.setText(newValue.name);
		    	editartist.setText(newValue.artist);
		    	editalbum.setText(newValue.album);
		    	edityear.setText(newValue.year);
		    }
		});
		listview.getSelectionModel().select(0);
	}
	
	public void add(ActionEvent e){
		if(addname.getText().compareTo("") == 0){
			//error
		}
		else if(addartist.getText().compareTo("") == 0){
			//error
		}
		else{
			ObservableList<Song> ol = FXCollections.observableArrayList();
			ol.addAll(listview.getItems());
			String album = addalbum.getText().compareTo("")==0?"?":addalbum.getText();
			String year = addyear.getText().compareTo("")==0?"?":addyear.getText();
			ol.add(new Song(addname.getText(), addartist.getText(), album, year));
			addname.setText("");
			addartist.setText("");
			addalbum.setText("");
			addyear.setText("");
			listview.setItems(ol.sorted());
		}
	}
	
	public void edit(ActionEvent e){
		if(editname.getText().compareTo("") == 0){
			//error
		}
		else if(editartist.getText().compareTo("") == 0){
			//error
		}
		else{
			ObservableList<Song> ol = FXCollections.observableArrayList();
			ol.addAll(listview.getItems());
			int index = listview.getSelectionModel().getSelectedIndex();
			ol.remove(index);
			String album = editalbum.getText().compareTo("")==0?"?":editalbum.getText();
			String year = edityear.getText().compareTo("")==0?"?":edityear.getText();
			Song newSong = new Song(editname.getText(), editartist.getText(), album, year);
			ol.add(newSong);
			listview.setItems(ol.sorted());
			listview.getSelectionModel().select(newSong);
		}
		System.out.println("edited");
	}
	
	public void delete(ActionEvent e){
		ObservableList<Song> ol = FXCollections.observableArrayList();
		ol.addAll(listview.getItems());
		int index = listview.getSelectionModel().getSelectedIndex();
		ol.remove(index);
		int selectIndex = -1;
		if(index<ol.size()){
			selectIndex = index;
		}
		else if(index-1>=0 && !ol.isEmpty()){
			selectIndex = index-1;
		}
		editname.setText("");
		editartist.setText("");
		editalbum.setText("");
		edityear.setText("");
		listview.setItems(ol);
		if(selectIndex!=-1)
			listview.getSelectionModel().select(selectIndex);
	}
}
