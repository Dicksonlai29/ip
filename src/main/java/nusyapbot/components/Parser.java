package nusyapbot.components;

import nusyapbot.command.*;
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.exceptions.UnrecognisedCommandException;
import nusyapbot.tasktype.Deadline;
import nusyapbot.tasktype.ToDo;

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
            case "mark"     -> new markTaskCommand();
            case "unmark"   -> new unmarkTaskCommand();
            case "delete"   -> new deleteCommand();
            case "find"     -> new findCommand();
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
        String deadline = parts[1];
        LocalDateTime date = DateHandler.saveAsDateTime(deadline);

        return new DeadlineCommand(title, date);

    }

    private static EventCommand parseEvent(String paramInfo)
            throws NUSYapBotException {
        String[] parts = paramInfo.split("/",3);
        if (parts.length < 3) {
            throw new LackingInputException("title or startTime or endTime");
        }

        String title = parts[0];
        String start = parts[1];
        LocalDateTime startDate = DateHandler.saveAsDateTime(start);
        String end = parts[2];
        LocalDateTime endDate = DateHandler.saveAsDateTime(end);

        return new EventCommand(paramInfo, startDate, endDate);

    }
}
