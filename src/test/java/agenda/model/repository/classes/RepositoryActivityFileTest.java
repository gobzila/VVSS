package agenda.model.repository.classes;

import agenda.model.base.Activity;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class RepositoryActivityFileTest {
    private RepositoryActivity repositoryActivity;
    private RepositoryActivity repositoryActivity2;
    @Before
    public void setUp() throws Exception {
        RepositoryContact repositoryContact = new RepositoryContactFile();
        repositoryActivity = new RepositoryActivityFile(repositoryContact);

        RepositoryContact repositoryContact2 = new RepositoryContactFile();
        repositoryActivity2 = new RepositoryActivityFile(repositoryContact2);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String string1 = "29/02/2019", string2 = "27/03/2019", string3= "29/05/2019", string4 = "27/06/2019";

        Date date1 = format.parse(string1), date2 = format.parse(string2), date3 = format.parse(string3), date4 = format.parse(string4);

        Activity activity1 = new Activity("name1", date1, date2, null, "description");
        Activity activity2 = new Activity("name2", date3, date4, null, "description");

        repositoryActivity.addActivity(activity1);
        repositoryActivity.addActivity(activity2);
    }

    @Test
    public void test1() {
        System.out.println("-------Start test1-------");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "29/03/2019", string2 = "25/04/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);

            repositoryActivity.addActivity(new Activity("activity", date1, date2, null, "desc"));
            assertEquals(3, repositoryActivity.count());
        } catch (ParseException exception) {
//            System.out.println(exception.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void test2() {
        System.out.println("-------Start test2-------");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "30/02/2019", string2 = "23/03/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);

            repositoryActivity.addActivity(new Activity("activity", date1, date2, null, "desc"));
            assertEquals(2, repositoryActivity.count());
        } catch (ParseException exception) {
//            System.out.println(exception.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void test3() {
        System.out.println("-------Start test3-------");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "30/02/2019", string2 = "23/03/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);

            repositoryActivity2.addActivity(new Activity("activity", date1, date2, null, "desc"));
            assertEquals(1, repositoryActivity2.count());
        } catch (ParseException exception) {
//            System.out.println(exception.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void test4() {
        System.out.println("-------Start test4-------");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "30/02/2019", string2 = "25/01/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);

            repositoryActivity2.addActivity(new Activity("activity", date1, date2, null, "desc"));
            assertEquals(0, repositoryActivity2.count());
        } catch (ParseException exception) {
//            System.out.println(exception.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void test1F03Valid () {
        System.out.println("-------Start test1F03Valid-------");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "29/02/2019";
            Date date1 = format.parse(string1);
            List<Activity> list = repositoryActivity.activitiesOnDate("name1", date1);
            assertTrue(list.size() == 1);
        } catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void test2F03NonValid() {
        System.out.println("-------Start test2F03NonValid-------");
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "29/02/2019";
            Date date1 = format.parse(string1);
            List<Activity> list = repositoryActivity.activitiesOnDate("", date1);
            assertTrue(list.size() == 0);
        } catch(Exception e) {
            assertTrue(true);
        }
    }
}