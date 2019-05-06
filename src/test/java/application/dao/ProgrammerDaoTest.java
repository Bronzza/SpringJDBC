package application.dao;

import application.Starter;
import application.configuration.SpringHibernateSessionConfig;
import application.entities.Programmer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringHibernateSessionConfig.class)
public class ProgrammerDaoTest {
    @Autowired
    private ProgrammerDao programmerDao;
    private Programmer local;
    private int size;

    @Before
    public void setUp() {
        local = new Programmer("TestName", "TestSurname", new ArrayList<>());
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
        List<Programmer> programmers = programmerDao.get(local.getSurName());
        assertTrue(programmers.size() > 0);
    }

    @Test
    public void delete() {
        programmerDao.delete(local.getSurName());
        assertEquals(programmerDao.getAll().size(), size - 1);
    }

    @Test
    public void update() {
        List<Programmer> allLocals = programmerDao.get(local.getSurName());
        allLocals.forEach(a -> {
            a.setName("newTestName");
            programmerDao.update(a);
        });
        allLocals = programmerDao.get(local.getSurName());
        assertEquals("newTestName", allLocals.get(0).getName());
    }

    @Test
    public void save() {
        programmerDao.save(local);
        assertEquals(programmerDao.getAll().size(), size + 1);
    }
}