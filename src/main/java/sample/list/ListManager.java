package sample.list;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface ListManager {

    ListOfTasks createNewTaskList(String login, String nameOfList) throws FileNotFoundException;

    void addTaskToList(ListOfTasks listOfTasks, String task);

    void removeTaskFromListByName(ListOfTasks listOfTasks, String task) throws IOException;

    String findTaskByRowNumber(ListOfTasks listOfTasks, int rowNumber) throws IOException;

    boolean removeList(ListOfTasks listOfTasks, String login);

    void editNameOfList(String login, ListOfTasks listOfTasks, String newNameOfList) throws IOException;

    ListOfTasks findList(String inputNameOfList,String login) throws IOException;

    List<String> getTasks(ListOfTasks listOfTasks) throws IOException;

     void writeWholeListToTxtFile(List mainList, PrintWriter printWriter);


}
