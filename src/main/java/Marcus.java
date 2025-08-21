import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Marcus {
    public static void main(String[] args) {
        //initialise variables
        ArrayList<Task> taskList = new ArrayList<>();
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
                    if (taskListSize > 0) {
                        System.out.println("Here are the current chapters:");
                        for (int i = 0; i < taskListSize; i++) {
                            System.out.println((i + 1) + ". " + taskList.get(i));
                        }
                    } else {
                        System.out.println("Your story has no chapters currently");
                    }
                } else if (markStatusMatcher.matches()) {
                    int taskIndex = Integer.parseInt(markStatusMatcher.group(2));
                    if (taskIndex <= taskListSize & taskIndex > 0) {
                        System.out.println(taskList.get(taskIndex - 1).markComplete());
                    } else {
                        System.out.println("That chapter does not exist in your story");
                    }
                } else if (unmarkStatusMatcher.matches()) {
                    int taskIndex = Integer.parseInt(unmarkStatusMatcher.group(2));
                    if (taskIndex <= taskListSize & taskIndex > 0) {
                        System.out.println(taskList.get(taskIndex - 1).unmarkComplete());
                    } else {
                        System.out.println("That chapter does not exist in your story");
                    }
                } else if (toDoMatcher.matches()) {
                    String newTaskDescription = toDoMatcher.group(2);
                    if (newTaskDescription.isEmpty()) {
                        throw new MissingDescriptionError("Correct format: todo <taskDescription>");
                    } else {
                        taskList.add(new ToDoTask(newTaskDescription));
                        taskListSize++;
                        System.out.println("A new chapter in your story!");
                        System.out.println("added: " + taskList.get(taskListSize - 1));
                        System.out.println("Now you have " + taskListSize + " chapters in your story");
                    }
                } else if (deadlineMatcher.matches()) {
                    taskList.add(new DeadlineTask(deadlineMatcher.group(2), deadlineMatcher.group(4)));
                    taskListSize++;
                    System.out.println("A new chapter in your story!");
                    System.out.println("added: " + taskList.get(taskListSize - 1));
                    System.out.println("Now you have " + taskListSize + " chapters in your story");
                } else if (eventMatcher.matches()) {
                    taskList.add(new EventTask(eventMatcher.group(2), eventMatcher.group(4), eventMatcher.group(6)));
                    taskListSize++;
                    System.out.println("A new chapter in your story!");
                    System.out.println("added: " + taskList.get(taskListSize - 1));
                    System.out.println("Now you have " + taskListSize + " chapters in your story");
                } else if (deleteMatcher.matches()) {
                    int index = Integer.parseInt(deleteMatcher.group(2));
                    if (index <= taskListSize & index > 0) {
                        Task removedTask = taskList.get(index - 1);
                        taskList.remove(index - 1);
                        taskListSize--;

                        System.out.println("The following chapter has been removed from your story:");
                        System.out.println(removedTask);
                        System.out.println("Now you have " + taskListSize + " chapters in your story");
                    } else {
                        System.out.println("This chapter does not exist in your story");
                    }
                } else {
                    throw new InvalidInputError();
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
