package co.edu.upb.song.main;

import co.edu.upb.song.classes.Server;

import java.rmi.RemoteException;

public class SongServer {

    public static void main(String[] args) throws RemoteException {
        Server server = new Server(1802);
        server.run();
    }

}
