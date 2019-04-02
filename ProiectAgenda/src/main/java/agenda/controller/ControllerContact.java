package agenda.controller;

import agenda.repository.interfaces.RepositoryContact;

public class ControllerContact {
    RepositoryContact repositoryContact;
    ControllerContact(RepositoryContact repositoryContact) {
        this.repositoryContact = repositoryContact;
    }
}
