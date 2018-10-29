package app.killddl.killddl;
import com.google.firebase.Timestamp;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DbUnitTest {
    @Test
    public void getListByTime_isCorrect() throws ParseException {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date one = simFormat.parse("2008.10.01 22:45:56");
        Date two = simFormat.parse("2009.01.23 22:45:56");
        Date three = simFormat.parse("1997.09.10 22:45:56");
        Date four = simFormat.parse("2018.01.23 22:45:56");
        Date five = simFormat.parse("1945.08.15 22:45:56");

        Timestamp a = new Timestamp(one);
        Timestamp b = new Timestamp(two);
        Timestamp c = new Timestamp(three);
        Timestamp d = new Timestamp(four);
        Timestamp e = new Timestamp(five);


        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(new Tasks(0, a));
        tasksList.add(new Tasks(1, a));
        tasksList.add(new Tasks(2, a));
        tasksList.add(new Tasks(3, a));
        tasksList.add(new Tasks(4, b));
        tasksList.add(new Tasks(5, b));
        tasksList.add(new Tasks(6, c));
        tasksList.add(new Tasks(7, d));
        tasksList.add(new Tasks(8, d));
        tasksList.add(new Tasks(9, d));
        Db database = new Db("ZTWmtHyGR1SxFnttlRNBUkii09C3", tasksList);
        database.setTaskList(tasksList);


        assertEquals(4, database.getTaskListByTime(a).size());
        assertEquals(2, database.getTaskListByTime(b).size());
        assertEquals(1, database.getTaskListByTime(c).size());
        assertEquals(3, database.getTaskListByTime(d).size());
        assertEquals(0, database.getTaskListByTime(e).size());
    }
}
