package agenda.service;

import agenda.exceptions.InvalidFormatException;
import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.classes.RepositoryUserFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.model.repository.interfaces.RepositoryUser;

import java.util.Date;
import java.util.List;

public class Service {
    private RepositoryActivity repositoryActivity;
    private RepositoryUser repositoryUser;
    private RepositoryContact repositoryContact;

    public Service() throws Exception {
        this.repositoryContact = new RepositoryContactFile();
        this.repositoryActivity = new RepositoryActivityFile(repositoryContact);
        this.repositoryUser = new RepositoryUserFile();
    }

    public Service(RepositoryUser repositoryUser, RepositoryActivity repositoryActivity, RepositoryContact repositoryContact) {
        this.repositoryUser = repositoryUser;
        this.repositoryContact = repositoryContact;
        this.repositoryActivity = repositoryActivity;
    }

    public boolean addActivity(Activity activity) {
        return repositoryActivity.addActivity(activity);
    }

    public List<Activity> getAllByDate(String userName, Date date) {
        return repositoryActivity.activitiesOnDate(userName, date);
    }

    public void addContact(String name, String address, String telefon) {
        try{
            Contact c = new Contact(name, address, telefon);
            repositoryContact.addContact(name, address, telefon);
        } catch (InvalidFormatException e) {
            if (e.getCause() != null) {
                System.out.printf("Eroare: %s - %s\n" + e.getMessage(), e
                        .getCause().getMessage());
            }
            else
                System.out.printf("Eroare: %s\n" + e.getMessage());
        }
    }

    public boolean isUserValid(String username, String password) {
        User user = repositoryUser.getByUsername(username);

        return user.isPassword(password);
    }

    public User getUserByUsername(String username) {
        return repositoryUser.getByUsername(username);
    }
}
