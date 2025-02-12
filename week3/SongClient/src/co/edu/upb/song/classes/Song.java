package co.edu.upb.song.classes;

import java.io.Serial;
import java.io.Serializable;

public record Song(String title, String genre, String author, String language,
                   int releaseYear) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String toString() {
        return String.format("%s - %s (%s, %d, %s)", title, author, genre, releaseYear, language);
    }
}
