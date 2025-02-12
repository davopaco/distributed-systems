package co.edu.upb.files.classes;

public class ThreadManager {

    private final FileRepository fileRepository;

    public ThreadManager(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void start() {
        for(int i = 1; i<17; i++){ //For thread counts from 1 to 16
            long startTime = System.currentTimeMillis();

            FileConverter fileConverter = new FileConverter(fileRepository, i);
            fileConverter.convertFromFileToPDF();

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Total execution time: " + totalTime + " ms. This was for " + i + " thread(s).");
        }
    }
}
