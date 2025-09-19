package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.InvalidTaskException;
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Command to mark a task as completed or not.
 * When executed, it updates both the in-memory list and persistent storage,
 * then return a message to user if successful, else, throw an error.
 */
public class MarkTaskCommand extends Command{
    private String taskNumber;
    private boolean status;

    public MarkTaskCommand(String taskNumber, boolean status) {
        super(false);
        this.taskNumber = taskNumber;
        this.status = status;
    }

    @Override
    public String execute(ArrayList<Task> taskList, Memory memory)
            throws NUSYapBotException, IOException {
        if (taskNumber.isBlank()) {
            throw new LackingInputException("task number");
        }

        try {
            int taskNum = Integer.parseInt(taskNumber) - 1;
            taskList.get(taskNum).setIsCompleted(status);
            String message = status
                    ? "Nice! I've marked this task as done:"
                    : "OK, I've marked this task as not done yet:";

            //update in hard disk
            RandomAccessFile raf = new RandomAccessFile(memory.getStorageLocation(), "rw");

            //traverse to the line before the targeted line
            for (int i = 0; i < taskNum; i++) {
                String line = raf.readLine();
            }

            // record the position of start of that line
            long pointer = raf.getFilePointer();
            // read the targeted line fully
            String line = raf.readLine();
            String[] detail = line.split(" \\| ");

            // Update the completion status
            detail[1] = status ? "T" : "F";

            // Reconstruct line
            String newLine = String.join(" | ", detail);

            // Move back to that line and overwrite
            raf.seek(pointer);
            raf.writeBytes(newLine);

            raf.close();

            return message + "\n" + taskList.get(taskNum);


        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }
}
