package sample.list;



import java.io.*;

public class UserService {
    private static String CONTEXTPATH="C:\\resources";

    private static UserService USERSERVICE;

    private UserService() {
    }

    public static UserService getInstance(){
        if(USERSERVICE ==null){
            USERSERVICE= new UserService();
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
        File file = new File(CONTEXTPATH +"/users/" + login + "/" + login + ".txt");
        if (file.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String loginLine;
            String passwordLine;

            loginLine = bufferedReader.readLine().trim();
            passwordLine = bufferedReader.readLine().trim();
            System.out.println(loginLine);
            System.out.println(passwordLine);
            if (!loginLine.equals(login) || !passwordLine.equals(password)) {
                System.out.println("Invalid login or password");
                return false;
            }
        } else {
            System.out.println("System cannot find user having this login");
            return false;
        }
        return true;
    }

    public String getCONTEXTPATH() {
        return CONTEXTPATH;
    }
}
