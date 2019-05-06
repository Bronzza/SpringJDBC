package application.service;

import application.dao.ProgrammerDao;
import application.entities.Programmer;
import application.service.pojo.ProgrammerInsideApplication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class ProgrammerService implements ServiceForEntities<Programmer, ProgrammerInsideApplication>, ApplicationContextAware {
    @Autowired
    private ProgrammerDao programmerDao;
    private ApplicationContext applicationContext;

    @Override
    public List<? extends Programmer> getAll() {
        List<Programmer> all = programmerDao.getAll();
        ArrayList<ProgrammerInsideApplication> result = new ArrayList<>();
        resetToProgrammerApplication(all, result);
        return result;
    }

    private void resetToProgrammerApplication(List<Programmer> programmers,
                                              List<ProgrammerInsideApplication> insideProgrammers) {
        programmers.forEach(a -> {
            ProgrammerInsideApplication programmerInsideApplication =
                    applicationContext.getBean(ProgrammerInsideApplication.class);
            programmerInsideApplication.setName(a.getName());
            programmerInsideApplication.setSurName(a.getSurName());
            programmerInsideApplication.setTask(a.getTask());
            programmerInsideApplication.setId(a.getId());
            insideProgrammers.add(programmerInsideApplication);
        });
    }

    @Override
    public List<? extends Programmer> get(Programmer object) {
        List<Programmer> all = programmerDao.get(object.getSurName());
        ArrayList<ProgrammerInsideApplication> result = new ArrayList<>();
        resetToProgrammerApplication(all, result);
        return result;
    }

    @Override
    public void delete(ProgrammerInsideApplication object) {
        programmerDao.delete(object.getSurName());
    }


    @Override
    public void save(ProgrammerInsideApplication object) {
        programmerDao.save((Programmer) object);
    }

    @Override
    public void update(ProgrammerInsideApplication object) {
        programmerDao.update((Programmer) object);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
