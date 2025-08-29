package nusyapbot.components;

import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryTest {
    @Test
    public void testGetTaskList() throws IOException {
        String loc = "./data/forTest.txt";
        ArrayList<Task> taskList= new ArrayList<>();
        taskList.add(new ToDo(("text abc")));
        taskList.add(new ToDo(("text def")));
        taskList.add(new ToDo(("text ghi")));

        assertEquals(taskList, Memory.getTaskList(loc));
    }

}
