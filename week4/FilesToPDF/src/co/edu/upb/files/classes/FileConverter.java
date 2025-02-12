package co.edu.upb.files.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileConverter {
    private final FileRepository fileRepository;
    private final String outputPath;
    private final String inputPath;
    private final List<String> outputPaths;
    private final int threadCount;

    public FileConverter(FileRepository fileRepository, int threadCount) {
        this.fileRepository = fileRepository;
        this.threadCount = threadCount;

        this.outputPaths = new ArrayList<>();
        this.outputPath = "E:/UPB/8VO_SEMESTRE/DISTRIBUIDOS/PROJECTS/distributed-systems/week4/FilesToPDF/output/";
        this.inputPath = "E:/UPB/8VO_SEMESTRE/DISTRIBUIDOS/PROJECTS/distributed-systems/week4/FilesToPDF/assets/";
    }

    public void convertFromFileToPDF() {
        ExecutorService executor = Executors.newFixedThreadPool(this.threadCount); // Executes using a certain number of
                                                                                   // threads

        // Execute the files to PDF conversion using the executor
        executor.submit(() -> {
            this.fileRepository.getURLs().forEach((file) -> {
                try {
                    List<String> commands = getOfficeCommand(file);
                    boolean result = executeProcessBuild(commands);
                    if (!result)
                        throw new RuntimeException("Error converting file to PDF");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

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

    // Gets the respective command for the office program
    private List<String> getOfficeCommand(File file) {

        String fullOutputPath = this.outputPath + file.name() + ".pdf";
        this.outputPaths.add(fullOutputPath);

        List<String> commands = new ArrayList<>();
        commands.add("soffice");
        commands.add("--convert-to");
        commands.add("pdf");
        commands.add("--outdir");
        commands.add(String.format("\"%s\"", outputPath));
        commands.add(String.format("\"%s%s\"", inputPath, file.filePath()));
        commands.add("--headless");
        return commands;
    }

    // Execute the command using ProcessBuilder
    private boolean executeProcessBuild(List<String> commands) {

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
