package sample.services;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

public interface FileService {

    int getLastNumberOfFile(HttpServletRequest httpServletRequest, String login) throws FileNotFoundException;
}
