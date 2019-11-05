package sample.list;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListManager {

    public ListOfTasks createNewTaskList(String nameOfList) throws FileNotFoundException {
        File file1 = new File("to_do_project_list_tomcat/lists/");
        if (!file1.exists()) {
            new File("./lists").mkdirs();
        }
        File file = new File("to_do_project_list_tomcat/lists/" + nameOfList + ".txt");
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


    public void editNameOfList(ListOfTasks listOfTasks, String newNameOfList) throws IOException {
        listOfTasks.setName(newNameOfList);
        listOfTasks.getPrintWriter().close();

        FileUtils.moveFile(listOfTasks.getFile(), new File("to_do_project_list_tomcat/lists/" + newNameOfList + ".txt"));
        listOfTasks.setPrintWriter(new PrintWriter(new FileWriter("to_do_project_list_tomcat/lists/" + newNameOfList + ".txt", true)));
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
