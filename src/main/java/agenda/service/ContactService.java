package agenda.service;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Contact;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.interfaces.RepositoryContact;

public class ContactService {
    private RepositoryContact repositoryContact;

    public ContactService() throws Exception {
        repositoryContact = new RepositoryContactFile();
    }

    public void add(String name, String address, String telefon) {
        repositoryContact.addContact(name, address, telefon);
    }
}
