package co.edu.upb.song.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class JSocketServer {

    private int port;
    private ServerSocket serverSk;
    private Socket clientSk;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private final SongRepository songRepo;

    public JSocketServer(int port, SongRepository songRepo) {
        this.songRepo = songRepo;
        try {
            this.port = port;
            this.serverSk = new ServerSocket(port, 100);
            this.oos = null;
            this.ois = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listening() {
        try {
            System.out.println("\n [Server]: " + "Esperando.");
            this.clientSk = this.serverSk.accept();
            this.oos = new ObjectOutputStream(this.clientSk.getOutputStream());
            this.ois = new ObjectInputStream(this.clientSk.getInputStream());
            System.out.println("\n [Server]: " + "Conexión exitosa.");
            while (true) {
                try {
                    String type = ois.readUTF();
                    String value = ois.readUTF();

                    List<Song> songs = new ArrayList<>();

                    if(type.isEmpty() || value.isEmpty()){
                        this.closeService();
                    }

                    switch (type) {
                        case "title":
                            songs = songRepo.searchByTitle(value);
                            break;
                        case "genre":
                            songs = songRepo.searchByGenre(value);
                            break;
                        case "author":
                            songs = songRepo.searchByAuthor(value);
                            break;
                    }

                    oos.writeObject(songs);
                    oos.flush();

                } catch (Exception e) {
                    System.out.println("\n [Server]: No se puede recibir la data.");
                    this.closeService();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeService() {
        try {
            this.ois.close();
            this.oos.close();
            this.clientSk.close();
            this.serverSk.close();
            System.out.println("\n [Server]: Conexión terminada.");
        } catch (Exception e) {
            System.out.println("\n [Server]: No se puede cerrar la conexión.");
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
