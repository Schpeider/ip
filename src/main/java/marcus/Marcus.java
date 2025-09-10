package marcus;

import marcus.exception.InvalidIndexError;
import marcus.exception.InvalidInputError;
import marcus.exception.MissingDescriptionError;
import marcus.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class Marcus {
    private TaskList taskList = new TaskList();
    private UI ui = new UI();
    private boolean isExit = false;


    /**
     * Generates a welcome message.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Generates a message prompting user input.
     */
    public String requestAction() {
        return ui.requestAction();
    }

    public String run(String userInput) {
        String message;

        try {
            ArrayList<String> parsedCommand = Parser.parseCommand(userInput);
            String command = parsedCommand.get(0);

            switch (command) {
            case "bye":
                message = ui.showGoodbye();
                this.isExit = true;
                break;
            case "list":
                message = ui.showTaskList(taskList);
                break;
            case "mark": {
                int taskIndex = Integer.parseInt(parsedCommand.get(1));
                message = taskList.mark(taskIndex);
                break;
            }
            case "unmark": {
                int taskIndex = Integer.parseInt(parsedCommand.get(1));
                message = taskList.unmark(taskIndex);
                break;
            }
            case "toDo": {
                String taskDescription = parsedCommand.get(1);
                taskList.add(taskDescription);
                message = ui.showTaskAdded(taskList);
                break;
            }
            case "deadline": {
                String taskDescription = parsedCommand.get(1);
                LocalDate taskDeadline = LocalDate.parse(parsedCommand.get(2));
                taskList.add(taskDescription, taskDeadline);
                message = ui.showTaskAdded(taskList);
                break;
            }
            case "event": {
                String taskDescription = parsedCommand.get(1);
                String start = parsedCommand.get(2);
                String end = parsedCommand.get(3);
                taskList.add(taskDescription, start, end);
                message = ui.showTaskAdded(taskList);
                break;
            }
            case "delete":
                int index = Integer.parseInt(parsedCommand.get(1));
                Task deletedTask = taskList.delete(index);
                message = ui.showTaskDeleted(taskList, deletedTask);
                break;
            case "find":
                String keyword = parsedCommand.get(1);
                message = ui.findTask(taskList, keyword);
                break;
            default:
                throw new InvalidInputError();
            }
        } catch (MissingDescriptionError | InvalidInputError | InvalidIndexError e) {
            message = e.getMessage();
        }
        return message;
    }

    public boolean getIsExit() {
        return this.isExit;
    }


}
