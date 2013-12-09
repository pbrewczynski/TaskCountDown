package pl.twigit.timertry;

/**
 * Created by pawel on 08/12/13.
 */
public class Task {
    public Task(String taskName, long endTime) {
        this.taskName = taskName;
        this.endTime  = endTime;
    }
    public String taskName;
    public long   endTime;
}
