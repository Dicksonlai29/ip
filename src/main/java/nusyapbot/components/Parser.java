package nusyapbot.components;

import nusyapbot.command.*;
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.exceptions.UnrecognisedCommandException;

import java.time.LocalDateTime;

/**
 * Interprets user input strings and converting them into 
 * corresponding {@link Command} objects for execution.
 * <p>
 * It supports parsing commands such as {@code list}, {@code bye}, {@code todo},
 * {@code deadline}, {@code event}, {@code mark}, {@code unmark}, {@code delete},
 * {@code find}, and {@code sort}. Each command may require specific parameters,
 * which are extracted and validated within dedicated parsing methods.
 * <p>
 * If the input does not match any recognized command or lacks required parameters,
 * appropriate custom exceptions are thrown to signal errors.
 */
public class Parser {
    public static Command parse(String answer) throws NUSYapBotException {
        String[] parts = answer.split(" ", 2);
        String commandKeyword = parts[0];
        String paramInfo = (parts.length > 1) ? parts[1].trim() : "";

        // the structure below is inspired by @pei886
        return switch (commandKeyword) {
            case "list"     -> new ListCommand();
            case "bye"      -> new ByeCommand();
            case "todo"     -> parseToDo(paramInfo);
            case "deadline" -> parseDeadline(paramInfo);
            case "event"    -> parseEvent(paramInfo);
            case "mark"     -> new MarkTaskCommand(paramInfo, true);
            case "unmark"   -> new MarkTaskCommand(paramInfo, false);
            case "delete"   -> new DeleteCommand(paramInfo);
            case "find"     -> new FindCommand(paramInfo);
            case "sort"     -> parseSort(paramInfo);
            default         -> throw new UnrecognisedCommandException();
        };
    }

    /**
     * Parses the given parameter information to create a {@link ToDoCommand}.
     * <p>
     * Throws a {@link LackingInputException} if the parameter information is blank,
     * indicating that the title is missing.
     *
     * @param paramInfo the parameter information containing the title for the ToDo command
     * @return a {@link ToDoCommand} initialized with the given parameter information
     * @throws NUSYapBotException if the input is invalid or missing
     */
    private static ToDoCommand parseToDo(String paramInfo)
            throws NUSYapBotException {
        if (paramInfo.isBlank()) {
            throw new LackingInputException("title");
        }

        return new ToDoCommand(paramInfo);

    }

    /**
     * Parses the input string to create a {@link DeadlineCommand} object.
     * The input should contain a task title and a deadline, separated by "/by".
     *
     * @param paramInfo the input string containing the task title and deadline
     * @return a {@link DeadlineCommand} with the parsed title and deadline
     * @throws NUSYapBotException if the input is missing the title or deadline, or if the deadline format is invalid
     */
    private static DeadlineCommand parseDeadline(String paramInfo)
            throws NUSYapBotException {
        String[] parts = paramInfo.split("/by",2);
        if (parts.length < 2) {
            throw new LackingInputException("title or deadline");
        }
        String title = parts[0].trim();
        String deadline = parts[1].trim();
        LocalDateTime date = DateHandler.saveAsDateTime(deadline);

        return new DeadlineCommand(title, date);

    }

     /**
     * Parses the input string to create a {@link EventCommand} object.
     * The input should contain a task title, start time and end time,
     * separated by "/start" and "/end".
     *
     * @param paramInfo the string containing event details in the specified format
     * @return an {@link EventCommand} object with the parsed title, start time, and end time
     * @throws NUSYapBotException if the input is missing required fields or date parsing fails
     */
    private static EventCommand parseEvent(
            String paramInfo) throws NUSYapBotException {
        String[] parts = paramInfo.split("/from",2);
        if (parts.length < 2) {
            throw new LackingInputException("title or startTime or endTime");
        }

        String title = parts[0].trim();
        String[] dates = parts[1].split("/to",2);
        if (dates.length < 2) {
            throw new LackingInputException("endTime");
        }
        String start = dates[0].trim();
        LocalDateTime startDate = DateHandler.saveAsDateTime(start);
        String end = dates[1].trim();
        LocalDateTime endDate = DateHandler.saveAsDateTime(end);

        return new EventCommand(title, startDate, endDate);

    }

    /**
     * Parses the input string to create a {@link SortCommand} object.
     * The input should specify the field to sort by, optionally followed by the sorting order ("asc" or "desc").
     * If the sorting order is not specified, it defaults to ascending order.
     *
     * @param paramInfo the input string containing the field and optional sorting order
     * @return a {@link SortCommand} representing the sorting criteria
     * @throws NUSYapBotException if the input is blank, or if the sorting order is not "asc" or "desc"
     * @throws LackingInputException if the field to sort by is missing
     */
    private static SortCommand parseSort(String paramInfo) 
            throws NUSYapBotException {
        String[] parts = paramInfo.split(" ",2);

        if (paramInfo.isBlank()) {
            throw new LackingInputException("field (title/status/type)");
        }

        String field = parts[0];
        //default set to ascending
        boolean isAscending = true;

        if (parts.length == 2) {
            if (parts[1].equals("asc")) {
                isAscending = true;
            } else if (parts[1].equals("desc")) {
                isAscending = false;
            } else {
                throw new NUSYapBotException("Only asc/desc accepted for sorting order.");
            }
        }

        return new SortCommand(field, isAscending);
    }
}
