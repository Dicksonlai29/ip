package nusyapbot.components;

import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemoryTest {
    @Test
    public void testGetTaskList() throws IOException {
        String loc = "./data/forTest.txt";
        Memory memory = new Memory(loc);
        ArrayList<Task> taskList= new ArrayList<>();
        taskList.add(new ToDo(("text abc")));
        taskList.add(new ToDo(("text def")));
        taskList.add(new ToDo(("text ghi")));

        assertEquals(taskList.toString(),
                memory.getTaskList().toString());
    }

    @Test
    void testGetTaskList_emptyFile() throws IOException {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("memoryTest", ".txt");
        tempFile.deleteOnExit();
        Memory memory = new Memory(tempFile.getAbsolutePath());

        ArrayList<Task> taskList = memory.getTaskList();
        assertTrue(taskList.isEmpty());
    }

    @Test
    void testGetStorageLocation() throws IOException {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("memoryTest", ".txt");
        tempFile.deleteOnExit();
        Memory memory = new Memory(tempFile.getAbsolutePath());

        assertEquals(tempFile.getAbsolutePath(), memory.getStorageLocation());
    }

}
