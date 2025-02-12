package co.edu.upb.song.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

public class RMIServer {

    private int port;
    private final InterfaceSong service;

    public RMIServer(int port, InterfaceSong service) {
        this.service = service;
        this.port = port;
    }

    public void listening() {
        try {
            LocateRegistry.createRegistry(1802);
            try {
                Naming.rebind("//127.0.0.1:1802/song", service);
            } catch (RemoteException | MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
