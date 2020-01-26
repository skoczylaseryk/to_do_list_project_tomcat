package sample.list;

import java.io.*;


public class ListOfTasks {

    private String name;
    private File file;
    private PrintWriter printWriter;
    private boolean append;


    public ListOfTasks(String name, File file, boolean append ) {
        this.name = name;
        this.file = file;
        this.append = append;
    }

    public String getName() {
        return name;
    }

    public PrintWriter getOpenPrintWriter() {
        try {
            if(!append) {
                this.printWriter = new PrintWriter(file);
            } else {
                this.printWriter = new PrintWriter( new FileWriter(file, true));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printWriter;
    }

    public File getFile() {
        return file;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
}
