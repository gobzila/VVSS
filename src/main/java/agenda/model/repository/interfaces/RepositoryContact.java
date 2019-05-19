package agenda.model.repository.interfaces;

import java.util.List;

import agenda.model.base.Contact;

public interface RepositoryContact {

	List<Contact> getContacts();
	void addContact(String name, String address, String telefon);
	boolean removeContact(Contact contact);
	boolean saveContracts();
	int count();
	Contact getByName(String string);
}
