package sample.services;

import java.io.IOException;

public interface UserService {

    void createUser(String login, String password) throws IOException;

    boolean verifyLoginData(String login, String password) throws IOException;

    int verifySignUpData(String login);

    String getCONTEXTPATH();


}
