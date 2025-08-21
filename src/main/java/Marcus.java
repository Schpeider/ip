import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Marcus {
    public static void main(String[] args) {
        //initialise variables
        Task[] taskList = new Task[100];
        int taskListSize = 0;
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

                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the current chapters:");
                    for (int i = 0; i < taskListSize; i++) {
                        System.out.println((i + 1) + ". " + taskList[i]);
                    }
                } else if (markStatusMatcher.matches()) {
                    int taskIndex = Integer.parseInt(markStatusMatcher.group(2));
                    if (taskIndex <= taskListSize & taskIndex > 0) {
                        System.out.println(taskList[taskIndex - 1].markComplete());
                    } else {
                        System.out.println("That chapter does not exist in your story");
                    }
                } else if (unmarkStatusMatcher.matches()) {
                    int taskIndex = Integer.parseInt(unmarkStatusMatcher.group(2));
                    if (taskIndex <= taskListSize & taskIndex > 0) {
                        System.out.println(taskList[taskIndex - 1].unmarkComplete());
                    } else {
                        System.out.println("That chapter does not exist in your story");
                    }
                } else if (toDoMatcher.matches()) {
                    String newTaskDescription = toDoMatcher.group(2);
                    if (newTaskDescription.isEmpty()) {
                        throw new MissingDescriptionError("Correct format: todo <taskDescription>");
                    } else {
                        taskList[taskListSize] = new ToDoTask(newTaskDescription);
                        taskListSize++;
                        System.out.println("A new chapter in your story!");
                        System.out.println("added: " + taskList[taskListSize - 1]);
                        System.out.println("Now you have " + taskListSize + " chapters in your story");
                    }
                } else if (deadlineMatcher.matches()) {
                    taskList[taskListSize] = new DeadlineTask(deadlineMatcher.group(2), deadlineMatcher.group(4));
                    taskListSize++;
                    System.out.println("A new chapter in your story!");
                    System.out.println("added: " + taskList[taskListSize - 1]);
                    System.out.println("Now you have " + taskListSize + " chapters in your story");
                } else if (eventMatcher.matches()) {
                    taskList[taskListSize] = new EventTask(eventMatcher.group(2),
                            eventMatcher.group(4),
                            eventMatcher.group(6));
                    taskListSize++;
                    System.out.println("A new chapter in your story!");
                    System.out.println("added: " + taskList[taskListSize - 1]);
                    System.out.println("Now you have " + taskListSize + " chapters in your story");
                } else {
                    throw new InvalidInputError("I'm sorry, I don't know what you want me to do");
                }
            } catch (MissingDescriptionError e) {
                System.out.println("Description cannot be empty\n" + e.getMessage());
            } catch (InvalidInputError e) {
                System.out.println(e.getMessage());
            }
            System.out.print("\n");
        }
        System.out.println("Mission complete! Was I helpful today?");
    }
}
