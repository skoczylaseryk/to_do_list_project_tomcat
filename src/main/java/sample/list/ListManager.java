package sample.list;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ListManager {

    ListOfTasks createNewTaskList(String login, String nameOfList) throws FileNotFoundException;

    void addTaskToList(ListOfTasks listOfTasks, String task);

    void removeTaskFromList(ListOfTasks listOfTasks, String task) throws IOException;

    boolean removeList(ListOfTasks listOfTasks, String login);

    void editNameOfList(String login, ListOfTasks listOfTasks, String newNameOfList) throws IOException;

    ListOfTasks findList(String inputNameOfList,String login,String newTaskName) throws IOException;

    List<String> getTasks(ListOfTasks listOfTasks) throws IOException;


}
