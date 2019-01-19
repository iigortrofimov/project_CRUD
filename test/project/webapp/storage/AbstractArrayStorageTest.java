package project.webapp.storage;

import project.webapp.exception.StorageException;
import project.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static project.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("TestFullName" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Unexpected StorageException");
        }
        storage.save(new Resume("OverFlow"));
    }
}