package sample.list;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ListOfTasks {

    private String name;
    private File file;
    private PrintWriter printWriter;


    public ListOfTasks(String name, File file, PrintWriter printWriter) {
        this.name = name;
        this.file = file;
        this.printWriter = printWriter;

    }

    public String getName() {
        return name;
    }

    public PrintWriter getPrintWriter() {
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
