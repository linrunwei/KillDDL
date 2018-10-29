package app.killddl.killddl;

import com.google.firebase.Timestamp;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserUnitTest {
    @Test
    public void AddUser_ConstructorCorrect() {
        User test = new User("wang626@usc.edu");

        String expected = "wang626@usc.edu";
        String actual = test.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void AddTaskToUser_TaskIdCorrect() {
        User test = new User("wang626@usc.edu");
        test.taskList.add(new Tasks(0, Timestamp.now()));

        int expected = 0;
        int actual = test.taskList.get(0).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void AddTaskToUser_TimestampCorrect() {
        User test = new User("wang626@usc.edu");
        Timestamp deadline = Timestamp.now();
        test.taskList.add(new Tasks(1, deadline));

        Timestamp expected = deadline;
        Timestamp actual = test.taskList.get(0).getDeadline();
        assertEquals(expected, actual);
    }
}
