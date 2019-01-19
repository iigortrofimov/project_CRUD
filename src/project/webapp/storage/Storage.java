package project.webapp.storage;

import project.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */

public interface Storage {

    void save(Resume resume);

    void delete(String uuid);

    void update(Resume resume);

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void clear();

    int size();
}
