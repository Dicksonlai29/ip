package nusyapbot.components;

import nusyapbot.command.*;
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.exceptions.UnrecognisedCommandException;

import java.time.LocalDateTime;

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

    private static ToDoCommand parseToDo(String paramInfo)
            throws NUSYapBotException {
        if (paramInfo.isBlank()) {
            throw new LackingInputException("title");
        }

        return new ToDoCommand(paramInfo);

    }

    private static DeadlineCommand parseDeadline(String paramInfo)
            throws NUSYapBotException {
        String[] parts = paramInfo.split("/by",2);
        if (parts.length < 2) {
            throw new LackingInputException("title or deadline");
        }
        String title = parts[0];
        String deadline = parts[1].trim();
        LocalDateTime date = DateHandler.saveAsDateTime(deadline);

        return new DeadlineCommand(title, date);

    }

    private static EventCommand parseEvent(String paramInfo)
            throws NUSYapBotException {
        String[] parts = paramInfo.split("/from",2);
        if (parts.length < 2) {
            throw new LackingInputException("title or startTime or endTime");
        }

        String title = parts[0];
        String[] dates = parts[1].split("/to",2);
        String start = dates[0].trim();
        LocalDateTime startDate = DateHandler.saveAsDateTime(start);
        String end = dates[1].trim();
        LocalDateTime endDate = DateHandler.saveAsDateTime(end);

        return new EventCommand(title, startDate, endDate);

    }

    private static SortCommand parseSort(String paramInfo) 
            throws NUSYapBotException {
        String[] parts = paramInfo.split(" ",2);

        if (parts.length < 1) {
            throw new LackingInputException("field (title/status/type)");
        }

        String title = parts[0];
        //default set to ascending
        boolean isAscending = true;

        if (parts.length == 2) {
            if (parts[2].equals("asc")) {
                isAscending = true;
            } else if (parts[2].equals("desc")) {
                isAscending = false;
            } else {
                throw new NUSYapBotException("Only asc/desc accepted for sorting order.");
            }
        }

        return new SortCommand(title, isAscending);


    }
}
