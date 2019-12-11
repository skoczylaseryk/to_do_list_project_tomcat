package sample.list;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListManager {

    private final static Logger LogMe = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static String CONTEXTPATH = "C:\\resources";


    public ListOfTasks createNewTaskList(String login, String nameOfList) throws FileNotFoundException {
        LogMe.setLevel(Level.INFO);
        LogMe.info("Starting method _createNewTaskList_");
        File file1 = new File(CONTEXTPATH + "/lists/" + login + "/");

        if (!file1.exists()) {
            LogMe.info("Creating directory, which isn't exist");
            new File(CONTEXTPATH + "/lists/" + login + "/").mkdirs();
        }
        LogMe.info("Creating file with _" + nameOfList + "_.txt name");
        File file = new File(CONTEXTPATH + "/lists/" + login + "/" + nameOfList + ".txt");
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

        File file = new File(CONTEXTPATH + "\\lists\\" + login + "\\" + listOfTasks.getName() + ".txt");
        return file.delete();
    }


    public void editNameOfList(String login, ListOfTasks listOfTasks, String newNameOfList) throws IOException {
        listOfTasks.setName(newNameOfList);
        listOfTasks.getPrintWriter().close();

        FileUtils.moveFile(listOfTasks.getFile(), new File(CONTEXTPATH + "/lists/" + login + "/" + newNameOfList + ".txt"));
        listOfTasks.setPrintWriter(new PrintWriter(new FileWriter(CONTEXTPATH + "/lists/" + login + "/" + newNameOfList + ".txt", true)));
        listOfTasks.getPrintWriter().print("");

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
