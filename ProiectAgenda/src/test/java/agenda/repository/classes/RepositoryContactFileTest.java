package agenda.repository.classes;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Contact;
import agenda.repository.interfaces.RepositoryContact;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositoryContactFileTest {
    RepositoryContact repositoryContact;

    @Before
    public void setUp() throws Exception {
        repositoryContact = new RepositoryContactFile();
    }

    @Test
    public void test1addContact() {
        try {
            int n = repositoryContact.count();
            repositoryContact.addContact(new Contact("Dorel", "adresa1", "0700000000"));
            assertTrue(repositoryContact.count() == n+1);
        } catch (InvalidFormatException e) {
            fail();
        }
    }

    @Test
    public void test2addContact() {
        try {
            repositoryContact.addContact(new Contact("Dorel", "adresa1", "#0700000000"));
            fail();
        } catch (InvalidFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void test3addContact() {
        try {
            repositoryContact.addContact(new Contact("$Do", "adresa1", "0700000000"));
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test4addContact() {
        try {
            repositoryContact.addContact(new Contact("Do", "adresa1", "0700000000"));
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test5addContact() {
        try {
            repositoryContact.addContact(new Contact("Do", "adresa1", "00700000000"));
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test6addContact() {
        try {
            repositoryContact.addContact(new Contact("Mircea", "adresa1", "700000000"));
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void test7addContact() {
        try {
            int n = repositoryContact.count();
            repositoryContact.addContact(new Contact("Mircea", "adresa1", "0741561817"));
            assertTrue(repositoryContact.count() == n+1);
        } catch (Exception e) {
            fail();
        }
    }

}