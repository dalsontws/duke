package tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }
}
