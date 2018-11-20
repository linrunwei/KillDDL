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
    public void updateUser_isCorrect() throws ParseException  {
        List<Tasks> list = new ArrayList<>();
        String uid = "OVhQAIElVtcQMFXu6dn7O43ZI593";
        Db database = new Db(uid, list);
        User user = new User("abcd@usc.edu", 2);
        database.setUser(user);
        User newUser = new User("abcd@usc.edu", 4);
        assertEquals(4, database.getUser().getAvatar());
    }
    @Test
    public void getTaskList_isCorrect() throws ParseException  {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date one = simFormat.parse("2008.10.01 22:45:56");
        Timestamp a = new Timestamp(one);
        List<Tasks> list = new ArrayList<>();
        list.add(new Tasks(0, a));
        list.add(new Tasks(1, a));
        Db database = new Db("ZTWmtHyGR1SxFnttlRNBUkii09C3", list);
        assertEquals(2, database.getTaskList().size());
    }

    @Test
    public void addTask_isCorrect() throws ParseException {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date one = simFormat.parse("2018.01.23 22:45:56");
        Date two = simFormat.parse("2008.01.23 22:45:56");
        Date three = simFormat.parse("1998.01.23 22:45:56");

        Db database1 = new Db("ZTWmtHyGR1SxFnttlRNBUkii09C3", new ArrayList<Tasks>());
        database1.addTask(new Tasks(0, new Timestamp(one)));
        database1.addTask(new Tasks(1, new Timestamp(two)));

        Db database2 = new Db("BkYJZIdnLfcl10ANxrGyaKYQp4x1", new ArrayList<Tasks>());
        database2.addTask(new Tasks(0, new Timestamp(three)));

        assertEquals(2, database1.getTaskList().size());
        assertEquals(1, database2.getTaskList().size());


    }
    @Test
    public void removeTask_isCorrect() throws ParseException {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date one = simFormat.parse("2008.10.01 22:45:56");
        Date two = simFormat.parse("2009.01.23 22:45:56");

        Timestamp first = new Timestamp(one);
        Timestamp second = new Timestamp(two);

        Tasks a = new Tasks(0, first);
        Tasks b = new Tasks(1, first);
        Tasks c = new Tasks(2, second);
        Tasks d = new Tasks(3, second);
        Tasks e = new Tasks(4, second);

        a.EditIsFinished(true);
        b.EditIsFinished(true);
        c.EditIsFinished(false);
        d.EditIsFinished(false);
        e.EditIsFinished(false);

        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(a);
        tasksList.add(b);
        tasksList.add(c);
        tasksList.add(d);
        tasksList.add(e);
        Db database = new Db("ZTWmtHyGR1SxFnttlRNBUkii09C3", tasksList);

        database.removeTask(2);
        database.removeTask(3);
        database.removeTask(4);
        assertEquals(0, database.getUnfinishedTask());
    }
    @Test
    public void editTask_isCorrect() throws ParseException {
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
        database.EditTask(3, new Tasks(3, b));
        database.EditTask(6, new Tasks(6, d));
        database.EditTask(9, new Tasks(9, e));

        assertEquals(3, database.getTaskListByTime(a).size());
        assertEquals(3, database.getTaskListByTime(b).size());
        assertEquals(0, database.getTaskListByTime(c).size());
        assertEquals(3, database.getTaskListByTime(d).size());
        assertEquals(1, database.getTaskListByTime(e).size());
    }
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

        assertEquals(4, database.getTaskListByTime(a).size());
        assertEquals(2, database.getTaskListByTime(b).size());
        assertEquals(1, database.getTaskListByTime(c).size());
        assertEquals(3, database.getTaskListByTime(d).size());
        assertEquals(0, database.getTaskListByTime(e).size());
    }
    @Test
    public void getUnfinishedTask_isCorrect() throws ParseException {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date one = simFormat.parse("2008.10.01 22:45:56");
        Date two = simFormat.parse("2009.01.23 22:45:56");

        Timestamp first = new Timestamp(one);
        Timestamp second = new Timestamp(two);

        Tasks a = new Tasks(0, first);
        Tasks b = new Tasks(1, first);
        Tasks c = new Tasks(2, second);
        Tasks d = new Tasks(3, second);
        Tasks e = new Tasks(4, second);

        a.EditIsFinished(true);
        b.EditIsFinished(true);
        c.EditIsFinished(false);
        d.EditIsFinished(false);
        e.EditIsFinished(false);

        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(a);
        tasksList.add(b);
        tasksList.add(c);
        tasksList.add(d);
        tasksList.add(e);
        Db database = new Db("ZTWmtHyGR1SxFnttlRNBUkii09C3", tasksList);

        assertEquals(3, database.getUnfinishedTask());
    }
}
