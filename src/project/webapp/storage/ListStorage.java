package project.webapp.storage;

import project.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> resumeList = new ArrayList<>();

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        resumeList.remove(searchKey.intValue());
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        resumeList.set(searchKey, resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return resumeList.get(searchKey);
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(resumeList);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
