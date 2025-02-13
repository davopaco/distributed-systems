package co.edu.upb.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class URLConverter {
    private final URLRepository urlRepository;
    private final String outputPath;
    private final List<String> outputPaths;
    private final int threadCount;

    public URLConverter(URLRepository urlRepository, int threadCount) {
        this.urlRepository = urlRepository;
        this.threadCount = threadCount;

        this.outputPaths = new ArrayList<>();
        this.outputPath = "E:/UPB/8VO_SEMESTRE/DISTRIBUIDOS/PROJECTS/distributed-systems/week4/URLToPDF/output/";
    }

    public void convertFromURLToPDF() {
        ExecutorService executor = Executors.newFixedThreadPool(this.threadCount); // Executes using a certain number of
                                                                                   // threads

        // Execute the URL to PDF conversion using the executor
        for (URL url: this.urlRepository.getURLs()) {
            executor.submit(() -> {
                    try {
                        List<String> commands = getChromeCommand(url);
                        boolean result = executeProcessBuild(commands);
                        if (!result)
                            throw new RuntimeException("Error converting URL to PDF");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
                System.err.println("Executor did not terminate in the specified time.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        //Print the list of PDF files with its corresponding outputs
        for (int i = 0; i<this.outputPaths.size(); i++){
            String[] directories = this.outputPaths.get(i).split("/");
            String file = directories[directories.length-1];

            System.out.println("File: " + file + " - Corresponding path: " + this.outputPaths.get(i));
        }
    }

    // Gets the respective command for the URL
    private List<String> getChromeCommand(URL url) {

        String fullOutputPath = this.outputPath + url.name().replace(" ", "_") + ".pdf";
        this.outputPaths.add(fullOutputPath);

        List<String> commands = new ArrayList<>();
        commands.add("chrome");
        commands.add("--headless");
        commands.add("--disable-gpu");
        commands.add("--print-to-pdf=" + fullOutputPath);
        commands.add(url.urlPath());
        return commands;
    }

    // Execute the command using ProcessBuilder
    private boolean executeProcessBuild(List<String> commands) {

        // Execute the command using ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
