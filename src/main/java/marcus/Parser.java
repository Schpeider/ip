package marcus;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Pattern markStatusPattern = Pattern.compile("^(mark) (\\d+)$");
    private static Pattern unmarkStatusPattern = Pattern.compile("^(unmark) (\\d+)$");
    private static Pattern toDoPattern = Pattern.compile("^(todo)\\s*(.*)$");
    private static String datePattern = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
    private static Pattern deadlinePattern = Pattern.compile("^(deadline) (.+) (/by) (" + datePattern + ")$");
    private static Pattern eventPattern = Pattern.compile("^(event) (.+) (/from) (.+) (/to) (.+)$");
    private static Pattern deletePattern = Pattern.compile("^(delete) (\\d+)$");
    private static Pattern findPattern = Pattern.compile("^(find)\\s*(.*)$");


    /**
     * Parses user input and processes the important information for each command.
     *
     * @param userInput The user's input to the chatbot, Marcus.
     * @return An ArrayList, where the zero index stores type of command, and the remaining store the parsed parameters.
     */
    public static ArrayList<String> parseCommand(String userInput) {
        //matching user input with each of the possible patterns
        Matcher markStatusMatcher = markStatusPattern.matcher(userInput);
        Matcher unmarkStatusMatcher = unmarkStatusPattern.matcher(userInput);
        Matcher toDoMatcher = toDoPattern.matcher(userInput);
        Matcher deadlineMatcher = deadlinePattern.matcher(userInput);
        Matcher eventMatcher = eventPattern.matcher(userInput);
        Matcher deleteMatcher = deletePattern.matcher(userInput);
        Matcher findMatcher = findPattern.matcher(userInput);

        ArrayList<String> ret = new ArrayList<>();

        if (userInput.equals("bye")) {
            ret.add("bye");
        } else if (userInput.equals("list")) {
            ret.add("list");
        } else if (markStatusMatcher.matches()) {
            ret.add("mark");
            ret.add(markStatusMatcher.group(2));
        } else if (unmarkStatusMatcher.matches()) {
            ret.add("unmark");
            ret.add(unmarkStatusMatcher.group(2));
        } else if (toDoMatcher.matches()) {
            ret.add("toDo");
            ret.add(toDoMatcher.group(2));
        } else if (deadlineMatcher.matches()) {
            ret.add("deadline");
            ret.add(deadlineMatcher.group(2));
            ret.add(deadlineMatcher.group(4));
        } else if (eventMatcher.matches()) {
            ret.add("event");
            ret.add(eventMatcher.group(2));
            ret.add(eventMatcher.group(4));
            ret.add(eventMatcher.group(6));
        } else if (deleteMatcher.matches()) {
            ret.add("delete");
            ret.add(deleteMatcher.group(2));
        } else if (findMatcher.matches()) {
            ret.add("find");
            ret.add(findMatcher.group(2));
        } else {
            ret.add("Invalid Command");
        }

        return ret;
    }
}
