package agenda.service;

import agenda.model.base.User;
import agenda.model.repository.classes.RepositoryUserFile;
import agenda.model.repository.interfaces.RepositoryUser;

public class UserService {
    private RepositoryUser repositoryUser;

    public UserService() throws Exception {
        repositoryUser = new RepositoryUserFile();
    }

    public boolean isUserValid(String username, String password) {
        User user = repositoryUser.getByUsername(username);

        return user.isPassword(password);
    }

    public User getUserByUsername(String username) {
        return repositoryUser.getByUsername(username);
    }
}
