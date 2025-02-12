package co.edu.upb.song.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class JSocketClient implements InterfaceSong {

    private InetAddress address;
    private int port;
    private Socket clientSk;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean continueFlag;
    private final Scanner scanner;

    public JSocketClient(String address, int port) {
        this.continueFlag = true;
        this.scanner = new Scanner(System.in);
        try {
            this.port = port;
            this.address = InetAddress.getByName(address);
            this.oos = null;
            this.ois = null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void request() {
        try {
            this.clientSk = new Socket(this.address, this.port);
            this.oos = new ObjectOutputStream(this.clientSk.getOutputStream());
            this.ois = new ObjectInputStream(this.clientSk.getInputStream());
            System.out.println("\n [Client]: Conexión exitosa.");

            while (continueFlag) {
                System.out.println("Welcome! What would you like to search your song based on?");
                System.out.println("1. Name");
                System.out.println("2. Genre");
                System.out.println("3. Author");
                System.out.println("4. Exit");
                int option = scanner.nextInt();
                scanner.nextLine();
                String value;
                List<Song> songs = new ArrayList<>();

                switch (option) {
                    case 1:
                        System.out.println("Enter de name of the song: ");
                        value = scanner.nextLine();
                        songs = searchByTitle(value);
                        break;
                    case 2:
                        System.out.println("Enter de genre of the song: ");
                        value = scanner.nextLine();
                        songs = searchByGenre(value);
                        break;
                    case 3:
                        System.out.println("Enter de author of the song: ");
                        value = scanner.nextLine();
                        songs = searchByAuthor(value);
                        break;
                    case 4:
                        System.out.println("Thanks for using our system. See ya!");
                        this.continueFlag = false;
                        this.closeService();
                        return;
                    default:
                        System.out.println("Invalid choice. Do it again!");
                }

                if (songs.isEmpty()) {
                    System.out.println("No songs to be found! Try searching something else!");
                    continue;
                }

                for (int i = 0; i < songs.size(); i++) {
                    System.out.println(i + 1 + "- " + songs.get(i).toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeService() {
        try {
            this.ois.close();
            this.oos.close();
            this.clientSk.close();
            System.out.println("\n [Client]: Conexión terminada.");
        } catch (IOException e) {
            System.out.println("\n [Client]: No se puede cerrar la conexión.");
        }
    }

    private List<Song> getSongsFromServer(String type, String value) {
        try {
            this.oos.writeUTF(type);
            this.oos.writeUTF(value);
            oos.flush();

            return (List<Song>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Song> searchByTitle(String title) {
        return getSongsFromServer("title", title);
    }

    @Override
    public List<Song> searchByGenre(String genre) {
        return getSongsFromServer("genre", genre);
    }

    @Override
    public List<Song> searchByAuthor(String author) {
        return getSongsFromServer("author", author);
    }
}

