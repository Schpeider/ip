import java.time.LocalDate;
import java.util.ArrayList;

public class Marcus {
    public static void main(String[] args) {
        //initialise variables
        TaskList taskList = new TaskList();
        UI ui = new UI();

        ui.showWelcome();


        label:
        while (true) {
            try {
                ui.requestAction();
                String userInput = ui.getUserInput(); //to remove whitespace if accidentally added by user
                ArrayList<String> parsedCommand = Parser.parseCommand(userInput);
                String command = parsedCommand.get(0);

                switch (command) {
                case "bye":
                    break label;
                case "list":
                    ui.showTaskList(taskList);
                    break;
                case "mark": {
                    int taskIndex = Integer.parseInt(parsedCommand.get(1));
                    String message = taskList.mark(taskIndex);
                    ui.showMessage(message);
                    break;
                }
                case "unmark": {
                    int taskIndex = Integer.parseInt(parsedCommand.get(1));
                    String message = taskList.unmark(taskIndex);
                    ui.showMessage(message);
                    break;
                }
                case "toDo": {
                    String taskDescription = parsedCommand.get(1);
                    taskList.add(taskDescription);
                    ui.showTaskAdded(taskList);
                    break;
                }
                case "deadline": {
                    String taskDescription = parsedCommand.get(1);
                    LocalDate taskDeadline = LocalDate.parse(parsedCommand.get(2));
                    taskList.add(taskDescription, taskDeadline);
                    ui.showTaskAdded(taskList);
                    break;
                }
                case "event": {
                    String taskDescription = parsedCommand.get(1);
                    String start = parsedCommand.get(2);
                    String end = parsedCommand.get(3);
                    taskList.add(taskDescription, start, end);
                    ui.showTaskAdded(taskList);
                    break;
                }
                case "delete":
                    int index = Integer.parseInt(parsedCommand.get(1));
                    Task deletedTask = taskList.delete(index);
                    ui.showTaskDeleted(taskList, deletedTask);
                    break;
                default:
                    throw new InvalidInputError();
                }
            } catch (MissingDescriptionError | InvalidInputError | InvalidIndexError e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
}
