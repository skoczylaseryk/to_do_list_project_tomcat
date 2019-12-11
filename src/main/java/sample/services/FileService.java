package sample.services;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static FileService FILESERVICE;

    private List<String> strings = new ArrayList<>();
    private UserService userService = UserService.getInstance();
    private int numberOfFile;

    private FileService() {
    }


    public static FileService getInstance(){
        if(FILESERVICE==null) {
            FILESERVICE = new FileService();
            return FILESERVICE;
        }
         return FILESERVICE;
    }


    public int getLastNumberOfFile(HttpServletRequest request, String login) throws FileNotFoundException {

        File[] files = new File(userService.getCONTEXTPATH() + "/lists/" + login).listFiles();

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            if (fileName.contains("NewList")) {
                strings.add(fileName);
            }
        }

        for (int i = 1; i <= 100; i++) {
            if (!strings.contains("NewList" + i + ".txt")) {
                numberOfFile = i;
                break;
            }
        }

        request.setAttribute("newListNumber",numberOfFile+1);

        return numberOfFile;
    }
}
