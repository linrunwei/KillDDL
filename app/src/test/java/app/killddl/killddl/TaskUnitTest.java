package app.killddl.killddl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TaskUnitTest {

    private Tasks tasks;
    private Timestamp deadline;
    private Timestamp notification;
    private int ID;
    private String name;
    private String description;
    private int priority;
    private int color;
    private int frequency;

    @Before
    public void setup() {
        ID = 5;
        deadline = Timestamp.now();
        tasks = new Tasks(ID, deadline);

    }

    @Test
    public void getIDTest() {
        assertEquals(ID, tasks.getId());
    }

    @Test
    public void getIsFinishedTest() {
        tasks.EditIsFinished(true);
        assertEquals(true, tasks.getIsFinished());
        tasks.EditIsFinished(false);
        assertEquals(false, tasks.getIsFinished());
        tasks.EditIsFinished(true);
        assertEquals(true, tasks.getIsFinished());
    }

    @Test
    public void getNameTest() {
        name = "CSCI310 tests due.";
        tasks.EditName(name);
        assertEquals(name, tasks.getName());
        name = "KillDDL group meeting";
        tasks.EditName(name);
        assertEquals(name, tasks.getName());
        name = "CSCI310 tests due.";
        tasks.EditName(name);
        assertEquals(name, tasks.getName());
    }

    @Test
    public void getDescriptionTest() {
        description = "Test documents are due Monday Oct.29";
        tasks.EditDescription(description);
        assertEquals(description, tasks.getDescription());
        description = "Group meeting at 5pm Monday in SAL";
        tasks.EditDescription(description);
        assertEquals(description, tasks.getDescription());
        description = "Test documents are due Monday Oct.29";
        tasks.EditDescription(description);
        assertEquals(description, tasks.getDescription());
    }

    @Test
    public void getDeadlineTest() {
        assertEquals(deadline, tasks.getDeadline());
        deadline = Timestamp.now();
        tasks.EditDeadline(deadline);
        assertEquals(deadline, tasks.getDeadline());
    }

    @Test
    public void getPriorityTest() {
        priority = 2;
        tasks.EditPriority(priority);
        assertEquals(priority, tasks.getPriority());
        priority = 1;
        tasks.EditPriority(priority);
        assertEquals(priority, tasks.getPriority());
        priority = 2;
        tasks.EditPriority(priority);
        assertEquals(priority, tasks.getPriority());
    }

    @Test
    public void getNotificationTest() {
        notification = Timestamp.now();
        tasks.EditNotification(notification);
        assertEquals(notification, tasks.getNotification());
        notification = Timestamp.now();
        tasks.EditNotification(notification);
        assertEquals(notification, tasks.getNotification());
    }

    @Test
    public void getColorTest() {
        color = 3;
        tasks.EditColor(color);
        assertEquals(color, tasks.getColor());
        color = 1;
        tasks.EditColor(color);
        assertEquals(color, tasks.getColor());
    }

    @Test
    public void getFrequencyTest() {
        frequency = 1;
        tasks.EditFrequency(frequency);
        assertEquals(frequency, tasks.getFrequency());
        frequency = 2;
        tasks.EditFrequency(frequency);
        assertEquals(frequency, tasks.getFrequency());
        frequency = 3;
        tasks.EditFrequency(frequency);
        assertEquals(frequency, tasks.getFrequency());
    }

}
