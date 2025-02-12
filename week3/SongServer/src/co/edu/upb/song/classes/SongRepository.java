package co.edu.upb.song.classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SongRepository extends UnicastRemoteObject implements InterfaceSong {
    private final List<Song> songs;

    protected SongRepository() throws RemoteException {
        this.songs = new ArrayList<>();
        this.initSongs();
    }

    private static final long serialVersionUID = 1L;

    private void initSongs() {
        songs.add(new Song("Pensamientos Intrusivos", "Pop", "Kali Uchis", "Spanish", 2024));
        songs.add(new Song("Bohemian Rhapsody", "Rock", "Queen", "English", 1975));
        songs.add(new Song("Incendio", "Hyperpop", "Arca", "Spanish", 2021));
        songs.add(new Song("Hotel California", "Rock", "Eagles", "English", 1976));
        songs.add(new Song("Shape of You", "Pop", "Ed Sheeran", "English", 2017));
        songs.add(new Song("XS", "Pop", "Rina Sawayama", "English", 2020));
        songs.add(new Song("CUUUUuuuuuute", "Experimental Pop", "ROSALÍA", "Spanish", 2022));
        songs.add(new Song("Feel Special", "K-pop", "TWICE", "English", 2017));
        songs.add(new Song("Irreplaceable", "Pop", "Beyoncé", "English", 2007));
        songs.add(new Song("Rolling in the Deep", "Pop", "Adele", "English", 2010));
    }

    @Override
    public List<Song> searchByTitle(String title) {
        List<Song> results = new ArrayList<>();
        for (Song song : songs) {
            if (song.title().toLowerCase().contains(title.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }

    @Override
    public List<Song> searchByGenre(String genre) {
        List<Song> results = new ArrayList<>();
        for (Song song : songs) {
            if (song.genre().toLowerCase().contains(genre.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }

    @Override
    public List<Song> searchByAuthor(String author) {
        List<Song> results = new ArrayList<>();
        for (Song song : songs) {
            if (song.author().toLowerCase().contains(author.toLowerCase())) {
                results.add(song);
            }
        }
        return results;
    }
}
