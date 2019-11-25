package sample.list;

import org.junit.jupiter.api.Test;
import sample.Log;
import sample.list.ListManager;
import sample.list.ListOfTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListManagerTest {

    ListManager listManager = new ListManager();

    @Test
    void createNewTaskList_shouldReturnObjectOfListOfTasksClass() {

        ListManagerTest testLogging = new ListManagerTest();
        try {
            Log.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem to create log file");
        }

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder","MyList");
            assertEquals(ListOfTasks.class, listOfTasks.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Incorrect path to file");
        }

    }

    @Test
    void addTaskToList_shouldReturnAddedMessage() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder","MyList");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            File file = listOfTasks.getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            assertEquals("TestMessage", line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Incorrect file creating");
        } catch (IOException e) {
            e.printStackTrace();
            fail();             // jaki komunikat
        }
    }

    @Test
    void removeTaskFromList_shouldReturnFalse_removingTaskPlacedOnTheTopOfList() {
        ListOfTasks listOfTasks = null;
        try {
            listOfTasks = listManager.createNewTaskList("testFolder","MyList");
            listManager.addTaskToList(listOfTasks, "TestMessage2");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");

            listManager.removeTaskFromList(listOfTasks, "TestMessage2");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
            List<String> strings = new ArrayList<>();
            String currentLine;
            String trimmedLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                strings.add(trimmedLine);
            }
            assertFalse(strings.contains("TestMessage2"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Incorrect file creating");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }


    }


    @Test
    void removeTaskFromList_shouldReturnFalse_removingTaskPlacedInTheMiddleOfList() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder","MyList");

            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage2");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");

            listManager.removeTaskFromList(listOfTasks, "TestMessage2");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
            List<String> strings = new ArrayList<>();
            String currentLine;
            String trimmedLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                strings.add(trimmedLine);
            }
            assertFalse(strings.contains("TestMessage2"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Incorrect file creating");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void removeTaskFromList_shouldReturnFalse_removingTaskPlacedAtTheBottomOfList() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder","MyList");

            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage2");

            listManager.removeTaskFromList(listOfTasks, "TestMessage2");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
            List<String> strings = new ArrayList<>();
            String currentLine;
            String trimmedLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                strings.add(trimmedLine);
            }
            assertFalse(strings.contains("TestMessage2"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void editNameOfList() {
        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder","MyList");
            try {
                listManager.editNameOfList("testFolder",listOfTasks,"OtherList");
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
            assertEquals("OtherList", listOfTasks.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}