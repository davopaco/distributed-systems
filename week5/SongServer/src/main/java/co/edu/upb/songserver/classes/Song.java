package co.edu.upb.songserver.classes;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "song")
@XmlType(propOrder = {"title", "genre", "author", "language", "releaseYear"})
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String genre;
    private String author;
    private String language;
    private int releaseYear;

    public Song() {
    }

    public Song(String title, String genre, String author, String language, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.language = language;
        this.releaseYear = releaseYear;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s, %d, %s)", title, author, genre, releaseYear, language);
    }
}