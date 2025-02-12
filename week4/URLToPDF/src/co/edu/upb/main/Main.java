package co.edu.upb.main;

import co.edu.upb.classes.ThreadManager;
import co.edu.upb.classes.URLRepository;

public class Main {
    public static void main(String[] args) {
        URLRepository urlRepository = new URLRepository();
        ThreadManager threadManager = new ThreadManager(urlRepository);
        threadManager.start();
    }
}