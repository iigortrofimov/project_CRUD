package project.webapp.storage;

import project.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Resume searchKeyResume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume resume) {

        mapStorage.remove(resume.getUuid());
    }

    @Override
    protected void doUpdate(Resume resume, Resume searchKeyResume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
