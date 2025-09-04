import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Marcus {
    public static void main(String[] args) {
        //initialise variables
        TaskList taskList = new TaskList();
        String userInput;
        Scanner reader = new Scanner(System.in);


        //Greeting Text
        System.out.println("Greetings, Marcus here!");
        System.out.println("Reading helps quiet the world around me, turning challenges into words on a page");
        System.out.println("I think of each task as a chapter, one step at a time, each with its own meaning, "
                            + "each shaping the story I live.");
        System.out.println("I hope this way of thinking will help you face your challenges more easily too!\n");

        //regex for markStatus, to identify if user input matches this style
        Pattern markStatusPattern = Pattern.compile("^(mark) (\\d+)$");

        //regex for unmarkStatus, to identify if user input matches this style
        Pattern unmarkStatusPattern = Pattern.compile("^(unmark) (\\d+)$");

        //regex for ToDos, to identify if user input matches this style
        Pattern toDoPattern = Pattern.compile("^(todo)\\s*(.*)$");

        //regex for Deadlines, to identify if user input matches this style
        Pattern deadlinePattern = Pattern.compile("^(deadline) (.+) (/by) (.+)$");

        //regex for Events, to identify if user input matches this style
        Pattern eventPattern = Pattern.compile("^(event) (.+) (/from) (.+) (/to) (.+)$");

        //regex for delete, to identify if user input matches this style
        Pattern deletePattern = Pattern.compile("^(delete) (\\d+)$");

        while (true) {
            try {
                System.out.print("What can I do for you today?:\n");
                userInput = reader.nextLine().trim(); //to remove whitespace if accidentally added by user

                //matching user input with each of the possible patterns
                Matcher markStatusMatcher = markStatusPattern.matcher(userInput);
                Matcher unmarkStatusMatcher = unmarkStatusPattern.matcher(userInput);
                Matcher toDoMatcher = toDoPattern.matcher(userInput);
                Matcher deadlineMatcher = deadlinePattern.matcher(userInput);
                Matcher eventMatcher = eventPattern.matcher(userInput);
                Matcher deleteMatcher = deletePattern.matcher(userInput);

                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    taskList.listTasks();
                } else if (markStatusMatcher.matches()) {
                    int taskIndex = Integer.parseInt(markStatusMatcher.group(2));
                    taskList.mark(taskIndex);
                } else if (unmarkStatusMatcher.matches()) {
                    int taskIndex = Integer.parseInt(unmarkStatusMatcher.group(2));
                    taskList.unmark(taskIndex);
                } else if (toDoMatcher.matches()) {
                    String taskDescription = toDoMatcher.group(2);
                    taskList.add(taskDescription);
                } else if (deadlineMatcher.matches()) {
                    String taskDescription = deadlineMatcher.group(2);
                    String taskDeadline = deadlineMatcher.group(4);
                    taskList.add(taskDescription, taskDeadline);
                } else if (eventMatcher.matches()) {
                    String taskDescription = eventMatcher.group(2);
                    String start = eventMatcher.group(4);
                    String end = eventMatcher.group(6);
                    taskList.add(taskDescription, start, end);
                } else if (deleteMatcher.matches()) {
                    int index = Integer.parseInt(deleteMatcher.group(2));
                    taskList.delete(index);
                } else {
                    throw new InvalidInputError();
                }
            } catch (MissingDescriptionError e) {
                System.out.println("Description cannot be empty\n" + e.getMessage());
            } catch (InvalidInputError | InvalidIndexError e) {
                System.out.println(e.getMessage());
            }
            System.out.print("\n");
        }
        System.out.println("Mission complete! Was I helpful today?");
    }
}
