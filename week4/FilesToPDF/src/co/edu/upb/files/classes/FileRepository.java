package co.edu.upb.files.classes;

import java.util.ArrayList;

public class FileRepository {
    public final ArrayList<File> files;

    public FileRepository() {
        files = new ArrayList<>();
        configFiles();
    }

    void configFiles() {
        //Loop for docx documents
        for (int i = 1; i<=16; i++){
            String path = String.format("%sd.docx",i);
            this.files.add(new File(path, String.format("%sd",i)));
        }

        //Loop for pptx and xlsx documents
        for (int i = 1; i<=8; i++){
            String path = String.format("%sp.pptx",i);
            this.files.add(new File(path, String.format("%sp",i)));

            String path2 = String.format("%sx.xlsx",i);
            this.files.add(new File(path2, String.format("%sx",i)));
        }
    }

    ArrayList<File> getURLs() {
        return this.files;
    }
}
