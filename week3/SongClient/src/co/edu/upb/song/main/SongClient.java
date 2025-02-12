package co.edu.upb.song.main;

import co.edu.upb.song.classes.Client;

public class SongClient {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client("127.0.0.1", 1802);
        client.operation();
    }
}
