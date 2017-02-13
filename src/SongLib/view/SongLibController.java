//Nick Prezioso
//Tim Gassaway

package SongLib.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
