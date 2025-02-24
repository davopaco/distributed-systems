package co.edu.upb.songclient.classes;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jakarta.xml.ws.Service;

import java.net.URL;
import javax.xml.namespace.QName;

public class SOAPClient {

    private boolean continueFlag;
    private final Scanner scanner;
    private InterfaceSong songService;

    public SOAPClient() throws MalformedURLException {
        this.continueFlag = true;
        this.scanner = new Scanner(System.in);

        this.setup();
    }

    public void setup() throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:6969/song?wsdl");
        QName servQName = new QName("http://classes.songserver.upb.edu.co/", "SongService");
        QName portQName = new QName("http://classes.songserver.upb.edu.co/", "SongPort");

        Service service = Service.create(wsdlURL, servQName);
        songService = service.getPort(portQName, InterfaceSong.class);
    }

    public void run() {
        try {
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
                        songs = songService.searchByTitle(value);
                        break;
                    case 2:
                        System.out.println("Enter de genre of the song: ");
                        value = scanner.nextLine();
                        songs = songService.searchByGenre(value);
                        break;
                    case 3:
                        System.out.println("Enter de author of the song: ");
                        value = scanner.nextLine();
                        songs = songService.searchByAuthor(value);
                        break;
                    case 4:
                        System.out.println("Thanks for using our system. See ya!");
                        this.continueFlag = false;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

