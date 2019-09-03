package task;

import command.Parser;
import command.Ui;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }

    public void printList() {
        int size = this.list.size();
        for (int i = 0; i < size; i++) {
            System.out.print("     " + (i + 1) + ".");
            System.out.println(list.get(i));
        }
    }

    public void getList(String action) {
        int size = this.list.size();
        int index = 1;
        for (int i = 0; i < size; i++) {
            if (list.get(i).toString().contains(action)) {
                System.out.print("     " + (index) + ".");
                System.out.println(list.get(i));
                index += 1;
            }
        }
        if (index == 1) { // Index did not increment, no match found
            ui.noMatch();
        }
    }

    public void setDone(String input) {
        // Assumption: fixed format - remove first 4 characters to get index. i.e. "done"
        String value = input.substring(4);

        // Get integer found in user input
        try {
            int index = Integer.parseInt(value.trim()); // Remove any blank space
            list.get(index - 1).setDone();
            ui.completedTask();
            System.out.println(list.get(index - 1));
        } catch (Exception e) {
            ui.invalidEntry();
        }

    }

    public void deleteTask(String input) {
        // Assumption: fixed format - remove first 6 characters to get index. i.e. "delete"
        String value = input.substring(6);

        // Get integer found in user input
        try {
            int index = Integer.parseInt(value.trim()); // Remove any blank space
            Task delete = list.get(index - 1);
            list.remove(index - 1); //index start from 0
            ui.deleteTask();
            System.out.println(delete);
            int n = this.getSize();
            ui.numTasks(n);
        } catch (Exception e) {
            ui.invalidEntry();
        }
    }

    public void addTask(String action, String input) {

        switch (action) {

        case "todo":
            String description1 = parser.parseDescription(action, input);
            list.add(new ToDo(description1));
            break;

        case "deadline":
            String description2 = parser.parseDescription(action, input);
            list.add(new Deadline(description2, parser.parseDateTime(action, input)));
            break;

        case "event":
            String description3 = parser.parseDescription(action, input);
            list.add(new Event(description3, parser.parseDateTime(action, input)));
            break;

        default:
        }

        int n = this.getSize();
        System.out.println(this.list.get(n - 1));
        ui.numTasks(n);
    }
}
