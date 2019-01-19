package project.webapp.storage;

import project.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    public void insertResume(Resume resume, int index) {
        int sortedIndex = -index - 1;
        System.arraycopy(storage, sortedIndex, storage, sortedIndex + 1, size - sortedIndex);
        storage[sortedIndex] = resume;
    }

    @Override
    public void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
