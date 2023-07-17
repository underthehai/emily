package data;

import java.util.ArrayList;

import exception.EmptyListException;
import exception.TaskNotFoundException;
import task.Task;

public class TaskHandler {

    private Load loader = new Load();
    private ArrayList<Task> arr;

    public TaskHandler() {
        this.arr = loader.loadData();
    }

    public void saveTasks() {
        this.loader.saveData(this.arr);
    }

    public void addItem(Task task) {
        this.arr.add(task);
    }

    public Task findOne(String id) throws TaskNotFoundException {
        if (id.matches("\\d+")) {
            int index = Integer.valueOf(id) - 1;
            if (index < 0 || index >= this.arr.size()) {
                throw new TaskNotFoundException(id);
            }
            return this.arr.get(index);
        } else {
            for (int i = 0; i < this.arr.size(); i++) {
                Task x = this.arr.get(i);
                if (x.getDescription().equals(id)) {
                    return x;
                }
            }
        }
        return null;
    }

    public Task removeItem(String id) throws TaskNotFoundException, EmptyListException {
        if (this.arr.size() == 0) {
            throw new EmptyListException();
        }

        int index = Integer.valueOf(id) - 1;
        if (index < 0 || index >= this.arr.size()) {
            throw new TaskNotFoundException(id);
        }
        Task t = this.arr.get(index);
        this.arr.remove(index);

        return t;

    }

    public Task markDoneTask(String id) throws TaskNotFoundException, EmptyListException {
        if (this.arr.size() == 0) {
            throw new EmptyListException();
        }
        Task t = findOne(id);
        if (t != null) {
            t.markDone();
            return t;
        } else {
            throw new TaskNotFoundException(id);
        }

    }

    public String listItems() {
        if (this.arr.size() == 0) {
            return "There are no items";
        }
        String builder = "";
        for (int i = 1; i <= this.arr.size(); i++) {
            int index = i - 1;
            builder += i + ". " + this.arr.get(index).toString() + "\n";
        }
        return builder;
    }

    public Task[] findTasks(String desc) throws TaskNotFoundException, EmptyListException {
        if (this.arr.size() == 0) {
            throw new EmptyListException();
        }
        ArrayList<Task> found_arr = new ArrayList<>();
        for (int i = 0; i < this.arr.size(); i++) {
            Task temp = this.arr.get(i);
            if (temp.getDescription().contains(desc)) {
                found_arr.add(temp);
            }
        }

        if (found_arr.size() > 0) {
            Task[] arr = new Task[found_arr.size()];
            return found_arr.toArray(arr);
        }

        throw new TaskNotFoundException(desc);
    }
}
