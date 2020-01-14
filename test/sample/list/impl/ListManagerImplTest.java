package sample.list.impl;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sample.Log;
import sample.list.ListManager;
import sample.list.ListOfTasks;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class listManagerTest {

    ListManager listManager = ListManagerImpl.getInstance();
    Path path = Paths.get("C:\\resources\\lists\\testFolder");

    @AfterEach
    void deleteDirectory() {
        File file = new File("C:\\resources\\lists\\testFolder");
        file.mkdirs();
        file.list();
        String[] files = file.list();
        for (String file1 : files) {
            File file3 = new File(file.getPath(),file1);
            System.out.println(file3.delete());
            
        }
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("niedziala");
        }


//        System.out.println(file.delete());
    }


    @Test
    void createNewTaskList_shouldReturnObjectOfListOfTasksClass() {

        try {
            Log.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem to create log file");
        }
        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
            listOfTasks.getPrintWriter().close();
            assertEquals(ListOfTasks.class, listOfTasks.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Incorrect path to file");

        }
    }
    @Test
    void addTaskToList_shouldReturnAddedMessage() {

        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
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
    void removeList() {

        ListOfTasks listOfTasks = null;
        try {
            listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        listOfTasks.getPrintWriter().close();
        boolean result = listManager.removeList(listOfTasks, "testFolder6");
        assertEquals(true, result);


    }

    @Test
    void editNameOfList() {
        try {
            ListOfTasks listOfTasks = listManager.createNewTaskList("testFolder", "MyList");
            try {
                listManager.editNameOfList("testFolder", listOfTasks, "OtherList10");
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
            assertEquals("OtherList10", listOfTasks.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}