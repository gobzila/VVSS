package biir2052MV;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.classes.RepositoryUserFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.model.repository.interfaces.RepositoryUser;
import agenda.service.Service;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class IntegrareTopDown {
    private Contact contact;
    private RepositoryContact repositoryContact;
    private RepositoryActivity repositoryActivity;
    private Service service;

    @Before
    public void setUp() throws Exception {
        repositoryContact = new RepositoryContactFile();
        repositoryActivity = new RepositoryActivityFile(repositoryContact);
        RepositoryUser repositoryUser = new RepositoryUserFile();
        service = new Service(repositoryUser,repositoryActivity,repositoryContact);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String string1 = "29/02/2019", string2 = "27/03/2019", string3= "29/05/2019", string4 = "27/06/2019";

        Date date1 = format.parse(string1), date2 = format.parse(string2), date3 = format.parse(string3), date4 = format.parse(string4);

        Activity activity1 = new Activity("name1", date1, date2, null, "description");
        Activity activity2 = new Activity("name2", date3, date4, null, "description");

        repositoryActivity.addActivity(activity1);
        repositoryActivity.addActivity(activity2);
    }

    @Test
    public void testUnitarF01() {
        try {
            contact = new Contact("Popescu", "adresa1", "+8729829238");
            repositoryContact.addContact(contact.getName(),contact.getAddress(),contact.getTelefon());
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    assertTrue(true);
                    break;
                }
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testUnitarF02() {
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "29/03/2019";
            String string2 = "25/04/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);

            repositoryActivity.addActivity(new Activity("activity", date1, date2, null, "desc"));
            assertEquals(3, repositoryActivity.count());
        } catch (ParseException exception) {
            assertTrue(false);
        }
    }

    @Test
    public void testUnitarF03 () {
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
    public void testIntegrarePA() {
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "18/04/2019";
            String string2 = "19/04/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);
            assertTrue(repositoryContact.count() == 0);
            service.addContact("name", "adress", "+1234567890");
            assertTrue(repositoryContact.count() == 1);
            contact = new Contact("Ionescu", "adr", "+8729829238");
            repositoryContact.addContact(contact.getName(),contact.getAddress(),contact.getTelefon());
            for (Contact c : repositoryContact.getContacts()) {
                if (c.equals(contact)) {
                    assertTrue(true);
                    break;
                }
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testIntegrarePAB() {
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "18/04/2019";
            String string2 = "19/04/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);
            assertTrue(repositoryContact.count() == 0);
            service.addContact("name", "adress", "+1234567890");
            assertTrue(repositoryContact.count() == 1);
            contact = new Contact("Ionescu", "adr", "+8729829238");
            repositoryContact.addContact(contact.getName(),contact.getAddress(), contact.getTelefon());
            for (Contact c : repositoryContact.getContacts()) {
                if (c.equals(contact)) {
                    assertTrue(true);
                    break;
                }
            }
            service.addActivity(new Activity("activity", date1,date2,repositoryContact.getContacts(),"desc"));
            assertTrue(repositoryActivity.count() == 3);
            repositoryActivity.addActivity(new Activity("a", date1,date2,null, "description"));
            assertTrue(repositoryActivity.count() == 3);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testIntegrarePABC() {
        try {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String string1 = "18/04/2019";
            String string2 = "19/04/2019";
            Date date1 = format.parse(string1), date2 = format.parse(string2);
            assertTrue(repositoryContact.count() == 0);
            service.addContact("name", "adress", "+1234567890");
            assertTrue(repositoryContact.count() == 1);
            contact = new Contact("Ionescu", "adr", "+8729829238");
            repositoryContact.addContact(contact.getName(),contact.getAddress(),contact.getTelefon());
            for (Contact c : repositoryContact.getContacts()) {
                if (c.equals(contact)) {
                    assertTrue(true);
                    break;
                }
            }
            service.addActivity(new Activity("activity", date1,date2,repositoryContact.getContacts(),"desc"));
            assertTrue(repositoryActivity.count() == 3);
            repositoryActivity.addActivity(new Activity("a", date1,date2,null, "description"));
            assertTrue(repositoryActivity.count() == 3);

            List<Activity> list1 = service.getAllByDate("a", date1);
            assertTrue(list1.size() == 0);
            List<Activity> list2 = service.getAllByDate("activity", date1);
            assertTrue(list2.size() == 1);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}