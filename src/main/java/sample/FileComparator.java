package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class FileComparator implements Comparator<File> {


    @Override
    public int compare(File f1, File f2) {
        long firstTime = getFileCreationEpoch(f1);
        long secondTime = getFileCreationEpoch(f2);


        return Long.valueOf(firstTime).compareTo(secondTime);
    }

    private Long getFileCreationEpoch(File file) {
        BasicFileAttributes basicFileAttributes = null;
        try {
            basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return basicFileAttributes.creationTime().toInstant().toEpochMilli();
    }
}