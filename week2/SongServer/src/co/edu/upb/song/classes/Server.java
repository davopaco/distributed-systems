package co.edu.upb.song.classes;

public class Server {
	
	private final JSocketServer sk;
	
	public Server(int port) {		
		this.sk = new JSocketServer(port, new SongRepository());
	}

	public void run() {
		sk.listening();
	}

}
