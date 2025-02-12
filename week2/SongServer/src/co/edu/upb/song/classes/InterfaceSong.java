package co.edu.upb.song.classes;

import java.util.List;

public interface InterfaceSong {
    List<Song> searchByTitle(String title);
    List<Song> searchByGenre(String genre);
    List<Song> searchByAuthor(String author);
}
