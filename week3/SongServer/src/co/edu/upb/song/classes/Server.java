package co.edu.upb.song.classes;

import java.rmi.RemoteException;

public class Server {

    private final RMIServer sk;

    public Server(int port) throws RemoteException {
        this.sk = new RMIServer(port, new SongRepository());
    }

    public void run() {
        sk.listening();
    }

}
