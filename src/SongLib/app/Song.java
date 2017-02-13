package SongLib.app;

public class Song{
	public String name;
	public String artist;
	public String album;
	public String year;
	
	public Song(String name, String artist){
		this(name, artist, "?", "?");
	}
	
	public Song(String name, String artist, String album, String year){
		this.name=name;
		this.artist=artist;
		this.album=album;
		this.year=year;
	}
}
