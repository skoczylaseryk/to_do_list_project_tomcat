package sample.services.impl;

import sample.services.FileService;
import sample.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    private static FileServiceImpl FILESERVICE;


    private UserService userService = UserServiceImpl.getInstance();
    private int numberOfFile = 0;

    private FileServiceImpl() {
    }


    public static FileServiceImpl getInstance() {
        if (FILESERVICE == null) {
            FILESERVICE = new FileServiceImpl();
            return FILESERVICE;
        }
        return FILESERVICE;
    }


    public int getLastNumberOfFile(HttpServletRequest request, String login) throws FileNotFoundException {
        List<String> strings = new ArrayList<>();

        File[] files = new File(userService.getCONTEXTPATH() + File.separator+"lists"+ File.separator + login).listFiles();


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

        request.setAttribute("newListNumber", numberOfFile + 1);

        return numberOfFile;
    }
}
