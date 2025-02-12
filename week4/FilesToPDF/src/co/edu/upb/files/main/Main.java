package co.edu.upb.files.main;

import co.edu.upb.files.classes.ThreadManager;
import co.edu.upb.files.classes.FileRepository;

public class Main {
    public static void main(String[] args) {
        FileRepository fileRepository = new FileRepository();
        ThreadManager threadManager = new ThreadManager(fileRepository);
        threadManager.start();
    }
}