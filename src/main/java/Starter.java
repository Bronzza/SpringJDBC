import configuration.SpringHibernateSessionConfig;
import dao.ProgrammerDao;
import entities.Programmer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ProgramerJsonConverter;

import java.util.ArrayList;

public class Starter {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringHibernateSessionConfig.class);
        ProgrammerDao programmerDao = context.getBean(ProgrammerDao.class);
       programmerDao.getAll().forEach(System.out::println);
        System.out.println();
        programmerDao.save(new Programmer("Alloha", "Man", new ArrayList<>()));
        ProgramerJsonConverter programerJsonConverter = context.getBean(ProgramerJsonConverter.class);
        System.out.println(programerJsonConverter.toJsonProgrammer(programmerDao.get("Pupkin").get(0)));
        System.out.println(programerJsonConverter.toJsonProgramersList(programmerDao.getAll()));
    }
}
