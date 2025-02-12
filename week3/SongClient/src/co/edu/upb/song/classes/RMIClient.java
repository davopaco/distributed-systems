package co.edu.upb.song.classes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RMIClient {

    private final String addressName;
    private boolean continueFlag;
    private final Scanner scanner;

    public RMIClient(String address, int port) {
        this.continueFlag = true;
        this.scanner = new Scanner(System.in);
        this.addressName = "rmi://127.0.0.1:1802/song";
    }

    public void request() {
        try {
            InterfaceSong service = (InterfaceSong) Naming.lookup(this.addressName);

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
                        songs = service.searchByTitle(value);
                        break;
                    case 2:
                        System.out.println("Enter de genre of the song: ");
                        value = scanner.nextLine();
                        songs = service.searchByGenre(value);
                        break;
                    case 3:
                        System.out.println("Enter de author of the song: ");
                        value = scanner.nextLine();
                        songs = service.searchByAuthor(value);
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
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}

