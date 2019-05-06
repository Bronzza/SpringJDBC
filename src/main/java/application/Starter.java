package application;

import application.configuration.DataSourseConfigurator;
import application.dao.ProgrammerDao;
import application.entities.Programmer;
import application.service.ProgrammerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DataSourseConfigurator.class);

        ProgrammerDao programmerDao = context.getBean(ProgrammerDao.class);
        programmerDao.getAll().forEach(System.out::println);

    }
}
