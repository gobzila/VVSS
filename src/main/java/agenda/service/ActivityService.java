package agenda.service;

import agenda.model.base.Activity;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;

import java.util.Date;
import java.util.List;

public class ActivityService {
    private RepositoryActivity repositoryActivity;
    private RepositoryContact repositoryContact;

    public ActivityService() throws Exception {
        repositoryContact = new RepositoryContactFile();
        repositoryActivity = new RepositoryActivityFile(repositoryContact);
    }

    public boolean add(Activity activity) {
        return repositoryActivity.addActivity(activity);
    }

    public List<Activity> getAllByDate(String userName, Date date) {
        return repositoryActivity.activitiesOnDate(userName, date);
    }
}
