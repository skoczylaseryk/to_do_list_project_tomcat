package sample.list.impl;

import org.apache.commons.io.FileUtils;
import sample.list.ListManager;
import sample.list.ListOfTasks;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListManagerImpl implements ListManager {

    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private UserService userService = UserServiceImpl.getInstance();
    private static ListManagerImpl LISTMANAGER;

    private ListManagerImpl() {
    }

    public static ListManagerImpl getInstance() {
        if (LISTMANAGER == null) {
            return new ListManagerImpl();
        }
        return LISTMANAGER;
    }

    public ListOfTasks createNewTaskList(String login, String nameOfList) throws FileNotFoundException {
        LOG.setLevel(Level.INFO);
        LOG.info("Starting method _createNewTaskList_");
        File file1 = new File(userService.getCONTEXTPATH() + File.separator + "lists" + File.separator + login + File.separator);

        if (!file1.exists()) {
            LOG.info("Creating directory, which isn't exist");
            new File(userService.getCONTEXTPATH() + File.separator + "lists" + File.separator + login + File.separator).mkdirs();
        }
        LOG.info("Creating file with _" + nameOfList + ".txt_ name");
        File file = new File(userService.getCONTEXTPATH() + File.separator + "lists" + File.separator + login + File.separator + nameOfList + ".txt");

        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Set creation time of file, cease Windows sets old previous creation time of file with same name
        try {
            Files.setAttribute(file.toPath(), "creationTime", FileTime.fromMillis(System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.info("File _" + nameOfList + "_ has been created");
        ListOfTasks list = new ListOfTasks(nameOfList, file, false);
        return list;
    }


    public void addTaskToList(ListOfTasks listOfTasks, String task) {
        PrintWriter printWriter = listOfTasks.getOpenPrintWriter();
        printWriter.println(task);
        printWriter.flush();
        printWriter.close();
    }

    public void removeTaskFromListByName(ListOfTasks listOfTasks, String task) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));

        List<String> upperSubList = addToUpperList(bufferedReader, task);
        List<String> lowerSubList = addToLowerList(bufferedReader);
        List<String> mainList = mergeLists(upperSubList, lowerSubList);

        PrintWriter printWriter = new PrintWriter(replaceOldFileClearFile(listOfTasks));

        writeWholeListToTxtFile(mainList, printWriter);
        bufferedReader.close();
    }

    @Override
    public String findTaskByRowNumber(ListOfTasks listOfTasks, int rowNumber) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
        int temp = 0;

        String currentLine;
        String lineToRemove = null;
        while ((currentLine = bufferedReader.readLine()) != null) {

            if (temp == rowNumber) {
                lineToRemove = currentLine;
            }
            temp++;
        }

        return lineToRemove;
    }

    public boolean removeList(ListOfTasks listOfTasks) {

        try {
            FileUtils.forceDelete(listOfTasks.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !listOfTasks.getFile().exists();
    }


    public void editNameOfList(String login, ListOfTasks listOfTasks, String newNameOfList) throws IOException {
        listOfTasks.setName(newNameOfList);
        listOfTasks.getFile().renameTo(new File(listOfTasks.getFile().getParentFile() + File.separator + newNameOfList));

    }

    @Override
    public ListOfTasks findList(String nameOfList, String login) throws IOException {
        for (File file : new File(userService.getCONTEXTPATH() + File.separator + "lists" + File.separator + login + File.separator).listFiles()) {
            String fileName = file.getName();
            fileName = fileName.substring(0, fileName.length() - 4);
            if (fileName.equals(nameOfList)) {
                ListOfTasks listOfTasks = new ListOfTasks(fileName, file, true);

                return listOfTasks;
            }
        }
        return null;
    }

    @Override
    public List<String> getTasks(ListOfTasks listOfTasks) throws IOException {
        List<String> list = new ArrayList<>();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfTasks.getFile()));
        do {
            line = bufferedReader.readLine();
            list.add(line);

        } while (line != null);
        bufferedReader.close();
        list.remove(list.size() - 1);

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
        String currentLine = bufferedReader.readLine();
        while (currentLine != null && !currentLine.equals(task)) {
            upperSubList.add(currentLine);
            currentLine = bufferedReader.readLine();
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

    public void writeWholeListToTxtFile(List mainList, PrintWriter printWriter) {
        for (int i = 0; i < mainList.size(); i++) {
            printWriter.write(mainList.get(i) + "\n");
        }
        printWriter.flush();
        printWriter.close();

    }
}
