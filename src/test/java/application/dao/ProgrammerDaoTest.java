package application.dao;

import application.configuration.DataSourseConfigurator;
import application.entities.Programmer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourseConfigurator.class)
public class ProgrammerDaoTest {

    @Autowired
    private ProgrammerDao programmerDao;
    private Programmer local;
    private int size;

    @Before
    public void setUp() {
        local = new Programmer("TestName", "TestSurname");
        programmerDao.save(local);
        size = programmerDao.getAll().size();
    }

    @After
    public void finalizing() {
        programmerDao.delete(local.getSurName());
    }

    @Test
    public void getAll() {
        programmerDao.save(local);
        assertEquals(programmerDao.getAll().size(), size + 1);
    }

    @Test
    public void get() {
        Programmer programmers = programmerDao.get("Test");
        assertNotNull(programmers);
    }

    @Test
    public void delete() {
        programmerDao.delete(local.getSurName());
        assertEquals(programmerDao.getAll().size(), size - 1);
    }

    @Test
    public void save() {
        programmerDao.save(local);
        assertEquals(programmerDao.getAll().size(), size + 1);
    }

    @Test
    public void update() {
        Programmer programmer = programmerDao.get("Test");
        String previousName = programmer.getName();
        programmer.setName("Temp");
        programmerDao.update(programmer);
        assertEquals(programmer, programmerDao.get("Test"));
        programmer.setName(previousName);
        programmerDao.update(programmer);
        assertEquals(programmer, programmerDao.get("Test"));
    }
}