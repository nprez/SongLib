//Nick Prezioso
//Tim Gassaway

package SongLib.view;

import SongLib.app.Song;
import SongLib.app.SongLib;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	@FXML public ListView<Song> listview;
	
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
	
	public void popupError(String msg){
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(SongLib.primaryStage);
        VBox dialogVbox = new VBox(1);
        dialogVbox.getChildren().add(new Text(msg));
        Scene dialogScene = new Scene(dialogVbox, msg.length()*5.5, 50);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	public void popupConfirmation(String source){
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(SongLib.primaryStage);
        VBox dialogVbox = new VBox(1);
        dialogVbox.getChildren().add(new Text("Are you sure?"));
        Button confirm = new Button("Confirm");
        confirm.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(source.equals("add")){
					doAdd();
					dialog.close();
				}
				else if(source.equals("edit")){
					doEdit();
					dialog.close();
				}
				else{
					doDelete();
					dialog.close();
				}
				
			}
		});
        dialogVbox.getChildren().add(confirm);
        Button cancel = new Button("Cancel");
        cancel.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				dialog.close();
				
			}
		});
        dialogVbox.getChildren().add(cancel);
        Scene dialogScene = new Scene(dialogVbox, 100, 100);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	public void add(ActionEvent e){
		if(addname.getText().compareTo("") == 0){
			//error
			popupError("Name is required!");
		}
		else if(addartist.getText().compareTo("") == 0){
			//error
			popupError("Artist is required!");
		}
		else{
			boolean properYear = true;
			try{
				Integer.parseInt(addyear.getText());
			}catch(NumberFormatException E){
				//error
				if(addyear.getText().length()>0)
					properYear = false;
			}
			if(properYear){
				ObservableList<Song> ol = FXCollections.observableArrayList();
				ol.addAll(listview.getItems());
				boolean repeat = false;
				for(int i=0; i<ol.size(); i++){
					if(ol.get(i).name.equals(addname.getText()) && ol.get(i).artist.equals(addartist.getText())){
						repeat = true;
						break;
					}
				}
				if(!repeat){
					popupConfirmation("add");
				}
				else{
					//error
					popupError(addname.getText()+" by "+addartist.getText()+" already exists!");
				}
			}
			else{
				//error
				popupError("Year is invalid! Integer expected.");
			}
		}
	}
	
	public void doAdd(){
		ObservableList<Song> ol = FXCollections.observableArrayList();
		ol.addAll(listview.getItems());
		Song newSong = new Song(addname.getText(), addartist.getText(), addalbum.getText(), addyear.getText());
		ol.add(newSong);
		addname.setText("");
		addartist.setText("");
		addalbum.setText("");
		addyear.setText("");
		listview.setItems(ol.sorted());
		listview.getSelectionModel().select(newSong);
	}
	
	public void edit(ActionEvent e){
		if(editname.getText().compareTo("") == 0){
			//error
			popupError("Name is required!");
		}
		else if(editartist.getText().compareTo("") == 0){
			//error
			popupError("Artist is required!");
		}
		else{
			boolean properYear = true;
			try{
				Integer.parseInt(edityear.getText());
			}catch(NumberFormatException E){
				//error
				if(edityear.getText().length()>0)
					properYear = false;
			}
			if(properYear){
				ObservableList<Song> ol = FXCollections.observableArrayList();
				ol.addAll(listview.getItems());
				int index = listview.getSelectionModel().getSelectedIndex();
				ol.remove(index);
				boolean repeat = false;
				for(int i=0; i<ol.size(); i++){
					if(ol.get(i).name.equals(editname.getText()) && ol.get(i).artist.equals(editartist.getText())){
						repeat = true;
						break;
					}
				}
				if(!repeat){
					popupConfirmation("edit");
				}
				else{
					//error
					popupError(editname.getText()+" by "+editartist.getText()+" already exists!");
				}
			}
			else{
				//error
				popupError("Year is invalid! Integer expected.");
			}
		}
	}
	
	public void doEdit(){
		ObservableList<Song> ol = FXCollections.observableArrayList();
		ol.addAll(listview.getItems());
		int index = listview.getSelectionModel().getSelectedIndex();
		ol.remove(index);
		Song newSong = new Song(editname.getText(), editartist.getText(), editalbum.getText(), edityear.getText());
		ol.add(newSong);
		listview.setItems(ol.sorted());
		listview.getSelectionModel().select(newSong);
	}
	
	public void delete(ActionEvent e){
		popupConfirmation("delete");
	}
	
	public void doDelete(){
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
