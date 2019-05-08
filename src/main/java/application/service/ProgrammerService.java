package application.service;

import application.dao.ProgrammerDaoImpl;
import application.entities.Programmer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
@Getter
@Setter
public class ProgrammerService {
    @Autowired
    private ProgrammerDaoImpl programmerDao;

    public void addToDB(Programmer programmer) {
        programmerDao.save(programmer);
    }

    public void deleteProgrammersBySurname(Programmer programmer) {
        programmerDao.delete(programmer.getSurName());
    }

    public Optional<Programmer> getProgrammer(String surName) {
        return programmerDao.get(surName);
    }


    public List<Programmer> getAllProgrammers() {
        return programmerDao.getAll();
    }

    public void updateProgrammer(Programmer programmer) {
        programmerDao.update(programmer);
    }
}
