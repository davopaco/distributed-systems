package co.edu.upb.classes;

public class ThreadManager {

    private final URLRepository urlRepository;

    public ThreadManager(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void start() {
        for(int i = 1; i<17; i++){ //For thread counts from 1 to 16
            long startTime = System.currentTimeMillis();

            URLConverter urlConverter = new URLConverter(urlRepository, i);
            urlConverter.convertFromURLToPDF();

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Total execution time: " + totalTime + " ms. This was for " + i + " thread(s).");
        }
    }
}
