package sample.services.impl;


import sample.services.UserService;

import java.io.*;

public class UserServiceImpl implements UserService {
    private static String CONTEXTPATH = "C:\\resources";

    private static UserServiceImpl USERSERVICE;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (USERSERVICE == null) {
            USERSERVICE = new UserServiceImpl();
        }
        return USERSERVICE;
    }


    public void createUser(String login, String password) throws IOException {


        new File(CONTEXTPATH + "/users/" + login).mkdirs();
        new File(CONTEXTPATH + "/lists/" + login).mkdirs();

        PrintWriter printWriter1 = new PrintWriter(new FileWriter(new File(CONTEXTPATH + "/users/" + login + "/" + login + ".txt")));
        printWriter1.println(login);
        printWriter1.println(password);
        printWriter1.close();
    }

    public boolean verifyLoginData(String login, String password) throws IOException {
        File file = new File(CONTEXTPATH + "/users/" + login + "/" + login + ".txt");
        if (file.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String loginLine;
            String passwordLine;

            loginLine = bufferedReader.readLine().trim();
            passwordLine = bufferedReader.readLine().trim();

            if (!loginLine.equals(login) || !passwordLine.equals(password)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }


    public int verifySignUpData(String login,String password) {
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        for (File file : new File(userServiceImpl.getCONTEXTPATH() + "/users/").listFiles()) {
            String fileName = file.getName();
            if (fileName.equals(login)) {
                return 0;
            }
            if (!checkIllegalCharacters(login)) {
                return 1;
            }
            if(password.equals("")){
                return 3;
            }
        }
        return 2;
    }

    private boolean checkIllegalCharacters(String login) {
        boolean result = true;

        String[] arrayOfCharacters = {"\\", "/", ":", ";", "\'", "[", "{", "}", "]", ",", ".", "+", "=", "-", "_", "*", "?", "\"", " ", "<", ">", "|", "!", "@", "#", "$", "%", "^", "&", "(", ")"};
        for (String z : arrayOfCharacters) {
            if (login.contains(z)) {
                result = false;
            }
            if (result == false) {
                break;
            }
        }

        return result;
    }

    public String getCONTEXTPATH() {
        return CONTEXTPATH;
    }
}
