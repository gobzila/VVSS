package agenda.controller;

import agenda.model.base.Activity;
import agenda.repository.interfaces.RepositoryActivity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ControllerActivity {

    private RepositoryActivity repositoryActivity;

    public ControllerActivity(RepositoryActivity repository) {
        this.repositoryActivity = repository;
    }

    public void addActivity(Activity activity){
        repositoryActivity.addActivity(activity);
    }

    public List<Activity> getAllActivities(){
        return repositoryActivity.getActivities();
    }
}
