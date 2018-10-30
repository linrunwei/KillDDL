package app.killddl.killddl;
import com.google.firebase.Timestamp;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
public class TaskUnitTest {

    private Tasks tasks;
    private Timestamp current;
    private int ID;
    private String name;
    private String description;
    private int priority;

    @Before
    public void setup() {
        ID = 5;
        current = Timestamp.now();
        tasks = new Tasks(ID, current);

    }

    @Test
    public void getIDTest() {
        assertEquals(ID, tasks.getId());
    }

    @Test
    public void EditIsFinishedTest() {
        tasks.EditIsFinished(true);
        assertEquals(true, tasks.getIsFinished());
    }

    @Test
    public void getNameTest() {
        name = "CSCI310 tests due.";
        tasks.EditName(name);
        assertEquals(name, tasks.getName());
    }

    @Test
    public void getDescriptionTest() {
        description = "Test documents are due Monday Oct.29";
        tasks.EditDescription(description);
        assertEquals(description, tasks.getDescription());
    }

    @Test
    public void getDeadlineTest() {
        assertEquals(current, tasks.getDeadline());
    }

    @Test
    public void getPriorityTest() {
        priority = 2;
        tasks.EditPriority(priority);
        assertEquals(priority, tasks.getPriority());
    }
}

