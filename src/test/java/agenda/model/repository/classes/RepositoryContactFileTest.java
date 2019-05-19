package agenda.model.repository.classes;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Contact;
import agenda.model.repository.interfaces.RepositoryContact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryContactFileTest {
    private Contact contact;
    private Contact contact2;
    private RepositoryContact repositoryContact;

    @Before
    public void setUp() throws Exception {
        repositoryContact = new RepositoryContactFile();
    }

    @Test
    public void testConstructor() {
        System.out.println("-------Start testConstructor-------");
        try {
            contact2 = new Contact("Popescu", "adresa1", "+8729829238");
        } catch(InvalidFormatException e) {
            assertTrue(false);
        }

        assertEquals("Popescu", contact2.getName());
        assertEquals("+8729829238", contact2.getTelefon());
    }

    @Test
    public void testAddContact1() {
        System.out.println("-------Start testAddContact1-------");
        try {
            repositoryContact.addContact("Popescu", "adresa1", "+8729829238");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(true);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testAddContact2() {
        System.out.println("-------Start testAddContact2-------");
        try {
            repositoryContact.addContact("576", "adresa1", "+8729829238");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(false);
                    break;
                }
        } catch (Exception e) {
            assertTrue(true);
        }


    }

    @Test
    public void testAddContact3() {
        System.out.println("-------Start testAddContact3-------");
        try {
            repositoryContact.addContact("Popescu", "adresa1", "snjnsdjdn");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(false);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testAddContact4() {
        System.out.println("-------Start testAddContact4-------");
        try {
            repositoryContact.addContact("Popa", "adresa1", "+1234567890");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(true);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testAddContact5() {
        System.out.println("-------Start testAddContact5-------");
        try {
            repositoryContact.addContact("", "adresa1", "+8729829238");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(false);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testAddContact6() {
        System.out.println("-------Start testAddContact6-------");
        try {
            repositoryContact.addContact("?", "address", "+1234567890");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(false);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    public void testAddContact7() {
        System.out.println("-------Start testAddContact7-------");
        try {
            repositoryContact.addContact("M", "address", "+1234567890");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(true);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testAddContact8() {
        System.out.println("-------Start testAddContact8-------");
        try {
            repositoryContact.addContact("Ionescu", "address", "+0987654321");
            for(Contact c : repositoryContact.getContacts())
                if (c.equals(contact))
                {
                    System.out.println("S-a adaugat cu succes");
                    assertTrue(true);
                    break;
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }

    @After
    public void tearDown() {
        contact = null;
        System.out.println("in tearDown");
    }
}