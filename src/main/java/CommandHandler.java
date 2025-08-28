import java.util.ArrayList;

import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandHandler {
    public static Task createTask(String answer) throws LackingInputException, UnrecognisedCommandException {

        if (answer.startsWith("todo ")) {

            if (answer.length() < 6) {
                throw new LackingInputException("title");
            }

            //create ToDo object
            String taskTitle = answer.substring(5);
            return new ToDo(taskTitle);

        } else if (answer.startsWith("deadline ")) {
            if (answer.length() < 10) {
                throw new LackingInputException("title");
            }
            //create Deadline object
            int indexOfBy = answer.indexOf("/by");
            if (indexOfBy == -1) {
                throw new LackingInputException("deadline");
            }

            int indexOfDeadline = indexOfBy + 4;

            String taskTitle = answer.substring(9, indexOfBy-1);
            String deadline = answer.substring(indexOfDeadline);
            return new Deadline(taskTitle, deadline);

        } else if (answer.startsWith("event ")) {
            if (answer.length() < 7) {
                throw new LackingInputException("title");
            }

            //create Event object
            int indexOfFrom = answer.indexOf("/from");
            if (indexOfFrom == -1) {
                throw new LackingInputException("start time");
            }
            int indexOfStartDate = indexOfFrom + 6;

            int indexOfTo = answer.indexOf("/to");
            if (indexOfTo == -1) {
                throw new LackingInputException("end time");
            }
            int indexOfEndDate = indexOfTo + 4;

            String taskTitle = answer.substring(6, indexOfFrom-1);
            String startDate = answer.substring(indexOfStartDate, indexOfTo);
            String endDate = answer.substring(indexOfEndDate);
            return new Event(taskTitle, startDate, endDate);
        } else {
            //unrecognised command
            throw new UnrecognisedCommandException();
        }
        
    }
    public static void delete(
        ArrayList<Task> taskList, String answer, int pointer) 
        throws LackingInputException, InvalidTaskException {
        String[] parts = answer.split(" ");
        if (parts.length < 2) {
            throw new LackingInputException("task number");
        }

        try {
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task delTask = taskList.get(taskNum);
            taskList.remove(taskNum);

            System.out.println("_________________________________" + "\n" +
                    "Noted. I've removed this task:\n" +
                    delTask + "\n" +
                    "Now you have " + (pointer - 1) + " tasks in the list.\n" +
                    "_________________________________");

        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }
        public static void markTask(
            ArrayList<Task> taskList, String answer, boolean status, String loc) 
            throws LackingInputException, InvalidTaskException, IOException, FileNotFoundException {
            String[] parts = answer.split(" ");
            if (parts.length < 2) {
                throw new LackingInputException("task number");
            }

            try {
                int taskNum = Integer.parseInt(parts[1]) - 1;
                taskList.get(taskNum).setIsCompleted(status);
                String message = status
                        ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:";

                System.out.println( "_________________________________" + "\n" +
                        message + "\n" +
                        taskList.get(taskNum) + "\n" +
                        "_________________________________");

                //update in hard disk
                RandomAccessFile raf = new RandomAccessFile(loc, "rw");

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


            } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                throw new InvalidTaskException();
            } 

    }



}
