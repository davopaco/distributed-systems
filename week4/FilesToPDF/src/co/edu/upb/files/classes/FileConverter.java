package co.edu.upb.files.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
        this.outputPaths = Collections.synchronizedList(new ArrayList<>());
        // Ajusta las rutas según tu entorno
        this.outputPath = "E:/UPB/8VO_SEMESTRE/DISTRIBUIDOS/PROJECTS/distributed-systems/week4/FilesToPDF/output/";
        this.inputPath = "E:/UPB/8VO_SEMESTRE/DISTRIBUIDOS/PROJECTS/distributed-systems/week4/FilesToPDF/assets/";
    }

    public void convertFromFileToPDF() {
        ExecutorService executor = Executors.newFixedThreadPool(this.threadCount);

        for (File file : this.fileRepository.getURLs()) {
            executor.submit(() -> {
                try {
                    List<String> commands = getOfficeCommand(file);
                    boolean result = executeProcessBuild(commands);
                    if (!result)
                        throw new RuntimeException("Error converting file: " + file.name());
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

        // Imprime la lista de archivos PDF con su ruta correspondiente
        for (String path : this.outputPaths) {
            String[] directories = path.split("/");
            String fileName = directories[directories.length - 1];
            System.out.println("File: " + fileName + " - Corresponding path: " + path);
        }
    }

    // Construye la lista de comandos para convertir un archivo usando soffice
    private List<String> getOfficeCommand(File file) {
        String fullOutputPath = this.outputPath + file.name() + ".pdf";
        this.outputPaths.add(fullOutputPath);

        // Construye el directorio temporal en formato URL
        String tmpDir = System.getProperty("java.io.tmpdir").replace("\\", "/");
        if (!tmpDir.startsWith("file:///")) {
            tmpDir = "file:///" + tmpDir;
        }
        String uniqueUserInstallation = tmpDir + "LOProfile_" + UUID.randomUUID().toString();

        List<String> commands = new ArrayList<>();
        commands.add("soffice");
        // Agrega el parámetro con el perfil de usuario único
        commands.add("-env:UserInstallation=" + uniqueUserInstallation);
        commands.add("--convert-to");
        commands.add("pdf");
        commands.add("--outdir");
        commands.add(outputPath);
        commands.add(inputPath + file.filePath());
        commands.add("--headless");
        return commands;
    }

    // Ejecuta el comando mediante ProcessBuilder
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
