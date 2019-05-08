package application;

import application.configuration.DataSourseConfigurator;
import application.dao.ProgrammerDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DataSourseConfigurator.class);

        ProgrammerDaoImpl programmerDao = context.getBean(ProgrammerDaoImpl.class);
        programmerDao.getAll().forEach(System.out::println);

    }
}
