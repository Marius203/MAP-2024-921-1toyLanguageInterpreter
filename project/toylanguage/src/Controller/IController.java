package Controller;

import Repository.Repository;

public interface IController {
    void executeAll();
    Repository getRepo();
}
