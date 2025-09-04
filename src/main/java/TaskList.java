import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int taskListSize = 0;

    public TaskList() {
        SaveFileManager.init();
        try {
            taskList = SaveFileManager.readFromFile();
            taskListSize = taskList.size();
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found");
            taskList = new ArrayList<>();
        }
    }

    public void updateTask() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskListSize; i++) {
            tasks.append(taskList.get(i).getSaveFileString()).append("\n");
        }
        SaveFileManager.writeToFile(tasks.toString());
    }

    public void listTasks() {
        if (taskListSize > 0) {
            System.out.println("Here are the current chapters:");
            for (int i = 0; i < taskListSize; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        } else {
            System.out.println("Your story has no chapters currently");
        }
    }

    public void mark(int taskIndex) throws InvalidIndexError {
        if (taskIndex <= taskListSize & taskIndex > 0) {
            System.out.println(taskList.get(taskIndex - 1).markComplete());
            this.updateTask();
        } else {
            throw(new InvalidIndexError());
        }
    }

    public void unmark(int taskIndex) throws InvalidIndexError {
        if (taskIndex <= taskListSize & taskIndex > 0) {
            System.out.println(taskList.get(taskIndex - 1).unmarkComplete());
            this.updateTask();
        } else {
            throw(new InvalidIndexError());
        }
    }

    public void add(String taskDescription) throws MissingDescriptionError {
        if (taskDescription.isEmpty()) {
            throw new MissingDescriptionError("Correct format: todo <taskDescription>");
        } else {
            taskList.add(new ToDoTask(taskDescription));
            taskListSize++;
            System.out.println("A new chapter in your story!");
            System.out.println("added: " + taskList.get(taskListSize - 1));
            System.out.println("Now you have " + taskListSize + " chapters in your story");
            this.updateTask();
        }
    }

    public void add(String taskDescription, LocalDate taskDeadline) {
        taskList.add(new DeadlineTask(taskDescription, taskDeadline));
        taskListSize++;
        System.out.println("A new chapter in your story!");
        System.out.println("added: " + taskList.get(taskListSize - 1));
        System.out.println("Now you have " + taskListSize + " chapters in your story");
        this.updateTask();
    }

    public void add(String taskDescription, String start, String end) {
        taskList.add(new EventTask(taskDescription, start, end));
        taskListSize++;
        System.out.println("A new chapter in your story!");
        System.out.println("added: " + taskList.get(taskListSize - 1));
        System.out.println("Now you have " + taskListSize + " chapters in your story");
        this.updateTask();
    }

    public void delete(int index) throws InvalidIndexError {
        if (index <= taskListSize & index > 0) {
            Task removedTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            taskListSize--;

            System.out.println("The following chapter has been removed from your story:");
            System.out.println(removedTask);
            System.out.println("Now you have " + taskListSize + " chapters in your story");
            this.updateTask();
        } else {
            throw(new InvalidIndexError());
        }
    }
 }
