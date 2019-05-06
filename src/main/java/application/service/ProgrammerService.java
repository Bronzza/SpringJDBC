package application.service;

import application.dao.ProgrammerDao;
import application.entities.Programmer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Getter
@Setter
public class ProgrammerService {
    @Autowired
    private ProgrammerDao programmerDao;

    public void addToDB(Programmer programmer) {
        programmerDao.save(programmer);
    }

    public void deleteProgrammersBySurname(Programmer programmer) {
        programmerDao.delete(programmer.getSurName());
    }

    public Programmer getProgrammer(String surName) {
        return programmerDao.get(surName);
    }


    public List<Programmer> getAllProgrammers() {
        return programmerDao.getAll();
    }

    public void updateProgrammer(Programmer programmer) {
        programmerDao.update(programmer);
    }
}
