package marcus;

import marcus.exception.InvalidIndexError;
import marcus.exception.MissingDescriptionError;
import marcus.task.DeadlineTask;
import marcus.task.EventTask;
import marcus.task.Task;
import marcus.task.ToDoTask;

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

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskListSize() {
        return taskListSize;
    }

    public void updateTask() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskListSize; i++) {
            tasks.append(taskList.get(i).getSaveFileString()).append("\n");
        }
        SaveFileManager.writeToFile(tasks.toString());
    }

    public String mark(int taskIndex) throws InvalidIndexError {
        if (taskIndex <= taskListSize & taskIndex > 0) {
            String message = taskList.get(taskIndex - 1).markComplete();
            this.updateTask();
            return message;
        } else {
            throw(new InvalidIndexError());
        }
    }

    public String unmark(int taskIndex) throws InvalidIndexError {
        if (taskIndex <= taskListSize & taskIndex > 0) {
            String message = taskList.get(taskIndex - 1).unmarkComplete();
            this.updateTask();
            return message;
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
            this.updateTask();
        }
    }

    public void add(String taskDescription, LocalDate taskDeadline) {
        taskList.add(new DeadlineTask(taskDescription, taskDeadline));
        taskListSize++;
        this.updateTask();
    }

    public void add(String taskDescription, String start, String end) {
        taskList.add(new EventTask(taskDescription, start, end));
        taskListSize++;
        this.updateTask();
    }

    public Task delete(int index) throws InvalidIndexError {
        if (index <= taskListSize & index > 0) {
            Task deletedTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            taskListSize--;

            this.updateTask();

            return deletedTask;
        } else {
            throw(new InvalidIndexError());
        }
    }
 }
