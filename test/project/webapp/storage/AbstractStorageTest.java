package project.webapp.storage;

import project.webapp.exception.ExistStorageException;
import project.webapp.exception.NotExistStorageException;
import project.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String DUMMY = "dummyUuid";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Name1");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Name2");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Name3");
    private static final Resume RESUME_4 = new Resume(UUID_4, "Name4");
    private static final Resume DUMMY_RESUME = new Resume(DUMMY, "dummyName");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertSame(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(DUMMY);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2, "newName");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_2));
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(DUMMY_RESUME);
    }

    @Test
    public void get() throws Exception {
        assertSame(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> testList = storage.getAllSorted();
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expected, testList);
        assertEquals(expected.size(), testList.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}