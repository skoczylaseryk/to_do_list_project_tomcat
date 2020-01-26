package sample.list.impl;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import sample.Log;
import sample.list.ListManager;
import sample.list.ListOfTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ListManagerImplTest {


    ListManager listManager = ListManagerImpl.getInstance();

    @After
    public void after() {
        File file = new File("C:\\resources\\lists\\testFolder");
        file.mkdirs();
        file.list();
        String[] files = file.list();
        for (String file1 : files) {
            File file3 = new File(file.getPath(), file1);
            file3.delete();

        }
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @Test
    public void createNewTaskList_shouldReturnObjectOfListOfTasksClass() {

        try {
            Log.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem to create log file");
        }
        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
            listOfTasks.getOpenPrintWriter().close();
            assertEquals(ListOfTasks.class, listOfTasks.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Incorrect path to file");

        }
    }

    @Test
    public void addTaskToList_shouldReturnAddedMessage() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            File file = listOfTasks.getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();
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
    public void removeTaskFromList_shouldReturnFalse_removingTaskPlacedOnTheTopOfList() {
        ListOfTasks listOfTasks;
        try {
            listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
            listManager.addTaskToList(listOfTasks, "TestMessage2");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.removeTaskFromListByName(listOfTasks, "TestMessage2");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
            List<String> strings = new ArrayList<>();
            String currentLine;
            String trimmedLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                strings.add(trimmedLine);
            }
            bufferedReader.close();
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
    public void removeTaskFromList_shouldReturnFalse_removingTaskPlacedInTheMiddleOfList() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");

            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage2");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");

            listManager.removeTaskFromListByName(listOfTasks, "TestMessage2");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
            List<String> strings = new ArrayList<>();
            String currentLine;
            String trimmedLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                strings.add(trimmedLine);
            }
            bufferedReader.close();
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
    public void removeTaskFromList_shouldReturnFalse_removingTaskPlacedAtTheBottomOfList() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");

            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage");
            listManager.addTaskToList(listOfTasks, "TestMessage2");

            listManager.removeTaskFromListByName(listOfTasks, "TestMessage2");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
            List<String> strings = new ArrayList<>();
            String currentLine;
            String trimmedLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                strings.add(trimmedLine);
            }
            bufferedReader.close();
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
    public void removeList() {
        ListOfTasks listOfTasks = null;
        try {
            listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        boolean result = listManager.removeList(listOfTasks, "testFolder");
        assertEquals(true, result);
    }

    @Test
    public void editNameOfList() {
        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
            listManager.editNameOfList("testFolder", listOfTasks, "OtherList10");
            assertEquals("OtherList10", listOfTasks.getName());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}