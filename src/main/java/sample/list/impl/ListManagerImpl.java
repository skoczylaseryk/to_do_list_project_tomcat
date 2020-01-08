package sample.list.impl;

import org.apache.commons.io.FileUtils;
import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListManagerImpl implements ListManager {

    private final static Logger LogMe = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private UserService userService = UserServiceImpl.getInstance();
    private static ListManagerImpl LISTMANAGER;

    private ListManagerImpl() {
    }

    public static ListManagerImpl getInstance(){
        if(LISTMANAGER==null){
            return new ListManagerImpl();
        }
        return LISTMANAGER;
    }

    public ListOfTasks createNewTaskList(String login, String nameOfList) throws FileNotFoundException {
        LogMe.setLevel(Level.INFO);
        LogMe.info("Starting method _createNewTaskList_");
        File file1 = new File(userService.getCONTEXTPATH() + "/lists/" + login + "/");

        if (!file1.exists()) {
            LogMe.info("Creating directory, which isn't exist");
            new File(userService.getCONTEXTPATH() + "/lists/" + login + "/").mkdirs();
        }
        LogMe.info("Creating file with _" + nameOfList + "_.txt name");
        File file = new File(userService.getCONTEXTPATH() + "/lists/" + login + "/" + nameOfList + ".txt");
        LogMe.info("File _" + nameOfList + "_ has been created");
        PrintWriter printWriter = new PrintWriter(file);
        ListOfTasks list = new ListOfTasks(nameOfList, file, printWriter);
        return list;
    }


    public void addTaskToList(ListOfTasks listOfTasks, String task) {


        PrintWriter printWriter = listOfTasks.getPrintWriter();
        printWriter.println(task);
        printWriter.flush();

    }

    public void removeTaskFromList(ListOfTasks listOfTasks, String task) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));

        List<String> upperSubList = addToUpperList(bufferedReader, task);
        List<String> lowerSubList = addToLowerList(bufferedReader);
        List<String> mainList = mergeLists(upperSubList, lowerSubList);

        PrintWriter printWriter = new PrintWriter(replaceOldFileClearFile(listOfTasks));

        writeWholeListToTxtFile(mainList, printWriter);

    }

    public boolean removeList(ListOfTasks listOfTasks, String login) {

        File file = new File(userService.getCONTEXTPATH() + "\\lists\\" + login + "\\" + listOfTasks.getName() + ".txt");
        return file.delete();
    }


    public void editNameOfList(String login, ListOfTasks listOfTasks, String newNameOfList) throws IOException {
        listOfTasks.setName(newNameOfList);
        listOfTasks.getPrintWriter().close();

        FileUtils.moveFile(listOfTasks.getFile(), new File(userService.getCONTEXTPATH() + "/lists/" + login + "/" + newNameOfList + ".txt"));
        listOfTasks.setPrintWriter(new PrintWriter(new FileWriter(userService.getCONTEXTPATH() + "/lists/" + login + "/" + newNameOfList + ".txt", true)));
        listOfTasks.getPrintWriter().print("");

    }

    @Override
    public ListOfTasks findList(String nameOfList, String login) throws IOException {                  //Todo finish this method and in ADDTaskservlet also
        for (File file : new File(userService.getCONTEXTPATH() + "/lists/" + login + "/").listFiles()) {
            String fileName = file.getName();
            fileName = fileName.substring(0, fileName.length() - 4);
            if (fileName.equals(nameOfList)) {
                ListOfTasks listOfTasks = new ListOfTasks(fileName, file, new PrintWriter(new FileWriter(file, true)));
                 new ListManagerImpl().addTaskToList(listOfTasks, newTaskName);
                return listOfTasks;
            }
        }
        return null;
    }

    @Override
    public List<String> getTasks(ListOfTasks listOfTasks) throws IOException {
       List<String> list = new ArrayList<>();
        String line ;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
        do {
            line = bufferedReader.readLine();
            list.add(line);

        } while (line != null);
        bufferedReader.close();
        list.remove(list.size() - 1); //because bufferedReader adds last line as null

        return list;
    }


    private File replaceOldFileClearFile(ListOfTasks listOfTasks) throws IOException {
        File file = listOfTasks.getFile();
        file.delete();
        file.createNewFile();
        return file;
    }

    private List<String> addToUpperList(BufferedReader bufferedReader, String task) throws IOException {
        List<String> upperSubList = new ArrayList<>();
        String currentLine;
        while (!(currentLine = bufferedReader.readLine()).equals(task)) {
            upperSubList.add(currentLine);
        }

        return upperSubList;
    }

    private List<String> addToLowerList(BufferedReader bufferedReader) throws IOException {
        List<String> lowerSubList = new ArrayList<>();
        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null) {

            lowerSubList.add(currentLine);

        }
        return lowerSubList;
    }

    private List<String> mergeLists(List<String> upperSubList, List<String> lowerSubList) {
        List<String> mainList = new ArrayList<>();

        for (int i = 0; i < upperSubList.size(); i++) {
            mainList.add(upperSubList.get(i));


        }
        for (int i = 0; i < lowerSubList.size(); i++) {
            mainList.add(lowerSubList.get(i));

        }
        return mainList;
    }

    private void writeWholeListToTxtFile(List mainList, PrintWriter printWriter) {
        for (int i = 0; i < mainList.size(); i++) {
            printWriter.write(mainList.get(i) + "\n");
        }
        printWriter.flush();
    }
}