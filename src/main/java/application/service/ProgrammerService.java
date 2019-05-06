package application.service;

import application.dao.ProgrammerDao;
import application.entities.Programmer;
import application.entities.ProgrammerForInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammerService {
    @Autowired
    private ProgrammerDao programmerDao;

    public void addToDB(Programmer programmer) {
        programmerDao.save(programmer);
    }

    public void deleteProgrammersBySurname(Programmer programmer) {
        programmerDao.delete(programmer.getSurName());
    }

    public ProgrammerForInside getProgrammer(String surName) {
        Programmer programmer = programmerDao.get(surName);
        ProgrammerForInside result = new ProgrammerForInside();
        resertFieldsFromProgramer(programmer, result);
        return result;
    }

    private void resertFieldsFromProgramer(Programmer programmer, ProgrammerForInside programmerForInside) {
        programmerForInside.setName(programmer.getName());
        programmerForInside.setSurName(programmer.getSurName());
        programmerForInside.setId(programmer.getId());
    }

    public List<ProgrammerForInside> getAllProgrammers() {
        List<ProgrammerForInside> result = new ArrayList<>();
        programmerDao.getAll().forEach(a -> {
            ProgrammerForInside programmerForInside = new ProgrammerForInside();
            resertFieldsFromProgramer(a, programmerForInside);
            result.add(programmerForInside);
        });
        return  result;
    }

    public void updateProgrammer(Programmer programmer) {
        programmerDao.update(programmer);
    }
}
