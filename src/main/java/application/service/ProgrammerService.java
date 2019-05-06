package application.service;

import application.dao.ProgrammerDao;
import application.entities.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammerService {
    @Autowired
    private ProgrammerDao programmerDao;

    public void addToDB(Programmer programmer){
        programmerDao.save(programmer);
    }

    public void deleteProgrammersBySurname(Programmer programmer){
        programmerDao.delete(programmer.getSurName());
    }

    public Optional<Programmer> getProgrammer(String surName){
        return Optional.of(programmerDao.get(surName));
    }

    public Optional<List<Programmer>> getAllProgrammers(){
        return Optional.of(programmerDao.getAll());
    }

    public void updateProgrammer(Programmer programmer){
        programmerDao.update(programmer);
    }
}
