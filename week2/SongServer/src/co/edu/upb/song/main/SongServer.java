package co.edu.upb.song.main;

import co.edu.upb.song.classes.Server;

public class SongServer {
	
	public static void main(String[] args) {
		Server server = new Server(1802);
		server.run();
	}

}
