package co.edu.upb.songserver.main;

import co.edu.upb.songserver.classes.SOAPServer;
import co.edu.upb.songserver.classes.SongRepository;

public class SongServer {

    public static void main(String[] args) {
        SongRepository songRepository = new SongRepository();
        SOAPServer soapServer = new SOAPServer(songRepository);
        soapServer.run();
        System.out.println("Server running");
    }

}
