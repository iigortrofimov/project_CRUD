package project.webapp.storage;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import project.webapp.exception.ExistStorageException;
import project.webapp.exception.NotExistStorageException;
import project.webapp.model.*;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("C:\\projects\\storage");

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String DUMMY = "dummyUuid";

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;
    private static final Resume DUMMY_RESUME = new Resume(DUMMY, "dummyName");

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.TELEPHON, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE,
                new ExperienceSection(
                        new Experience("Organization11", "http://Organization11.ru",
                                new Experience.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Experience.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new ExperienceSection(
                        new Experience("Institute", null,
                                new Experience.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Experience.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Experience("Organization12", "http://Organization12.ru")));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.TELEPHON, "22222");
        R2.addSection(SectionType.EXPERIENCE,
                new ExperienceSection(
                        new Experience("Organization2", "http://Organization2.ru",
                                new Experience.Position(2015, Month.JANUARY, "position1", "content1"))));
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertEquals(4, storage.size());
        assertSame(R4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R2);
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
        assertSame(R2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> testList = storage.getAllSorted();
        List<Resume> expected = Arrays.asList(R1, R2, R3);
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